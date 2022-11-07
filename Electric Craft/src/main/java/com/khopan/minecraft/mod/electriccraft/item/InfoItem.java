package com.khopan.minecraft.mod.electriccraft.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public abstract class InfoItem extends Item {
	public InfoItem(Settings Settings) {
		super(Settings);
	}

	public abstract void appendInfo(List<Text> Info);

	@Override
	public void appendTooltip(ItemStack Item, World World, List<Text> Tooltip, TooltipContext Context) {
		super.appendTooltip(Item, World, Tooltip, Context);

		if(Screen.hasShiftDown()) {
			List<Text> Info = new ArrayList<>();

			this.appendInfo(Info);

			if(Info.size() == 0) {
				Tooltip.add(new TranslatableText("info.tooltip.electriccraft.not_found", Item.getName().asString()));
			} else {
				for(Text Text : Info) {
					Tooltip.add(Text);
				}
			}
		} else {
			Tooltip.add(new TranslatableText("info.tooltip.electriccraft.tooltip"));
		}
	}
}
