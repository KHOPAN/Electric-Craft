package com.khopan.minecraft.mod.electriccraft.energy;

import java.util.Objects;

import net.minecraft.util.math.Direction;

public class TransferDirection {
	public static final TransferDirection ALL = new TransferDirection(Direction.UP, Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH);
	public static final TransferDirection SIDE = new TransferDirection(Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH);

	private final Direction[] Directions;

	public TransferDirection(Direction... Directions) {
		this.Directions = Objects.requireNonNull(Directions);
	}

	public Direction[] getDirections() {
		return this.Directions;
	}
}
