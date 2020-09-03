package laz.plasmine.jei;

import static laz.plasmine.recipes.AllRecipies.getRecipiesBG;
import static laz.plasmine.recipes.AllRecipies.getRecipiesCollector;
import static laz.plasmine.recipes.AllRecipies.getRecipiesCrusher;
import static laz.plasmine.recipes.AllRecipies.getRecipiesCrystalizer;
import static laz.plasmine.recipes.AllRecipies.getRecipiesEM;
import static laz.plasmine.recipes.AllRecipies.getRecipiesExtractor;
import static laz.plasmine.recipes.AllRecipies.getRecipiesIonizer;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.generator.BGCategorie;
import laz.plasmine.jei.generator.EMCategorie;
import laz.plasmine.jei.machine.CollectorCategorie;
import laz.plasmine.jei.machine.CrusherCategorie;
import laz.plasmine.jei.machine.CrystalizerCategorie;
import laz.plasmine.jei.machine.ExtractorCategorie;
import laz.plasmine.jei.machine.IonizerCategorie;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIRegistry implements IModPlugin {

	public JEIRegistry() {
	}

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Plasmine.MOD_ID, "default");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		
		registry.addRecipeCategories(new CollectorCategorie(guiHelper));
		registry.addRecipeCategories(new ExtractorCategorie(guiHelper));
		registry.addRecipeCategories(new CrystalizerCategorie(guiHelper));
		registry.addRecipeCategories(new IonizerCategorie(guiHelper));
		registry.addRecipeCategories(new CrusherCategorie(guiHelper));
		registry.addRecipeCategories(new BGCategorie(guiHelper));
		registry.addRecipeCategories(new EMCategorie(guiHelper));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		
		registration.addRecipes(getRecipiesCollector(recipeManager), CollectorCategorie.ID);
		registration.addRecipes(getRecipiesExtractor(recipeManager), ExtractorCategorie.ID);
		registration.addRecipes(getRecipiesCrystalizer(recipeManager), CrystalizerCategorie.ID);
		registration.addRecipes(getRecipiesIonizer(recipeManager), IonizerCategorie.ID);
		registration.addRecipes(getRecipiesCrusher(recipeManager), CrusherCategorie.ID);	
		registration.addRecipes(getRecipiesBG(recipeManager), BGCategorie.ID);
		registration.addRecipes(getRecipiesEM(recipeManager), EMCategorie.ID);	
	
		System.out.println("Plasmine JEI compat add");
	}

}