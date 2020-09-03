package laz.plasmine.client.screen.heat;

import static laz.plasmine.client.draw.PMDrawable.BASE_SLOT;

import java.util.ArrayList;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.client.screen.DrawBaseGui;
import laz.plasmine.content.tiles.heat.crusher.ContainerCrusher;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class CrusherScreen extends ContainerScreen<ContainerCrusher> {

	public CrusherScreen(ContainerCrusher screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float p_230450_2_, int mx,
			int my) {
		this.renderBackground(p_230450_1_);
		
		TileHeatMachineBase tile = this.container.getTile();
		HeatHelper helper = tile.getHeatHelper();
		
		DrawBaseGui.drawGuiSpeed(p_230450_1_, this.font, guiLeft, guiTop, " °C", (int) helper.getCelcius(),
				tile.speedFactor(), 12976128);
		DrawBaseGui.drawHeatBar(p_230450_1_, guiLeft, guiTop, helper, font);
		DrawBaseGui.drawProgressBar(p_230450_1_, guiLeft + 72, guiTop + 38, tile.getProgress(), tile.getMaxProgress());
		
		
		func_243308_b(p_230450_1_, new ArrayList<ITextComponent>(), mx, my);
		BASE_SLOT.draw(guiLeft + 44, guiTop + 35, 18, 18);
		BASE_SLOT.draw(guiLeft + 116, guiTop + 35, 18, 18);
		
		func_230459_a_(p_230450_1_, mx, my);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
	}

}
