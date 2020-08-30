package laz.plasmine.jei.machine;

import java.util.Arrays;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.JEIScreenDraw;
import laz.plasmine.recipes.ionizer.IonizerRecipe;
import laz.plasmine.recipes.sediementextractor.SedimentExtractorRecipe;
import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class IonizerCategorie implements IRecipeCategory<IonizerRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Plasmine.MOD_ID, "ionizer");

	private final IGuiHelper guiHelper;

	public IonizerCategorie(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends IonizerRecipe> getRecipeClass() {
		return IonizerRecipe.class;
	}

	@Override
	public String getTitle() {
		return "Ionizer";
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(160, 100);
	}

	@Override
	public IDrawable getIcon() {
		return guiHelper.createDrawableIngredient(new ItemStack(PMTilesInit.IONIZER.get()));
	}

	@Override
	public void setIngredients(IonizerRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(Arrays.asList(recipe.itemIn1, recipe.itemIn2));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getItemOut());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IonizerRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 35, 16);
		guiItemStacks.init(1, true, 35, 38);
		guiItemStacks.init(2, false, 105, 27);
		guiItemStacks.set(ingredients);
	}

	@Override
	public void draw(IonizerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		
		guiHelper.getSlotDrawable().draw(matrixStack, 35, 16);
		guiHelper.getSlotDrawable().draw(matrixStack, 35, 38);
		guiHelper.getSlotDrawable().draw(matrixStack, 105, 27);
		JEIScreenDraw.drawJEI(minecraft, this, matrixStack, recipe);

		IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
	}

}