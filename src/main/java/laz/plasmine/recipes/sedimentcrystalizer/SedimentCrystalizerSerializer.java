package laz.plasmine.recipes.sedimentcrystalizer;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SedimentCrystalizerSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<SedimentCrystalizerRecipe> {

	@Override
	public SedimentCrystalizerRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack itemIn = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "input"));
		ItemStack itemOut = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		int temp = JSONUtils.getInt(json, "temp");
		int time = JSONUtils.getInt(json, "time");

		return new SedimentCrystalizerRecipe(recipeId, itemIn, itemOut, temp, time);
	}

	@Nullable
	@Override
	public SedimentCrystalizerRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack itemIn = buffer.readItemStack();
		ItemStack itemOut = buffer.readItemStack();

		int temp = buffer.readInt();
		int time = buffer.readInt();

		return new SedimentCrystalizerRecipe(recipeId, itemIn, itemOut, temp, time);
	}

	@Override
	public void write(PacketBuffer buffer, SedimentCrystalizerRecipe recipe) {
		buffer.writeItemStack(recipe.getItemIn());		
		buffer.writeItemStack(recipe.getItemOut());
		
		buffer.writeInt(recipe.getTemp());
		buffer.writeInt(recipe.getCookTime());
	}
}