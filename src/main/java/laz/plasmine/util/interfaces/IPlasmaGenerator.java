package laz.plasmine.util.interfaces;

import java.util.List;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.util.DirectionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlasmaGenerator {

	PlasmaHelper getPlasmaHelper();

	default int sendEnergy(World world, BlockPos pos, int amount) {
		int return_amount = amount;
		if (amount > 0) {
			TileEntity tile = null;
			for (int i = 0; i < 6; i++) {
				if (((IConnection) world.getTileEntity(pos)).getConnectionFace(Direction.byIndex(i)))
					tile = world.getTileEntity(DirectionUtils.getPosDirection(pos, Direction.byIndex(i)));
			}
			if (tile instanceof ICable) {
				List<BlockPos> outputs = ((ICable) tile).getNetwork();
				if (outputs.size() == 0)
					return 0;
				if (outputs.size() > 0) {
					for (int i = 0; i < outputs.size(); i++) {
						IPlasmaMachine machine = (IPlasmaMachine) world.getTileEntity(outputs.get(i));
						if (machine != null) {
							return_amount = machine.receiveEnergy(return_amount);
						}
					}
				}
			} else if (tile instanceof IPlasmaMachine) {
				IPlasmaMachine machine = (IPlasmaMachine) tile;
				if (machine != null) {
					int toRemove = machine.receiveEnergy(amount);
					amount -= toRemove;
				}
			} else
				return 0;
		} else {
			return 0;
		}
		return amount - return_amount;
	}

	int produceEnergy();

	void setWorkingState(boolean working);
}
