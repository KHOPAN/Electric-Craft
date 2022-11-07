package com.khopan.minecraft.mod.electriccraft.machine.electricFurnace;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ElectricFurnaceScreen extends HandledScreen<ElectricFurnaceScreenHandler> {
	public static final Identifier TEXTURE = new Identifier(ElectricCraft.MOD_ID, "textures/gui/electric_furnace.png");

	public ElectricFurnaceScreen(ElectricFurnaceScreenHandler Handler, PlayerInventory Inventory, Text Title) {
		super(Handler, Inventory, Title);
	}

	@Override
	public void init() {
		super.init();
		this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
	}

	@Override
	public void drawBackground(MatrixStack Matrix, float Delta, int MouseX, int MouseY) {
		RenderSystem.setShader(GameRenderer :: getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, ElectricFurnaceScreen.TEXTURE);

		int x = (this.width - this.backgroundWidth) / 2;
		int y = (this.height - this.backgroundHeight) / 2;

		this.drawTexture(Matrix, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

		if(this.handler.isCrafting()) {
			this.drawTexture(Matrix, x + 80, y + 34, 176, 0, this.handler.getScaledProgress(), 16);
		}

		int Energy = this.handler.getScaledEnergy();
		this.drawTexture(Matrix, x + 9, y + 50 - Energy, 176, 82 - Energy, 14, Energy);
	}

	@Override
	public void render(MatrixStack Matrix, int MouseX, int MouseY, float Delta) {
		this.renderBackground(Matrix);
		super.render(Matrix, MouseX, MouseY, Delta);
		this.drawMouseoverTooltip(Matrix, MouseX, MouseY);

		int x = (this.width - this.backgroundWidth) / 2;
		int y = (this.height - this.backgroundHeight) / 2;

		if(
				(MouseX - x) >= 10 && (MouseX - x) <= 23 &&
				(MouseY - y) >= 21 && (MouseY - y) <= 62
				) {
			int Energy = this.handler.PropertyDelegate.get(2);
			int MaxEnergy = this.handler.PropertyDelegate.get(3);
			this.renderTooltip(Matrix, new LiteralText("Energy: " + Energy + "/" + MaxEnergy + " EP [" + ((int) Math.round((((double) Energy) / ((double) MaxEnergy)) * ((double) 100))) + "%]"), MouseX, MouseY);
		}
	}
}
