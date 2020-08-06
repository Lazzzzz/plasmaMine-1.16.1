package laz.plasmine.content.base.cable;

import java.util.List;

import laz.plasmine.Constante;
import laz.plasmine.content.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.util.ICable;
import laz.plasmine.util.IConnection;
import laz.plasmine.util.direction.DirectionUtils;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TileCableBase extends TileEntity implements ITickableTileEntity, ICable, IConnection {

	protected boolean [] connected = new boolean[6];

	public TileCableBase(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (world.getDayTime() % 40 == 0) connectedTo(world, pos, connected);
		}
	}

	@Override
	public int getCableAround(Direction from_dir, int amount, int iterration, List<BlockPos> passed) {
		int energy = amount;
		
		if (iterration == Constante.MAX_CABLE_ITERRATION) return energy;
		if (amount == 0) return 0;
		
		for (int i = 0; i < 6; i++) {
			Direction cur_dir = Direction.byIndex(i);
			if (connected[i] && cur_dir.getOpposite() != from_dir) {
				BlockPos tilePos = DirectionUtils.getPosDirection(pos, cur_dir);
				
				if (!isNew(passed, tilePos)) return 0;
				
				TileEntity tile = world.getTileEntity(tilePos);
				if (tile != null) {
					if (tile instanceof TileCableBase) energy = ((TileCableBase) tile).getCableAround(cur_dir, energy, iterration++, passed);
					else if (tile instanceof TilePlasmaMachineBase)	energy = ((TilePlasmaMachineBase) tile).receiveEnergy(energy);
				}
			}
		}
		return energy;
	}
	
	private boolean isNew(List<BlockPos> passed, BlockPos pos) {
		for (int i = 0; i < passed.size(); i++) {
			if (passed.get(i).equals(pos)) return false;
		}
		passed.add(pos);
		return true;
	}
}
