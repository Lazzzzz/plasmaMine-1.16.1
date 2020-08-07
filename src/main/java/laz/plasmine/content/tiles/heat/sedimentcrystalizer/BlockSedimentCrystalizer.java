package laz.plasmine.content.tiles.heat.sedimentcrystalizer;

import laz.plasmine.content.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockSedimentCrystalizer extends BlockHeatMachineBase {

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSedimentCrystalizer();
	}
	
}
