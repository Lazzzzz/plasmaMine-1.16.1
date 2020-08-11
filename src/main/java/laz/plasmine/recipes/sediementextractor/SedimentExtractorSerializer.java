package laz.plasmine.recipes.sediementextractor;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SedimentExtractorSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<SedimentExtractorRecipe> {

	@Override
	public SedimentExtractorRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack itemIn1 = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "input1"));
		ItemStack itemIn2 = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "input2"));
		ItemStack itemOut = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		int temp = JSONUtils.getInt(json, "temp");
		int time = JSONUtils.getInt(json, "time");

		return new SedimentExtractorRecipe(recipeId, itemIn1, itemIn2, itemOut, temp, time);
	}

	@Nullable
	@Override
	public SedimentExtractorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack itemIn1 = buffer.readItemStack();
		ItemStack itemIn2 = buffer.readItemStack();
		ItemStack itemOut = buffer.readItemStack();

		int temp = buffer.readInt();
		int time = buffer.readInt();

		return new SedimentExtractorRecipe(recipeId, itemIn1, itemIn2, itemOut, temp, time);
	}

	@Override
	public void write(PacketBuffer buffer, SedimentExtractorRecipe recipe) {
		buffer.writeItemStack(recipe.getItemIn1());
		buffer.writeItemStack(recipe.getItemIn2());	
		buffer.writeItemStack(recipe.getItemOut());
		
		buffer.writeInt(recipe.getTemp());
		buffer.writeInt(recipe.getCookTime());
	}
}