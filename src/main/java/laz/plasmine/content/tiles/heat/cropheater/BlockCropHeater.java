package laz.plasmine.content.tiles.heat.cropheater;

import java.util.Random;

import laz.plasmine.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCropHeater extends BlockHeatMachineBase {

	public BlockCropHeater() {
		super(500, 0.4f);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileCropHeater(maxCelcius, thermo);
	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(BlockHeatMachineBase.WORKING)) {
			particles(stateIn, worldIn, pos, rand);
		}
		super.animateTick(stateIn, worldIn, pos, rand);
	}

	private void particles(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}

}
