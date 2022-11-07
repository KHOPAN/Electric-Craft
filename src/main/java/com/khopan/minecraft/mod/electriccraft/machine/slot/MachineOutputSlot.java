package com.khopan.minecraft.mod.electriccraft.machine.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MachineOutputSlot extends Slot {
	public MachineOutputSlot(Inventory Inventory, int Index, int x, int y) {
		super(Inventory, Index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack Item) {
		return false;
	}
}
