package laz.plasmine.util.interfaces;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.util.BlockPosUtil;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface ICable {

	// 0 added 1 remove
	void updateNetwork(Direction dir, List<BlockPos> passed, BlockPos pos, int flag);



	void getSignal(BlockPos pos, int flag);

	default boolean isNew(List<BlockPos> l, BlockPos pos) {
		for (int i = 0; i < l.size(); i++) {
			if (BlockPosUtil.areSame(l.get(i), pos))
				return false;
		}
		l.add(pos);
		return true;
	}
	
	void resetNetwork();

	void refrechNetwork();
	
	void updateOutputs(boolean[] before, boolean[] after);

	List<BlockPos> getNetwork();

	void setNetwok(List<BlockPos> l);
	
	default List<BlockPos> setNetWorkEqual(List<BlockPos> list1, List<BlockPos> list2) {
		List<BlockPos> l = new ArrayList<BlockPos>();
		l.addAll(list1);
		for (int i = 0; i < list2.size(); i++)
			isNew(l, list2.get(i));
		return l;
	}

}
