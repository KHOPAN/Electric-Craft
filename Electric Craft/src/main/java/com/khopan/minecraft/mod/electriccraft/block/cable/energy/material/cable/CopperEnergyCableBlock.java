package com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.cable;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.BaseEnergyCableBlock;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.CableTier;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity.CopperEnergyCableBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CopperEnergyCableBlock extends BaseEnergyCableBlock {
	public CopperEnergyCableBlock(Settings Settings) {
		super(Settings, CableTier.COPPER);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos Position, BlockState State) {
		return new CopperEnergyCableBlockEntity(Position, State);
	}
}
