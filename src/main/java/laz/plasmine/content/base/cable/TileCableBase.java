package laz.plasmine.content.base.cable;

import static laz.plasmine.util.direction.DirectionUtils.getPosDirection;

import java.util.List;

import laz.plasmine.api.Constante;
import laz.plasmine.content.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.util.ICable;
import laz.plasmine.util.IConnection;
import laz.plasmine.util.direction.DirectionUtils;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
	public void connectedTo(World world, BlockPos pos, boolean[] connected) {
		for (int i = 0; i < 6; i++) {
			connected[i] = false;
			BlockPos p = getPosDirection(pos, Direction.byIndex(i));
			TileEntity tile = world.getTileEntity(p);
			if (tile != null && tile instanceof IConnection)
				connected[i] = ((IConnection) tile).getConnectionFace(Direction.byIndex(i).getOpposite());
		}
		updateState(world, pos);
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
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		for (int i = 0; i < 6; i++) {
			compound.putBoolean("connected_" + i, connected[i]);
		}
		return super.write(compound);
	}
	
	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		for (int i = 0; i < 6; i++) {
			connected[i] = p_230337_2_.getBoolean("connected_" + i);
		}
		super.func_230337_a_(p_230337_1_, p_230337_2_);
	}
}
