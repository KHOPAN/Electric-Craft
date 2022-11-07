package com.khopan.minecraft.mod.electriccraft.machine.electricCharger;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ElectricChargerRegistry {
	public static final String ELECTRIC_CHARGER_ID = "electric_charger";
	public static final Identifier ELECTRIC_CHARGER_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, ElectricChargerRegistry.ELECTRIC_CHARGER_ID);
	public static final Block ELECTRIC_CHARGER_BLOCK = Registry.register(Registry.BLOCK, ElectricChargerRegistry.ELECTRIC_CHARGER_IDENTIFIER, new ElectricChargerBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool()));
	public static final Item ELECTRIC_CHARGER_BLOCK_ITEM = Registry.register(Registry.ITEM, ElectricChargerRegistry.ELECTRIC_CHARGER_IDENTIFIER, new BlockItem(ElectricChargerRegistry.ELECTRIC_CHARGER_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
	public static final BlockEntityType<ElectricChargerBlockEntity> ELECTRIC_CHARGER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ElectricChargerRegistry.ELECTRIC_CHARGER_IDENTIFIER, FabricBlockEntityTypeBuilder.create(ElectricChargerBlockEntity :: new, ElectricChargerRegistry.ELECTRIC_CHARGER_BLOCK).build(null));

	public static void registerElectricCharger() {
		ElectricCraft.LOGGER.info("Initializing Electric Charger");
	}
}
