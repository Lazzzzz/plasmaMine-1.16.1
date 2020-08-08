package laz.plasmine.client.container.heat;

import static laz.plasmine.client.draw.PMDrawable.HEAT_BAR;
import static laz.plasmine.client.draw.PMDrawable.HEAT_LOGO;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.client.container.DrawBaseGui;
import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.content.tiles.generator.BlockBasicGenerator;
import laz.plasmine.content.tiles.heat.sedimentcollector.ContainerSedimentCollector;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SedimentCollectorScreen extends ContainerScreen<ContainerSedimentCollector> {

	public SedimentCollectorScreen(ContainerSedimentCollector screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void func_230450_a_(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		TileHeatMachineBase tile = this.container.getTile();
		HeatHelper helper = tile.getHeatHelper();
		DrawBaseGui.drawGui(guiLeft, guiTop);
		HEAT_LOGO.draw(guiLeft + 5, guiTop + 60, 9, 17);
		HEAT_BAR.drawPartial(guiLeft + 2, guiTop + 2, 16, 56, 0, 1 - ((float) helper.getCelcius() / helper.getMaxCelcius()), 1f, 1f);
		//BASE_SLOT.draw(guiLeft + 80, guiTop + 35, 16, 16);

		if (tile.getWorld().getBlockState(tile.getPos()).get(BlockBasicGenerator.WORKING))
	    	this.field_230712_o_.func_238422_b_(p_230450_1_, new StringTextComponent("\u00A7n\u00A7lworking"), guiLeft + 113,  guiTop + 39, 50432);
	    else
	    	this.field_230712_o_.func_238422_b_(p_230450_1_, new StringTextComponent("\u00A7n\u00A7lnot working"), guiLeft + 102,  guiTop + 39, 12976128);
	
}

	@Override
	protected void func_230451_b_(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {}
	
}
