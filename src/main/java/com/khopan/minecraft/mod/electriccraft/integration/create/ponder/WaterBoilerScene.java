package com.khopan.minecraft.mod.electriccraft.integration.create.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;

public class WaterBoilerScene {
	public static void initialize(SceneBuilder Builder, SceneBuildingUtil Util) {
		Builder.title("water_boiler", "Using the Water Boiler");
		Builder.configureBasePlate(0, 0, 5);
		Builder.showBasePlate();
	}
}
