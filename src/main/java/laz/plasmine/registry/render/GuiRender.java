package laz.plasmine.registry.render;

import laz.plasmine.client.container.heat.SedimentCollectorScreen;
import laz.plasmine.client.container.plasma.BasicGeneratorScreen;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.client.gui.ScreenManager;

public class GuiRender {
	public static void init() {
		ScreenManager.registerFactory(PMContainersInit.BASIC_GENERATOR_CONTAINER.get(),	BasicGeneratorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_COLLECTOR_CONTAINER.get(),	SedimentCollectorScreen::new);
	}
}
