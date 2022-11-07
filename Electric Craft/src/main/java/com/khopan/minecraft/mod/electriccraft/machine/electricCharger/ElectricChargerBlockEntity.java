package com.khopan.minecraft.mod.electriccraft.machine.electricCharger;

import com.khopan.minecraft.mod.electriccraft.energy.TransferDirection;
import com.khopan.minecraft.mod.electriccraft.energy.TransferableEnergyBlock;
import com.khopan.minecraft.mod.electriccraft.machine.ImplementedInventory;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class ElectricChargerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, TransferableEnergyBlock {
	public final DefaultedList<ItemStack> Inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

	public ElectricChargerBlockEntity(BlockPos Position, BlockState State) {
		super(ElectricChargerRegistry.ELECTRIC_CHARGER_BLOCK_ENTITY, Position, State);
	}

	@Override
	public ScreenHandler createMenu(int SyncID, PlayerInventory Inventory, PlayerEntity Player) {
		return null;
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.Inventory;
	}

	@Override
	public Text getDisplayName() {
		return new LiteralText("Electric Charger");
	}

	@Override
	public int getEnergy() {
		return 0;
	}

	@Override
	public int getMaxEnergy() {
		return 0;
	}

	@Override
	public int getEPInputPerTick() {
		return 0;
	}

	@Override
	public int getEPOutputPerTick() {
		return 0;
	}

	@Override
	public boolean canInput() {
		return false;
	}

	@Override
	public boolean canOutput() {
		return false;
	}

	@Override
	public boolean addEnergy(int Amout) {
		return false;
	}

	@Override
	public boolean removeEnergy(int Amout) {
		return false;
	}

	@Override
	public boolean setEnergy(int Energy) {
		return false;
	}

	@Override
	public boolean isEnergyEmpty() {
		return false;
	}

	@Override
	public boolean isEnergyFull() {
		return false;
	}

	@Override
	public TransferDirection getTransferDirection() {
		return null;
	}
}
