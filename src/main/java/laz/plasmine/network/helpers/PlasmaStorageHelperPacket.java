package laz.plasmine.network.helpers;

import java.util.function.Supplier;

import laz.plasmine.content.tiles.storage.TilePlasmaStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlasmaStorageHelperPacket {
	private BlockPos pos;
	private int amount;
	private int maxCapacity;
	private int rate;
	
	public PlasmaStorageHelperPacket(BlockPos pos, int amount, int maxCap, int rate) {
		this.pos = pos;
		this.amount = amount;
		this.maxCapacity = maxCap;
		this.rate = rate;
	}

	public static PlasmaStorageHelperPacket decode(PacketBuffer buf) {
		PlasmaStorageHelperPacket packet = new PlasmaStorageHelperPacket(buf.readBlockPos(), buf.readInt(), buf.readInt(), buf.readInt());
		return packet;
	}

	public static void encode(PlasmaStorageHelperPacket packet, PacketBuffer buf) {
		buf.writeBlockPos(packet.pos);
		buf.writeInt(packet.amount);
		buf.writeInt(packet.maxCapacity);
		buf.writeInt(packet.rate);
	}

	public static void handle(PlasmaStorageHelperPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientWorld world = Minecraft.getInstance().world;
			TilePlasmaStorage tile = (TilePlasmaStorage) world.getTileEntity(packet.pos);
			if (tile != null)
				tile.receiveData(packet.amount, packet.maxCapacity, packet.rate);
		});

		ctx.get().setPacketHandled(true);
	}
}
