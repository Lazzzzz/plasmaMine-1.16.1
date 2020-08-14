package laz.plasmine.content.tiles.convertor;

import static laz.plasmine.api.PlasmaToHeatConvertion.transformPlasmaToHeat;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMSoundInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;

public class TileSolarConvertor extends TileConvertorBase {

	public TileSolarConvertor(int rate, float efficiency) {
		super(PMTilesInit.SOLAR_CONVERTOR.getTileEntityType(), 100, rate, efficiency);
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return false;
	}
	
	@Override
	public void tick() {
		if (!world.isRemote) {
			if (world.isDaytime()) plasmaHelper.addPlasma(1);
			
			boolean isWorking = plasmaHelper.getCapacity() > 0 && world.isBlockPowered(pos);
			setWorkingState(world, pos, world.getBlockState(pos), isWorking);
			float heat = 0;
			if (isTileConnect() && world.isBlockPowered(pos)) {
				HeatHelper helper = heatAround();
				if (helper != null && helper.getCelcius() < 50) heat = transformPlasmaToHeat(helper, plasmaHelper.removePlasma(amountToConvertPerTick), efficiency);
				helper.addCelcius(heat);
				if (isWorking) {
					heatAround();
					if (world.rand.nextInt(100) == 0) world.playSound(null, pos, PMSoundInit.CONVERTOR_RUNNING.get(), SoundCategory.MASTER, 0.5f, 1f);
				}
			}
		}

	}
	
}
