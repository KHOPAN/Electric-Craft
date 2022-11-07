package com.khopan.minecraft.mod.electriccraft.energy;

import com.khopan.minecraft.mod.electriccraft.energy.ac.IACEnergy;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class EnergyTickerRegistry {
	public static void registerACEnergy(Class<? extends IACEnergy> Energy) {
		ServerTickEvents.END_SERVER_TICK.register(EnergyTickerRegistry :: tick);
	}

	private static void tick(MinecraftServer Server) {

	}
}
