package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.api.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockSedimentExtractor extends BlockHeatMachineBase {

	public BlockSedimentExtractor() {
		super(500, 0.4f);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSedimentExtractor(maxCelcius, thermo);
	}
	
}
