package com.khopan.minecraft.mod.electriccraft.block.steam.water_boiler;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterBoilerBlockEntity extends BlockEntity {
	public WaterBoilerBlockEntity(BlockPos Position, BlockState State) {
		super(WaterBoilerRegistry.WATER_BOILER_BLOCK_ENTITY, Position, State);
	}

	public static void tick(World World, BlockPos Position, BlockState State, ElectricAlloySmelterBlockEntity BlockEntity) {
		BlockPos BottomPosition = Position.add(0, -1, 0);
		BlockState BottomState = World.getBlockState(BottomPosition);

		if(BottomState.getBlock() instanceof BoilerHeatSource Heat) {
			double HeatLevel = Heat.getHeatLevel(World, BottomPosition, BottomState).Temperature;

			ElectricCraft.LOGGER.info(String.format("Temperature: %d Celsius", HeatLevel));
		}
	}
}
