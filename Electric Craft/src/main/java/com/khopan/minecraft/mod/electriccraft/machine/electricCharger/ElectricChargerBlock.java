package com.khopan.minecraft.mod.electriccraft.machine.electricCharger;

import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterBlockEntity;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterRegistry;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElectricChargerBlock extends BlockWithEntity implements BlockEntityProvider {
	public ElectricChargerBlock(Settings Settings) {
		super(Settings);
	}

	@Override
	public BlockRenderType getRenderType(BlockState State) {
		return BlockRenderType.MODEL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStateReplaced(BlockState State, World World, BlockPos Position, BlockState NewState, boolean Moved) {
		if(State.getBlock() != NewState.getBlock()) {
			BlockEntity BlockEntity = World.getBlockEntity(Position);

			if(BlockEntity instanceof ElectricAlloySmelterBlockEntity ElectricAlloySmelter) {
				ItemScatterer.spawn(World, Position, ElectricAlloySmelter);
				World.updateComparators(Position, this);
			}

			super.onStateReplaced(State, World, Position, NewState, Moved);
		}
	}

	@Override
	public ActionResult onUse(BlockState State, World World, BlockPos Position, PlayerEntity Player, Hand Hand, BlockHitResult Result) {
		if(!World.isClient) {
			NamedScreenHandlerFactory Factory = State.createScreenHandlerFactory(World, Position);

			if(Factory != null) {
				Player.openHandledScreen(Factory);
			}
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos Position, BlockState State) {
		return null;
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World World, BlockState State, BlockEntityType<T> Type) {
		return BlockWithEntity.checkType(Type, ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_BLOCK_ENTITY, ElectricAlloySmelterBlockEntity :: tick);
	}
}
