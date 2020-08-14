package laz.plasmine.util.interfaces;

import net.minecraft.util.math.BlockPos;

public interface IMaster {

	void receiveDestroy(BlockPos pos);
	
	void bindBlock(BlockPos pos, ISlave slave);
	
}
