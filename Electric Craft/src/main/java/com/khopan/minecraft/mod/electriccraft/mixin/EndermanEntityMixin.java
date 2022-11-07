package com.khopan.minecraft.mod.electriccraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.khopan.minecraft.mod.electriccraft.item.supersonicgunitem.SuperSonicGunProjectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {
	@Shadow
	public abstract boolean damage(DamageSource source, float amount);

	@Inject(at=@At("HEAD"), method="damage(Lnet/minecrft/entity/damage/DamageSource;F)Z")
	public void damage(DamageSource Source, float Amount, CallbackInfoReturnable<Boolean> Info) {
		boolean Damage = this.damage(Source, Amount);
		Entity Entity = Source.getSource();

		if(Entity instanceof SuperSonicGunProjectile) {
			Info.setReturnValue(true);
		}

		Info.setReturnValue(Damage);
	}
}
