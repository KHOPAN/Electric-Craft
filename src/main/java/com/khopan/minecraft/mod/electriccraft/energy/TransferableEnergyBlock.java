package com.khopan.minecraft.mod.electriccraft.energy;

public interface TransferableEnergyBlock extends TransferableEnergy {
	public int getEnergy();
	public int getMaxEnergy();
	public int getEPInputPerTick();
	public int getEPOutputPerTick();
	public boolean canInput();
	public boolean canOutput();
	public boolean addEnergy(int Amout);
	public boolean removeEnergy(int Amout);
	public boolean setEnergy(int Energy);
	public boolean isEnergyEmpty();
	public boolean isEnergyFull();
	public TransferDirection getTransferDirection();
}
