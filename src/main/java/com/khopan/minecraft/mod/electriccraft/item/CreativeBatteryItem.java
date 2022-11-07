package com.khopan.minecraft.mod.electriccraft.item;

import com.khopan.minecraft.mod.electriccraft.energy.TransferableEnergyItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeBatteryItem extends Item implements TransferableEnergyItem {
	public CreativeBatteryItem() {
		super(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
	}

	@Override
	public boolean hasGlint(ItemStack Item) {
		return true;
	}

	@Override
	public int getEnergy(ItemStack Item) {
		return this.getMaxEnergy(Item);
	}

	@Override
	public int getMaxEnergy(ItemStack Item) {
		return Integer.MAX_VALUE;
	}

	@Override
	public int getEnergyInputPerTick(ItemStack Item) {
		return this.getMaxEnergy(Item);
	}

	@Override
	public int getEnergyOutputPerTick(ItemStack Item) {
		return this.getMaxEnergy(Item);
	}

	@Override
	public boolean canInput(ItemStack Item) {
		return false;
	}

	@Override
	public boolean canOutput(ItemStack Item) {
		return true;
	}

	@Override
	public boolean canAddEnergy(int Amout, ItemStack Item) {
		return false;
	}

	@Override
	public boolean canRemoveEnergy(int Amout, ItemStack Item) {
		return true;
	}

	@Override
	public boolean addEnergy(int Amout, ItemStack Item) {
		return false;
	}

	@Override
	public boolean removeEnergy(int Amout, ItemStack Item) {
		return true;
	}

	@Override
	public boolean setEnergy(int Energy, ItemStack Item) {
		return false;
	}

	@Override
	public boolean isEnergyEmpty(ItemStack Item) {
		return false;
	}

	@Override
	public boolean isEnergyFull(ItemStack Item) {
		return true;
	}

	/*@Override
	public int getEnergy() {
		return this.getMaxEnergy();
	}

	@Override
	public int getMaxEnergy() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int getEPInputPerTick() {
		return 0;
	}

	@Override
	public int getEPOutputPerTick() {
		return this.getMaxEnergy();
	}

	@Override
	public boolean canInput() {
		return false;
	}

	@Override
	public boolean canOutput() {
		return true;
	}

	@Override
	public boolean addEnergy(int Amout) {
		return false;
	}

	@Override
	public boolean removeEnergy(int Amout) {
		return true;
	}

	@Override
	public boolean setEnergy(int Energy) {
		return false;
	}

	@Override
	public boolean isEnergyEmpty() {
		return false;
	}

	@Override
	public boolean isEnergyFull() {
		return true;
	}*/
}
