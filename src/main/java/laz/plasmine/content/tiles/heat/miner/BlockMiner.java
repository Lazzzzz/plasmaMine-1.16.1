package laz.plasmine.content.tiles.heat.miner;

import java.util.List;

import laz.plasmine.base.heat.BlockHeatMachineBase;
import laz.plasmine.base.multiblock.structure.TileStructureBase;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.DirectionUtils;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMiner extends BlockHeatMachineBase {
	VoxelShape SHAPE = VoxelShapes.create(0.0001f, 0.0001f, 0.0001f, 1f-0.0001f, 1f-0.0001f, 1f-0.0001f);
	
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
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (!worldIn.isRemote) {
			if (!checkSpace(worldIn, pos, state))
				worldIn.destroyBlock(pos, true);
			else
				placeStructure(worldIn, pos, state);
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			if (state.getBlock() != newState.getBlock()) {
				destroyStructure(worldIn, pos, state);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	
	
	private void placeStructure(World worldIn, BlockPos pos, BlockState state) {
		BlockPos center = DirectionUtils.getPosDirection(pos, state.get(FACING).getOpposite());
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					BlockPos p = center.add(i, k, j);
					if (!BlockPosUtil.areSame(p, pos)) {
						worldIn.setBlockState(p, PMTilesInit.STRUCTURE_BLOCK.getDefaultState());
						((TileStructureBase) worldIn.getTileEntity(p)).bindToMaster(pos);
					}
				}
			}
		}
	}

	private void destroyStructure(World worldIn, BlockPos pos, BlockState state) {
		BlockPos center = DirectionUtils.getPosDirection(pos, state.get(FACING).getOpposite());
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					BlockPos p = center.add(i, k, j);
					if (!BlockPosUtil.areSame(p, pos)) {
						TileEntity tile = worldIn.getTileEntity(p);
						if (tile instanceof TileStructureBase) {
							if (BlockPosUtil.areSame(((TileStructureBase) tile).getBlockPosMaster(), pos))
								worldIn.destroyBlock(p, false);
						}
					}

				}
			}
		}
	}

	private boolean checkSpace(World worldIn, BlockPos pos, BlockState state) {
		BlockPos center = DirectionUtils.getPosDirection(pos, state.get(FACING).getOpposite());
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					if (!BlockPosUtil.areSame(center.add(i, k, j), pos)
							&& worldIn.getBlockState(center.add(i, k, j)) != Blocks.AIR.getDefaultState())
						return false;
				}
			}
		}

		return true;

	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("WIP"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
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
