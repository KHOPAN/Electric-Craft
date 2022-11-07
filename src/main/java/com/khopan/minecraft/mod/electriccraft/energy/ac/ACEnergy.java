package com.khopan.minecraft.mod.electriccraft.energy.ac;

public class ACEnergy {
	public double Voltage;
	public double Current;
	public ACWave Wave;

	public ACEnergy(double Voltage, double Current, ACWave Wave) {
		this.Voltage = Voltage;
		this.Current = Current;
		this.Wave = Wave;
	}

	public double getVoltage() {
		return this.Voltage;
	}

	public double getCurrent() {
		return this.Current;
	}

	public ACWave getWave() {
		return this.Wave;
	}

	public void setVoltage(double Voltage) {
		this.Voltage = ACWave.NONE.equals(this.Wave) ? 0.0d : Voltage;
	}

	public void setCurrent(double Current) {
		this.Current = ACWave.NONE.equals(this.Wave) ? 0.0d : Current;
	}

	public void setWave(ACWave Wave) {
		this.Wave = Wave;

		if(ACWave.NONE.equals(this.Wave)) {
			this.Voltage = 0.0d;
			this.Current = 0.0d;
		}
	}
}
