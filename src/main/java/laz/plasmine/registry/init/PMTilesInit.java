package laz.plasmine.registry.init;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.registry.PMRegistry.BLOCKS;
import static laz.plasmine.registry.PMRegistry.ITEMS;
import static laz.plasmine.registry.PMRegistry.TILE_ENTITIES;
import static laz.plasmine.registry.PMRegistry.addTileEntityNoItem;

import laz.plasmine.base.multiblock.input.BlockItemInput;
import laz.plasmine.base.multiblock.input.BlockPlasmaInput;
import laz.plasmine.base.multiblock.input.TileItemInput;
import laz.plasmine.base.multiblock.input.TilePlasmaInput;
import laz.plasmine.base.multiblock.output.BlockPlasmaOutput;
import laz.plasmine.base.multiblock.output.TilePlasmaOutput;
import laz.plasmine.base.multiblock.structure.BlockStructureBase;
import laz.plasmine.base.multiblock.structure.TileStructureBase;
import laz.plasmine.content.tiles.BlockMachine;
import laz.plasmine.content.tiles.TileMachine;
import laz.plasmine.content.tiles.cable.BlockBasicPlasmaCable;
import laz.plasmine.content.tiles.cable.TileBasicPlasmaCable;
import laz.plasmine.content.tiles.coils.BlockKetiumCoil;
import laz.plasmine.content.tiles.coils.BlockLisiumCoil;
import laz.plasmine.content.tiles.coils.BlockMagneticCoil;
import laz.plasmine.content.tiles.coils.BlockRosiumCoil;
import laz.plasmine.content.tiles.coils.TileKetiumCoil;
import laz.plasmine.content.tiles.coils.TileLisiumCoil;
import laz.plasmine.content.tiles.coils.TileMagneticCoil;
import laz.plasmine.content.tiles.coils.TileRosiumCoil;
import laz.plasmine.content.tiles.convertor.BlockAdvancedConvertor;
import laz.plasmine.content.tiles.convertor.BlockBasicConvertor;
import laz.plasmine.content.tiles.convertor.BlockSolarConvertor;
import laz.plasmine.content.tiles.convertor.BlockSuperAdvancedConvertor;
import laz.plasmine.content.tiles.convertor.BlockUltimateConvertor;
import laz.plasmine.content.tiles.convertor.TileAdvancedConvertor;
import laz.plasmine.content.tiles.convertor.TileBasicConvertor;
import laz.plasmine.content.tiles.convertor.TileSolarConvertor;
import laz.plasmine.content.tiles.convertor.TileSuperAdvancedConvertor;
import laz.plasmine.content.tiles.convertor.TileUltimateConvertor;
import laz.plasmine.content.tiles.generator.basicgenerator.BlockBasicGenerator;
import laz.plasmine.content.tiles.generator.basicgenerator.TileBasicGenerator;
import laz.plasmine.content.tiles.generator.electromagneticgenerator.BlockEMGenerator;
import laz.plasmine.content.tiles.generator.electromagneticgenerator.TileEMGenerator;
import laz.plasmine.content.tiles.heat.cropblower.BlockCropBlower;
import laz.plasmine.content.tiles.heat.cropblower.TileCropBlower;
import laz.plasmine.content.tiles.heat.cropheater.BlockCropHeater;
import laz.plasmine.content.tiles.heat.cropheater.TileCropHeater;
import laz.plasmine.content.tiles.heat.crusher.BlockCrusher;
import laz.plasmine.content.tiles.heat.crusher.TileCrusher;
import laz.plasmine.content.tiles.heat.furnace.BlockFurnace;
import laz.plasmine.content.tiles.heat.furnace.TileFurnace;
import laz.plasmine.content.tiles.heat.ionizer.BlockIonizer;
import laz.plasmine.content.tiles.heat.ionizer.TileIonizer;
import laz.plasmine.content.tiles.heat.miner.BlockMiner;
import laz.plasmine.content.tiles.heat.miner.TileMiner;
import laz.plasmine.content.tiles.heat.sedimentcollector.BlockSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcollector.TileSedimentCollector;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.BlockSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentcrystalizer.TileSedimentCrystalizer;
import laz.plasmine.content.tiles.heat.sedimentextractor.BlockSedimentExtractor;
import laz.plasmine.content.tiles.heat.sedimentextractor.TileSedimentExtractor;
import laz.plasmine.content.tiles.other.BlockHupper;
import laz.plasmine.content.tiles.other.TileHupper;
import laz.plasmine.content.tiles.storage.BlockPlasmaStorage;
import laz.plasmine.content.tiles.storage.TilePlasmaStorage;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import laz.plasmine.registry.PMRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class PMTilesInit {

	public static BlockRegistryObjectGroup<BlockBasicPlasmaCable, BlockItem, TileBasicPlasmaCable> BASIC_PLASMA_CABLE;
	public static BlockRegistryObjectGroup<BlockBasicGenerator, BlockItem, TileBasicGenerator> BASIC_GENERATOR;
	public static BlockRegistryObjectGroup<BlockEMGenerator, BlockItem, TileEMGenerator> EM_GENERATOR;

	public static BlockRegistryObjectGroup<BlockSedimentCollector, BlockItem, TileSedimentCollector> SEDIMENT_COLLECTOR;
	public static BlockRegistryObjectGroup<BlockSedimentExtractor, BlockItem, TileSedimentExtractor> SEDIMENT_EXTRACTOR;
	public static BlockRegistryObjectGroup<BlockSedimentCrystalizer, BlockItem, TileSedimentCrystalizer> SEDIMENT_CRYSTALIZER;
	public static BlockRegistryObjectGroup<BlockIonizer, BlockItem, TileIonizer> IONIZER;
	public static BlockRegistryObjectGroup<BlockFurnace, BlockItem, TileFurnace> FURNACE;
	public static BlockRegistryObjectGroup<BlockCrusher, BlockItem, TileCrusher> CRUSHER;

	public static BlockRegistryObjectGroup<BlockSolarConvertor, BlockItem, TileSolarConvertor> SOLAR_CONVERTOR;
	public static BlockRegistryObjectGroup<BlockBasicConvertor, BlockItem, TileBasicConvertor> BASIC_CONVERTOR;
	public static BlockRegistryObjectGroup<BlockAdvancedConvertor, BlockItem, TileAdvancedConvertor> ADVANCED_CONVERTOR;
	public static BlockRegistryObjectGroup<BlockSuperAdvancedConvertor, BlockItem, TileSuperAdvancedConvertor> SUPER_ADVANCED_CONVERTOR;
	public static BlockRegistryObjectGroup<BlockUltimateConvertor, BlockItem, TileUltimateConvertor> ULTIMATE_CONVERTOR;

	public static BlockRegistryObjectGroup<BlockMachine, BlockItem, TileMachine> MACHINE_BLOCK;
	
	public static BlockRegistryObjectGroup<BlockPlasmaStorage, BlockItem, TilePlasmaStorage> PLASMA_STORAGE;
	
	public static BlockRegistryObjectGroup<BlockPlasmaInput, BlockItem, TilePlasmaInput> PLASMA_INPUT;
	public static BlockRegistryObjectGroup<BlockPlasmaOutput, BlockItem, TilePlasmaOutput> PLASMA_OUTPUT;
	public static BlockRegistryObjectGroup<BlockItemInput, BlockItem, TileItemInput> ITEM_INPUT;
	
	public static BlockRegistryObjectGroup<BlockLisiumCoil, BlockItem, TileLisiumCoil> LISIUM_COIL;
	public static BlockRegistryObjectGroup<BlockKetiumCoil, BlockItem, TileKetiumCoil> KETIUM_COIL;
	public static BlockRegistryObjectGroup<BlockRosiumCoil, BlockItem, TileRosiumCoil> ROSIUM_COIL;
	public static BlockRegistryObjectGroup<BlockMagneticCoil, BlockItem, TileMagneticCoil> MAGNETIC_COIL;
	
	public static BlockRegistryObjectGroup<BlockCropBlower, BlockItem, TileCropBlower> CROP_BLOWER;
	public static BlockRegistryObjectGroup<BlockCropHeater, BlockItem, TileCropHeater> CROP_HEATER;
	public static BlockRegistryObjectGroup<BlockMiner, BlockItem, TileMiner> MINER;

	public static BlockRegistryObjectGroup<BlockHupper, BlockItem, TileHupper> HUPPER;
	public static BlockRegistryObjectGroup<Block, BlockItem, TileEntity> STRUCTURE_BLOCK;
	
	public static void init() {

		// CABLES
		BASIC_PLASMA_CABLE = new BlockRegistryObjectGroup<>("basic_plasma_cable", BlockBasicPlasmaCable::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileBasicPlasmaCable::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);

		BASIC_GENERATOR = new BlockRegistryObjectGroup<>("basic_generator", BlockBasicGenerator::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileBasicGenerator(2000, 20, 1)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		EM_GENERATOR = new BlockRegistryObjectGroup<>("em_generator", BlockEMGenerator::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileEMGenerator(7500, 100, 25)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		// CONVERTOR
		SOLAR_CONVERTOR = new BlockRegistryObjectGroup<>("solar_convertor", BlockSolarConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSolarConvertor(1, 0.5f, 50)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		BASIC_CONVERTOR = new BlockRegistryObjectGroup<>("basic_convertor", BlockBasicConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileBasicConvertor(2, 0.3f, 150)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		ADVANCED_CONVERTOR = new BlockRegistryObjectGroup<>("advanced_convertor", BlockAdvancedConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileAdvancedConvertor(5, 0.5f, 250)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		SUPER_ADVANCED_CONVERTOR = new BlockRegistryObjectGroup<>("super_advanced_convertor", BlockSuperAdvancedConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileSuperAdvancedConvertor(5, 0.5f, 350)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		ULTIMATE_CONVERTOR = new BlockRegistryObjectGroup<>("ultimate_convertor", BlockUltimateConvertor::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileUltimateConvertor(20, 0.95f, 750)).register(BLOCKS, ITEMS, TILE_ENTITIES);

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
		
		IONIZER = new BlockRegistryObjectGroup<>("ionizer", BlockIonizer::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileIonizer(1500, 0.8f)).register(BLOCKS, ITEMS, TILE_ENTITIES);

		FURNACE = new BlockRegistryObjectGroup<>("furnace", BlockFurnace::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileFurnace(500, 0.1f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		CRUSHER = new BlockRegistryObjectGroup<>("crusher", BlockCrusher::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileCrusher(700, 0.2f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		CROP_BLOWER = new BlockRegistryObjectGroup<>("crop_blower", BlockCropBlower::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileCropBlower(500, 0.4f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		CROP_HEATER = new BlockRegistryObjectGroup<>("crop_heater", BlockCropHeater::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileCropHeater(500, 0.4f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		MINER = new BlockRegistryObjectGroup<>("miner", BlockMiner::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				() -> new TileMiner(1500, 0.5f)).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		//STORAGE
		MACHINE_BLOCK = new BlockRegistryObjectGroup<>("machine_block", BlockMachine::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileMachine::new)
				.register(BLOCKS, ITEMS, TILE_ENTITIES);	 
		
		PLASMA_STORAGE = new BlockRegistryObjectGroup<>("plasma_storage", BlockPlasmaStorage::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TilePlasmaStorage::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		PLASMA_INPUT = new BlockRegistryObjectGroup<>("plasma_input", BlockPlasmaInput::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TilePlasmaInput::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		PLASMA_OUTPUT = new BlockRegistryObjectGroup<>("plasma_output", BlockPlasmaOutput::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TilePlasmaOutput::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);	
		
		ITEM_INPUT = new BlockRegistryObjectGroup<>("item_input", BlockItemInput::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileItemInput::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);	
		
		LISIUM_COIL = new BlockRegistryObjectGroup<>("lisium_coil", BlockLisiumCoil::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				TileLisiumCoil::new).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		KETIUM_COIL = new BlockRegistryObjectGroup<>("ketium_coil", BlockKetiumCoil::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				TileKetiumCoil::new).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		ROSIUM_COIL = new BlockRegistryObjectGroup<>("rosium_coil", BlockRosiumCoil::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				TileRosiumCoil::new).register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		MAGNETIC_COIL = new BlockRegistryObjectGroup<>("magnetic_coil", BlockMagneticCoil::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)),
				TileMagneticCoil::new).register(BLOCKS, ITEMS, TILE_ENTITIES);	


		//OTHER
		HUPPER = new BlockRegistryObjectGroup<>("hupper", BlockHupper::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileHupper::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		STRUCTURE_BLOCK = addTileEntityNoItem("structure_block", BlockStructureBase::new, TileStructureBase::new);

	}

}
