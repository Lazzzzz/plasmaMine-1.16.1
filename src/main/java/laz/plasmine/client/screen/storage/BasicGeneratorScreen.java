package laz.plasmine.client.screen.storage;

import static laz.plasmine.client.draw.PMDrawable.BASE_SLOT;
import static laz.plasmine.client.draw.PMDrawable.PLASMA_BAR;
import static laz.plasmine.client.draw.PMDrawable.PLASMA_LOGO;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.client.screen.DrawBaseGui;
import laz.plasmine.content.tiles.generator.BlockBasicGenerator;
import laz.plasmine.content.tiles.generator.ContainerBasicGenerator;
import laz.plasmine.content.tiles.generator.TileBasicGenerator;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class BasicGeneratorScreen extends ContainerScreen<ContainerBasicGenerator> {

	public BasicGeneratorScreen(ContainerBasicGenerator screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void func_230450_a_(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		TileBasicGenerator tile = this.container.getTile();
		PlasmaHelper helper = tile.getPlasmaHelper();
		DrawBaseGui.drawGui(p_230450_1_, this.field_230712_o_, guiLeft, guiTop, " PU", (int) helper.getCapacity(), 3833343);
		PLASMA_LOGO.draw(guiLeft + 5, guiTop + 60, 9, 17);
		PLASMA_BAR.drawPartial(guiLeft + 2, guiTop + 2, 16, 56, 0, 1 - ((float) helper.getCapacity() / helper.getMaxCapacity()), 1f, 1f);
		BASE_SLOT.draw(guiLeft + 80, guiTop + 35, 16, 16);

		if (tile.getWorld().getBlockState(tile.getPos()).get(BlockBasicGenerator.WORKING))
	    	this.field_230712_o_.func_243248_b(p_230450_1_, new StringTextComponent("\u00A7n\u00A7lworking"), guiLeft + 113,  guiTop + 39, 50432);
	    else
	    	this.field_230712_o_.func_243248_b(p_230450_1_, new StringTextComponent("\u00A7n\u00A7lnot working"), guiLeft + 102,  guiTop + 39, 12976128);
	}

	@Override
	protected void func_230451_b_(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {}
	
	public void func_230430_a_(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.func_230446_a_(p_230430_1_);
		super.func_230430_a_(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		this.func_230459_a_(p_230430_1_, p_230430_2_, p_230430_3_);
	}
	
}
