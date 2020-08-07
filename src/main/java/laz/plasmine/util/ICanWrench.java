package laz.plasmine.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICanWrench {

	default void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		if (player.isSneaking()) {
			world.addEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f, new ItemStack(world.getBlockState(pos).getBlock(), 1)));
			world.removeBlock(pos, false);
		}
	}

}
