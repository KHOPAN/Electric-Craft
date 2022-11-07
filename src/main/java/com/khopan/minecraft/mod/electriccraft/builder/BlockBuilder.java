package com.khopan.minecraft.mod.electriccraft.builder;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockBuilder {
	public static Block build(FabricBlockSettings Settings, String Name) {
		return Registry.register(Registry.BLOCK, new Identifier(ElectricCraft.MOD_ID, Name), new Block(Settings));
	}

	public static FabricBlockSettings of(Material Material) {
		return FabricBlockSettings.of(Material);
	}
}
