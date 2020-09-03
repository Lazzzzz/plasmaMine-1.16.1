package laz.plasmine.client.draw;

import laz.plasmine.Plasmine;
import laz.plasmine.util.interfaces.IDrawable;
import net.minecraft.util.ResourceLocation;

public class PMDrawable {

	public static final IDrawable BASE_GUI = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/hud_base.png"), 176, 166).getArea(0, 0, 176, 166);
	public static final IDrawable BASE_BAR = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/hud_base_bar.png"), 18, 77).getArea(0, 0, 18, 77);
	public static final IDrawable BASE_SLOT = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/hud_base_slot.png"), 18, 18).getArea(0, 0, 18, 18);
	
	public static final IDrawable HEAT_LOGO = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/heat_logo.png"), 7, 15).getArea(0, 0, 7, 15);
	public static final IDrawable PLASMA_LOGO = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/plasma_logo.png"), 7, 15).getArea(0, 0, 7, 15);
	
	public static final IDrawable HEAT_BAR = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/heat_bar.png"), 18, 77).getArea(0, 0, 18, 77);
	public static final IDrawable PLASMA_BAR = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/plasma_bar.png"), 18, 77).getArea(0, 0, 18, 77);
	
	public static final IDrawable LOADING_BAR_EMPTY = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/loading_bar_empty.png"), 34, 12).getArea(0, 0, 34, 12);
	public static final IDrawable LOADING_BAR_FULL = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/loading_bar_full.png"), 34, 12).getArea(0, 0, 34, 12);
	
	
	public static final IDrawable BASE_GUI_STORAGE = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/plasma_gui_storage.png"), 176, 166).getArea(0, 0, 176, 166);
	public static final IDrawable PLASMA_STORAGE_EMPTY = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/plasma_storage_empty.png"), 168, 63).getArea(0, 0, 168, 63);
	public static final IDrawable PLASMA_STORAGE_FULL = new UiTexture(
			new ResourceLocation(Plasmine.MOD_ID, "textures/gui/plasma_storage_full.png"), 168, 63).getArea(0, 0, 168, 63);

	
}
