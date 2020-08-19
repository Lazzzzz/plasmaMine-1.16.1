package laz.plasmine.network.helpers;

import java.util.function.Supplier;

import laz.plasmine.base.generator.TileGeneratorBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlasmaHelperPacket {
	private BlockPos pos;
	private int amount;
	private int maxCapacity;
	
	public PlasmaHelperPacket(BlockPos pos, int amount, int maxCap) {
		this.pos = pos;
		this.amount = amount;
		this.maxCapacity = maxCap;
	}

	public static PlasmaHelperPacket decode(PacketBuffer buf) {
		PlasmaHelperPacket packet = new PlasmaHelperPacket(buf.readBlockPos(), buf.readInt(), buf.readInt());
		return packet;
	}

	public static void encode(PlasmaHelperPacket packet, PacketBuffer buf) {
		buf.writeBlockPos(packet.pos);
		buf.writeInt(packet.amount);
		buf.writeInt(packet.maxCapacity);
	}

	public static void handle(PlasmaHelperPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientWorld world = Minecraft.getInstance().world;
			TileGeneratorBase tile = (TileGeneratorBase) world.getTileEntity(packet.pos);
			if (tile != null)
				tile.receiveData(packet.amount, packet.maxCapacity);
		});

		ctx.get().setPacketHandled(true);
	}
}
