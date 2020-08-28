package laz.plasmine.client.screen.plasma;

import static laz.plasmine.client.draw.PMDrawable.PLASMA_BAR;
import static laz.plasmine.client.draw.PMDrawable.PLASMA_LOGO;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.base.generator.TileGeneratorBase;
import laz.plasmine.client.screen.DrawBaseGui;
import laz.plasmine.content.tiles.generator.basicgenerator.BlockBasicGenerator;
import laz.plasmine.content.tiles.generator.electromagneticgenerator.ContainerEMGenerator;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class EMGeneratorScreen extends ContainerScreen<ContainerEMGenerator> {

	public EMGeneratorScreen(ContainerEMGenerator screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		
		this.renderBackground(p_230450_1_);
		
		TileGeneratorBase tile = this.container.getTile();
		PlasmaHelper helper = tile.getPlasmaHelper();
		DrawBaseGui.drawGui(p_230450_1_, this.font, guiLeft, guiTop, " PU", (int) helper.getCapacity(),
				3833343);
		PLASMA_LOGO.draw(guiLeft + 5, guiTop + 60, 9, 17);
		if (helper.getCapacity() == -1) {
			this.font.func_243248_b(p_230450_1_, new StringTextComponent("\u00A7nIncomplete Multi-block"),
					guiLeft + 40, guiTop + 39, 12976128);
		} else {
			PLASMA_BAR.drawPartial(guiLeft + 2, guiTop + 2, 16, 56, 0,
					1 - ((float) helper.getCapacity() / helper.getMaxCapacity()), 1f, 1f);
			if (tile.getWorld().getBlockState(tile.getPos()).get(BlockBasicGenerator.WORKING))
				this.font.func_243248_b(p_230450_1_, new StringTextComponent("\u00A7nworking"), guiLeft + 75,
						guiTop + 39, 50432);
			else
				this.font.func_243248_b(p_230450_1_, new StringTextComponent("\u00A7nnot working"),
						guiLeft + 64, guiTop + 39, 12976128);
		}
		
		func_230459_a_(p_230450_1_, p_230450_3_, p_230450_4_);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}


}
