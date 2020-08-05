package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.addTileEntity;

import laz.plasmine.content.base.PlasmaMachineBlockBase;
import laz.plasmine.content.base.TilePlasmaMachineBase;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.tileentity.TileEntity;

public class PMTilesInit {

	public static BlockRegistryObjectGroup<Block, BlockItem, TileEntity> TEST;

	public static void init() {

		TEST = addTileEntity("test", () -> new PlasmaMachineBlockBase(TEST.getTileEntityType(), 1000, 10, 1, 20, true),
				() -> new TilePlasmaMachineBase(TEST.getTileEntityType(), 1000, 10, 1, 20, true));
	}

}