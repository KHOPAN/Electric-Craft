package com.khopan.minecraft.mod.electriccraft.integration.create.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ElectricAlloySmelterScene {
	public static void initialize(SceneBuilder Builder, SceneBuildingUtil Util) {
		BlockPos ElectricAlloySmelter = Util.grid.at(2, 1, 2);
		BlockPos EnergySourceBlock = Util.grid.at(2, 1, 1);
		BlockPos KineticSourceBlock = Util.grid.at(2, 1, 3);

		Builder.title("electric_alloy_smelter", "Electric Alloy Smelter");
		Builder.configureBasePlate(0, 0, 5);
		Builder.showBasePlate();
		Builder.idle(10);
		Builder.world.showSection(Util.select.position(ElectricAlloySmelter), Direction.DOWN);
		Builder.idle(10);
		Builder.overlay.showText(70).attachKeyFrame().text("Electric Alloy Smelter is a machine used to combine different materials.").pointAt(Util.vector.blockSurface(ElectricAlloySmelter, Direction.WEST)).placeNearTarget();
		Builder.idle(80);
		Builder.overlay.showText(70).attachKeyFrame().text("It uses electrical energy or a rotational energy.").pointAt(Util.vector.blockSurface(ElectricAlloySmelter, Direction.WEST)).placeNearTarget();
		Builder.idle(80);
		Builder.world.showSection(Util.select.position(EnergySourceBlock), Direction.SOUTH);
		Builder.idle(10);
		Builder.overlay.showText(70).attachKeyFrame().text("By placing an energy source block near it, It will now recives the energy and start operating.").pointAt(Util.vector.blockSurface(EnergySourceBlock, Direction.WEST)).placeNearTarget();
		Builder.idle(80);
		Builder.world.hideSection(Util.select.position(EnergySourceBlock), Direction.UP);
		Builder.idle(20);
		Builder.rotateCameraY(-90);
		Builder.idle(25);
		Builder.overlay.showControls(new InputWindowElement(Util.vector.blockSurface(ElectricAlloySmelter, Direction.SOUTH), Pointing.LEFT).rightClick().withItem(AllBlocks.SHAFT.asStack()), 10);
		Builder.idle(20);
		Builder.overlay.showText(90).attachKeyFrame().text("By right clicking on any side but not the front with the shaft, It will now recives the kinetic energy and start operating.").pointAt(Util.vector.blockSurface(KineticSourceBlock, Direction.UP)).placeNearTarget();
		Builder.idle(100);
		Builder.world.showSection(Util.select.fromTo(Util.grid.at(2, 1, 5), Util.grid.at(1, 0, 5)), Direction.DOWN);
		Builder.idle(5);
		Builder.world.showSection(Util.select.position(Util.grid.at(2, 1, 4)), Direction.DOWN);
		Builder.idle(5);
		Builder.world.showSection(Util.select.position(KineticSourceBlock), Direction.DOWN);
		Builder.idle(10);
		Builder.world.setKineticSpeed(Util.select.position(Util.grid.at(1, 0, 5)), -8);
		Builder.world.setKineticSpeed(Util.select.fromTo(KineticSourceBlock, Util.grid.at(2, 1, 5)), 16);
		Builder.idle(20);
		Builder.rotateCameraY(90);
	}
}
