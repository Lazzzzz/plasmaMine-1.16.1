package laz.plasmine.client.render.block;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.content.base.cable.TileCableBase;
import laz.plasmine.content.test.TileCableTest;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class CableRender extends TileEntityRenderer<TileCableTest> {

    public CableRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TileCableTest press, float v, MatrixStack stack, IRenderTypeBuffer buf, int combinedLight, int combinedOverlay) {

    }


}
