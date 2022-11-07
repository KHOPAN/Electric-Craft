package com.khopan.minecraft.mod.electriccraft;

import com.khopan.minecraft.mod.electriccraft.block.cable.energy.EnergyCableRegistry;
import com.khopan.minecraft.mod.electriccraft.client.NBTTagViewer;
import com.khopan.minecraft.mod.electriccraft.integration.IntegrationInitializer;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterRegistry;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterScreen;
import com.khopan.minecraft.mod.electriccraft.machine.electricFurnace.ElectricFurnaceRegistry;
import com.khopan.minecraft.mod.electriccraft.machine.electricFurnace.ElectricFurnaceScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class ElectricCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ElectricCraft.LOGGER.info("Initializing Electric Craft Client");

		IntegrationInitializer.preInitializeClient();
		NBTTagViewer.register();

		BlockRenderLayerMap.INSTANCE.putBlock(EnergyCableRegistry.COPPER_ENERGY_CABLE_BLOCK.get(), RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(EnergyCableRegistry.SILVER_ENERGY_CABLE_BLOCK.get(), RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(EnergyCableRegistry.GOLD_ENERGY_CABLE_BLOCK.get(), RenderLayer.getCutout());

		ScreenRegistry.register(ElectricFurnaceRegistry.ELECTRIC_FURNACE_SCREEN_HANDLER, ElectricFurnaceScreen :: new);
		ScreenRegistry.register(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_SCREEN_HANDLER, ElectricAlloySmelterScreen :: new);
		IntegrationInitializer.initializeClient();
	}
}
