package laz.plasmine.registry.render;

import laz.plasmine.client.screen.heat.SedimentCollectorScreen;
import laz.plasmine.client.screen.heat.SedimentCrystalizerScreen;
import laz.plasmine.client.screen.heat.SedimentExtractorScreen;
import laz.plasmine.client.screen.plasma.BasicGeneratorScreen;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.client.gui.ScreenManager;

public class GuiRender {
	public static void init() {
		ScreenManager.registerFactory(PMContainersInit.BASIC_GENERATOR_CONTAINER.get(), BasicGeneratorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_COLLECTOR_CONTAINER.get(), SedimentCollectorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_EXTRACTOR_CONTAINER.get(), SedimentExtractorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_CRYSTALIZER_CONTAINER.get(), SedimentCrystalizerScreen::new);
	}
}
