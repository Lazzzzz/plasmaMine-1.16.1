package laz.plasmine.client.screen;

import static laz.plasmine.client.draw.PMDrawable.BASE_GUI;
import static laz.plasmine.client.draw.PMDrawable.HEAT_BAR;
import static laz.plasmine.client.draw.PMDrawable.LOADING_BAR_EMPTY;
import static laz.plasmine.client.draw.PMDrawable.LOADING_BAR_FULL;
import static laz.plasmine.client.draw.PMDrawable.PLASMA_BAR;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.api.PlasmaHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.StringTextComponent;

public class DrawBaseGui {

	public static void drawGui(MatrixStack matrix, FontRenderer font, int guiLeft, int guiTop, String indic, int amount, int color) {
		BASE_GUI.draw(guiLeft, guiTop, 176, 166);
		font.func_243248_b(matrix, new StringTextComponent(amount + indic), guiLeft + 78, guiTop + 70.5f, color);
	
	}
	
	public static void drawGuiSpeed(MatrixStack matrix, FontRenderer font, int guiLeft, int guiTop, String indic, int amount, double speed, int color) {
		BASE_GUI.draw(guiLeft, guiTop, 176, 166);
		font.func_243248_b(matrix, new StringTextComponent(amount + indic), guiLeft + 60, guiTop + 73.5f, color);
		font.func_243248_b(matrix, new StringTextComponent("Speed : " + String.format("%.2f", speed) + "x"), guiLeft + 28, guiTop + 5, 20736);
	
	}

	public static void drawPlasmaBar(MatrixStack matrix, int guiLeft, int guiTop, PlasmaHelper helper, FontRenderer font) {
		font.func_243248_b(matrix, new StringTextComponent("Storage : "), guiLeft + 27, guiTop + 70, 3833343);
		PLASMA_BAR.drawPartial(guiLeft + 7, guiTop + 4, 18, 77, 0,
				1 - ((float) helper.getCapacity() / helper.getMaxCapacity()), 1f, 1f);
	}
	
	public static void drawHeatBar(MatrixStack matrix, int guiLeft, int guiTop, HeatHelper helper, FontRenderer font) {
		font.func_243248_b(matrix, new StringTextComponent("Temp : "), guiLeft + 27, guiTop + 73, 12976128);
		HEAT_BAR.drawPartial(guiLeft + 7, guiTop + 4, 18, 77, 0,
				1 - ((float) helper.getCelcius() / helper.getMaxCelcius()), 1f, 1f);
	}
	
	public static void drawProgressBar(MatrixStack matrix, int posX, int posY, double current, double max) {
		LOADING_BAR_EMPTY.draw(posX, posY, 34, 12);
		LOADING_BAR_FULL.drawPartial(posX, posY, 34, 12, 0, 0, (float) (current / max), 1f);
	}
}
