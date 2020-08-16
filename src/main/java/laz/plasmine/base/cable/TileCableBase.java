package laz.plasmine.base.cable;

import static laz.plasmine.util.DirectionUtils.getPosDirection;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.base.generator.BlockGeneratorBase;
import laz.plasmine.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.ICable;
import laz.plasmine.util.interfaces.IConnection;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileCableBase extends TileEntity implements ITickableTileEntity, ICable, IConnection {

	public boolean[] connected = new boolean[6];

	protected List<BlockPos> NETWORK = new ArrayList<BlockPos>();

	protected int maxTime = 30;
	protected int timer = 0;
	protected boolean working = false;

	public TileCableBase(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	@Override
	public void tick() {
		if (!world.isRemote) {
			connectedTo(world, pos, connected);
			if (working == true) {
				timer++;
				if (timer == maxTime) {
					timer = 0;
					working = false;
					updateWorkingState();
				}
			}
			markDirty();
		}
	}

	@Override
	public void connectedTo(World world, BlockPos pos, boolean[] connected) {
		boolean[] afterIn = new boolean[6];

		for (int i = 0; i < 6; i++) {
			afterIn[i] = false;
			BlockPos p = getPosDirection(pos, Direction.byIndex(i));

			TileEntity tile = world.getTileEntity(p);
			if (tile instanceof IConnection) {
				afterIn[i] = ((IConnection) tile).getConnectionFace(Direction.byIndex(i).getOpposite());
			}

			if (tile instanceof TileCableBase)
				syncNetwork((TileCableBase) tile);

		}
		updateOutputs(connected, afterIn);
		updateState(world, pos);
	}

	public void syncNetwork(TileCableBase tile) {
		List<BlockPos> l = setNetWorkEqual(tile.getNetwork(), getNetwork());
		setNetwok(l);
		tile.setNetwok(l);
	}

	@Override
	public void updateOutputs(boolean[] before, boolean[] after) {
		for (int i = 0; i < 6; i++) {
			if (before[i] != after[i]) {
				Direction dir = Direction.byIndex(i);
				BlockPos toUpdate = DirectionUtils.getPosDirection(pos, dir);
				TileEntity tile = world.getTileEntity(toUpdate);
				if (!after[i])
					updateNetwork(dir.getOpposite(), new ArrayList<BlockPos>(), toUpdate, 1);
				else if (tile instanceof TilePlasmaMachineBase)
					updateNetwork(dir.getOpposite(), new ArrayList<BlockPos>(), toUpdate, 0);
			}
		}

		connected = after;

	}

	@Override
	public void updateNetwork(Direction from_dir, List<BlockPos> passed, BlockPos p, int flag) {
		for (int i = 0; i < 6; i++) {
			Direction cur_dir = Direction.byIndex(i);
			if (connected[i] && cur_dir.getOpposite() != from_dir) {
				BlockPos tilePos = DirectionUtils.getPosDirection(pos, cur_dir);
				if (!isNew(passed, tilePos)) {
					return;
				}

				updateWorkingState();

				TileEntity tile = world.getTileEntity(tilePos);
				if (tile != null && tile instanceof ICable)
					((ICable) tile).updateNetwork(cur_dir, passed, p, flag);
			}
		}
		if (p != null || flag == 3)
			getSignal(p, flag);
	}

	@Override
	public void getSignal(BlockPos p, int flag) {
		if (flag == 3) {
			resetNetwork();
		}
		else if (flag == 0)
			isNew(NETWORK, p);

		else if (flag == 1) {
			for (int i = 0; i < NETWORK.size(); i++) {
				if (BlockPosUtil.areSame(NETWORK.get(i), p)) {
					NETWORK.remove(i);
					break;
				}
			}
		}
	}

	public void updateWorkingState() {
		BlockState state = world.getBlockState(pos);
		if (working) {
			if (state.get(BlockCableBase.WORKING) == false)
				world.setBlockState(pos, state.with(BlockCableBase.WORKING, true));
		} else {
			if (state.get(BlockGeneratorBase.WORKING) == true)
				world.setBlockState(pos, state.with(BlockCableBase.WORKING, false));

		}
	}

	@Override
	public void updateState(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		BlockState newState = state.with(BlockCableBase.DOWN, connected[0]).with(BlockCableBase.UP, connected[1])
				.with(BlockCableBase.NORTH, connected[2]).with(BlockCableBase.SOUTH, connected[3])
				.with(BlockCableBase.WEST, connected[4]).with(BlockCableBase.EAST, connected[5]);

		if (state != newState)
			world.setBlockState(pos, newState);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		for (int i = 0; i < 6; i++) {
			compound.putBoolean("connected_" + i, connected[i]);
		}

		compound = BlockPosUtil.writeListBlockPos(compound, NETWORK, "net");
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.func_230337_a_(p_230337_1_, p_230337_2_);
		for (int i = 0; i < 6; i++) {
			connected[i] = p_230337_2_.getBoolean("connected_" + i);
		}

		NETWORK = BlockPosUtil.readListBlockPos(p_230337_2_, "net");
	}

	@Override
	public List<BlockPos> getNetwork() {
		return NETWORK;
	}

	@Override
	public void setNetwok(List<BlockPos> l) {
		NETWORK = l;
	}

	@Override
	public void resetNetwork() {
		NETWORK.clear();
		refrechNetwork();
	}

	@Override
	public void refrechNetwork() {
		for (int i = 0; i < 6; i++) {
			connected[i] = false;
		}

	}

}
