package laz.plasmine.util;

import net.minecraft.item.ItemStack;

public class RecipiesUtils {
	
	public static boolean isSameTag(ItemStack in, ItemStack [] tag) {
		for (int i = 0; i < tag.length; i++) {
			if (in.getItem() == tag[i].getItem()) return true;
		}
		return false;
	}
	
}
