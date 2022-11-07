package com.khopan.minecraft.mod.electriccraft.machine.electricFurnace;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.machine.electricFurnace.ElectricFurnaceRecipe.Serializer;
import com.khopan.minecraft.mod.electriccraft.machine.electricFurnace.ElectricFurnaceRecipe.Type;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("deprecation")
public class ElectricFurnaceRegistry {
	public static final String ELECTRIC_FURNACE_ID = "electric_furnace";
	public static final Identifier ELECTRIC_FURNACE_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, ElectricFurnaceRegistry.ELECTRIC_FURNACE_ID);
	public static final BlockEntry<Block> ELECTRIC_FURNACE_BLOCK = ElectricCraft.REGISTRATE.block("electric_furnace", Block :: new).item().tab(NonNullSupplier.lazy(() -> ItemGroup.MISC)).lang("Electric Furnace").build().register();
	public static final BlockEntityType<ElectricFurnaceBlockEntity> ELECTRIC_FURNACE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ElectricFurnaceRegistry.ELECTRIC_FURNACE_IDENTIFIER, FabricBlockEntityTypeBuilder.create(ElectricFurnaceBlockEntity :: new, ElectricFurnaceRegistry.ELECTRIC_FURNACE_BLOCK.get()).build(null));
	public static final ScreenHandlerType<ElectricFurnaceScreenHandler> ELECTRIC_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ElectricFurnaceRegistry.ELECTRIC_FURNACE_IDENTIFIER, ElectricFurnaceScreenHandler :: new);
	public static final Serializer ELECTRIC_FURNACE_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ElectricCraft.MOD_ID, ElectricFurnaceRecipe.Serializer.IDENTIFIER), ElectricFurnaceRecipe.Serializer.INSTANCE);
	public static final Type ELECTRIC_FURNACE_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(ElectricCraft.MOD_ID, ElectricFurnaceRecipe.Type.IDENTIFIER), ElectricFurnaceRecipe.Type.INSTANCE);

	public static void registerElectricFurnace() {
		ElectricCraft.LOGGER.info("Initializing Electric Furnace");
	}
}
