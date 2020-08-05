package laz.plasmine.registry.init;

import laz.plasmine.content.test.CableTest;
import laz.plasmine.content.test.TileCableTest;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import laz.plasmine.registry.PMRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.registry.PMRegistry.*;

public class PMTilesInit {
	
	public static BlockRegistryObjectGroup<CableTest, BlockItem, TileCableTest> CABLE;
	
	public static void init() {
		CABLE = new BlockRegistryObjectGroup<>("cable", CableTest::new, (block)-> new BlockItem(block,new Item.Properties().group(ITEM_GROUP)),TileCableTest::new).register(BLOCKS,ITEMS,TILE_ENTITIES);
		
	}

}
