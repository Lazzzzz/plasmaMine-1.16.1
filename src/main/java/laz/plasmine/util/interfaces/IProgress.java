package laz.plasmine.util.interfaces;

import java.util.List;

import laz.plasmine.network.PacketHandler;
import laz.plasmine.network.packets.ProgressPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public interface IProgress {
	
	default void sendProgress(World world, BlockPos pos, double amount, double maxAmount) {
		List<? extends PlayerEntity> players = world.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			PacketHandler.INSTANCE.sendTo(new ProgressPacket(pos, maxAmount, amount),
					((ServerPlayerEntity) players.get(i)).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	void receiveProgress(double amount, double maxAmount);
	
}
