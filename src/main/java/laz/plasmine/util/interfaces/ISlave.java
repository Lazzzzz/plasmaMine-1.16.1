package laz.plasmine.util.interfaces;

import net.minecraft.util.math.BlockPos;

public interface ISlave {
	
	default void sendMasterDestroy(BlockPos pos, IMaster master) {
		if (master != null) master.receiveDestroy(pos, master.getConnectedBlock());
	}
	
	void bindToMaster(BlockPos masterPos);
	
	void unbindToMaster();

	BlockPos getBlockPosMaster();
	
	boolean isBind();
	
}
