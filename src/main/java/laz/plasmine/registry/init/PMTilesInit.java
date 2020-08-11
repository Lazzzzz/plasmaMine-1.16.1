package laz.plasmine.registry.init;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.registry.PMRegistry.BLOCKS;
import static laz.plasmine.registry.PMRegistry.ITEMS;
import static laz.plasmine.registry.PMRegistry.TILE_ENTITIES;

import laz.plasmine.content.tiles.cable.BlockBasicPlasmaCable;
import laz.plasmine.content.tiles.cable.TileBasicPlasmaCable;
import laz.plasmine.content.tiles.convertor.BlockAdvancedConvertor;
import laz.plasmine.content.tiles.convertor.BlockBasicConvertor;
import laz.plasmine.content.tiles.convertor.TileAdvancedConvertor;
import laz.plasmine.content.tiles.convertor.TileBasicConvertor;
import laz.plasmine.content.tiles.generator.BlockBasicGenerator;
import laz.plasmine.content.tiles.generator.TileBasicGenerator;
import laz.plasmine.content.tiles.heat.cropblower.BlockCropBlower;
import laz.plasmine.content.tiles.heat.cropblower.TileCropBlower;
import laz.plasmine.content.tiles.heat.sedimentcollector.BlockSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcollector.TileSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.BlockSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.TileSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentextractor.BlockSedimentExtractor;
import laz.plasmine.content.tiles.heat.sedimentextractor.TileSedimentExtractor;
import laz.plasmine.content.tiles.other.BlockHupper;
import laz.plasmine.content.tiles.other.TileHupper;
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
	public static BlockRegistryObjectGroup<BlockAdvancedConvertor, BlockItem, TileAdvancedConvertor> ADVANCED_CONVERTOR;
	
	public static BlockRegistryObjectGroup<BlockCropBlower, BlockItem, TileCropBlower> CROP_BLOWER;
	
	public static BlockRegistryObjectGroup<BlockHupper, BlockItem, TileHupper> HUPPER;

	public static void init() {
 
		// CABLES
		BASIC_PLASMA_CABLE = new BlockRegistryObjectGroup<>("basic_plasma_cable", BlockBasicPlasmaCable::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileBasicPlasmaCable::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

		BASIC_GENERATOR = new BlockRegistryObjectGroup<>("basic_generator", BlockBasicGenerator::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileBasicGenerator(2000, 20, 1)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		
		// CONVERTOR
		BASIC_CONVERTOR = new BlockRegistryObjectGroup<>("basic_convertor", BlockBasicConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileBasicConvertor(2, 0.3f)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		ADVANCED_CONVERTOR = new BlockRegistryObjectGroup<>("advanced_convertor", BlockAdvancedConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileAdvancedConvertor(5, 0.5f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		// MACHINE
		SEDIMENT_COLLECTOR = new BlockRegistryObjectGroup<>("sediment_collector", BlockSedimentCollector::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSedimentCollector(500, 0.4f)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		SEDIMENT_EXTRACTOR = new BlockRegistryObjectGroup<>("sediment_extractor", BlockSedimentExtractor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSedimentExtractor(500, 0.4f)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		SEDIMENT_CRYSTALIZER = new BlockRegistryObjectGroup<>("sediment_crystalizer", BlockSedimentCrystalizer::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSedimentCrystalizer(700, 0.2f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		CROP_BLOWER = new BlockRegistryObjectGroup<>("crop_blower", BlockCropBlower::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileCropBlower(500, 0.4f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		HUPPER = new BlockRegistryObjectGroup<>("hupper", BlockHupper::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				TileHupper::new).register(BLOCKS, ITEMS, TILE_ENTITIES);

	}

}
