package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.PM_CONTAINER;

import laz.plasmine.base.container.ContainerItemInput;
import laz.plasmine.content.tiles.generator.basicgenerator.ContainerBasicGenerator;
import laz.plasmine.content.tiles.generator.electromagneticgenerator.ContainerEMGenerator;
import laz.plasmine.content.tiles.heat.ionizer.ContainerIonizer;
import laz.plasmine.content.tiles.heat.sedimentcollector.ContainerSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.ContainerSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentextractor.ContainerSedimentExtractor;
import laz.plasmine.content.tiles.storage.ContainerPlasmaStorage;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class PMContainersInit {

	public static RegistryObject<ContainerType<ContainerBasicGenerator>> BASIC_GENERATOR_CONTAINER;
	public static RegistryObject<ContainerType<ContainerEMGenerator>> EM_CONTAINER;
	
	public static RegistryObject<ContainerType<ContainerSedimentCollector>> SEDIMENT_COLLECTOR_CONTAINER;
	public static RegistryObject<ContainerType<ContainerSedimentExtractor>> SEDIMENT_EXTRACTOR_CONTAINER;
	public static RegistryObject<ContainerType<ContainerSedimentCrystalizer>> SEDIMENT_CRYSTALIZER_CONTAINER;
	public static RegistryObject<ContainerType<ContainerIonizer>> IONIZER_CONTAINER;
	
	public static RegistryObject<ContainerType<ContainerPlasmaStorage>> PLASMA_STORAGE_CONTAINER;
	public static RegistryObject<ContainerType<ContainerItemInput>> ITEM_INPUT_CONTAINER;
	
	public static void init() {
		BASIC_GENERATOR_CONTAINER = PM_CONTAINER.register("basic_generator", () -> IForgeContainerType.create(ContainerBasicGenerator::new));
		EM_CONTAINER = PM_CONTAINER.register("em_generator", () -> IForgeContainerType.create(ContainerEMGenerator::new));
		
		SEDIMENT_COLLECTOR_CONTAINER = PM_CONTAINER.register("sediment_collector", () -> IForgeContainerType.create(ContainerSedimentCollector::new));
		SEDIMENT_EXTRACTOR_CONTAINER = PM_CONTAINER.register("sediment_extractor", () -> IForgeContainerType.create(ContainerSedimentExtractor::new));
		SEDIMENT_CRYSTALIZER_CONTAINER = PM_CONTAINER.register("sediment_crystalizer", () -> IForgeContainerType.create(ContainerSedimentCrystalizer::new));
		IONIZER_CONTAINER = PM_CONTAINER.register("ionizer", () -> IForgeContainerType.create(ContainerIonizer::new));
		
		PLASMA_STORAGE_CONTAINER = PM_CONTAINER.register("plasma_storage", () -> IForgeContainerType.create(ContainerPlasmaStorage::new));
		ITEM_INPUT_CONTAINER = PM_CONTAINER.register("item_input", () -> IForgeContainerType.create(ContainerItemInput::new));
		
	
	}
	
}
