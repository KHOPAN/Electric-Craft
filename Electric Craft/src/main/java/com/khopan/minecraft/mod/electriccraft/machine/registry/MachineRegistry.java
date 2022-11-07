package com.khopan.minecraft.mod.electriccraft.machine.registry;

import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class MachineRegistry {
	private MachineRegistry() {}

	public static void register(Class<? extends Machine> Machine) {
		Objects.requireNonNull(Machine);

		try {
			Machine Instance = Machine.getDeclaredConstructor().newInstance();
			Block Block = Instance.getBlock();
			Item Item = Instance.getItem();

			if(Block != null) {
				Registry.register(Registry.BLOCK, Instance.Identifier, Block);
			}

			if(Item != null) {
				Registry.register(Registry.ITEM, Instance.Identifier, Item);
			}
		} catch(Throwable Errors) {
			Errors.printStackTrace();
		}
	}
}
