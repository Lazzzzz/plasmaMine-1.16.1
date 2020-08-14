package laz.plasmine.content.tiles.heat.cropblower;

import java.util.List;

import laz.plasmine.base.heat.BlockHeatMachineBase;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.DirectionUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class TileCropBlower extends TileHeatMachineBase {

	public TileCropBlower(int maxCelcius, float thermo) {
		super(PMTilesInit.CROP_BLOWER.getTileEntityType(), maxCelcius, thermo, 0);
	}

	@Override
	public void onWorking() {
		Direction dir = world.getBlockState(pos).get(BlockHeatMachineBase.FACING);
		if (livingtick % 20 == 0) {
			for (int i = -2; i < 3; i++) {
				for (int j = -2; j < 3; j++) {
					BlockPos p = DirectionUtils.getPosFromRot(pos, dir, i, j, 3);
					BlockState state = world.getBlockState(p);
					if (state.getBlock() instanceof CropsBlock) {
						if (((CropsBlock) state.getBlock()).isMaxAge(state)) {
							world.destroyBlock(p, true);
							world.setBlockState(p, state.with(CropsBlock.AGE, 0));
						}
					}
				}
			}
		}

		pushEntity(dir);
	}

	private BlockPos[] getCorner(Direction dir) {
		if (dir == Direction.EAST)
			return new BlockPos[] { pos.add(1, 0, -2), pos.add(6, 1, 3) };
		else if (dir == Direction.WEST)
			return new BlockPos[] { pos.add(0, 0, -2), pos.add(-5, 1, 3) };
		else if (dir == Direction.NORTH)
			return new BlockPos[] { pos.add(-2, 0, 0), pos.add(3, 1, -5) };

		return new BlockPos[] { pos.add(-2, 0, 1), pos.add(3, 1, 6) };
	}

	private void pushEntity(Direction dir) {
		BlockPos[] corner = getCorner(dir);
		List<Entity> entitys = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(corner[0], corner[1]));
		for (int i = 0; i < entitys.size(); i++) {
			Entity entity = entitys.get(i);

			if (entity.func_233570_aj_()) {
				Vector3d speed = DirectionUtils.getMotion(dir, 0.2f);
				entity.setMotion(entity.getMotion().x + speed.x, entity.getMotion().y, entity.getMotion().z + speed.z);
			}
		}
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 5;
	}

}
