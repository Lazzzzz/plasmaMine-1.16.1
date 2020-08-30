package laz.plasmine.jei;

import java.awt.Color;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;

public class JEIScreenDraw {

	@SuppressWarnings("rawtypes")
	public static void drawJEI(Minecraft minecraft, IRecipeCategory category, MatrixStack matrixStack, AbstractPlasmineRecipe recipe) {		
		minecraft.fontRenderer.drawString(matrixStack, category.getTitle(), 0, 1, Color.darkGray.getRGB());
		minecraft.fontRenderer.drawString(matrixStack, "\u00A7nInformation :", 0, 62, Color.darkGray.getRGB());
		minecraft.fontRenderer.drawString(matrixStack, String.format("Temperature required : %d °C", recipe.getTemp()), -1, 78, Color.RED.getRGB());
		if (recipe.getCookTime() > 0) minecraft.fontRenderer.drawString(matrixStack, String.format("Process time : %d secondes", recipe.getCookTime() / 20), 0, 90, 29184);

	}
	
}
