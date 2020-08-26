package laz.plasmine.content.tiles.heat.miner;

import java.util.List;

import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.interfaces.IMaster;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TileMiner extends TileHeatMachineBase implements IMaster {

	public TileMiner(float maxCelcius, float thermo) {
		super(PMTilesInit.MINER.getTileEntityType(), maxCelcius, thermo, 12);
	}

	@Override
	public List<BlockPos> getConnectedBlock() {
		return null;
	}

	@Override
	public boolean checkIsFormed() {
		return false;
	}

	@Override
	public void sendStructureBind(BlockPos p, Direction dir) {	
	}

	@Override
	public void sendStructureUnBind(BlockPos p, Direction dir) {
	}
	
	@Override
	public void receiveDestroy(BlockPos pos, List<BlockPos> connectedBlock) {
		IMaster.super.receiveDestroy(pos, connectedBlock);
	}	
}