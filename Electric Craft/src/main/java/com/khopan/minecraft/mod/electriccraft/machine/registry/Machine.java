package com.khopan.minecraft.mod.electriccraft.machine.registry;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;
import com.sun.jna.platform.unix.X11.Screen;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;

public abstract class Machine {
	protected final Identifier Identifier;
	protected final FabricBlockSettings BlockSettings;

	public Machine(String Identifier) {
		this(new Identifier(ElectricCraft.MOD_ID, Identifier));
	}

	public Machine(Identifier Identifier) {
		this.Identifier = Identifier;
		this.BlockSettings = FabricBlockSettings.of(Material.STONE);
	}

	public Block getBlock() {
		return null;
	}

	public Item getItem() {
		return null;
	}

	public Class<? extends ScreenHandler> getScreenHandler() {
		return null;
	}

	public Class<? extends Screen> getScreen() {
		return null;
	}

	public static <T> T getInstance(T Type) {
		return null;
	}

	public static <T extends Machine> void register(Class<T> Machine) {
		MachineRegistry.register(Machine);
	}
}
