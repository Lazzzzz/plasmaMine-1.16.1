package laz.plasmine.event;

import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMSoundInit;
import laz.plasmine.util.ICanWrench;
import net.minecraft.block.BlockState;
import net.minecraft.command.impl.PlaySoundCommand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WrenchEvent {

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
