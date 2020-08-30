package laz.plasmine.base.multiblock.input;

import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.util.interfaces.ICanWrench;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockItemInput extends BlockRotationBase implements ICanWrench {

	public BlockItemInput() {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileItemInput();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && player.getHeldItemMainhand().getItem() != PMItemsInit.WRENCH.get()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileItemInput) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (TileItemInput) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;

	}
	
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			ISlave tile = (ISlave) worldIn.getTileEntity(pos);
			if (tile != null && tile.isBind() && state.getBlock() != newState.getBlock()) {
				TileEntity master = worldIn.getTileEntity(tile.getBlockPosMaster());
				if (master instanceof IMaster) {
					tile.sendMasterDestroy(pos, (IMaster) master);
				}
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	

}
