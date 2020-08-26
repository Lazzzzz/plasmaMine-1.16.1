package laz.plasmine.registry.render;

import laz.plasmine.client.render.block.convertor.AdvancedConvertorRenderer;
import laz.plasmine.client.render.block.convertor.BasicConvertorRenderer;
import laz.plasmine.client.render.block.convertor.SolarConvertorRenderer;
import laz.plasmine.client.render.block.convertor.SuperAdvancedConvertorRenderer;
import laz.plasmine.client.render.block.convertor.UltimateConvertorRenderer;
import laz.plasmine.client.render.block.miner.MinerRenderer;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TileEntityRender {

	public static void init() {
				
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.SOLAR_CONVERTOR.getTileEntityType(), SolarConvertorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.BASIC_CONVERTOR.getTileEntityType(), BasicConvertorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.ADVANCED_CONVERTOR.getTileEntityType(), AdvancedConvertorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.SUPER_ADVANCED_CONVERTOR.getTileEntityType(), SuperAdvancedConvertorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.ULTIMATE_CONVERTOR.getTileEntityType(), UltimateConvertorRenderer::new);
		
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.MINER.getTileEntityType(), MinerRenderer::new);
	}
}
