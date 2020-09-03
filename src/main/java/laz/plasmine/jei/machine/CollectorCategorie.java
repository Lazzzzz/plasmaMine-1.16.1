package laz.plasmine.jei.machine;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.Plasmine;
import laz.plasmine.jei.JEIScreenDraw;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
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

public class CollectorCategorie implements IRecipeCategory<SedimentCollectorRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Plasmine.MOD_ID, "collector");

	private final IGuiHelper guiHelper;

	public CollectorCategorie(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends SedimentCollectorRecipe> getRecipeClass() {
		return SedimentCollectorRecipe.class;
	}

	@Override
	public String getTitle() {
		return "Sediment Collector";
	}

	@Override
	public IDrawable getBackground() {
		return guiHelper.createBlankDrawable(160, 100);
	}

	@Override
	public IDrawable getIcon() {
		return guiHelper.createDrawableIngredient(new ItemStack(PMTilesInit.SEDIMENT_COLLECTOR.get()));
	}

	@Override
	public void setIngredients(SedimentCollectorRecipe recipe, IIngredients ingredients) {
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getItemOut());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SedimentCollectorRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, false, 75, 30);
		guiItemStacks.set(ingredients);
	}

	@Override
	public void draw(SedimentCollectorRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		
		guiHelper.getSlotDrawable().draw(matrixStack, 75, 30);
		JEIScreenDraw.drawJEI(minecraft, this, matrixStack, recipe);
		minecraft.fontRenderer.drawString(matrixStack, String.format("Chance : %.2f", recipe.getChance() * 100) + "%", 0, 90, 29184);
	}

}