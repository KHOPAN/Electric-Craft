package com.khopan.minecraft.mod.electriccraft.block.cable.energy;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.block.cable.AbstractCableBlock;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.CableTier;
import com.khopan.minecraft.mod.electriccraft.energy.ac.ElectrocutedDamage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public abstract class BaseEnergyCableBlock extends AbstractCableBlock implements BlockEntityProvider {
	public final CableTier Tier;

	public BaseEnergyCableBlock(Settings Settings, CableTier Tier) {
		super(Settings);
		this.Tier = Tier;
	}

	@Override
	public boolean canConnectBlock(WorldAccess World, BlockState State, BlockPos Position) {
		Block Block = State.getBlock();

		if(Block instanceof BaseEnergyCableBlock EnergyCable) {
			ElectricCraft.LOGGER.info(State.hasBlockEntity() + "");
			return EnergyCable.Tier.equals(this.Tier);
		}

		return false;
	}

	@Override
	public void onEntityLand(BlockView World, Entity Entity) {
		Entity.damage(new ElectrocutedDamage(), Float.MAX_VALUE);
	}

	@Override
	public void onBreak(World World, BlockPos Position, BlockState State, PlayerEntity Player) {
		Player.damage(new ElectrocutedDamage(), Float.MAX_VALUE);
	}
}
