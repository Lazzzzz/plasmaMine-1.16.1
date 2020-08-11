package laz.plasmine.registry.render;

import laz.plasmine.client.render.block.convertor.AdvancedConvertorRenderer;
import laz.plasmine.client.render.block.convertor.BasicConvertorRenderer;
import laz.plasmine.client.render.block.generator.BasicGeneratorRenderer;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TileEntityRender {

	public static void init() {
		
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.BASIC_GENERATOR.getTileEntityType(), BasicGeneratorRenderer::new);
		
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.BASIC_CONVERTOR.getTileEntityType(), BasicConvertorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(PMTilesInit.ADVANCED_CONVERTOR.getTileEntityType(), AdvancedConvertorRenderer::new);
		

	}
}
