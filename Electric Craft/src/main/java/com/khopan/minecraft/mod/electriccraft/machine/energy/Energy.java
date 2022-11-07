package com.khopan.minecraft.mod.electriccraft.machine.energy;

public abstract class Energy {
	public final double Voltage;
	public final double Current;
	public final double MaxVoltage;
	public final double MaxCurrent;

	public Energy(double Voltage, double Current, double MaxVoltage, double MaxCurrent) {
		this.Voltage = Voltage;
		this.Current = Current;
		this.MaxVoltage = MaxVoltage;
		this.MaxCurrent = MaxCurrent;
	}
}
