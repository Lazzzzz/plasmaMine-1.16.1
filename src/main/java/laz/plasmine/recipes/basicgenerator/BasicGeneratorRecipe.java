package laz.plasmine.recipes.basicgenerator;

import laz.plasmine.recipes.AbstralGeneratorRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

public class BasicGeneratorRecipe extends AbstralGeneratorRecipe {

	public BasicGeneratorRecipe(ResourceLocation id, ItemStack fuel, int time) {
		super(PMRecipesSerializer.BG_TYPE, id, fuel, time);
	}
		
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return PMRecipesSerializer.BG_SERIALIZER.get();
	}

}
