package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.util.Direction;

public class TileSolarConvertor extends TileConvertorBase {

	public TileSolarConvertor(int rate, float efficiency, int temp) {
		super(PMTilesInit.SOLAR_CONVERTOR.getTileEntityType(), 100, rate, efficiency, temp);
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return false;
	}
	
	@Override
	public void tick() {
		livingtick++;
		if (!world.isRemote) {
			if (world.isDaytime() && world.canBlockSeeSky(pos)) plasmaHelper.addPlasma(1);
		}
		super.tick();
	}
}
