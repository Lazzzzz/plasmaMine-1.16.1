package laz.plasmine.content.tiles.heat.cropblower;

import java.util.Random;

import laz.plasmine.api.Constante;
import laz.plasmine.api.base.heat.BlockHeatMachineBase;
import laz.plasmine.util.direction.DirectionUtils;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCropBlower extends BlockHeatMachineBase {

	public BlockCropBlower() {
		super(500, 0.4f);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileCropBlower(maxCelcius, thermo);
	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(BlockHeatMachineBase.WORKING)) {
			particles(stateIn, worldIn, pos, rand);
		}
		super.animateTick(stateIn, worldIn, pos, rand);
	}

	private void particles(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		for (int i = -2; i < 3; i++) {
			Direction dir = stateIn.get(BlockHeatMachineBase.FACING);
			BlockPos p = DirectionUtils.getPosFromRot(pos, dir, i, 1);
			Vector3d motion = DirectionUtils.getMotion(dir, 0.25f);
			worldIn.addParticle(Constante.MACHINE_PARTICLES, p.getX() - 0.5f, p.getY() + 0.5f, p.getZ() - 0.5f,
					motion.x, motion.y, motion.z);
		}
	}

}
