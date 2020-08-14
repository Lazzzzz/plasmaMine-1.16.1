package laz.plasmine.util.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public interface IPipeConnection {
	
	void sendItem(Direction from, ItemStack item, IPipeConnection pipe);
	
	boolean canAcceptItem(ItemStack item);
	
}
