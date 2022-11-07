package com.khopan.minecraft.mod.electriccraft.block.steam.water_boiler;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterBoilerBlock extends BlockWithEntity implements BlockEntityProvider {
	public WaterBoilerBlock(Settings Settings) {
		super(Settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos Position, BlockState State) {
		return new WaterBoilerBlockEntity(Position, State);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World World, BlockState State, BlockEntityType<T> Type) {
		return BlockWithEntity.checkType(Type, null, WaterBoilerBlockEntity :: tick);
	}
}
