package laz.plasmine.client.render.block.miner;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.Plasmine;
import laz.plasmine.client.models.block.miner.MinerModel;
import laz.plasmine.content.tiles.generator.basicgenerator.BlockBasicGenerator;
import laz.plasmine.content.tiles.heat.miner.TileMiner;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;

public class MinerRenderer extends TileEntityRenderer<TileMiner> {

	MinerModel model = new MinerModel();
	ResourceLocation TEXTURE_OFF = new ResourceLocation(Plasmine.MOD_ID, "textures/blocks/machine/miner/miner_off.png");
	ResourceLocation TEXTURE_ON  = new ResourceLocation(Plasmine.MOD_ID, "textures/blocks/machine/miner/miner_on.png");

	public MinerRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileMiner tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
		BlockState state = tileEntityIn.getWorld().getBlockState(tileEntityIn.getPos());
		model.rotate(state.get(BlockBasicGenerator.FACING).getOpposite().rotateYCCW());
		
		matrixStackIn.push();
		IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.func_239267_e_(TEXTURE_OFF));
		matrixStackIn.translate(0.5D, 1.5D, 0.5D);
		matrixStackIn.scale(1.0F, -1.0F, 1.0F);
		
		model.render(matrixStackIn, ivertexbuilder, combinedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		
		matrixStackIn.pop();

	}

	
}
