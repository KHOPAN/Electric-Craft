package com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter;

import com.khopan.minecraft.mod.electriccraft.machine.slot.MachineEnergyInputSlot;
import com.khopan.minecraft.mod.electriccraft.machine.slot.MachineOutputSlot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ElectricAlloySmelterScreenHandler extends ScreenHandler {
	public final Inventory Inventory;
	public final PropertyDelegate PropertyDelegate;

	public ElectricAlloySmelterScreenHandler(int SyncID, PlayerInventory Inventory) {
		this(SyncID, Inventory, new SimpleInventory(11), new ArrayPropertyDelegate(4));
	}

	public ElectricAlloySmelterScreenHandler(int SyncID, PlayerInventory PlayerInventory, Inventory Inventory, PropertyDelegate PropertyDelegate) {
		super(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_SCREEN_HANDLER, SyncID);
		ElectricAlloySmelterScreenHandler.checkSize(Inventory, 11);
		this.Inventory = Inventory;
		this.Inventory.onOpen(PlayerInventory.player);
		this.PropertyDelegate = PropertyDelegate;
		this.addElectricAlloySmelterSlot(this.Inventory);
		this.addPlayerInventory(PlayerInventory);
		this.addPlayerHotbar(PlayerInventory);
		this.addProperties(this.PropertyDelegate);
	}

	public boolean isCrafting() {
		return this.PropertyDelegate.get(0) > 0;
	}

	public int getScaledProgress() {
		int Progress = this.PropertyDelegate.get(0);
		int MaxProgress = this.PropertyDelegate.get(1);
		int ProgressArrowSize = 26;

		return Progress != 0 && MaxProgress != 0 ? Progress * ProgressArrowSize / MaxProgress : 0;
	}

	public int getScaledEnergy() {
		int Energy = this.PropertyDelegate.get(2);
		int EnergyHeight = 42;

		return Energy != 0 ? Energy * EnergyHeight / ElectricAlloySmelterBlockEntity.MAX_ENERGY : 0;
	}

	@Override
	public boolean canUse(PlayerEntity Player) {
		return this.Inventory.canPlayerUse(Player);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity Player, int InventorySlot) {
		ItemStack NewStack = ItemStack.EMPTY;
		Slot Slot = this.slots.get(InventorySlot);

		if(Slot != null && Slot.hasStack()) {
			ItemStack OriginalStack = Slot.getStack();
			NewStack = OriginalStack.copy();

			if(InventorySlot < this.Inventory.size()) {
				if(!this.insertItem(OriginalStack, this.Inventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!this.insertItem(OriginalStack, 0, this.Inventory.size(), false)) {
				return ItemStack.EMPTY;
			}

			if(OriginalStack.isEmpty()) {
				Slot.setStack(ItemStack.EMPTY);
			} else {
				Slot.markDirty();
			}
		}

		return NewStack;
	}

	public void addElectricAlloySmelterSlot(Inventory Inventory) {
		for(int y = 0; y < 3; ++y) {
			for(int x = 0; x < 3; ++x) {
				this.addSlot(new Slot(Inventory, x + y * 3, 40 + x * 18, 17 + y * 18));
			}
		}

		this.addSlot(new MachineOutputSlot(Inventory, 9, 136, 35));
		this.addSlot(new MachineEnergyInputSlot(Inventory, 10, 8, 53));
	}

	public void addPlayerInventory(PlayerInventory PlayerInventory) {
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlot(new Slot(PlayerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}
	}

	public void addPlayerHotbar(PlayerInventory PlayerInventory) {
		for(int x = 0; x < 9; x++) {
			this.addSlot(new Slot(PlayerInventory, x, 8 + x * 18, 142));
		}
	}
}
