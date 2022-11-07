package com.khopan.minecraft.mod.electriccraft.energy.ac;

import net.minecraft.entity.damage.DamageSource;

public class ElectrocutedDamage extends DamageSource {
	public ElectrocutedDamage() {
		super("electrocuted");
	}

	public static ElectrocutedDamage create() {
		return (ElectrocutedDamage) new ElectrocutedDamage().setUsesMagic();
	}
}
