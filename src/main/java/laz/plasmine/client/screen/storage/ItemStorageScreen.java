package laz.plasmine.client.screen.storage;

import static laz.plasmine.client.draw.PMDrawable.BASE_GUI_STORAGE;
import static laz.plasmine.client.draw.PMDrawable.BASE_SLOT;

import com.mojang.blaze3d.matrix.MatrixStack;

import laz.plasmine.base.container.ContainerItemInput;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ItemStorageScreen extends ContainerScreen<ContainerItemInput> {

	public ItemStorageScreen(ContainerItemInput screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		
		this.renderBackground(p_230450_1_);
		
		BASE_GUI_STORAGE.draw(guiLeft, guiTop, 176, 166);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				BASE_SLOT.draw(guiLeft + 53 + j * 18, guiTop + 5 + i * 18, 16, 16);
			}
		}
		
		func_230459_a_(p_230450_1_, p_230450_3_, p_230450_4_);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}


}
