package laz.plasmine.recipes.crusher;

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

public class CrusherSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<CrusherRecipe> {

	@Override
	public CrusherRecipe read(ResourceLocation recipeId, JsonObject json) {

		JsonElement jsonelement1 = (JsonElement) (JSONUtils.isJsonArray(json, "input")
				? JSONUtils.getJsonArray(json, "input")
				: JSONUtils.getJsonObject(json, "input"));

		Ingredient itemIn = Ingredient.deserialize(jsonelement1);
		ItemStack itemOut = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		
		int temp = JSONUtils.getInt(json, "temp");
		int time = JSONUtils.getInt(json, "time");

		return new CrusherRecipe(recipeId, itemIn, itemOut, temp, time);
	}

	@Nullable
	@Override
	public CrusherRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		Ingredient itemIn = Ingredient.read(buffer);
		ItemStack itemOut = buffer.readItemStack();

		int temp = buffer.readInt();
		int time = buffer.readInt();

		return new CrusherRecipe(recipeId, itemIn, itemOut, temp, time);
	}

	@Override
	public void write(PacketBuffer buffer, CrusherRecipe recipe) {
		recipe.itemIn.write(buffer);

		buffer.writeItemStack(recipe.getItemOut(), false);

		buffer.writeInt(recipe.getTemp());
		buffer.writeInt(recipe.getCookTime());
	}
}