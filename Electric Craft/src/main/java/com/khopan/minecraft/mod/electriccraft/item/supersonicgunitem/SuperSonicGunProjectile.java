package com.khopan.minecraft.mod.electriccraft.item.supersonicgunitem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SuperSonicGunProjectile extends ArrowEntity {
	public SuperSonicGunProjectile(World World, PlayerEntity Player) {
		super(World, Player);
		this.setVelocity(Player, Player.getPitch(), Player.getYaw(), 0.0f, 100.0f, 1.0f);
		this.setNoGravity(true);
		this.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
		this.setDamage(Double.MAX_VALUE);
	}

	@Override
	protected void onEntityHit(EntityHitResult Hit) {
		Entity Entity = Hit.getEntity();

		if(Entity instanceof LivingEntity LivingEntity) {
			if(LivingEntity instanceof PlayerEntity Player) {
				if(Player.getAbilities().creativeMode) {
					return;
				}

				Player.onDeath(SuperSonicGunDamageSource.create(this));
			}

			Entity.kill();
		}
	}

	@Override
	protected void onHit(LivingEntity Entity) {
		if(Entity instanceof PlayerEntity Player) {
			if(Player.getAbilities().creativeMode) {
				return;
			}

			Player.onDeath(SuperSonicGunDamageSource.create(this));
		}

		Entity.kill();
	}

	@Override
	protected boolean canHit(Entity Entity) {
		return Entity instanceof LivingEntity;
	}

	@Override
	public void tick() {
		super.tick();

		if(this.inGround) {
			this.kill();
		}
	}
}
