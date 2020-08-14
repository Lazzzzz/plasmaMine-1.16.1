package laz.plasmine.content.block;

import laz.plasmine.api.Constante;
import laz.plasmine.registry.init.PMBlocksInit;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ConveyorBelt extends Block implements ICanWrench {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty LEFT = BooleanProperty.create("left");
	public static final BooleanProperty RIGHT = BooleanProperty.create("right");

	VoxelShape SHAPE = VoxelShapes.create(0, 0, 0, 1, 0.25, 1);

	public ConveyorBelt() {
		super(Properties.from(Blocks.BLACK_WOOL));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
				.with(LEFT, Boolean.valueOf(false)).with(RIGHT, Boolean.valueOf(false)));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, LEFT, RIGHT);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		Direction dir = worldIn.getBlockState(pos).get(FACING);
		Vector3d motion = DirectionUtils.getMotion(dir, Constante.CONVEYOR_SPEED);
		double dif = 0;
		if (dir == Direction.EAST || dir == Direction.WEST) {
			dif = pos.getZ() + 0.5f - entityIn.getPosZ();
			motion = motion.add(0, 0, dif / 10);
		} else {
			dif = pos.getX() + 0.5f - entityIn.getPosX();
			motion = motion.add(dif / 10, 0, 0);
		}
		entityIn.setMotion(motion);
		
		if (entityIn instanceof ItemEntity) ((ItemEntity) entityIn).setNoDespawn();
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		Direction dir = stateIn.get(FACING);
		if (dir == Direction.EAST || dir == Direction.WEST) {
			if (facingState.getBlock() == PMBlocksInit.CONVEYOR.get()) {
				if (currentPos.getZ() - facingPos.getZ() > 0) worldIn.setBlockState(currentPos,	stateIn.with(LEFT, true), 3);
				else if (currentPos.getZ() - facingPos.getZ() < 0) worldIn.setBlockState(currentPos,	stateIn.with(RIGHT, true), 3);
			}else{
				if (currentPos.getZ() - facingPos.getZ() > 0) worldIn.setBlockState(currentPos,	stateIn.with(LEFT, false), 3);
				else if (currentPos.getZ() - facingPos.getZ() < 0) worldIn.setBlockState(currentPos,	stateIn.with(RIGHT, false), 3);
			}
		} else {
			if (facingState.getBlock() == PMBlocksInit.CONVEYOR.get()) {
				if (currentPos.getX() - facingPos.getX() > 0) worldIn.setBlockState(currentPos,	stateIn.with(LEFT, true), 3);
				else if (currentPos.getX() - facingPos.getX() < 0) worldIn.setBlockState(currentPos,	stateIn.with(RIGHT, true), 3);
			}else{
				if (currentPos.getX() - facingPos.getX() > 0) worldIn.setBlockState(currentPos,	stateIn.with(LEFT, false), 3);
				else if (currentPos.getX() - facingPos.getX() < 0) worldIn.setBlockState(currentPos,	stateIn.with(RIGHT, false), 3);
			}
		}
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
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

	@Override
	public void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		if (!world.isRemote)
			world.setBlockState(pos, state.with(FACING, state.get(FACING).rotateY()));
	}
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		// TODO Auto-generated method stub
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
	}
}
