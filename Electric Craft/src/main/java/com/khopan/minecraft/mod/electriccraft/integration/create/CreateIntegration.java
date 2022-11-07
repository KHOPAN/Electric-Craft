package com.khopan.minecraft.mod.electriccraft.integration.create;

import java.lang.reflect.Field;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.block.steam.water_boiler.WaterBoilerRegistry;
import com.khopan.minecraft.mod.electriccraft.integration.create.ponder.ElectricAlloySmelterScene;
import com.khopan.minecraft.mod.electriccraft.integration.create.ponder.WaterBoilerScene;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterRegistry;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;

public class CreateIntegration {
	public static void initialize() {
		ElectricCraft.LOGGER.info("Registering ponder scene reflectively.");

		try {
			Field Helper = Class.forName("com.simibubi.create.foundation.ponder.content.PonderIndex").getDeclaredField("HELPER");
			Helper.setAccessible(true);
			CreateIntegration.initializePonderScene((PonderRegistrationHelper) Helper.get(null));
		} catch(Throwable Errors) {
			Errors.printStackTrace();
		}
	}

	public static void initializeClient() {
		PonderTag Tag = new PonderTag(Create.asResource("electric_craft")).item(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_BLOCK.asStack().getItem());

		PonderRegistry.TAGS.forTag(Tag).add(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_BLOCK);
	}

	private static void initializePonderScene(PonderRegistrationHelper Helper) {
		Helper.forComponents(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_BLOCK).addStoryBoard("electric_alloy_smelter", ElectricAlloySmelterScene :: initialize);
		Helper.forComponents(WaterBoilerRegistry.WATER_BOILER_BLOCK).addStoryBoard("water_boiler", WaterBoilerScene :: initialize);
	}
}
