package laz.plasmine.content.block;

import laz.plasmine.api.Constante;
import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.registry.init.PMBlocksInit;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ConveyorBeltUp extends BlockRotationBase implements ICanWrench {

	public static final BooleanProperty TOP = BooleanProperty.create("top");

	VoxelShape SHAPE_NORTH = VoxelShapes.or(VoxelShapes.create(0, 0, 0, 1, 0.25, 1),
			VoxelShapes.create(0, 0, 0, 1, 1, 0.25));
	VoxelShape SHAPE_SOUTH = VoxelShapes.or(VoxelShapes.create(0, 0, 0, 1, 0.25, 1),
			VoxelShapes.create(0, 0, 0.75, 1, 1, 1));
	VoxelShape SHAPE_EAST = VoxelShapes.or(VoxelShapes.create(0, 0, 0, 1, 0.25, 1),
			VoxelShapes.create(0.75, 0, 0, 1, 1, 1));
	VoxelShape SHAPE_WEST = VoxelShapes.or(VoxelShapes.create(0, 0, 0, 1, 0.25, 1),
			VoxelShapes.create(0, 0, 0, 0.25, 1, 1));

	VoxelShape TOP_NORTH = VoxelShapes.create(0, 0, 0, 1, 1, 0.25);
	VoxelShape TOP_EAST = VoxelShapes.create(0.75, 0, 0, 1, 1, 1);
	VoxelShape TOP_SOUTH = VoxelShapes.create(0, 0, 0.75, 1, 1, 1);
	VoxelShape TOP_WEST = VoxelShapes.create(0, 0, 0, 0.25, 1, 1);

	public ConveyorBeltUp() {
		super(Properties.from(Blocks.BLACK_WOOL));
		this.setDefaultState(
				this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TOP, Boolean.valueOf(false)));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, TOP);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		moveIt(worldIn, pos, entityIn);
		if (entityIn.getPosY() - pos.getY() == 1) {
			BlockState state = worldIn.getBlockState(pos);
			if (worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
				BlockState next = worldIn.getBlockState(DirectionUtils.getPosDirection(pos.up(), state.get(FACING)));
				if (next == Blocks.AIR.getDefaultState()
						|| next.getBlock() == PMBlocksInit.CONVEYOR.getDefaultState().getBlock()) {
					Vector3d newPos = DirectionUtils.getMotion(state.get(FACING), 0.3f);
					entityIn.setPositionAndUpdate(entityIn.getPosX() + newPos.x, entityIn.getPosY() + 0.25f,
							entityIn.getPosZ() + newPos.z);
				}
			}
		}
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		Direction dir = worldIn.getBlockState(pos).get(FACING);
		Vector3d motion = DirectionUtils.getMotion(dir, Constante.CONVEYOR_SPEED);
		boolean goingUp = false;
		double dif = 0;
		if (dir == Direction.EAST || dir == Direction.WEST) {
			dif = pos.getZ() + 0.5f - entityIn.getPosZ();
			motion = motion.add(0, 0, dif / 10);
			if (dir == Direction.EAST) {
				if (entityIn.getPosX() - pos.getX() == 0.625)
					goingUp = true;
			} else {
				if (entityIn.getPosX() - pos.getX() == 0.375)
					goingUp = true;
			}
		} else {
			dif = pos.getX() + 0.5f - entityIn.getPosX();
			motion = motion.add(dif / 10, 0, 0);
			if (dir == Direction.SOUTH) {
				if (entityIn.getPosZ() - pos.getZ() == 0.625)
					goingUp = true;
			} else {
				if (entityIn.getPosZ() - pos.getZ() == 0.375)
					goingUp = true;
			}
		}

		if (goingUp) {
			entityIn.func_230245_c_(false);
			motion = new Vector3d(motion.x, Constante.CONVEYOR_SPEED, motion.z);
		}
		entityIn.setMotion(motion);

	}

	private void moveIt(World worldIn, BlockPos pos, Entity entityIn) {

	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return getDirectionShape(state);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getDirectionShape(state);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return getDirectionShape(state);
	}

	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return getDirectionShape(state);
	}

	private VoxelShape getDirectionShape(BlockState state) {
		Direction dir = state.get(FACING);
		boolean top = state.get(TOP);
		if (dir == Direction.EAST) {
			if (!top)
				return SHAPE_EAST;
			else
				return TOP_EAST;
		} else if (dir == Direction.WEST) {
			if (!top)
				return SHAPE_WEST;
			else
				return TOP_WEST;
		} else if (dir == Direction.SOUTH)
			if (!top)
				return SHAPE_SOUTH;
			else
				return TOP_SOUTH;
		if (!top)
			return SHAPE_NORTH;
		else
			return TOP_NORTH;
	}

	@Override
	public void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				if (state.get(TOP)) world.setBlockState(pos, state.with(TOP, !state.get(TOP)));
				else world.setBlockState(pos, state.with(TOP, true));
				
			}else
				world.setBlockState(pos, state.with(FACING, state.get(FACING).rotateY()));
		}
	}

}
