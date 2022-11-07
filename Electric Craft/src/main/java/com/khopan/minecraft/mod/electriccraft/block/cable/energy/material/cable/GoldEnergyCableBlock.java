package com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.cable;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.BaseEnergyCableBlock;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.CableTier;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity.GoldEnergyCableBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class GoldEnergyCableBlock extends BaseEnergyCableBlock {
	public GoldEnergyCableBlock(Settings Settings) {
		super(Settings, CableTier.GOLD);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos Position, BlockState State) {
		return new GoldEnergyCableBlockEntity(Position, State);
	}
}
