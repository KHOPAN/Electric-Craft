package com.khopan.minecraft.mod.electriccraft.machine.electricFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ElectricFurnaceBlock extends BlockWithEntity implements BlockEntityProvider {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty POWERED = BooleanProperty.of("powered");
	public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

	public ElectricFurnaceBlock(Settings Settings) {
		super(Settings);
		BlockState DefaultState = this.stateManager.getDefaultState();
		DefaultState = DefaultState.with(ElectricFurnaceBlock.FACING, Direction.NORTH);
		DefaultState = DefaultState.with(ElectricFurnaceBlock.POWERED, false);
		DefaultState = DefaultState.with(ElectricFurnaceBlock.ACTIVATED, false);
		this.setDefaultState(DefaultState);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext Context) {
		return this.getDefaultState().with(ElectricFurnaceBlock.FACING, Context.getPlayerFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState State, BlockRotation Rotation) {
		return State.with(ElectricFurnaceBlock.FACING, Rotation.rotate(State.get(ElectricFurnaceBlock.FACING)));
	}

	@Override
	public BlockState mirror(BlockState State, BlockMirror Mirror) {
		return State.rotate(Mirror.getRotation(State.get(ElectricFurnaceBlock.FACING)));
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> Builder) {
		Builder.add(ElectricFurnaceBlock.FACING, ElectricFurnaceBlock.POWERED, ElectricFurnaceBlock.ACTIVATED);
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

			if(BlockEntity instanceof ElectricFurnaceBlockEntity ElectricFurnace) {
				ItemScatterer.spawn(World, Position, ElectricFurnace);
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
		return new ElectricFurnaceBlockEntity(Position, State);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World World, BlockState State, BlockEntityType<T> Type) {
		return BlockWithEntity.checkType(Type, ElectricFurnaceRegistry.ELECTRIC_FURNACE_BLOCK_ENTITY, ElectricFurnaceBlockEntity :: tick);
	}
}
