package com.khopan.minecraft.mod.electriccraft.block.steam.water_boiler;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WaterBoilerRegistry {
	public static final String WATER_BOILER_ID = "water_boiler";
	public static final Identifier WATER_BOILER_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, WaterBoilerRegistry.WATER_BOILER_ID);
	public static final BlockEntry<WaterBoilerBlock> WATER_BOILER_BLOCK = ElectricCraft.REGISTRATE.block(WaterBoilerRegistry.WATER_BOILER_ID, WaterBoilerBlock :: new).item().tab(() -> ItemGroup.MISC).build().register();
	public static final BlockEntityType<WaterBoilerBlockEntity> WATER_BOILER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, WaterBoilerRegistry.WATER_BOILER_IDENTIFIER, FabricBlockEntityTypeBuilder.create(WaterBoilerBlockEntity :: new, WaterBoilerRegistry.WATER_BOILER_BLOCK.get()).build(null));

	public static void registerWaterBoiler() {
		ElectricCraft.LOGGER.info("Initializing Water Boiler");
	}
}
