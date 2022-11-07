package com.khopan.minecraft.mod.electriccraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;

import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public abstract class ElectricCraftMixin {
	@Inject(at=@At("HEAD"), method="init()V")
	private void init(CallbackInfo Info) {
		ElectricCraft.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
