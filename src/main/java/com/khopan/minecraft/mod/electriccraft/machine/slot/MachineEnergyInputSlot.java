package com.khopan.minecraft.mod.electriccraft.machine.slot;

import com.khopan.minecraft.mod.electriccraft.energy.TransferableEnergyItem;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MachineEnergyInputSlot extends Slot {
	public MachineEnergyInputSlot(Inventory Inventory, int Index, int x, int y) {
		super(Inventory, Index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack Stack) {
		return Stack.getItem() instanceof TransferableEnergyItem;
	}
}
