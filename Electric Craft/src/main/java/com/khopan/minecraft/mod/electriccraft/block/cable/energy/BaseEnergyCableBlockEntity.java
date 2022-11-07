package com.khopan.minecraft.mod.electriccraft.block.cable.energy;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.CableTier;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class BaseEnergyCableBlockEntity extends BlockEntity {
	public BaseEnergyCableBlockEntity(BlockPos Position, BlockState State, CableTier Tier, BlockEntityType<?> Type) {
		super(Type, Position, State);
	}
}
