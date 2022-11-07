package com.khopan.minecraft.mod.electriccraft.block.cable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public abstract class AbstractCableBlock extends Block {
	public static final BooleanProperty NORTH = Properties.NORTH;
	public static final BooleanProperty EAST  = Properties.EAST;
	public static final BooleanProperty SOUTH = Properties.SOUTH;
	public static final BooleanProperty WEST  = Properties.WEST;
	public static final BooleanProperty UP    = Properties.UP;
	public static final BooleanProperty DOWN  = Properties.DOWN;
	public static final BooleanProperty[] PROPERTIES = {AbstractCableBlock.NORTH, AbstractCableBlock.EAST, AbstractCableBlock.SOUTH, AbstractCableBlock.WEST, AbstractCableBlock.UP, AbstractCableBlock.DOWN};

	public static final VoxelShape CORE          = Block.createCuboidShape(6,  6,  6,  10, 10, 10);
	public static final VoxelShape CONNECT_NORTH = Block.createCuboidShape(7,  7,  0,  9,  9,  6);
	public static final VoxelShape CONNECT_EAST  = Block.createCuboidShape(10, 7,  7,  16, 9,  9);
	public static final VoxelShape CONNECT_SOUTH = Block.createCuboidShape(7,  7,  10, 9,  9,  16);
	public static final VoxelShape CONNECT_WEST  = Block.createCuboidShape(0,  7,  7,  6,  9,  9);
	public static final VoxelShape CONNECT_UP    = Block.createCuboidShape(7,  10, 7,  9,  16, 9);
	public static final VoxelShape CONNECT_DOWN  = Block.createCuboidShape(7,  0,  7,  9,  6,  9);
	public static final VoxelShape[] SHAPES = {AbstractCableBlock.CONNECT_NORTH, AbstractCableBlock.CONNECT_EAST, AbstractCableBlock.CONNECT_SOUTH, AbstractCableBlock.CONNECT_WEST, AbstractCableBlock.CONNECT_UP, AbstractCableBlock.CONNECT_DOWN};

	public AbstractCableBlock(Settings Settings) {
		super(Settings);

		BlockState Default = this.stateManager.getDefaultState();

		Default = Default.with(AbstractCableBlock.NORTH, false);
		Default = Default.with(AbstractCableBlock.EAST, false);
		Default = Default.with(AbstractCableBlock.SOUTH, false);
		Default = Default.with(AbstractCableBlock.WEST, false);
		Default = Default.with(AbstractCableBlock.UP, false);
		Default = Default.with(AbstractCableBlock.DOWN, false);

		this.setDefaultState(Default);
	}

	@Override
	public BlockRenderType getRenderType(BlockState State) {
		return BlockRenderType.MODEL;
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> Builder) {
		Builder.add(AbstractCableBlock.PROPERTIES);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState State, BlockView World, BlockPos Position, ShapeContext Context) {
		VoxelShape Shape = AbstractCableBlock.CORE;

		for(int i = 0; i < 6; i++) {
			if(State.get(AbstractCableBlock.PROPERTIES[i])) {
				Shape = VoxelShapes.combine(Shape, AbstractCableBlock.SHAPES[i], BooleanBiFunction.OR);
			}
		}

		return Shape;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState State, Direction Direction, BlockState NeighborState, WorldAccess World, BlockPos Position, BlockPos NeighborPos) {
		return this.updateBlockProperty(World, State, Position);
	}

	@Override
	public void onBlockAdded(BlockState State, World World, BlockPos Position, BlockState OldState, boolean Notify) {
		BlockState NewState = this.updateBlockProperty(World, State, Position);
		World.setBlockState(Position, NewState);
	}

	public BlockState updateBlockProperty(WorldAccess World, BlockState State, BlockPos Position) {
		BlockPos[] Positions = {
				Position.add(0, 0, -1),
				Position.add(1, 0, 0),
				Position.add(0, 0, 1),
				Position.add(-1, 0, 0),
				Position.add(0, 1, 0),
				Position.add(0, -1, 0)
		};

		BlockState North    = World.getBlockState(Positions[0]);
		BlockState East     = World.getBlockState(Positions[1]);
		BlockState South    = World.getBlockState(Positions[2]);
		BlockState West     = World.getBlockState(Positions[3]);
		BlockState Up       = World.getBlockState(Positions[4]);
		BlockState Down     = World.getBlockState(Positions[5]);
		BlockState[] Blocks = {North, East, South, West, Up, Down};

		for(int i = 0; i < 6; i++) {
			if(this.canConnectBlock(World, Blocks[i], Positions[i])) {
				State = State.with(AbstractCableBlock.PROPERTIES[i], true);
			} else {
				State = State.with(AbstractCableBlock.PROPERTIES[i], false);
			}
		}

		return State;
	}

	public abstract boolean canConnectBlock(WorldAccess World, BlockState State, BlockPos Position);
}
