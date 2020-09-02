package laz.plasmine.client.screen.storage;

import static laz.plasmine.client.draw.PMDrawable.BASE_GUI_STORAGE;
import static laz.plasmine.client.draw.PMDrawable.PLASMA_STORAGE_EMPTY;
import static laz.plasmine.client.draw.PMDrawable.PLASMA_STORAGE_FULL;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.content.tiles.storage.ContainerPlasmaStorage;
import laz.plasmine.content.tiles.storage.TilePlasmaStorage;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class PlasmaStorageScreen extends ContainerScreen<ContainerPlasmaStorage> {

	public PlasmaStorageScreen(ContainerPlasmaStorage screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		
		this.renderBackground(p_230450_1_);
		
		TilePlasmaStorage tile = container.getTile();
		PlasmaHelper plasma = tile.getHelper();

		BASE_GUI_STORAGE.draw(guiLeft, guiTop, 176, 166);
		PLASMA_STORAGE_EMPTY.draw(guiLeft + 4, guiTop + 4, 168, 63);
		if (plasma != null) {
			if (plasma.getMaxCapacity() == -1)
				this.font.func_243248_b(p_230450_1_, new StringTextComponent("Incomplete Multi-block"),
						guiLeft + 3, guiTop + 69, 12976128);
			else {
				PLASMA_STORAGE_FULL.drawPartial(guiLeft + 4, guiTop + 4, 168, 63, 0,
						1f - (float) plasma.getCapacity() / plasma.getMaxCapacity(), 1, 1);
				this.font.func_243248_b(p_230450_1_,
						new StringTextComponent(plasma.getCapacity() + "/" + plasma.getMaxCapacity() + "PU"),
						guiLeft + 8, guiTop + 71, 3833343);
			}
		}
		
		func_230459_a_(p_230450_1_, p_230450_3_, p_230450_4_);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}

}
