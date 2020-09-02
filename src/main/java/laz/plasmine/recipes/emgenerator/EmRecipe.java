package laz.plasmine.recipes.emgenerator;

import laz.plasmine.recipes.AbstralGeneratorRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

public class EmRecipe extends AbstralGeneratorRecipe {

	public EmRecipe(ResourceLocation id, ItemStack fuel, int time) {
		super(PMRecipesSerializer.EM_TYPE, id, fuel, time);
	}
		
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return PMRecipesSerializer.EM_SERIALIZER.get();
	}

}
