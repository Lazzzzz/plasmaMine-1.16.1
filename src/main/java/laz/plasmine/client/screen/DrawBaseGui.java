package laz.plasmine.client.screen;

import static laz.plasmine.client.draw.PMDrawable.BASE_BAR;
import static laz.plasmine.client.draw.PMDrawable.BASE_GUI;

public class DrawBaseGui {

	public static void drawGui(int guiLeft, int guiTop) {
		BASE_GUI.draw(guiLeft, guiTop, 176, 166);
		BASE_BAR.draw(guiLeft + 2, guiTop + 2, 16, 56);
	}

}
