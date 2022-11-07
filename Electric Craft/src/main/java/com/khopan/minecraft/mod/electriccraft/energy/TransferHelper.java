package com.khopan.minecraft.mod.electriccraft.energy;

import com.khopan.minecraft.mod.electriccraft.exception.ElectricCraftException;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class TransferHelper {
	public static NbtCompound checkAndGetNBT(ItemStack Item) {
		TransferHelper.check(Item);
		NbtCompound NBT = Item.getOrCreateNbt();

		if(NBT.contains(TransferableEnergyItem.NBT_KEY)) {
			NBT.putInt(TransferableEnergyItem.NBT_KEY, 0);
		}

		return NBT;
	}

	public static TransferableEnergyItem checkAndGetTransferable(ItemStack Item) {
		TransferHelper.check(Item);
		return TransferHelper.getTransferable(Item);
	}

	public static TransferableEnergyItem getTransferable(ItemStack Item) {
		return (TransferableEnergyItem) Item.getItem();
	}

	public static void check(ItemStack Item) {
		if(!(Item.getItem() instanceof TransferableEnergyItem)) {
			throw new ElectricCraftException("Item stack must be instance of TransferableEnergyItem interface.");
		}
	}

	public static int getEnergy(ItemStack Item) {
		return TransferHelper.checkAndGetNBT(Item).getInt(TransferableEnergyItem.NBT_KEY);
	}

	public static int pullEnergy(ItemStack Item) {
		TransferableEnergyItem Transferable = TransferHelper.checkAndGetTransferable(Item);
		int PullingEnergy = Math.min(Transferable.getEnergy(Item), Transferable.getEnergyOutputPerTick(Item));

		if(Transferable.canOutput(Item) && Transferable.removeEnergy(PullingEnergy, Item)) {
			return PullingEnergy;
		}

		return 0;
	}
}
