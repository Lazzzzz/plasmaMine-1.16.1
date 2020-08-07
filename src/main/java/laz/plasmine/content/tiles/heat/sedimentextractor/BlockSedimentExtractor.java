package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.content.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockSedimentExtractor extends BlockHeatMachineBase {

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSedimentExtractor();
	}
	
}
