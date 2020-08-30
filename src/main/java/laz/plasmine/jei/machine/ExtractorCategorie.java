package laz.plasmine.jei.machine;

import java.util.Arrays;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.JEIScreenDraw;
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

public class ExtractorCategorie implements IRecipeCategory<SedimentExtractorRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Plasmine.MOD_ID, "extractor");

	private final IGuiHelper guiHelper;

	public ExtractorCategorie(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends SedimentExtractorRecipe> getRecipeClass() {
		return SedimentExtractorRecipe.class;
	}

	@Override
	public String getTitle() {
		return "Sediment Extractor";
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(160, 100);
	}

	@Override
	public IDrawable getIcon() {
		return guiHelper.createDrawableIngredient(new ItemStack(PMTilesInit.SEDIMENT_EXTRACTOR.get()));
	}

	@Override
	public void setIngredients(SedimentExtractorRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(Arrays.asList(recipe.itemIn1, recipe.itemIn2));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getItemOut());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SedimentExtractorRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 35, 16);
		guiItemStacks.init(1, true, 35, 38);
		guiItemStacks.init(2, false, 105, 27);
		guiItemStacks.set(ingredients);
	}

	@Override
	public void draw(SedimentExtractorRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		
		guiHelper.getSlotDrawable().draw(matrixStack, 35, 16);
		guiHelper.getSlotDrawable().draw(matrixStack, 35, 38);
		guiHelper.getSlotDrawable().draw(matrixStack, 105, 27);
		JEIScreenDraw.drawJEI(minecraft, this, matrixStack, recipe);

		IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
	}

}