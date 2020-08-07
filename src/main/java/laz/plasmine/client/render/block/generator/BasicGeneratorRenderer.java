package laz.plasmine.client.render.block.generator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.Plasmine;
import laz.plasmine.client.models.block.convertor.BasicConvertorModel;
import laz.plasmine.client.models.block.generator.BasicGeneratorModel;
import laz.plasmine.content.tiles.generator.TileBasicGenerator;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;

public class BasicGeneratorRenderer extends TileEntityRenderer<TileBasicGenerator> {

	BasicGeneratorModel model = new BasicGeneratorModel();
	ResourceLocation TEXTURE = new ResourceLocation(Plasmine.MOD_ID, "textures/blocks/generator/basic_generator.png");

	public BasicGeneratorRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		//SkullTileEntityRenderer
	}

	@Override
	public void render(TileBasicGenerator tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
	    matrixStackIn.push();
        matrixStackIn.translate(0.5D, 0.0D, 0.5D);
		IVertexBuilder ivertexbuilder = buffer.getBuffer(getTexture());
		model.render(matrixStackIn, ivertexbuilder, combinedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
	}
	
	private RenderType getTexture() {
		return RenderType.func_239267_e_(TEXTURE);
	}

}
