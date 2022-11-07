package com.khopan.minecraft.mod.electriccraft.energy.ac;

import java.util.ArrayList;
import java.util.List;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.BaseEnergyCableBlock;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ACUtils {
	private static final int[][] CHECKING_POSITION = {
			{1,  0,  0},
			{-1, 0,  0},
			{0,  1,  0},
			{0, -1,  0},
			{0,  0,  1},
			{0,  0, -1}
	};

	public static void transferACEnergyToAdjacentCables(IACEnergy EnergyBlock, World World, BlockState State, BlockPos Position) {
		ACEnergy Energy = EnergyBlock.getEnergy();

		for(int i = 0; i < 6; i++) {
			BlockPos CheckingPosition = Position.add(ACUtils.CHECKING_POSITION[i][0], ACUtils.CHECKING_POSITION[i][1], ACUtils.CHECKING_POSITION[i][2]);
			BlockState CheckingState = World.getBlockState(CheckingPosition);

			if(CheckingState.getBlock() instanceof BaseEnergyCableBlock) {

			}
		}
	}

	public static void trySendEnergy(World World, BlockPos CablePosition, ACEnergy Energy) {

	}

	private static List<IACEnergy> runCablePathFinder(World World, BlockPos CablePosition) {
		return ACUtils.subPathFinder(World, CablePosition, CablePosition);
	}

	private static List<IACEnergy> subPathFinder(World World, BlockPos CablePosition, BlockPos From) {
		List<IACEnergy> Result = new ArrayList<>();
		List<BaseEnergyCableBlock> Cables = new ArrayList<>();
		List<BlockPos> CablePositions = new ArrayList<>();

		for(int i = 0; i < 6; i++) {
			BlockPos CheckingPosition = CablePosition.add(ACUtils.CHECKING_POSITION[i][0], ACUtils.CHECKING_POSITION[i][1], ACUtils.CHECKING_POSITION[i][2]);

			if(!ACUtils.isSamePosition(From, CheckingPosition)) {
				BlockState CheckingState = World.getBlockState(CheckingPosition);

				if(CheckingState.getBlock() instanceof BaseEnergyCableBlock Cable) {
					Cables.add(Cable);
				} else if(CheckingState.getBlock() instanceof IACEnergy Energy) {
					Result.add(Energy);
					CablePositions.add(CheckingPosition);
				}
			}
		}

		int size = Cables.size();
		int Index;

		if(size > 1) {
			Index = ElectricCraft.RANDOM.nextInt(0, size);
		} else {
			Index = 0;
		}

		Result.addAll(ACUtils.subPathFinder(World, CablePositions.get(Index), CablePosition));

		return Result;
	}

	private static boolean isSamePosition(BlockPos xPosition, BlockPos yPosition) {
		int xx = xPosition.getX();
		int yx = xPosition.getY();
		int zx = xPosition.getZ();

		int xy = yPosition.getX();
		int yy = yPosition.getY();
		int zy = yPosition.getZ();

		return (xx == xy) && (yx == yy) && (zx == zy);
	}
}
