package laz.plasmine.util.interfaces;

import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.base.convertor.BlockConvertorBase;
import laz.plasmine.util.DirectionUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICanWrench {

	default void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		if (player.isSneaking()) {
			world.addEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f,
					new ItemStack(world.getBlockState(pos).getBlock(), 1)));
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof IInventory)
				InventoryHelper.dropInventoryItems(world, pos, ((IInventory) tile));
			if (tile instanceof ISlave && ((ISlave) tile).isBind()) {
				IMaster master = (IMaster) world.getTileEntity(((ISlave) tile).getBlockPosMaster());
				((ISlave) tile).sendMasterDestroy(pos, master);
			}
			if (tile instanceof IMaster) {
				Direction d = state.get(BlockRotationBase.FACING).getOpposite();
				BlockPos p = DirectionUtils.getPosDirection(pos, dir, 2).down();
				((IMaster) tile).sendStructureUnBind(p, d);
			}
			if (tile != null)
				tile.remove();
			world.removeBlock(pos, false);
		} else {
			if (dir != Direction.UP && dir != Direction.DOWN && !(state.getBlock() instanceof ICable)) {
				if (!player.isSneaking())
					world.setBlockState(pos, state.with(BlockConvertorBase.FACING, dir));
			}
		}
	}

}
