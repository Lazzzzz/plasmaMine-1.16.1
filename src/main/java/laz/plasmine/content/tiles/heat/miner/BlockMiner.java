package laz.plasmine.content.tiles.heat.miner;

import laz.plasmine.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMiner extends BlockHeatMachineBase {
 		
	public BlockMiner() {
		super(1500, 0.5f);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMiner(this.maxCelcius, this.thermo);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		// TODO Auto-generated method stub
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return getShapeFromState(state);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getShapeFromState(state);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return getShapeFromState(state);
	}

	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return getShapeFromState(state);
	}

	
	private VoxelShape getShapeFromState(BlockState state) {
		VoxelShape SHAPE_NORTH = VoxelShapes.create(0.125, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);
		VoxelShape SHAPE_SOUTH = VoxelShapes.create(0.125, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);
		VoxelShape SHAPE_EAST = VoxelShapes.create(0.125, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);
		VoxelShape SHAPE_WEST = VoxelShapes.create(-1, 0, 0, 2, 2, 3);
		
		if (state.get(FACING) == Direction.NORTH) return SHAPE_NORTH;
		if (state.get(FACING) == Direction.SOUTH) return SHAPE_SOUTH;
		if (state.get(FACING) == Direction.EAST) return SHAPE_EAST;
		return SHAPE_WEST;
	}
	
}
