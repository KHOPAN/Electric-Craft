package com.khopan.minecraft.mod.electriccraft.block.cable.energy;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class BaseEnergyCableItem extends BlockItem {
	public final BaseEnergyCableBlock Block;

	public BaseEnergyCableItem(BaseEnergyCableBlock Block, Settings Settings) {
		super(Block, Settings);
		this.Block = Block;
	}

	@Override
	public void appendTooltip(ItemStack Item, World World, List<Text> Tooltip, TooltipContext Context) {
		Tooltip.add(new LiteralText("Tier: " + this.Block.Tier.Color + this.Block.Tier.Level));
	}
}
