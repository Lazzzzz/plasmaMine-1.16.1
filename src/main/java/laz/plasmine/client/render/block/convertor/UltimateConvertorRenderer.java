package laz.plasmine.client.render.block.convertor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.base.convertor.TileConvertorBase;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class UltimateConvertorRenderer extends BaseConvertorRenderer {

	public UltimateConvertorRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileConvertorBase tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer buffer,
			int combinedLightIn, int combinedOverlayIn) {
		super.render(tile, partialTicks, matrixStackIn, buffer, combinedLightIn, combinedOverlayIn);
		
		matrixStackIn.push();
		matrixStackIn.translate(0.5D, -0.5D, 0.5D);
		IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.func_239267_e_(TEXTURE));
		model.render(matrixStackIn, ivertexbuilder, combinedLightIn, OverlayTexture.NO_OVERLAY, 0.1f , 0.1f, 0.1f, 1.0F);
		matrixStackIn.pop();
	}
}
