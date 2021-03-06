package laz.plasmine.jei.generator;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.JEIScreenDraw;
import laz.plasmine.recipes.emgenerator.EmRecipe;
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

public class EMCategorie implements IRecipeCategory<EmRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Plasmine.MOD_ID, "em");

	private final IGuiHelper guiHelper;

	public EMCategorie(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends EmRecipe> getRecipeClass() {
		return EmRecipe.class;
	}

	@Override
	public String getTitle() {
		return "Electromagnetic generator";
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(160, 100);
	}

	@Override
	public IDrawable getIcon() {
		return guiHelper.createDrawableIngredient(new ItemStack(PMTilesInit.EM_GENERATOR.get()));
	}

	@Override
	public void setIngredients(EmRecipe recipe, IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.ITEM, recipe.getfuel());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, EmRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 0, 18);
		guiItemStacks.set(ingredients);
	}

	@Override
	public void draw(EmRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		guiHelper.getSlotDrawable().draw(matrixStack, 0, 18);
		JEIScreenDraw.drawJEIGenerator(minecraft, this, matrixStack, recipe, 25);
		
	}

}