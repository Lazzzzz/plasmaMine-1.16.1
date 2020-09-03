package laz.plasmine.network.packets;

import java.util.function.Supplier;

import laz.plasmine.base.heat.TileHeatMachineBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class HeatHelperPacket {
	private BlockPos pos;
	private float amount;
	
	public HeatHelperPacket(BlockPos pos, float amount) {
		this.pos = pos;
		this.amount = amount;
	}

	public static HeatHelperPacket decode(PacketBuffer buf) {
		HeatHelperPacket packet = new HeatHelperPacket(buf.readBlockPos(), buf.readFloat());
		return packet;
	}

	public static void encode(HeatHelperPacket packet, PacketBuffer buf) {
		buf.writeBlockPos(packet.pos);
		buf.writeFloat(packet.amount);
	}

	public static void handle(HeatHelperPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientWorld world = Minecraft.getInstance().world;
			TileHeatMachineBase tile = (TileHeatMachineBase) world.getTileEntity(packet.pos);
			if (tile != null)
				tile.receiveData(packet.amount);
		});

		ctx.get().setPacketHandled(true);
	}
}
