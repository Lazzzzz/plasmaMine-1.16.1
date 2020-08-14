package laz.plasmine.client.screen.heat;

import static laz.plasmine.client.draw.PMDrawable.BASE_SLOT;
import static laz.plasmine.client.draw.PMDrawable.HEAT_BAR;
import static laz.plasmine.client.draw.PMDrawable.HEAT_LOGO;

import java.util.ArrayList;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.client.screen.DrawBaseGui;
import laz.plasmine.content.tiles.heat.sedimentcollector.ContainerSedimentCollector;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;

public class SedimentCollectorScreen extends ContainerScreen<ContainerSedimentCollector> {

	public SedimentCollectorScreen(ContainerSedimentCollector screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void func_230450_a_(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		TileHeatMachineBase tile = this.container.getTile();
		HeatHelper helper = tile.getHeatHelper();
		DrawBaseGui.drawGui(p_230450_1_, this.field_230712_o_, guiLeft, guiTop, " °C", (int) helper.getCelcius(), 12976128);
		HEAT_LOGO.draw(guiLeft + 5, guiTop + 60, 9, 17);
		HEAT_BAR.drawPartial(guiLeft + 2, guiTop + 2, 16, 56, 0,
				1 - ((float) helper.getCelcius() / helper.getMaxCelcius()), 1f, 1f);
		renderToolTip(p_230450_1_, new ArrayList<ITextProperties>(), p_230450_3_, p_230450_3_, this.field_230712_o_);

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 8; ++j) {
				BASE_SLOT.draw(guiLeft + 26 + j * 18, guiTop + 10 + i * 18, 16, 16);
			}
		}
	}

	public void func_230430_a_(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.func_230446_a_(p_230430_1_);
		super.func_230430_a_(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		this.func_230459_a_(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	@Override
	protected void func_230451_b_(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}

}
