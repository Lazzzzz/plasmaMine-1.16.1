package laz.plasmine.content.base.cable;

import java.util.Random;

import laz.plasmine.content.base.machine.PlasmaMachineBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CableBase extends PlasmaMachineBlockBase {

	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty DOWN = SixWayBlock.DOWN;

	VoxelShape SHAPE = VoxelShapes.create(0.125, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);

	public CableBase() {
		super();

		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false))
				.with(UP, Boolean.valueOf(false)));

	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		spawnParticles(stateIn, worldIn, pos, rand);
	}

	public void spawnParticles(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		BasicParticleType particle = ParticleTypes.CLOUD;
		int proba = 50;
		
		if (stateIn.get(UP))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(particle, pos.getX() + rand.nextFloat(), pos.getY() + 1,
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
		if (stateIn.get(DOWN))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(particle, pos.getX() + rand.nextFloat(), pos.getY(),
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
		if (stateIn.get(NORTH))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(particle, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(),
						pos.getZ(), 0, 0, 0);
		if (stateIn.get(SOUTH))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(particle, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(),
						pos.getZ() + 1, 0, 0, 0);
		if (stateIn.get(WEST))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(particle, pos.getX(), pos.getY() + rand.nextFloat(),
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
		if (stateIn.get(EAST))
			if (rand.nextInt(proba) == 0)
				worldIn.addParticle(particle, pos.getX() + 1, pos.getY() + rand.nextFloat(),
						pos.getZ() + rand.nextFloat(), 0, 0, 0);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST);
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isRemote) {
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
