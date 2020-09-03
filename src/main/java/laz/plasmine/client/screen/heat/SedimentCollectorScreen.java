package laz.plasmine.client.screen.heat;

import static laz.plasmine.client.draw.PMDrawable.BASE_GUI;
import static laz.plasmine.client.draw.PMDrawable.BASE_SLOT;

import java.util.ArrayList;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.client.screen.DrawBaseGui;
import laz.plasmine.content.tiles.heat.sedimentcollector.ContainerSedimentCollector;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SedimentCollectorScreen extends ContainerScreen<ContainerSedimentCollector> {

	public SedimentCollectorScreen(ContainerSedimentCollector screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_,
			int p_230450_4_) {

		this.renderBackground(p_230450_1_);

		TileHeatMachineBase tile = this.container.getTile();
		HeatHelper helper = tile.getHeatHelper();
		BASE_GUI.draw(guiLeft, guiTop, 176, 166);
		font.func_243248_b(p_230450_1_, new StringTextComponent((int) helper.getCelcius() + " °C"), guiLeft + 60, guiTop + 73.5f, 12976128);
		
		DrawBaseGui.drawHeatBar(p_230450_1_, guiLeft, guiTop, helper, font);
		func_243308_b(p_230450_1_, new ArrayList<ITextComponent>(), p_230450_3_, p_230450_3_);

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 8; ++j) {
				BASE_SLOT.draw(guiLeft + 26 + j * 18, guiTop + 10 + i * 18, 18, 18);
			}
		}

		func_230459_a_(p_230450_1_, p_230450_3_, p_230450_4_);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}

}
