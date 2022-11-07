package com.khopan.minecraft.mod.electriccraft.registry;

import com.google.common.base.Supplier;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Shader;
import net.minecraft.util.Identifier;

public class ScreenBuilder {
	public ScreenBuilder() {

	}

	public void setTexShader(Supplier<Shader> Shader) {
		RenderSystem.setShader(Shader);
	}

	public void setShaderColor(float f, float g, float h, float i) {
		RenderSystem.setShaderColor(f, g, h, i);
	}

	public void setShaderTexture(int i, Identifier Identifier) {
		RenderSystem.setShaderTexture(i, Identifier);
	}

	public void setupShader(Identifier GUILocation) {
		this.setTexShader(GameRenderer :: getPositionTexShader);
		this.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		this.setShaderTexture(0, GUILocation);
	}
}
