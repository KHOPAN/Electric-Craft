package com.khopan.minecraft.mod.electriccraft.energy;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public interface TransferableEnergyItem extends TransferableEnergy {
	public static final String NBT_KEY = "Energy";

	/*public default void getEnergy(ItemStack Item) {}
	public default void getMaxEnergy(ItemStack Item) {}
	public default void getEPInputPerTick(ItemStack Item) {}
	public default void getEPOutputPerTick(ItemStack Item) {}
	public default void canInput(ItemStack Item) {}
	public default void canOutput(ItemStack Item) {}
	public default void addEnergy(int Amout, ItemStack Item) {}
	public default void removeEnergy(int Amout, ItemStack Item) {}
	public default void setEnergy(int Energy, ItemStack Item) {}
	public default void isEnergyEmpty(ItemStack Item) {}
	public default void isEnergyFull(ItemStack Item) {}*/

	public default int getEnergy(ItemStack Item) {
		return TransferHelper.getEnergy(Item);
	}

	public int getMaxEnergy(ItemStack Item);
	public int getEnergyInputPerTick(ItemStack Item);
	public int getEnergyOutputPerTick(ItemStack Item);

	public default boolean canInput(ItemStack Item) {
		return TransferHelper.getEnergy(Item) < TransferHelper.getTransferable(Item).getMaxEnergy(Item);
	}

	public default boolean canOutput(ItemStack Item) {
		return TransferHelper.getEnergy(Item) > 0;
	}

	public default boolean canAddEnergy(int Amout, ItemStack Item) {
		return TransferHelper.getEnergy(Item) + Amout <= TransferHelper.getTransferable(Item).getMaxEnergy(Item);
	}

	public default boolean canRemoveEnergy(int Amout, ItemStack Item) {
		return TransferHelper.getEnergy(Item) - Amout >= 0;
	}

	public default boolean addEnergy(int Amout, ItemStack Item) {
		NbtCompound NBT = TransferHelper.checkAndGetNBT(Item);

		if(this.canAddEnergy(Amout, Item)) {
			int Energy = NBT.getInt(TransferableEnergyItem.NBT_KEY);
			NBT.remove(TransferableEnergyItem.NBT_KEY);
			NBT.putInt(TransferableEnergyItem.NBT_KEY, Energy + Amout);
			return true;
		} else {
			return false;
		}
	}

	public default boolean removeEnergy(int Amout, ItemStack Item) {
		NbtCompound NBT = TransferHelper.checkAndGetNBT(Item);

		if(this.canRemoveEnergy(Amout, Item)) {
			int Energy = NBT.getInt(TransferableEnergyItem.NBT_KEY);
			NBT.remove(TransferableEnergyItem.NBT_KEY);
			NBT.putInt(TransferableEnergyItem.NBT_KEY, Energy - Amout);
			return true;
		} else {
			return false;
		}
	}

	public default boolean setEnergy(int Energy, ItemStack Item) {
		return Energy >= 0 && Energy <= TransferHelper.getEnergy(Item);
	}

	public default boolean isEnergyEmpty(ItemStack Item) {
		return TransferHelper.getEnergy(Item) <= 0;
	}

	public default boolean isEnergyFull(ItemStack Item) {
		return TransferHelper.getEnergy(Item) >= TransferHelper.getTransferable(Item).getMaxEnergy(Item);
	}
}
