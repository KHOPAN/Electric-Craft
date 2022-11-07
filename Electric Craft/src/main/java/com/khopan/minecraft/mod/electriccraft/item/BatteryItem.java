package com.khopan.minecraft.mod.electriccraft.item;

import java.util.List;

import com.khopan.math.MathUtils;
import com.khopan.minecraft.mod.electriccraft.energy.EnergyHelper;
import com.khopan.minecraft.mod.electriccraft.energy.TransferHelper;
import com.khopan.minecraft.mod.electriccraft.energy.TransferableEnergyItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class BatteryItem extends Item implements TransferableEnergyItem {
	public BatteryItem() {
		super(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
	}

	@Override
	public boolean isItemBarVisible(ItemStack Item) {
		return TransferHelper.getEnergy(Item) > 0;
	}

	@Override
	public int getItemBarColor(ItemStack Item) {
		return 0xAA0000;
	}

	@Override
	public int getItemBarStep(ItemStack Item) {
		return (int) Math.round(MathUtils.map(TransferHelper.getEnergy(Item), 0, this.getMaxEnergy(Item), 0, 13));
	}

	@Override
	public void appendTooltip(ItemStack Item, World World, List<Text> Tooltip, TooltipContext Context) {
		Tooltip.add(new LiteralText(EnergyHelper.formatEnergy(this.getEnergy(Item), this.getMaxEnergy(Item))).formatted(Formatting.GRAY));
	}

	@Override
	public int getMaxEnergy(ItemStack Item) {
		return 10000000;
	}

	@Override
	public int getEnergyInputPerTick(ItemStack Item) {
		return 100000;
	}

	@Override
	public int getEnergyOutputPerTick(ItemStack Item) {
		return 100000;
	}

	/*@Override
	public int getEnergy() {
		return this.Energy;
	}

	@Override
	public int getMaxEnergy() {
		return 10000000;
	}

	@Override
	public int getEPInputPerTick() {
		return 100000;
	}

	@Override
	public int getEPOutputPerTick() {
		return 100000;
	}

	@Override
	public boolean canInput() {
		return true;
	}

	@Override
	public boolean canOutput() {
		return true;
	}

	@Override
	public boolean addEnergy(int Amout) {
		if(this.Energy + Amout <= this.getMaxEnergy()) {
			this.Energy += Amout;
			return true;
		}

		return false;
	}

	@Override
	public boolean removeEnergy(int Amout) {
		if(Amout <= this.Energy) {
			this.Energy -= Amout;
			return true;
		}

		return false;
	}

	@Override
	public boolean setEnergy(int Energy) {
		if(Energy <= this.getMaxEnergy()) {
			this.Energy = Energy;
			return true;
		}

		return false;
	}

	@Override
	public boolean isEnergyEmpty() {
		return this.Energy == 0;
	}

	@Override
	public boolean isEnergyFull() {
		return this.Energy == this.getMaxEnergy();
	}*/
}
