package laz.plasmine.registry.init;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.registry.PMRegistry.BLOCKS;
import static laz.plasmine.registry.PMRegistry.ITEMS;
import static laz.plasmine.registry.PMRegistry.TILE_ENTITIES;

import laz.plasmine.content.tiles.cable.BlockBasicPlasmaCable;
import laz.plasmine.content.tiles.cable.TileBasicPlasmaCable;
import laz.plasmine.content.tiles.convertor.BlockBasicConvertor;
import laz.plasmine.content.tiles.convertor.TileBasicConvertor;
import laz.plasmine.content.tiles.generator.BlockBasicGenerator;
import laz.plasmine.content.tiles.generator.TileBasicGenerator;
import laz.plasmine.content.tiles.heat.sedimentcollector.BlockSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcollector.TileSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.BlockSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.TileSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentextractor.BlockSedimentExtractor;
import laz.plasmine.content.tiles.heat.sedimentextractor.TileSedimentExtractor;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class PMTilesInit {

	public static BlockRegistryObjectGroup<BlockBasicPlasmaCable, BlockItem, TileBasicPlasmaCable> BASIC_PLASMA_CABLE;
	public static BlockRegistryObjectGroup<BlockBasicGenerator, BlockItem, TileBasicGenerator> BASIC_GENERATOR;

	public static BlockRegistryObjectGroup<BlockSedimentCollector, BlockItem, TileSedimentCollector> SEDIMENT_COLLECTOR;
	public static BlockRegistryObjectGroup<BlockSedimentExtractor, BlockItem, TileSedimentExtractor> SEDIMENT_EXTRACTOR;
	public static BlockRegistryObjectGroup<BlockSedimentCrystalizer, BlockItem, TileSedimentCrystalizer> SEDIMENT_CRYSTALIZER;

	public static BlockRegistryObjectGroup<BlockBasicConvertor, BlockItem, TileBasicConvertor> BASIC_CONVERTOR;

	public static void init() {

		BASIC_PLASMA_CABLE = new BlockRegistryObjectGroup<>("basic_plasma_cable", BlockBasicPlasmaCable::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileBasicPlasmaCable::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

		BASIC_GENERATOR = new BlockRegistryObjectGroup<>("basic_generator", BlockBasicGenerator::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileBasicGenerator(2000, 20, 1)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		BASIC_CONVERTOR = new BlockRegistryObjectGroup<>("basic_convertor", BlockBasicConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileBasicConvertor::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

		SEDIMENT_COLLECTOR = new BlockRegistryObjectGroup<>("sediment_collector", BlockSedimentCollector::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSedimentCollector(500, 0.4f))
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

		SEDIMENT_EXTRACTOR = new BlockRegistryObjectGroup<>("sediment_extractor", BlockSedimentExtractor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSedimentExtractor(500, 0.4f))
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

		SEDIMENT_CRYSTALIZER = new BlockRegistryObjectGroup<>("sediment_crystalizer", BlockSedimentCrystalizer::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSedimentCrystalizer(700, 0.6f))
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

	}

}
