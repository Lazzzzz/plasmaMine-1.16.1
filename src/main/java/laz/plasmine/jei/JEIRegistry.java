package laz.plasmine.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.machine.CrystalizerJEI;
import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@JeiPlugin
public class JEIRegistry implements IModPlugin {

	public JEIRegistry() {}
	
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Plasmine.MOD_ID, "default");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new CrystalizerJEI());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (Minecraft.getInstance().world != null) {
			registration.addRecipes(getRecipes(Minecraft.getInstance().world,
					PMRecipesSerializer.SEDIMENT_CRYSTALIZER_TYPE), CrystalizerJEI.ID);
		}
		System.out.println("--- JEI RECIPES DONE ---");
	}
	
	public static <T extends IRecipe<?>> Collection<T> getRecipes(World world, IRecipeType<T> recipeType) {
		Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper
				.getPrivateValue(RecipeManager.class, world.getRecipeManager(), "field_199522_d");
		if (recipes != null) {
			Map<ResourceLocation, IRecipe<?>> typedRecipes = recipes.get(recipeType);
			if (typedRecipes != null) {
				return (Collection<T>) typedRecipes.values();
			}
		}
		return new ArrayList<>();
	}
}