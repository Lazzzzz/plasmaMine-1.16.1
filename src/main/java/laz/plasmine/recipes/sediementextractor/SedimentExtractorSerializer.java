package laz.plasmine.recipes.sediementextractor;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SedimentExtractorSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<SedimentExtractorRecipe> {

	@Override
	public SedimentExtractorRecipe read(ResourceLocation recipeId, JsonObject json) {
		JsonElement jsonelement1 = (JsonElement) (JSONUtils.isJsonArray(json, "input1")
				? JSONUtils.getJsonArray(json, "input1")
				: JSONUtils.getJsonObject(json, "input1"));
		JsonElement jsonelement2 = (JsonElement) (JSONUtils.isJsonArray(json, "input2")
				? JSONUtils.getJsonArray(json, "input2")
				: JSONUtils.getJsonObject(json, "input2"));
		
		Ingredient itemIn1 = Ingredient.deserialize(jsonelement1);
		Ingredient itemIn2 = Ingredient.deserialize(jsonelement2);
		
		ItemStack itemOut = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		int temp = JSONUtils.getInt(json, "temp");
		int time = JSONUtils.getInt(json, "time");

		return new SedimentExtractorRecipe(recipeId, itemIn1, itemIn2, itemOut, temp, time);
	}

	@Nullable
	@Override
	public SedimentExtractorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
	    Ingredient itemIn1 = Ingredient.read(buffer);
		Ingredient itemIn2 = Ingredient.read(buffer);
		
		ItemStack itemOut = buffer.readItemStack();

		int temp = buffer.readInt();
		int time = buffer.readInt();

		return new SedimentExtractorRecipe(recipeId, itemIn1, itemIn2, itemOut, temp, time);
	}

	@Override
	public void write(PacketBuffer buffer, SedimentExtractorRecipe recipe) {
		recipe.itemIn1.write(buffer);
		recipe.itemIn2.write(buffer);
		buffer.writeItemStack(recipe.getItemOut());
		
		buffer.writeInt(recipe.getTemp());
		buffer.writeInt(recipe.getCookTime());
	}
}