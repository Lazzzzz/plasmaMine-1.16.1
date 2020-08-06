package laz.plasmine.registry.init;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.registry.PMRegistry.BLOCKS;
import static laz.plasmine.registry.PMRegistry.ITEMS;
import static laz.plasmine.registry.PMRegistry.TILE_ENTITIES;

import laz.plasmine.content.cable.CableT1;
import laz.plasmine.content.cable.TileCableT1;
import laz.plasmine.content.generator.BasicGenerator;
import laz.plasmine.content.generator.TileBasicGenerator;
import laz.plasmine.content.machine.BasicMachine;
import laz.plasmine.content.machine.TileBasicMachine;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class PMTilesInit {

	public static BlockRegistryObjectGroup<CableT1, BlockItem, TileCableT1> PLASMA_CABLE_T1;
	public static BlockRegistryObjectGroup<BasicGenerator, BlockItem, TileBasicGenerator> BASIC_GENERATOR;
	public static BlockRegistryObjectGroup<BasicMachine, BlockItem, TileBasicMachine> BASIC_MACHINE;
	
	public static void init() {
		PLASMA_CABLE_T1 = new BlockRegistryObjectGroup<>("plasma_pipe_t1", CableT1::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileCableT1::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		BASIC_GENERATOR = new BlockRegistryObjectGroup<>("basic_generator", BasicGenerator::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileBasicGenerator::new)
						.register(BLOCKS, ITEMS, TILE_ENTITIES);
		
		BASIC_MACHINE = new BlockRegistryObjectGroup<>("basic_machine", BasicMachine::new,
				(block) -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP)), TileBasicMachine::new)
				.register(BLOCKS, ITEMS, TILE_ENTITIES);

	}

}
