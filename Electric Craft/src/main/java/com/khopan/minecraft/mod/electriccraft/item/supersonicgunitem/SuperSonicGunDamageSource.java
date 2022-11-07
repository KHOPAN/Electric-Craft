package com.khopan.minecraft.mod.electriccraft.item.supersonicgunitem;

import net.minecraft.entity.damage.DamageSource;

public class SuperSonicGunDamageSource extends DamageSource {
	public SuperSonicGunDamageSource(SuperSonicGunProjectile Projectile) {
		super("super_sonic_gun");
	}

	public static SuperSonicGunDamageSource create(SuperSonicGunProjectile Projectile) {
		return new SuperSonicGunDamageSource(Projectile);
	}
}
