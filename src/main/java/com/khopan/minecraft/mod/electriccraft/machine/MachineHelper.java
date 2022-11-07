package com.khopan.minecraft.mod.electriccraft.machine;

import com.khopan.math.MathUtils;
import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.machine.energy.Energy;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MachineHelper {
	public static final Identifier VOLTAGE_AND_CURRENT_BAR = new Identifier(ElectricCraft.MOD_ID, "textures/gui/voltage_and_current_bar.png");

	public static void drawEnergyBar(Screen Screen, MatrixStack Matrix, int x, int y, Energy Energy) {
		RenderSystem.setShaderTexture(0, MachineHelper.VOLTAGE_AND_CURRENT_BAR);
		Screen.drawTexture(Matrix, x + 9, y + 8, 0, 0, 14, (int) Math.round(MathUtils.map(Energy.Voltage, 0.0d, Energy.MaxVoltage, 42.0d, 0.0d)));
		Screen.drawTexture(Matrix, x + 9, y + (int) Math.round(MathUtils.map(Energy.Voltage, 0.0d, Energy.MaxVoltage, 50.0d, 8.0d)), 28, (int) Math.round(MathUtils.map(Energy.Voltage, 0.0d, Energy.MaxVoltage, 42.0d, 0.0d)), 9, (int) Math.round(MathUtils.map(Energy.Voltage, 0.0d, Energy.MaxVoltage, 42.0d, 0.0d)));
		
		Screen.drawTexture(Matrix, x + 25, y + 8, 14, 0, 14, (int) Math.round(MathUtils.map(Energy.Current, 0.0d, Energy.MaxCurrent, 42.0d, 0.0d)));
		//Screen.drawTexture(Matrix,);
	}
}
