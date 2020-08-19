package laz.plasmine.content.tiles.storage;

import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IMaster;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockPlasmaStorage extends BlockRotationBase {

	public static final IntegerProperty STORAGE = IntegerProperty.create("storage", 0, 10);
	
	public BlockPlasmaStorage() {
		super(Properties.from(Blocks.IRON_BLOCK.getBlock()));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(STORAGE, 0));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, STORAGE);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TilePlasmaStorage();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && player.getHeldItemMainhand().getItem() != PMItemsInit.WRENCH.get()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TilePlasmaStorage) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (TilePlasmaStorage) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;

	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			IMaster tile = (IMaster) worldIn.getTileEntity(pos);
			tile.sendStructureUnBind(DirectionUtils.getPosDirection(pos, state.get(FACING).getOpposite()));
		}
		
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
}
