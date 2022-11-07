package com.khopan.minecraft.mod.electriccraft.registry;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.builder.BlockBuilder;
import com.khopan.minecraft.mod.electriccraft.builder.ItemBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockRegistry {
	public static final Block LITHIUM_BLOCK = BlockBuilder.build(BlockBuilder.of(Material.STONE).strength(4.0f).requiresTool(), "lithium_block");

	public static final Item LITHIUM_BLOCK_ITEM = ItemBuilder.buildBlockItem(ItemBuilder.group(ItemGroup.MISC), BlockRegistry.LITHIUM_BLOCK, "lithium_block");

	public static void registerBlock() {
		ElectricCraft.LOGGER.info("Initializing Blocks");
	}
}
