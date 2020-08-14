package laz.plasmine.util.interfaces;

import java.util.List;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.content.tiles.generator.BlockBasicGenerator;
import laz.plasmine.util.DirectionUtils;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlasmaGenerator {

	PlasmaHelper getPlasmaHelper();

	default int sendEnergy(World world, BlockPos pos, int amount) {
		if (amount > 0) {
			BlockState state = world.getBlockState(pos);
			TileEntity tile = world.getTileEntity(DirectionUtils.getPosDirection(pos, state.get(BlockBasicGenerator.FACING).getOpposite()));
			if (tile instanceof ICable) {
				List<BlockPos> outputs = ((ICable) tile).getNetwork();
				if (outputs.size() == 0) return 0;
				
				if (outputs.size() > 0) {
					int amountEach = (int) Math.ceil(amount / outputs.size());
					for (int i = 0; i < outputs.size(); i++) {
						amount -= ((IPlasmaMachine) world.getTileEntity(outputs.get(i))).receiveEnergy(amountEach);
					}
				}
			} else return 0;
		}
		return amount;
	}

	int produceEnergy();

	void setWorkingState(boolean working);
}
