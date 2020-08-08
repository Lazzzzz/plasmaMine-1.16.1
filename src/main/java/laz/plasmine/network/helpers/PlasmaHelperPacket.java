package laz.plasmine.network.helpers;

import java.util.function.Supplier;

import laz.plasmine.content.base.generator.TileGeneratorBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlasmaHelperPacket {
	private BlockPos pos;
	private int amount;
	
	public PlasmaHelperPacket(BlockPos pos, int amount) {
		this.pos = pos;
		this.amount = amount;
	}

	public static PlasmaHelperPacket decode(PacketBuffer buf) {
		PlasmaHelperPacket packet = new PlasmaHelperPacket(buf.readBlockPos(), buf.readInt());
		return packet;
	}

	public static void encode(PlasmaHelperPacket packet, PacketBuffer buf) {
		buf.writeBlockPos(packet.pos);
		buf.writeInt(packet.amount);
	}

	public static void handle(PlasmaHelperPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientWorld world = Minecraft.getInstance().world;
			TileGeneratorBase tile = (TileGeneratorBase) world.getTileEntity(packet.pos);
			if (tile != null)
				tile.receiveData(packet.amount);
		});

		ctx.get().setPacketHandled(true);
	}
}
