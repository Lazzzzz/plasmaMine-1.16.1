package laz.plasmine.util.interfaces;

import laz.plasmine.api.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
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
			if (tile instanceof IInventory)	dropInventoryItems(world, pos, ((IInventory) tile));
			if (tile != null) tile.remove();
			world.removeBlock(pos, false);
		} else {
			if (dir != Direction.UP && dir != Direction.DOWN && !(state.getBlock() instanceof ICable)) {
				if (!player.isSneaking())
					world.setBlockState(pos, state.with(BlockConvertorBase.FACING, dir));
			}
		}
	}

	default void dropInventoryItems(World worldIn, BlockPos pos, IInventory inventory) {
		for (int i = 0; i < inventory.getSizeInventory(); ++i) {
			spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
		}
	}

	default void spawnItemStack(World worldIn, double x, double y, double z, ItemStack stack) {
		double d0 = (double) EntityType.ITEM.getWidth();
		double d1 = 1.0D - d0;
		double d2 = d0 / 2.0D;
		double d3 = Math.floor(x) + worldIn.rand.nextDouble() * d1 + d2;
		double d4 = Math.floor(y) + worldIn.rand.nextDouble() * d1;
		double d5 = Math.floor(z) + worldIn.rand.nextDouble() * d1 + d2;

		while (!stack.isEmpty()) {
			ItemEntity itementity = new ItemEntity(worldIn, d3, d4, d5, stack.split(worldIn.rand.nextInt(21) + 10));
			float f = 0.05F;
			itementity.setMotion(worldIn.rand.nextGaussian() * (double) 0.05F,
					worldIn.rand.nextGaussian() * (double) 0.05F + (double) 0.2F, worldIn.rand.nextGaussian() * (double) 0.05F);
			worldIn.addEntity(itementity);
		}

	}
	
}
