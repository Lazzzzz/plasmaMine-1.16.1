package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.addTileEntity;

import laz.plasmine.content.test.TestBlock;
import laz.plasmine.content.test.TestMachine;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.tileentity.TileEntity;

public class PMTilesInit {

	public static BlockRegistryObjectGroup<Block, BlockItem, TileEntity> TEST;

	public static void init() {

		TEST = addTileEntity("test", TestBlock::new, TestMachine::new);
	}

}
