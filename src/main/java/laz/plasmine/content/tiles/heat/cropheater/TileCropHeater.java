package laz.plasmine.content.tiles.heat.cropheater;

import laz.plasmine.base.heat.BlockHeatMachineBase;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TileCropHeater extends TileHeatMachineBase {

	public TileCropHeater(int maxCelcius, float thermo) {
		super(PMTilesInit.CROP_HEATER.getTileEntityType(), maxCelcius, thermo, 0);
	}

	@Override
	public void onWorking() {

		setWorkingState(world, pos, world.getBlockState(pos), true);

		if (livingtick % Math.max(1, (int) (100 - speedFactor() * 10)) == 0) {
			for (int i = -2; i < 3; i++) {
				for (int j = -2; j < 3; j++) {
					if (world.rand.nextBoolean()) {
						BlockPos plant = pos.add(i,2,j);
						BlockState state = world.getBlockState(plant);
						if (state.getBlock() instanceof CropsBlock) {
							if (!((CropsBlock) state.getBlock()).isMaxAge(state)) {
								world.setBlockState(plant, state.with(CropsBlock.AGE, state.get(CropsBlock.AGE) + 1));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 5;
	}

}
