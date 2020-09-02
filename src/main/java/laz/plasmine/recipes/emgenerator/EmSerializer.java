package laz.plasmine.recipes.emgenerator;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import laz.plasmine.recipes.AbstralGeneratorRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class EmSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<EmRecipe> {

	@Override
	public EmRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack fuel = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "fuel"));
		
		int time = JSONUtils.getInt(json, "time");

		return new EmRecipe(recipeId, fuel, time);
	}

	@Nullable
	@Override
	public EmRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack fuel = buffer.readItemStack();
		int time = buffer.readInt();

		return new EmRecipe(recipeId, fuel, time);
	}

	@Override
	public void write(PacketBuffer buffer, EmRecipe recipe) {
		buffer.writeItemStack(recipe.getfuel(), false);

		buffer.writeInt(recipe.getCookTime());
	}
}