package laz.plasmine.base.cable;

import static laz.plasmine.api.Constante.MACHINE_PARTICLES;

import java.util.ArrayList;
import java.util.Random;

import laz.plasmine.base.plasma.BlockPlasmaMachineBase;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockCableBase extends BlockPlasmaMachineBase implements ICanWrench {

	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty DOWN = SixWayBlock.DOWN;

	public static final BooleanProperty WORKING = BooleanProperty.create("working");

	VoxelShape SHAPE = VoxelShapes.create(0.25, 0.25, 0.25, 1 - 0.25, 1 - 0.25, 1 - 0.25);

	public BlockCableBase() {
		super();

		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false))
				.with(WORKING, Boolean.valueOf(false)));

	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(WORKING))
			spawnParticles(stateIn, worldIn, pos, rand);
	}

	public void spawnParticles(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		int proba = 50;

		if (stateIn.get(UP))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(MACHINE_PARTICLES, pos.getX() + rand.nextFloat(), pos.getY() + 1,
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
		if (stateIn.get(DOWN))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(MACHINE_PARTICLES, pos.getX() + rand.nextFloat(), pos.getY(),
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
		if (stateIn.get(NORTH))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(MACHINE_PARTICLES, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(),
						pos.getZ(), 0, 0, 0);
		if (stateIn.get(SOUTH))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(MACHINE_PARTICLES, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(),
						pos.getZ() + 1, 0, 0, 0);
		if (stateIn.get(WEST))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(MACHINE_PARTICLES, pos.getX(), pos.getY() + rand.nextFloat(),
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
		if (stateIn.get(EAST))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(MACHINE_PARTICLES, pos.getX() + 1, pos.getY() + rand.nextFloat(),
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
		if (!world.isRemote()) {
			if (world.getBlockState(neighbor) == Blocks.AIR.getDefaultState()) {
				TileCableBase tile = (TileCableBase) world.getTileEntity(pos);
				tile.updateNetwork(null, new ArrayList<BlockPos>(), null, 3);
			}
		}
		super.onNeighborChange(state, world, pos, neighbor);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST, WORKING);
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isRemote) {
			if (state.get(WORKING))
				entityIn.attackEntityFrom(new DamageSource("heat"), 3);
		}
	}

	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}

}
