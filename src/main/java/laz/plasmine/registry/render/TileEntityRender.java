package laz.plasmine.registry.render;

import laz.plasmine.client.render.block.CableRender;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TileEntityRender {
	
	public static void init() {
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.CABLE.getTileEntityType(), CableRender::new);
	}
}
