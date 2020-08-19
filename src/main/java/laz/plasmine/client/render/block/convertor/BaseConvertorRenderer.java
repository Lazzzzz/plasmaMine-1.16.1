package laz.plasmine.client.render.block.convertor;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.Plasmine;
import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.base.generator.BlockGeneratorBase;
import laz.plasmine.client.models.block.convertor.BasicConvertorModel;
import laz.plasmine.content.tiles.generator.basicgenerator.BlockBasicGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseConvertorRenderer extends TileEntityRenderer<TileConvertorBase> {

	BasicConvertorModel model = new BasicConvertorModel();
	ResourceLocation TEXTURE = new ResourceLocation(Plasmine.MOD_ID, "textures/blocks/convertor.png");

	public BaseConvertorRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileConvertorBase tile, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {

		World world = tile.getWorld();
		BlockPos pos = tile.getPos();
		BlockState state = world.getBlockState(pos);
		if (state.get(BlockGeneratorBase.WORKING) && world.isBlockPowered(pos)) model.animation(tile.livingtick, partialTicks);
		else model.resetState();

		model.rotate(state.get(BlockBasicGenerator.FACING).getOpposite());

	}
}
