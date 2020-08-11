package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.PM_CONTAINER;

import laz.plasmine.content.tiles.generator.ContainerBasicGenerator;
import laz.plasmine.content.tiles.heat.sedimentcollector.ContainerSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.ContainerSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentextractor.ContainerSedimentExtractor;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class PMContainersInit {

	public static RegistryObject<ContainerType<ContainerBasicGenerator>> BASIC_GENERATOR_CONTAINER;
	
	public static RegistryObject<ContainerType<ContainerSedimentCollector>> SEDIMENT_COLLECTOR_CONTAINER;
	public static RegistryObject<ContainerType<ContainerSedimentExtractor>> SEDIMENT_EXTRACTOR_CONTAINER;
	public static RegistryObject<ContainerType<ContainerSedimentCrystalizer>> SEDIMENT_CRYSTALIZER_CONTAINER;
	
	public static void init() {
		BASIC_GENERATOR_CONTAINER = PM_CONTAINER.register("basic_generator", () -> IForgeContainerType.create(ContainerBasicGenerator::new));
		
		SEDIMENT_COLLECTOR_CONTAINER = PM_CONTAINER.register("sediment_collector", () -> IForgeContainerType.create(ContainerSedimentCollector::new));
		SEDIMENT_EXTRACTOR_CONTAINER = PM_CONTAINER.register("sediment_extractor", () -> IForgeContainerType.create(ContainerSedimentExtractor::new));
		SEDIMENT_CRYSTALIZER_CONTAINER = PM_CONTAINER.register("sediment_crystalizer", () -> IForgeContainerType.create(ContainerSedimentCrystalizer::new));
		
	}
	
}
