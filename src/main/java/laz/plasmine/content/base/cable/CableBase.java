package laz.plasmine.content.base.cable;

import laz.plasmine.content.base.machine.PlasmaMachineBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CableBase extends PlasmaMachineBlockBase {

	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty DOWN = SixWayBlock.DOWN;

	public CableBase() {
		super();

		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false))
				.with(UP, Boolean.valueOf(false)));

	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
}
