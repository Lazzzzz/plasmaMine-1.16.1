package laz.plasmine.recipes.basicgenerator;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class BasicGeneratorSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<BasicGeneratorRecipe> {

	@Override
	public BasicGeneratorRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack fuel = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "fuel"));
		
		int time = JSONUtils.getInt(json, "time");

		return new BasicGeneratorRecipe(recipeId, fuel, time);
	}

	@Nullable
	@Override
	public BasicGeneratorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack fuel = buffer.readItemStack();
		int time = buffer.readInt();

		return new BasicGeneratorRecipe(recipeId, fuel, time);
	}

	@Override
	public void write(PacketBuffer buffer, BasicGeneratorRecipe recipe) {
		buffer.writeItemStack(recipe.getfuel(), false);

		buffer.writeInt(recipe.getCookTime());
	}
}