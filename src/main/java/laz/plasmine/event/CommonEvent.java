package laz.plasmine.event;

import java.util.Random;

import laz.plasmine.registry.init.PMBlocksInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CommonEvent {

	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity player = event.getPlayer();
		World world = player.getEntityWorld();
		BlockPos pos = event.getPos();
		if (event.getHand() == Hand.MAIN_HAND && WrenchEvent.update(world, player, pos, event.getFace())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onBlockDestroy(BlockEvent.BreakEvent event) {
		System.out.println("d");
		if (event.getState().getBlock() == Blocks.GRASS.getBlock() && !event.getWorld().isRemote()) {
			Random rand = event.getWorld().getRandom();
			if (rand.nextInt(10) == 0) {
				event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(),
						event.getPos().getY(), event.getPos().getZ(), new ItemStack(PMBlocksInit.RAPESEED_CROP.get())));
			}
		}
	}

}
