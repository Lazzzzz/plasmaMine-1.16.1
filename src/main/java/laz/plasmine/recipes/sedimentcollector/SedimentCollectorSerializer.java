package laz.plasmine.recipes.sedimentcollector;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SedimentCollectorSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<SedimentCollectorRecipe> {

	@Override
	public SedimentCollectorRecipe read(ResourceLocation recipeId, JsonObject json) {
		float chance = JSONUtils.getFloat(json, "chance");
		ItemStack itemOut = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		int temp = JSONUtils.getInt(json, "temp");
		int time = JSONUtils.getInt(json, "time");

		return new SedimentCollectorRecipe(recipeId, chance, itemOut, temp, time);
	}

	@Nullable
	@Override
	public SedimentCollectorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		float chance = buffer.readFloat();

		ItemStack itemOut = buffer.readItemStack();

		int temp = buffer.readInt();
		int time = buffer.readInt();

		return new SedimentCollectorRecipe(recipeId, chance, itemOut, temp, time);
	}

	@Override
	public void write(PacketBuffer buffer, SedimentCollectorRecipe recipe) {
		buffer.writeFloat(recipe.getChance());
		buffer.writeItemStack(recipe.getItemOut());
		buffer.writeInt(recipe.getTemp());
		buffer.writeInt(recipe.getCookTime());
	}
}