package com.khopan.minecraft.mod.electriccraft.builder;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Builder {
	public static <T> T build(Registry<? super T> registry, String Name, T Entry) {
		return Registry.register(registry, new Identifier(ElectricCraft.MOD_ID, Name), Entry);
	}
}
