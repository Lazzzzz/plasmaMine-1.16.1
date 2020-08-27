package laz.plasmine.jei.machine;

import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class CrystalizerJEI implements IRecipeCategory<SedimentCrystalizerRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(
			PMRecipesSerializer.SEDIMENT_CRYSTALIZER_SERIALIZER.get().getRegistryType().toString());

	public CrystalizerJEI() {
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends SedimentCrystalizerRecipe> getRecipeClass() {
		return SedimentCrystalizerRecipe.class;
	}

	@Override
	public String getTitle() {
		return "Sediment Crystalizer";
	}

	@Override
	public IDrawable getBackground() {
		return null;
	}

	@Override
	public IDrawable getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIngredients(SedimentCrystalizerRecipe recipe, IIngredients ingredients) {		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SedimentCrystalizerRecipe recipe, IIngredients ingredients) {
	}

}