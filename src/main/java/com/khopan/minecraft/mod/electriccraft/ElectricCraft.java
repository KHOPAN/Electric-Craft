package com.khopan.minecraft.mod.electriccraft;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.EnergyCableRegistry;
import com.khopan.minecraft.mod.electriccraft.integration.IntegrationInitializer;
import com.khopan.minecraft.mod.electriccraft.registry.BlockRegistry;
import com.khopan.minecraft.mod.electriccraft.registry.ItemRegistry;
import com.khopan.minecraft.mod.electriccraft.registry.MachineRegistry;
import com.tterrag.registrate.Registrate;

import io.netty.util.internal.ThreadLocalRandom;
import net.fabricmc.api.ModInitializer;

public class ElectricCraft implements ModInitializer {
	public static final String MOD_NAME = "Electric Craft";
	public static final String MOD_VERSION = "1.0.0";
	public static final String MOD_ID = "electriccraft";
	public static final String MOD_AUTHOR = "KHOPAN";

	public static final Logger LOGGER = LoggerFactory.getLogger(ElectricCraft.MOD_NAME);
	public static final Registrate REGISTRATE = Registrate.create(ElectricCraft.MOD_ID);
	public static final Random RANDOM = ThreadLocalRandom.current();

	@Override
	public void onInitialize() {
		ElectricCraft.LOGGER.info("Initializing Electric Craft");

		IntegrationInitializer.preInitialize();
		ItemRegistry.registerItem();
		BlockRegistry.registerBlock();
		MachineRegistry.registerMachine();
		EnergyCableRegistry.registerEnergyCable();
		IntegrationInitializer.initialize();

		ElectricCraft.REGISTRATE.register();
	}
}
