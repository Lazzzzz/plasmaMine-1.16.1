package laz.plasmine.network.packets;

import java.util.function.Supplier;

import laz.plasmine.util.interfaces.IProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class ProgressPacket {
	private BlockPos pos;
	private double max;
	private double amount;
	
	public ProgressPacket(BlockPos pos, double max, double amount) {
		this.pos = pos;
		this.max = max;
		this.amount = amount;
	}

	public static ProgressPacket decode(PacketBuffer buf) {
		ProgressPacket packet = new ProgressPacket(buf.readBlockPos(), buf.readDouble(), buf.readDouble());
		return packet;
	}

	public static void encode(ProgressPacket packet, PacketBuffer buf) {
		buf.writeBlockPos(packet.pos);
		buf.writeDouble(packet.max);
		buf.writeDouble(packet.amount);
	}

	public static void handle(ProgressPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientWorld world = Minecraft.getInstance().world;
			IProgress tile = (IProgress) world.getTileEntity(packet.pos);
			if (tile != null)
				tile.receiveProgress(packet.amount, packet.max);
		});

		ctx.get().setPacketHandled(true);
	}
}
