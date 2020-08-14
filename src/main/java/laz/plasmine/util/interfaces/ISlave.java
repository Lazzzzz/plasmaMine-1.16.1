package laz.plasmine.util.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public interface ISlave {
	
	default void sendMasterDestroy(BlockPos pos, IMaster master) {
		master.receiveDestroy(pos);
	}
	
	void bindToMaster(BlockPos mastePos);

	BlockPos getBlockPosMaster(BlockState state);
	
}
