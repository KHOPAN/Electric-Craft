package com.khopan.minecraft.mod.electriccraft.exception;

public class ElectricCraftException extends RuntimeException {
	private static final long serialVersionUID = 4164829866381192481L;

	public ElectricCraftException() {
		super();
	}

	public ElectricCraftException(String Message) {
		super(Message);
	}

	public ElectricCraftException(String Message, Throwable Cause) {
		super(Message, Cause);
	}

	public ElectricCraftException(Throwable Cause) {
		super(Cause);
	}

	public ElectricCraftException(String Message, Throwable Cause, boolean EnableSuppression, boolean WritableStackTrace) {
		super(Message, Cause, EnableSuppression, WritableStackTrace);
	}
}
