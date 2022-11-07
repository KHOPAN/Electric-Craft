package com.khopan.minecraft.mod.electriccraft.block.steam.water_boiler;

import com.khopan.minecraft.mod.electriccraft.utils.Celsius;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BoilerHeatSource {
	public Celsius getHeatLevel(World World, BlockPos Position, BlockState State);
}
