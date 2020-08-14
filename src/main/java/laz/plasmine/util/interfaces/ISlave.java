package laz.plasmine.util.interfaces;

import net.minecraft.util.math.BlockPos;

public interface ISlave {
	
	default void sendMasterDestroy(BlockPos pos, IMaster master) {
		master.receiveDestroy(pos, master.getConnectedBlock());
	}
	
	void bindToMaster(BlockPos masterPos);
	
	void unbindToMaster();

	BlockPos getBlockPosMaster();
	
	boolean isBind();
	
}
