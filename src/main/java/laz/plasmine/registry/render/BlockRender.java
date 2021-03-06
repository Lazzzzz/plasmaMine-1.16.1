package laz.plasmine.registry.render;

import laz.plasmine.registry.init.PMBlocksInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BlockRender {
	
	public static void init() {
		RenderType cutout = RenderType.getCutout();
		RenderType opaque = RenderType.getTranslucent();
		
		RenderTypeLookup.setRenderLayer(PMTilesInit.BASIC_PLASMA_CABLE.get(), cutout);
		RenderTypeLookup.setRenderLayer(PMBlocksInit.RAPESEED_CROP.get(), cutout);
		
		RenderTypeLookup.setRenderLayer(PMTilesInit.MINER.get(), cutout);
		RenderTypeLookup.setRenderLayer(PMTilesInit.STRUCTURE_BLOCK.get(), cutout);
	}
}
