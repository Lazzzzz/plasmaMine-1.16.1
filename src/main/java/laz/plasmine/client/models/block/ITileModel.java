package laz.plasmine.client.models.block;

import net.minecraft.util.Direction;

public interface ITileModel {

	void rotate(Direction dir);
	
	void animation(int tick, float partialTick);

	void resetState();
}
