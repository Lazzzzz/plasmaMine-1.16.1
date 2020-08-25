package laz.plasmine.registry.render;

import laz.plasmine.client.screen.heat.CrusherScreen;
import laz.plasmine.client.screen.heat.FurnaceScreen;
import laz.plasmine.client.screen.heat.IonizerScreen;
import laz.plasmine.client.screen.heat.SedimentCollectorScreen;
import laz.plasmine.client.screen.heat.SedimentCrystalizerScreen;
import laz.plasmine.client.screen.heat.SedimentExtractorScreen;
import laz.plasmine.client.screen.plasma.BasicGeneratorScreen;
import laz.plasmine.client.screen.plasma.EMGeneratorScreen;
import laz.plasmine.client.screen.storage.ItemStorageScreen;
import laz.plasmine.client.screen.storage.PlasmaStorageScreen;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.client.gui.ScreenManager;

public class GuiRender {
	public static void init() {
		ScreenManager.registerFactory(PMContainersInit.BASIC_GENERATOR_CONTAINER.get(), BasicGeneratorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.EM_CONTAINER.get(), EMGeneratorScreen::new);
		
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_COLLECTOR_CONTAINER.get(), SedimentCollectorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_EXTRACTOR_CONTAINER.get(), SedimentExtractorScreen::new);
		ScreenManager.registerFactory(PMContainersInit.SEDIMENT_CRYSTALIZER_CONTAINER.get(), SedimentCrystalizerScreen::new);
		ScreenManager.registerFactory(PMContainersInit.IONIZER_CONTAINER.get(), IonizerScreen::new);
		ScreenManager.registerFactory(PMContainersInit.FURNACE_CONTAINER.get(), FurnaceScreen::new);
		ScreenManager.registerFactory(PMContainersInit.CRUSHER_CONTAINER.get(), CrusherScreen::new);
		
		
		ScreenManager.registerFactory(PMContainersInit.PLASMA_STORAGE_CONTAINER.get(), PlasmaStorageScreen::new);
		ScreenManager.registerFactory(PMContainersInit.ITEM_INPUT_CONTAINER.get(), ItemStorageScreen::new);
	}
}
