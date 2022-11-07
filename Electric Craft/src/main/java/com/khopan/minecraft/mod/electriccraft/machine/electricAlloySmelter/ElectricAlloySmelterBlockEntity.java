package com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter;

import java.util.Optional;

import com.khopan.minecraft.mod.electriccraft.energy.TransferDirection;
import com.khopan.minecraft.mod.electriccraft.energy.TransferableEnergyBlock;
import com.khopan.minecraft.mod.electriccraft.energy.TransferableEnergyItem;
import com.khopan.minecraft.mod.electriccraft.machine.ImplementedInventory;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElectricAlloySmelterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, TransferableEnergyBlock {
	public static final int MAX_ENERGY = 1000000;
	public static final int ENERGY_TRANSFER_PER_TICK = 2000;
	public static final int ENERGY_USED_PER_TICK = 2;

	public final DefaultedList<ItemStack> Inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
	public final PropertyDelegate PropertyDelegate;
	public int Progress;
	public int MaxProgress;
	public int Energy;

	public ElectricAlloySmelterBlockEntity(BlockPos Position, BlockState State) {
		super(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_BLOCK_ENTITY, Position, State);
		this.PropertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int Index) {
				switch(Index) {
				case 0:
					return ElectricAlloySmelterBlockEntity.this.Progress;
				case 1:
					return ElectricAlloySmelterBlockEntity.this.MaxProgress;
				case 2:
					return ElectricAlloySmelterBlockEntity.this.Energy;
				case 3:
					return ElectricAlloySmelterBlockEntity.MAX_ENERGY;
				default:
					return -1;
				}
			}

			@Override
			public void set(int Index, int Value) {
				switch(Index) {
				case 0:
					ElectricAlloySmelterBlockEntity.this.Progress = Value;
					break;
				case 1:
					ElectricAlloySmelterBlockEntity.this.MaxProgress = Value;
					break;
				case 2:
					ElectricAlloySmelterBlockEntity.this.Energy = Value;
					break;
				}
			}

			@Override
			public int size() {
				return 4;
			}
		};
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.Inventory;
	}

	@Override
	public Text getDisplayName() {
		return new LiteralText("Electric Alloy Smelter");
	}

	@Override
	public ScreenHandler createMenu(int SyncID, PlayerInventory Inventory, PlayerEntity Entity) {
		return new ElectricAlloySmelterScreenHandler(SyncID, Inventory, this, this.PropertyDelegate);
	}

	@Override
	protected void writeNbt(NbtCompound NBT) {
		super.writeNbt(NBT);
		Inventories.writeNbt(NBT, this.Inventory);
		NBT.putInt("progress", this.Progress);
		NBT.putInt("maxProgress", this.MaxProgress);
		NBT.putInt("energy", this.Energy);
	}

	@Override
	public void readNbt(NbtCompound NBT) {
		Inventories.readNbt(NBT, this.Inventory);
		super.readNbt(NBT);
		this.Progress = NBT.getInt("progress");
		this.MaxProgress = NBT.getInt("maxProgress");
		this.Energy = NBT.getInt("energy");
	}

	public void resetProgress() {
		this.Progress = 0;
	}

	public static void tick(World World, BlockPos Position, BlockState State, ElectricAlloySmelterBlockEntity BlockEntity) {
		if(!World.isClient) {
			boolean BeforePowered = State.get(ElectricAlloySmelterBlock.POWERED);//BlockEntity.Energy > 0;
			boolean BeforeActivated = State.get(ElectricAlloySmelterBlock.ACTIVATED);//BlockEntity.Progress > 0;
			boolean MarkDirty = false;

			ItemStack EnergyItem = BlockEntity.getStack(10);

			if(!EnergyItem.isEmpty()) {
				TransferableEnergyItem Item = (TransferableEnergyItem) EnergyItem.getItem();
				int Energy = Math.min(Item.getEnergyOutputPerTick(EnergyItem), BlockEntity.getMaxEnergy() - BlockEntity.Energy);

				if(!Item.isEnergyEmpty(EnergyItem) && Item.removeEnergy(Energy, EnergyItem)) {
					BlockEntity.Energy += Energy;
				}
			}

			if(ElectricAlloySmelterBlockEntity.hasRecipe(BlockEntity) && ElectricAlloySmelterBlockEntity.hasNotReachedStackLimit(BlockEntity) && ElectricAlloySmelterBlockEntity.hasEnoughEnergy(BlockEntity)) {
				BlockEntity.Progress++;
				BlockEntity.Energy -= ElectricAlloySmelterBlockEntity.ENERGY_USED_PER_TICK;

				if(BlockEntity.Progress > BlockEntity.MaxProgress) {
					ElectricAlloySmelterBlockEntity.craftItem(BlockEntity);
				}
			} else {
				if(BlockEntity.Progress != 0) {
					BlockEntity.resetProgress();
				}
			}

			boolean Powered = BeforePowered != BlockEntity.Energy > 0;
			boolean Activated = BeforeActivated != BlockEntity.Progress > 0;

			if(Powered || Activated) {
				MarkDirty = true;

				if(Powered) {
					State = State.with(ElectricAlloySmelterBlock.POWERED, BlockEntity.Energy > 0);
				}

				if(Activated) {
					State = State.with(ElectricAlloySmelterBlock.ACTIVATED, BlockEntity.Progress > 0);
				}

				World.setBlockState(Position, State);
			}

			if(MarkDirty) {
				ElectricAlloySmelterBlockEntity.markDirty(World, Position, State);
			}
		}
	}

	public static boolean hasEnoughEnergy(ElectricAlloySmelterBlockEntity BlockEntity) {
		return BlockEntity.Energy >= BlockEntity.MaxProgress * ElectricAlloySmelterBlockEntity.ENERGY_USED_PER_TICK;
	}

	public static void craftItem(ElectricAlloySmelterBlockEntity BlockEntity) {
		World World = BlockEntity.world;
		SimpleInventory Inventory = new SimpleInventory(BlockEntity.Inventory.size());

		for(int i = 0; i < Inventory.size(); i++) {
			Inventory.setStack(i, BlockEntity.getStack(i));
		}

		Optional<ElectricAlloySmelterRecipe> Match = World.getRecipeManager().getFirstMatch(ElectricAlloySmelterRecipe.Type.INSTANCE, Inventory, World);

		if(Match.isPresent()) {
			ElectricAlloySmelterRecipe Recipe = Match.get();
			boolean[] Slots = new boolean[9];

			for(int x = 0; x < Recipe.Input.size(); x++) {
				Ingredient Ingredient = Recipe.Input.get(x);

				for(int y = 0; y < 9; y++) {
					if(!Slots[y]) {
						if(Ingredient.test(Inventory.getStack(y))) {
							Slots[y] = true;
						}
					}
				}
			}

			for(int i = 0; i < Slots.length; i++) {
				if(Slots[i]) {
					BlockEntity.removeStack(i, 1);
				}
			}

			BlockEntity.setStack(9, new ItemStack(Recipe.getOutput().getItem(), BlockEntity.getStack(9).getCount() + Recipe.getOutput().getCount()));
			BlockEntity.resetProgress();
		}
	}

	public static boolean hasRecipe(ElectricAlloySmelterBlockEntity BlockEntity) {
		World World = BlockEntity.world;
		SimpleInventory Inventory = new SimpleInventory(BlockEntity.Inventory.size());

		for(int i = 0; i < Inventory.size(); i++) {
			Inventory.setStack(i, BlockEntity.getStack(i));
		}

		/*List<ElectricAlloySmelterRecipe> List = World.getRecipeManager().listAllOfType(ElectricAlloySmelterRecipe.Type.INSTANCE);
		System.out.println(List.size());*/

		Optional<ElectricAlloySmelterRecipe> Match = World.getRecipeManager().getFirstMatch(ElectricAlloySmelterRecipe.Type.INSTANCE, Inventory, World);

		if(Match.isPresent()) {
			ElectricAlloySmelterRecipe Recipe = Match.get();
			BlockEntity.MaxProgress = Recipe.getSmeltingTime();
			boolean[] Slots = new boolean[9];

			for(int x = 0; x < Recipe.Input.size(); x++) {
				Ingredient Ingredient = Recipe.Input.get(x);

				for(int y = 0; y < 9; y++) {
					if(!Slots[y]) {
						if(Ingredient.test(Inventory.getStack(y))) {
							Slots[y] = true;
						}
					}
				}
			}

			int Number = 0;

			for(int i = 0; i < Slots.length; i++) {
				if(Slots[i]) {
					Number++;
				}
			}

			return Number == Recipe.Input.size() && ElectricAlloySmelterBlockEntity.canInsertItem(Inventory, Recipe.getOutput(), Recipe.getOutput().getCount());
		} else {
			return false;
		}
	}

	public static boolean hasNotReachedStackLimit(ElectricAlloySmelterBlockEntity BlockEntity) {
		return BlockEntity.getStack(9).getCount() < BlockEntity.getStack(9).getMaxCount();
	}

	public static boolean canInsertAmoutIntoOutputSlot(SimpleInventory Inventory, int Count) {
		return Inventory.getStack(9).getCount() + Count <= Inventory.getStack(9).getMaxCount();
	}

	public static boolean canInsertItemIntoOutputSlot(SimpleInventory Inventory, ItemStack Output) {
		return Inventory.getStack(9).getItem() == Output.getItem() || Inventory.getStack(9).isEmpty();
	}

	public static boolean canInsertItem(SimpleInventory Inventory, ItemStack Output, int Count) {
		return ElectricAlloySmelterBlockEntity.canInsertAmoutIntoOutputSlot(Inventory, Count) && ElectricAlloySmelterBlockEntity.canInsertItemIntoOutputSlot(Inventory, Output);
	}

	@Override
	public int getEnergy() {
		return this.Energy;
	}

	@Override
	public int getMaxEnergy() {
		return ElectricAlloySmelterBlockEntity.MAX_ENERGY;
	}

	@Override
	public boolean setEnergy(int Energy) {
		if(Energy <= ElectricAlloySmelterBlockEntity.MAX_ENERGY) {
			this.Energy = Energy;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addEnergy(int Energy) {
		if((this.Energy + Energy) <= ElectricAlloySmelterBlockEntity.MAX_ENERGY) {
			this.Energy += Energy;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeEnergy(int Energy) {
		if((this.Energy - Energy) >= 0) {
			this.Energy -= Energy;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isEnergyEmpty() {
		return this.Energy == 0;
	}

	@Override
	public boolean isEnergyFull() {
		return this.Energy == ElectricAlloySmelterBlockEntity.MAX_ENERGY;
	}

	@Override
	public int getEPInputPerTick() {
		return ElectricAlloySmelterBlockEntity.ENERGY_TRANSFER_PER_TICK;
	}

	@Override
	public int getEPOutputPerTick() {
		return ElectricAlloySmelterBlockEntity.ENERGY_TRANSFER_PER_TICK;
	}

	@Override
	public boolean canInput() {
		return true;
	}

	@Override
	public boolean canOutput() {
		return true;
	}

	@Override
	public TransferDirection getTransferDirection() {
		return TransferDirection.ALL;
	}
}
