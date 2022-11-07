package com.khopan.minecraft.mod.electriccraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.LightBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.collection.DefaultedList;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
	@Shadow
	public DefaultedList<ItemStack> main;

	@Shadow
	public int selectedSlot;

	@Inject(at=@At("HEAD"), method="scrollInHotbar(D)V", cancellable=true)
	public void scrollInHotbar(double ScrollAmount, CallbackInfo Info) {
		if(Screen.hasShiftDown()) {
			ItemStack Stack;

			if(PlayerInventory.isValidHotbarIndex(this.selectedSlot)) {
				Stack = this.main.get(this.selectedSlot);
			} else {
				Stack = ItemStack.EMPTY;
			}

			Item Item = Stack.getItem();

			if(Item instanceof BlockItem BlockItem) {
				Block Block = BlockItem.getBlock();

				if(Block instanceof LightBlock) {
					String Key = Properties.LEVEL_15.getName();
					NbtCompound SubNbt = Stack.getSubNbt("BlockStateTag");
					int OldValue = SubNbt == null ? 15 : Integer.parseInt(SubNbt.getString(Key));
					int NewValue = OldValue + ((int) Math.signum(ScrollAmount));

					if(NewValue > 15) {
						NewValue = 0;
					}

					if(NewValue < 0) {
						NewValue = 15;
					}

					NbtCompound NBT = new NbtCompound();
					NBT.putString(Key, String.valueOf(NewValue));
					Stack.setSubNbt("BlockStateTag", NBT);
					Info.cancel();
				}
			}
		}
	}
}
