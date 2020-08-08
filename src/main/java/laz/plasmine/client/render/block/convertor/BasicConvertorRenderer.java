package laz.plasmine.client.render.block.convertor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.Plasmine;
import laz.plasmine.client.models.block.convertor.BasicConvertorModel;
import laz.plasmine.content.base.generator.BlockGeneratorBase;
import laz.plasmine.content.tiles.convertor.TileBasicConvertor;
import laz.plasmine.content.tiles.generator.BlockBasicGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasicConvertorRenderer extends TileEntityRenderer<TileBasicConvertor> {

	BasicConvertorModel model = new BasicConvertorModel();
	ResourceLocation TEXTURE = new ResourceLocation(Plasmine.MOD_ID, "textures/blocks/convertor/basic_convertor.png");

	public BasicConvertorRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileBasicConvertor tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {

		World world = tileEntityIn.getWorld();
		BlockPos pos = tileEntityIn.getPos();
		BlockState state = world.getBlockState(pos);

		matrixStackIn.push();

		if (state.get(BlockGeneratorBase.WORKING)) model.animation((int) world.getDayTime(), partialTicks);
		else model.resetState();

		model.rotate(state.get(BlockBasicGenerator.FACING).getOpposite());

		matrixStackIn.translate(0.5D, -0.5D, 0.5D);
		IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.func_239267_e_(TEXTURE));
		model.render(matrixStackIn, ivertexbuilder, combinedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
	}
}
