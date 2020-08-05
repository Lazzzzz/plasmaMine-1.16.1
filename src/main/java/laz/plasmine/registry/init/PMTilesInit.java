package laz.plasmine.registry.init;

import laz.plasmine.content.test.CableTest;
import laz.plasmine.content.test.TileCableTest;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import laz.plasmine.registry.PMRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.tileentity.TileEntity;

public class PMTilesInit {
	
	public static BlockRegistryObjectGroup<Block, BlockItem, TileCableTest> CABLE;//
	
	public static void init() {
		CABLE = PMRegistry.addTileEntity("cable", CableTest::new, TileCableTest::new);
		
	}

}
