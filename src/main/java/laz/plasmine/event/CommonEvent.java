package laz.plasmine.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CommonEvent {
	
	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity player = event.getPlayer();
		World world = player.getEntityWorld();
		BlockPos pos = event.getPos();
		WrenchEvent.update(world, player, pos, event.getFace());
	}
	
}
