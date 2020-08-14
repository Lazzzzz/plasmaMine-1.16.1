package laz.plasmine.util.interfaces;

import java.util.List;

import laz.plasmine.util.BlockPosUtil;
import net.minecraft.util.math.BlockPos;

public interface IMaster {

	default void receiveDestroy(BlockPos pos, List<BlockPos> connectedBlock) {
		for (int i = 0; i < connectedBlock.size(); i++) {
			if (BlockPosUtil.areSame(connectedBlock.get(i), pos))
				connectedBlock.remove(i);
		}
	}

	default void bindBlock(BlockPos masterPos, BlockPos pos, ISlave slave, List<BlockPos> connectedBlock) {
		connectedBlock.add(pos);
		slave.bindToMaster(masterPos);
	}

	List<BlockPos> getConnectedBlock();
	
	boolean checkIsFormed();
}
