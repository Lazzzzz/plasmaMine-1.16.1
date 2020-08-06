package laz.plasmine.client.render.block;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.content.base.heat.TileHeatMachineBase;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class HeatMachineRenderer extends TileEntityRenderer<TileHeatMachineBase>{

	public HeatMachineRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileHeatMachineBase tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		//make it red if overheating 
		
	}

}
