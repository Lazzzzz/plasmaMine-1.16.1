package laz.plasmine.client.screen.heat;

import static laz.plasmine.client.draw.PMDrawable.BASE_SLOT;
import static laz.plasmine.client.draw.PMDrawable.HEAT_BAR;
import static laz.plasmine.client.draw.PMDrawable.HEAT_LOGO;

import java.util.ArrayList;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.client.screen.DrawBaseGui;
import laz.plasmine.content.tiles.heat.sedimentextractor.ContainerSedimentExtractor;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SedimentExtractorScreen extends ContainerScreen<ContainerSedimentExtractor> {

	public SedimentExtractorScreen(ContainerSedimentExtractor screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void func_230450_a_(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {

		TileHeatMachineBase tile = this.container.getTile();
		HeatHelper helper = tile.getHeatHelper();
		DrawBaseGui.drawGuiSpeed(p_230450_1_, this.field_230712_o_, guiLeft, guiTop, " °C", (int) helper.getCelcius(),
				tile.speedFactor(), 12976128);
		HEAT_LOGO.draw(guiLeft + 5, guiTop + 60, 9, 17);
		HEAT_BAR.drawPartial(guiLeft + 2, guiTop + 2, 16, 56, 0,
				1 - ((float) helper.getCelcius() / helper.getMaxCelcius()), 1f, 1f);
		func_243308_b(p_230450_1_, new ArrayList<ITextComponent>(), p_230450_3_, p_230450_3_);

		BASE_SLOT.draw(guiLeft + 44, guiTop + 21, 16, 16);
		BASE_SLOT.draw(guiLeft + 44, guiTop + 40, 16, 16);
		BASE_SLOT.draw(guiLeft + 116, guiTop + 31, 16, 16);

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
