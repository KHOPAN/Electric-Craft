package com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.BaseEnergyCableBlockEntity;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.EnergyCableRegistry;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.CableTier;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class CopperEnergyCableBlockEntity extends BaseEnergyCableBlockEntity {
	public CopperEnergyCableBlockEntity(BlockPos Position, BlockState State) {
		super(Position, State, CableTier.COPPER, EnergyCableRegistry.COPPER_ENERGY_CABLE_BLOCK_ENTITY);
	}
}
