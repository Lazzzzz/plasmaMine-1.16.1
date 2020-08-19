package laz.plasmine.network;

import laz.plasmine.Plasmine;
import laz.plasmine.network.helpers.HeatHelperPacket;
import laz.plasmine.network.helpers.PlasmaHelperPacket;
import laz.plasmine.network.helpers.PlasmaStorageHelperPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	public static SimpleChannel INSTANCE;
	private static int ID = 0;

	public static int nextID() {
		return ID++;
	}

	public static void registerMessages() {
		INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Plasmine.MOD_ID, "plasmine"),
				() -> "1.0", s -> true, s -> true);
		INSTANCE.registerMessage(nextID(), PlasmaHelperPacket.class, PlasmaHelperPacket::encode, PlasmaHelperPacket::decode, PlasmaHelperPacket::handle);
		INSTANCE.registerMessage(nextID(), HeatHelperPacket.class, HeatHelperPacket::encode, HeatHelperPacket::decode, HeatHelperPacket::handle);
		INSTANCE.registerMessage(nextID(), PlasmaStorageHelperPacket.class, PlasmaStorageHelperPacket::encode, PlasmaStorageHelperPacket::decode, PlasmaStorageHelperPacket::handle);
		
	}

}
