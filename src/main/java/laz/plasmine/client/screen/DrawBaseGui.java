package laz.plasmine.client.screen;

import static laz.plasmine.client.draw.PMDrawable.BASE_BAR;
import static laz.plasmine.client.draw.PMDrawable.BASE_GUI;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.StringTextComponent;

public class DrawBaseGui {

	public static void drawGui(MatrixStack matrix, FontRenderer font, int guiLeft, int guiTop, String indic, int amount, int color) {
		BASE_GUI.draw(guiLeft, guiTop, 176, 166);
		BASE_BAR.draw(guiLeft + 2, guiTop + 2, 16, 56);
		font.func_238422_b_(matrix, new StringTextComponent("\u00A7l" + amount + indic), guiLeft + 22, guiTop + 68, color);
	}

}