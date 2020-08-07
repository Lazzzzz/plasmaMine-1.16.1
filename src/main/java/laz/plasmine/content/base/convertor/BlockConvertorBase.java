package laz.plasmine.content.base.convertor;

import laz.plasmine.util.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockConvertorBase extends Block implements ICanWrench {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public BlockConvertorBase() {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
		
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		ICanWrench.super.onWrenchAction(player, world, pos, state, dir);
		if (dir != Direction.UP && dir != Direction.DOWN) {
			if (!player.isSneaking()) world.setBlockState(pos, state.with(FACING, dir));
		}
	}
	
}
