package laz.plasmine.registry.render;

import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BlockRender {
	
	public static void init() {
		RenderType cutout = RenderType.getCutout();
		RenderType opaque = RenderType.getTranslucent();
		
		RenderTypeLookup.setRenderLayer(PMTilesInit.PLASMA_CABLE_T1.get(), opaque);
	}
}
