package laz.plasmine.event;

import java.util.Random;

import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMSoundInit;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WrenchEvent {

	static Random RANDOM = new Random();

	public static void update(World world, PlayerEntity player, BlockPos pos, Direction dir) {
		if (player.getHeldItemMainhand().getItem() == PMItemsInit.WRENCH.get()) {
			BlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof ICanWrench) {
				if (!world.isRemote) {
					((ICanWrench) state.getBlock()).onWrenchAction(player, world, pos, state, dir);
					world.playSound(null, pos, PMSoundInit.WRENCH_USE.get(), SoundCategory.MASTER, 0.5f, 1f);
				}
			}
		}
	}

}
