package com.khopan.minecraft.mod.electriccraft.item.supersonicgunitem;

import java.util.List;

import com.khopan.minecraft.mod.electriccraft.item.InfoItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SuperSonicGunItem extends InfoItem {
	public SuperSonicGunItem() {
		super(new FabricItemSettings().group(ItemGroup.MISC));
	}

	@Override
	public TypedActionResult<ItemStack> use(World World, PlayerEntity Player, Hand PlayerHand) {
		World.spawnEntity(new SuperSonicGunProjectile(World, Player));

		return super.use(World, Player, PlayerHand);
	}

	@Override
	public void appendInfo(List<Text> Info) {
		Info.add(new TranslatableText("info.tooltip.item.electriccraft.super_sonic_gun.line_one"));
		Info.add(new TranslatableText("info.tooltip.item.electriccraft.super_sonic_gun.line_two"));
		Info.add(new TranslatableText("info.tooltip.item.electriccraft.super_sonic_gun.line_three"));
	}
}
