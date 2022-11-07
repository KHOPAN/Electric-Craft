package com.khopan.minecraft.mod.electriccraft.block.cable.energy.material;

import net.minecraft.util.Formatting;

public enum CableTier {
	COPPER(1, Formatting.GREEN),
	SILVER(2, Formatting.YELLOW),
	GOLD(3, Formatting.RED);

	public final int Level;
	public final Formatting Color;

	private CableTier(int Level, Formatting Color) {
		this.Level = Level;
		this.Color = Color;
	}
}
