package com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.cable;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.BaseEnergyCableBlock;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.CableTier;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity.SilverEnergyCableBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SilverEnergyCableBlock extends BaseEnergyCableBlock {
	public SilverEnergyCableBlock(Settings Settings) {
		super(Settings, CableTier.SILVER);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos Position, BlockState State) {
		return new SilverEnergyCableBlockEntity(Position, State);
	}
}
