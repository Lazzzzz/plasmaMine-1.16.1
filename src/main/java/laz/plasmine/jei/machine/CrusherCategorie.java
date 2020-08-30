package laz.plasmine.jei.machine;

import java.util.Arrays;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.JEIScreenDraw;
import laz.plasmine.recipes.crusher.CrusherRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrusherCategorie implements IRecipeCategory<CrusherRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Plasmine.MOD_ID, "crusher");

	private final IGuiHelper guiHelper;

	public CrusherCategorie(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends CrusherRecipe> getRecipeClass() {
		return CrusherRecipe.class;
	}

	@Override
	public String getTitle() {
		return "Crusher";
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(160, 100);
	}

	@Override
	public IDrawable getIcon() {
		return guiHelper.createDrawableIngredient(new ItemStack(PMTilesInit.CRUSHER.get()));
	}

	@Override
	public void setIngredients(CrusherRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(Arrays.asList(recipe.itemIn));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getItemOut());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 35, 30);
		guiItemStacks.init(1, false, 105, 30);
		guiItemStacks.set(ingredients);
	}

	@Override
	public void draw(CrusherRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		
		guiHelper.getSlotDrawable().draw(matrixStack, 35, 30);
		guiHelper.getSlotDrawable().draw(matrixStack, 105, 30);
		JEIScreenDraw.drawJEI(minecraft, this, matrixStack, recipe);

		IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
	}

}