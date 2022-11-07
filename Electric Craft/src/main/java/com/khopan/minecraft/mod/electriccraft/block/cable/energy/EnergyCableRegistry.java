package com.khopan.minecraft.mod.electriccraft.block.cable.energy;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity.CopperEnergyCableBlockEntity;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity.GoldEnergyCableBlockEntity;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.blockEntity.SilverEnergyCableBlockEntity;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.cable.CopperEnergyCableBlock;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.cable.GoldEnergyCableBlock;
import com.khopan.minecraft.mod.electriccraft.block.cable.energy.material.cable.SilverEnergyCableBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnergyCableRegistry {
	public static final String COPPER_ENERGY_CABLE_ID = "energy_cable_copper";
	public static final String SILVER_ENERGY_CABLE_ID = "energy_cable_silver";
	public static final String GOLD_ENERGY_CABLE_ID = "energy_cable_gold";

	public static final Identifier COPPER_ENERGY_CABLE_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, EnergyCableRegistry.COPPER_ENERGY_CABLE_ID);
	public static final Identifier SILVER_ENERGY_CABLE_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, EnergyCableRegistry.SILVER_ENERGY_CABLE_ID);
	public static final Identifier GOLD_ENERGY_CABLE_IDENTIFIER = new Identifier(ElectricCraft.MOD_ID, EnergyCableRegistry.GOLD_ENERGY_CABLE_ID);

	public static final BlockEntry<CopperEnergyCableBlock> COPPER_ENERGY_CABLE_BLOCK = ElectricCraft.REGISTRATE.block(EnergyCableRegistry.COPPER_ENERGY_CABLE_ID, CopperEnergyCableBlock :: new).item(BaseEnergyCableItem :: new).tab(() -> ItemGroup.MISC).build().register();
	public static final BlockEntry<SilverEnergyCableBlock> SILVER_ENERGY_CABLE_BLOCK = ElectricCraft.REGISTRATE.block(EnergyCableRegistry.SILVER_ENERGY_CABLE_ID, SilverEnergyCableBlock :: new).item(BaseEnergyCableItem :: new).tab(() -> ItemGroup.MISC).build().register();
	public static final BlockEntry<GoldEnergyCableBlock> GOLD_ENERGY_CABLE_BLOCK = ElectricCraft.REGISTRATE.block(EnergyCableRegistry.GOLD_ENERGY_CABLE_ID, GoldEnergyCableBlock :: new).item(BaseEnergyCableItem :: new).tab(() -> ItemGroup.MISC).build().register();

	public static final BlockEntityType<CopperEnergyCableBlockEntity> COPPER_ENERGY_CABLE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, EnergyCableRegistry.COPPER_ENERGY_CABLE_IDENTIFIER, FabricBlockEntityTypeBuilder.create(CopperEnergyCableBlockEntity :: new, EnergyCableRegistry.COPPER_ENERGY_CABLE_BLOCK.get()).build(null));
	public static final BlockEntityType<SilverEnergyCableBlockEntity> SILVER_ENERGY_CABLE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, EnergyCableRegistry.SILVER_ENERGY_CABLE_IDENTIFIER, FabricBlockEntityTypeBuilder.create(SilverEnergyCableBlockEntity :: new, EnergyCableRegistry.SILVER_ENERGY_CABLE_BLOCK.get()).build(null));
	public static final BlockEntityType<GoldEnergyCableBlockEntity> GOLD_ENERGY_CABLE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, EnergyCableRegistry.GOLD_ENERGY_CABLE_IDENTIFIER, FabricBlockEntityTypeBuilder.create(GoldEnergyCableBlockEntity :: new, EnergyCableRegistry.GOLD_ENERGY_CABLE_BLOCK.get()).build(null));

	public static void registerEnergyCable() {
		ElectricCraft.LOGGER.info("Initializing Energy Cable");
	}
}
