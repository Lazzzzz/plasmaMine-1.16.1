package laz.plasmine.datagen;

import laz.plasmine.Plasmine;
import laz.plasmine.datagen.lang.PMLang;
import laz.plasmine.datagen.loot.PMLootsProvider;
import laz.plasmine.datagen.recipes.PMRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Plasmine.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlasmineData {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {

		final DataGenerator dataGenerator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        dataGenerator.addProvider(new PMLootsProvider(dataGenerator));
		dataGenerator.addProvider(new PMRecipeProvider(dataGenerator));
		dataGenerator.addProvider(new PMLang(dataGenerator));
	}
}