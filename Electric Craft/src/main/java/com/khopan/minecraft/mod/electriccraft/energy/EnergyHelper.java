package com.khopan.minecraft.mod.electriccraft.energy;

import com.khopan.math.MathUtils;

import net.minecraft.item.ItemStack;

public class EnergyHelper {
	private EnergyHelper() {

	}

	/*public static String formatEnergy(TransferableEnergyItem Energy) {
		return EnergyHelper.formatEnergy(Energy.getEnergy(), Energy.getMaxEnergy());
	}*/

	public static String formatEnergy(ItemStack Energy) {
		TransferHelper.check(Energy);
		return EnergyHelper.formatEnergy(TransferHelper.getEnergy(Energy), TransferHelper.getTransferable(Energy).getMaxEnergy(Energy));
	}

	public static String formatEnergy(int Energy, int MaxEnergy) {
		return String.format("Energy: %d/%d [%d%%]", Energy, MaxEnergy, (int) Math.round(MathUtils.map(Energy, 0, MaxEnergy, 0, 100)));
	}
}
