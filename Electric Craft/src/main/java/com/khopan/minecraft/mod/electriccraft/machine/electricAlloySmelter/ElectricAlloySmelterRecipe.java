package com.khopan.minecraft.mod.electriccraft.machine.electricAlloySmelter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class ElectricAlloySmelterRecipe implements Recipe<SimpleInventory> {
	public final Identifier Identifier;
	public final DefaultedList<Ingredient> Input;
	public final ItemStack Result;
	public final int SmeltingTime;

	public ElectricAlloySmelterRecipe(Identifier Identifier, DefaultedList<Ingredient> Input, ItemStack Result, int SmeltingTime) {
		this.Identifier = Identifier;
		this.Input = Input;
		this.Result = Result;
		this.SmeltingTime = SmeltingTime;
	}

	@Override
	public boolean matches(SimpleInventory Inventory, World World) {
		RecipeMatcher RecipeMatcher = new RecipeMatcher();
		int x = 0;

		for(int y = 0; y < 9; ++y) {
			ItemStack Item = Inventory.getStack(y);

			if(Item.isEmpty()) {
				continue;
			}

			++x;
			RecipeMatcher.addInput(Item, 1);
		}

		return x == this.Input.size() && RecipeMatcher.match(this, null);
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
		return ElectricAlloySmelterRecipe.Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return ElectricAlloySmelterRecipe.Type.INSTANCE;
	}

	public int getSmeltingTime() {
		return this.SmeltingTime;
	}

	public static class Type implements RecipeType<ElectricAlloySmelterRecipe> {
		public static final Type INSTANCE = new Type();
		public static final String IDENTIFIER = "alloy_smelting";

		private Type() {

		}
	}

	public static class Serializer implements RecipeSerializer<ElectricAlloySmelterRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String IDENTIFIER = ElectricAlloySmelterRecipe.Type.IDENTIFIER;

		private Serializer() {

		}

		@Override
		public ElectricAlloySmelterRecipe read(Identifier Identifier, JsonObject JsonObject) {
			DefaultedList<Ingredient> Input = Serializer.getIngredients(JsonHelper.getArray(JsonObject, "ingredients"));
			ItemStack Result = ShapedRecipe.outputFromJson(JsonHelper.getObject(JsonObject, "result"));
			int SmeltingTime = 200;

			if(JsonHelper.hasNumber(JsonObject, "smeltingTime")) {
				SmeltingTime = JsonHelper.getInt(JsonObject, "smeltingTime");
			}

			if(Input.isEmpty()) {
				throw new RuntimeException("No ingredients for smelting recipe.");
			}

			if(Input.size() > 9) {
				throw new RuntimeException("Too much ingredients for smelting recipe.");
			}

			return new ElectricAlloySmelterRecipe(Identifier, Input, Result, SmeltingTime);
		}

		@Override
		public ElectricAlloySmelterRecipe read(Identifier Identifier, PacketByteBuf Packet) {
			int SmeltingTime = Packet.readInt();
			int InputSize = Packet.readInt();
			DefaultedList<Ingredient> Input = DefaultedList.ofSize(3, Ingredient.EMPTY);

			for(int i = 0; i < InputSize; i++) {
				Input.set(i, Ingredient.fromPacket(Packet));
			}

			ItemStack Result = Packet.readItemStack();

			return new ElectricAlloySmelterRecipe(Identifier, Input, Result, SmeltingTime);
		}

		@Override
		public void write(PacketByteBuf Packet, ElectricAlloySmelterRecipe Recipe) {
			Packet.writeInt(Recipe.SmeltingTime);
			Packet.writeInt(Recipe.Input.size());

			for(Ingredient Input : Recipe.Input) {
				Input.write(Packet);
			}

			Packet.writeItemStack(Recipe.Result);
		}

		public static DefaultedList<Ingredient> getIngredients(JsonArray JsonArray) {
			DefaultedList<Ingredient> List = DefaultedList.of();

			for(int i = 0; i < JsonArray.size(); ++i) {
				Ingredient Input = Ingredient.fromJson(JsonArray.get(i));

				if(Input.isEmpty()) {
					continue;
				}

				List.add(Input);
			}

			return List;
		}
	}
}
