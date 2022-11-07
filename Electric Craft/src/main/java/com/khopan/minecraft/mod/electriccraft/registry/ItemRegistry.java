package com.khopan.minecraft.mod.electriccraft.registry;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.builder.ItemBuilder;
import com.khopan.minecraft.mod.electriccraft.item.BatteryItem;
import com.khopan.minecraft.mod.electriccraft.item.CreativeBatteryItem;
import com.khopan.minecraft.mod.electriccraft.item.supersonicgunitem.SuperSonicGunItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
	public static final Item LITHIUM_INGOT = ItemBuilder.build(ItemBuilder.group(ItemGroup.MISC), "lithium_ingot");
	public static final Item ELECTRIC_INGOT = ItemBuilder.build(ItemBuilder.group(ItemGroup.MISC), "electric_ingot");
	public static final Item ELECTRIC_BAR = ItemBuilder.build(ItemBuilder.group(ItemGroup.MISC), "electric_bar");
	public static final Item BATTERY_ITEM = Registry.register(Registry.ITEM, new Identifier(ElectricCraft.MOD_ID, "battery"), new BatteryItem());
	public static final Item CREATIVE_BATTERY_ITEM = Registry.register(Registry.ITEM, new Identifier(ElectricCraft.MOD_ID, "creative_battery"), new CreativeBatteryItem());
	public static final Item SUPER_SONIC_GUN_ITEM = Registry.register(Registry.ITEM, new Identifier(ElectricCraft.MOD_ID, "super_sonic_gun"), new SuperSonicGunItem());

	public static void registerItem() {
		ElectricCraft.LOGGER.info("Initializing Items");
	}
}
