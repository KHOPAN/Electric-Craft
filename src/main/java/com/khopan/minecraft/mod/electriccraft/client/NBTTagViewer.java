package com.khopan.minecraft.mod.electriccraft.client;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class NBTTagViewer {
	public static final KeyBinding NBT_TAG_VIEWER_KEY_BINDING = new KeyBinding("key.electriccraft.nbt_tag_viewer", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "key.category.electriccraft");

	public static void register() {
		KeyBindingHelper.registerKeyBinding(NBTTagViewer.NBT_TAG_VIEWER_KEY_BINDING);
		ItemTooltipCallback.EVENT.register(NBTTagViewer :: appendToolTip);
	}

	public static void appendToolTip(ItemStack Item, TooltipContext Context, List<Text> List) {
		if(Item.hasNbt()) {
			if(!NBTTagViewer.NBT_TAG_VIEWER_KEY_BINDING.isUnbound() && InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.fromTranslationKey(NBTTagViewer.NBT_TAG_VIEWER_KEY_BINDING.getBoundKeyTranslationKey()).getCode())) {
				List.add(new LiteralText(Item.getNbt().toString()).formatted(Formatting.DARK_GRAY));
			} else {
				List.add(new LiteralText(Formatting.DARK_GRAY + "Hold [" + Formatting.GRAY + NBTTagViewer.NBT_TAG_VIEWER_KEY_BINDING.getBoundKeyLocalizedText().asString() + Formatting.DARK_GRAY + "] to show NBT tag(s)"));
			}
		}
	}
}
