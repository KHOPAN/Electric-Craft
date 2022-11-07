package com.khopan.minecraft.mod.electriccraft.machine.electricFurnace;

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
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElectricFurnaceBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, TransferableEnergyBlock {
	public static final int MAX_ENERGY = 1000000;
	public static final int ENERGY_TRANSFER_PER_TICK = 2000;
	public static final int ENERGY_USED_PER_TICK = 2;

	public final DefaultedList<ItemStack> Inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
	public final PropertyDelegate PropertyDelegate;
	public int Progress;
	public int MaxProgress;
	public int Energy;
	public int MaxEnergy;

	public ElectricFurnaceBlockEntity(BlockPos Position, BlockState State) {
		super(ElectricFurnaceRegistry.ELECTRIC_FURNACE_BLOCK_ENTITY, Position, State);
		this.MaxEnergy = 1000000;
		this.PropertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int Index) {
				switch(Index) {
				case 0:
					return ElectricFurnaceBlockEntity.this.Progress;
				case 1:
					return ElectricFurnaceBlockEntity.this.MaxProgress;
				case 2:
					return ElectricFurnaceBlockEntity.this.Energy;
				case 3:
					return ElectricFurnaceBlockEntity.this.MaxEnergy;
				default:
					return -1;
				}
			}

			@Override
			public void set(int Index, int Value) {
				switch(Index) {
				case 0:
					ElectricFurnaceBlockEntity.this.Progress = Value;
					break;
				case 1:
					ElectricFurnaceBlockEntity.this.MaxProgress = Value;
					break;
				case 2:
					ElectricFurnaceBlockEntity.this.Energy = Value;
					break;
				case 3:
					ElectricFurnaceBlockEntity.this.MaxEnergy = Value;
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
		return new LiteralText("Electric Furnace");
	}

	@Override
	public ScreenHandler createMenu(int SyncID, PlayerInventory Inventory, PlayerEntity Entity) {
		return new ElectricFurnaceScreenHandler(SyncID, Inventory, this, this.PropertyDelegate);
	}

	@Override
	protected void writeNbt(NbtCompound NBT) {
		super.writeNbt(NBT);
		Inventories.writeNbt(NBT, this.Inventory);
		NBT.putInt("progress", this.Progress);
		NBT.putInt("maxProgress", this.MaxProgress);
		NBT.putInt("energy", this.Energy);
		NBT.putInt("maxEnergy", this.Energy);
	}

	@Override
	public void readNbt(NbtCompound NBT) {
		Inventories.readNbt(NBT, this.Inventory);
		super.readNbt(NBT);
		this.Progress = NBT.getInt("progress");
		this.MaxProgress = NBT.getInt("maxProgress");
		this.Energy = NBT.getInt("energy");
		this.MaxEnergy = NBT.getInt("maxEnergy");
	}

	public void resetProgress() {
		this.Progress = 0;
	}

	public static void tick(World World, BlockPos Position, BlockState State, ElectricFurnaceBlockEntity BlockEntity) {
		boolean BeforePowered = !(BlockEntity.Energy <= 0);
		boolean BeforeActivated = BlockEntity.Progress > 0;
		boolean MarkDirty = false;

		ItemStack EnergyItem = BlockEntity.getStack(2);

		if(!EnergyItem.isEmpty()) {
			TransferableEnergyItem Item = (TransferableEnergyItem) EnergyItem.getItem();
			int Energy = Math.min(Item.getEnergyOutputPerTick(EnergyItem), BlockEntity.getMaxEnergy() - BlockEntity.Energy);

			if(!Item.isEnergyEmpty(EnergyItem) && Item.removeEnergy(Energy, EnergyItem)) {
				BlockEntity.Energy += Energy;
			}
		}

		if(ElectricFurnaceBlockEntity.hasRecipe(BlockEntity) && ElectricFurnaceBlockEntity.hasNotReachedStackLimit(BlockEntity) && ElectricFurnaceBlockEntity.hasEnoughEnergy(BlockEntity)) {
			BlockEntity.Progress++;
			BlockEntity.Energy -= ElectricFurnaceBlockEntity.ENERGY_USED_PER_TICK;

			if(BlockEntity.Progress > BlockEntity.MaxProgress) {
				ElectricFurnaceBlockEntity.craftItem(BlockEntity);
			}
		} else {
			if(BlockEntity.Progress != 0) {
				BlockEntity.resetProgress();
			}
		}

		boolean Powered = BeforePowered != !(BlockEntity.Energy <= 0);
		boolean Activated = BeforeActivated != BlockEntity.Progress > 0;

		if(Powered || Activated) {
			MarkDirty = true;

			if(Powered) {
				State = State.with(ElectricFurnaceBlock.POWERED, Powered);
			}

			if(Activated) {
				State = State.with(ElectricFurnaceBlock.ACTIVATED, Activated);
			}

			World.setBlockState(Position, State);
		}

		if(MarkDirty) {
			ElectricFurnaceBlockEntity.markDirty(World, Position, State);
		}
	}

	public static boolean hasEnoughEnergy(ElectricFurnaceBlockEntity BlockEntity) {
		return BlockEntity.Energy >= BlockEntity.MaxProgress * ElectricFurnaceBlockEntity.ENERGY_USED_PER_TICK;
	}

	public static void craftItem(ElectricFurnaceBlockEntity BlockEntity) {
		World World = BlockEntity.world;
		SimpleInventory Inventory = new SimpleInventory(BlockEntity.Inventory.size());

		for(int i = 0; i < Inventory.size(); i++) {
			Inventory.setStack(i, BlockEntity.getStack(i));
		}

		Optional<SmeltingRecipe> Match = World.getRecipeManager().getFirstMatch(RecipeType.SMELTING, Inventory, World);

		if(Match.isPresent()) {
			BlockEntity.removeStack(0, 1);
			BlockEntity.setStack(1, new ItemStack(Match.get().getOutput().getItem(), BlockEntity.getStack(1).getCount() + 1));
			BlockEntity.resetProgress();
		}
	}

	public static boolean hasRecipe(ElectricFurnaceBlockEntity BlockEntity) {
		World World = BlockEntity.world;
		SimpleInventory Inventory = new SimpleInventory(BlockEntity.Inventory.size());

		for(int i = 0; i < Inventory.size(); i++) {
			Inventory.setStack(i, BlockEntity.getStack(i));
		}

		//Optional<ElectricFurnaceRecipe> Match = World.getRecipeManager().getFirstMatch(ElectricFurnaceRecipe.Type.INSTANCE, Inventory, World);
		Optional<SmeltingRecipe> Match = World.getRecipeManager().getFirstMatch(RecipeType.SMELTING, Inventory, World);

		if(Match.isPresent()) {
			SmeltingRecipe Recipe = Match.get();
			BlockEntity.MaxProgress = Recipe.getCookTime() / 2;
			return ElectricFurnaceBlockEntity.canInsertItem(Inventory, Recipe.getOutput());
		} else {
			return false;
		}
	}

	public static boolean hasNotReachedStackLimit(ElectricFurnaceBlockEntity BlockEntity) {
		return BlockEntity.getStack(1).getCount() < BlockEntity.getStack(1).getMaxCount();
	}

	public static boolean canInsertAmoutIntoOutputSlot(SimpleInventory Inventory) {
		return Inventory.getStack(1).getMaxCount() > Inventory.getStack(1).getCount();
	}

	public static boolean canInsertItemIntoOutputSlot(SimpleInventory Inventory, ItemStack Output) {
		return Inventory.getStack(1).getItem() == Output.getItem() || Inventory.getStack(3).isEmpty();
	}

	public static boolean canInsertItem(SimpleInventory Inventory, ItemStack Output) {
		return ElectricFurnaceBlockEntity.canInsertAmoutIntoOutputSlot(Inventory) && ElectricFurnaceBlockEntity.canInsertItemIntoOutputSlot(Inventory, Output);
	}

	@Override
	public int getEnergy() {
		return this.Energy;
	}

	@Override
	public int getMaxEnergy() {
		return ElectricFurnaceBlockEntity.MAX_ENERGY;
	}

	@Override
	public boolean setEnergy(int Energy) {
		if(Energy <= ElectricFurnaceBlockEntity.MAX_ENERGY) {
			this.Energy = Energy;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addEnergy(int Energy) {
		if((this.Energy + Energy) <= ElectricFurnaceBlockEntity.MAX_ENERGY) {
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
		return this.Energy == ElectricFurnaceBlockEntity.MAX_ENERGY;
	}

	@Override
	public int getEPInputPerTick() {
		return ElectricFurnaceBlockEntity.ENERGY_TRANSFER_PER_TICK;
	}

	@Override
	public int getEPOutputPerTick() {
		return ElectricFurnaceBlockEntity.ENERGY_TRANSFER_PER_TICK;
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
