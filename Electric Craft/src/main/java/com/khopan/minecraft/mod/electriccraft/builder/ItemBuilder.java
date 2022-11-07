package com.khopan.minecraft.mod.electriccraft.builder;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemBuilder {
	public static Item build(FabricItemSettings Settings, String Name) {
		return Registry.register(Registry.ITEM, new Identifier(ElectricCraft.MOD_ID, Name), new Item(Settings));
	}

	public static Item buildBlockItem(FabricItemSettings Settings, Block Block, String Name) {
		return Registry.register(Registry.ITEM, new Identifier(ElectricCraft.MOD_ID, Name), new BlockItem(Block, Settings));
	}

	public static FabricItemSettings group(ItemGroup Group) {
		return new FabricItemSettings().group(Group);
	}
}
