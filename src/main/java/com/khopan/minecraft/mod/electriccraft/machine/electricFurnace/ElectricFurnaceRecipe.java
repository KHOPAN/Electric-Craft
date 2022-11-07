package com.khopan.minecraft.mod.electriccraft.machine.electricFurnace;

import com.google.gson.JsonObject;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class ElectricFurnaceRecipe implements Recipe<SimpleInventory> {
	public final Identifier Identifier;
	public final Ingredient Input;
	public final ItemStack Result;

	public ElectricFurnaceRecipe(Identifier Identifier, Ingredient Input, ItemStack Result) {
		this.Identifier = Identifier;
		this.Input = Input;
		this.Result = Result;
	}

	@Override
	public boolean matches(SimpleInventory Inventory, World World) {
		return this.Input.test(Inventory.getStack(0));
	}

	@Override
	public ItemStack craft(SimpleInventory Inventory) {
		return this.Result;
	}

	@Override
	public boolean fits(int Width, int Height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return this.Result.copy();
	}

	@Override
	public Identifier getId() {
		return this.Identifier;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ElectricFurnaceRecipe.Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return ElectricFurnaceRecipe.Type.INSTANCE;
	}

	public static class Type implements RecipeType<ElectricFurnaceRecipe> {
		public static final Type INSTANCE = new Type();
		public static final String IDENTIFIER = "electric_furnace";

		private Type() {

		}
	}

	public static class Serializer implements RecipeSerializer<ElectricFurnaceRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String IDENTIFIER = ElectricFurnaceRecipe.Type.IDENTIFIER;

		private Serializer() {

		}

		@Override
		public ElectricFurnaceRecipe read(Identifier Identifier, JsonObject JsonObject) {
			Ingredient Input = Ingredient.fromJson(JsonHelper.getObject(JsonObject, "input"));
			ItemStack Result = ShapedRecipe.outputFromJson(JsonHelper.getObject(JsonObject, "result"));

			return new ElectricFurnaceRecipe(Identifier, Input, Result);
		}

		@Override
		public ElectricFurnaceRecipe read(Identifier Identifier, PacketByteBuf Packet) {
			ItemStack Result = Packet.readItemStack();
			Ingredient Input = Ingredient.fromPacket(Packet);

			return new ElectricFurnaceRecipe(Identifier, Input, Result);
		}

		@Override
		public void write(PacketByteBuf Packet, ElectricFurnaceRecipe Recipe) {
			Packet.writeItemStack(Recipe.getOutput());
			Recipe.Input.write(Packet);
		}
	}
}
