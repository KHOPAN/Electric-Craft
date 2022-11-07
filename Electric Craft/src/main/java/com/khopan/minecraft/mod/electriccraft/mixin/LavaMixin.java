package com.khopan.minecraft.mod.electriccraft.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.khopan.math.MathUtils;
import com.khopan.minecraft.mod.electriccraft.block.steam.water_boiler.BoilerHeatSource;
import com.khopan.minecraft.mod.electriccraft.utils.Celsius;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(LavaFluid.class)
public abstract class LavaMixin implements BoilerHeatSource {
	@Override
	public Celsius getHeatLevel(World World, BlockPos Position, BlockState State) {
		return Celsius.of(MathUtils.map(((LavaFluid) (Object) this).getLevel(World.getFluidState(Position)), 0.0d, 8.0d, 0.0d, 1250.0d));
	}
}
