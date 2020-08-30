package laz.plasmine.recipes;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.recipes.crusher.CrusherRecipe;
import laz.plasmine.recipes.ionizer.IonizerRecipe;
import laz.plasmine.recipes.sediementextractor.SedimentExtractorRecipe;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;

public class AllRecipies {
	
	public static List<IRecipe> getRecipiesCrystalizer(RecipeManager recipeManager) {
		List<IRecipe> l = new ArrayList<>();
		for (IRecipe recipe : recipeManager.getRecipes()) {
			if (recipe instanceof SedimentCrystalizerRecipe) {
				l.add(recipe);
			}
		}
		return l;
	}
	
	public static List<IRecipe> getRecipiesCollector(RecipeManager recipeManager) {
		List<IRecipe> l = new ArrayList<>();
		for (IRecipe recipe : recipeManager.getRecipes()) {
			if (recipe instanceof SedimentCollectorRecipe) {
				l.add(recipe);
			}
		}
		return l;
	}
	
	public static List<IRecipe> getRecipiesExtractor(RecipeManager recipeManager) {
		List<IRecipe> l = new ArrayList<>();
		for (IRecipe recipe : recipeManager.getRecipes()) {
			if (recipe instanceof SedimentExtractorRecipe) {
				l.add(recipe);
			}
		}
		return l;
	}
	
	public static List<IRecipe> getRecipiesCrusher(RecipeManager recipeManager) {
		List<IRecipe> l = new ArrayList<>();
		for (IRecipe recipe : recipeManager.getRecipes()) {
			if (recipe instanceof CrusherRecipe) {
				l.add(recipe);
			}
		}
		return l;
	}
	
	public static List<IRecipe> getRecipiesIonizer(RecipeManager recipeManager) {
		List<IRecipe> l = new ArrayList<>();
		for (IRecipe recipe : recipeManager.getRecipes()) {
			if (recipe instanceof IonizerRecipe) {
				l.add(recipe);
			}
		}
		return l;
	}
}
