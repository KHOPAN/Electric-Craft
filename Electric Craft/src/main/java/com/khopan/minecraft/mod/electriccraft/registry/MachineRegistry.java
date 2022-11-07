package com.khopan.minecraft.mod.electriccraft.registry;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.block.steam.SteamRegistry;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterRegistry;
import com.khopan.minecraft.mod.electriccraft.machine.electricFurnace.ElectricFurnaceRegistry;

public class MachineRegistry {
	public static void registerMachine() {
		ElectricCraft.LOGGER.info("Initializing Machines");

		ElectricFurnaceRegistry.registerElectricFurnace();
		ElectricAlloySmelterRegistry.registerElectricAlloySmelter();
		SteamRegistry.registerSteamStuff();
	}
}
