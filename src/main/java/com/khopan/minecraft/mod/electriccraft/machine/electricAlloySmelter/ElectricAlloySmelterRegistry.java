package com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterRecipe.Serializer;
import com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter.ElectricAlloySmelterRecipe.Type;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("deprecation")
public class ElectricAlloySmelterRegistry {
	public static final String ELECTRIC_ALLOY_SMELTER_ID = "electric_alloy_smelter";
	public static final Identifier ELECTRIC_ALLOY_SMELTER_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_ID);
	public static final BlockEntry<ElectricAlloySmelterBlock> ELECTRIC_ALLOY_SMELTER_BLOCK = ElectricCraft.REGISTRATE.block(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_ID, ElectricAlloySmelterBlock :: new).item().tab(() -> ItemGroup.MISC).build().register();
	public static final BlockEntityType<ElectricAlloySmelterBlockEntity> ELECTRIC_ALLOY_SMELTER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_IDENTIFIER, FabricBlockEntityTypeBuilder.create(ElectricAlloySmelterBlockEntity :: new, ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_BLOCK.get()).build(null));
	public static final ScreenHandlerType<ElectricAlloySmelterScreenHandler> ELECTRIC_ALLOY_SMELTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ElectricAlloySmelterRegistry.ELECTRIC_ALLOY_SMELTER_IDENTIFIER, ElectricAlloySmelterScreenHandler :: new);
	public static final Serializer ELECTRIC_ALLOY_SMELTER_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ElectricCraft.MOD_ID, ElectricAlloySmelterRecipe.Serializer.IDENTIFIER), ElectricAlloySmelterRecipe.Serializer.INSTANCE);
	public static final Type ELECTRIC_ALLOY_SMELTER_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(ElectricCraft.MOD_ID, ElectricAlloySmelterRecipe.Type.IDENTIFIER), ElectricAlloySmelterRecipe.Type.INSTANCE);

	public static void registerElectricAlloySmelter() {
		ElectricCraft.LOGGER.info("Initializing Electric Alloy Smelter");
	}
}
