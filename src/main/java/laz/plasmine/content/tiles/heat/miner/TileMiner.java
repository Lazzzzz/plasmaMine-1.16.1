package laz.plasmine.content.tiles.heat.miner;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IMaster;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileMiner extends TileHeatMachineBase implements IMaster {

	private int radiusX = 1;
	private int radiusZ = 1;

	private int offsetX = -radiusX;
	private int offsetY = 2;
	private int offsetZ = -radiusZ;

	private int maxTimer = 100;
	private double timer = 0;

	private boolean done = false;

	public TileMiner(float maxCelcius, float thermo) {
		super(PMTilesInit.MINER.getTileEntityType(), maxCelcius, thermo, 0);
	}

	@Override
	public List<BlockPos> getConnectedBlock() {
		return null;
	}

	@Override
	public Direction heatInOut(BlockState state) {
		return state.get(BlockRotationBase.FACING);
	}

	@Override
	public void onWorking() {
		if (!done) {
			setWorkingState(world, pos, world.getBlockState(pos), true);
			if (timer >= maxTimer) {
				Direction dir = getBlockState().get(BlockRotationBase.FACING).getOpposite();
				timer = 0;
				List<ItemStack> items = mine(dir);
				expulseItem(items, dir);
				move(dir);
			} else
				timer += speedFactor() * Math.exp(speedFactor() * 2);
		}
	}

	private void expulseItem(List<ItemStack> items, Direction dir) {
		BlockPos p = DirectionUtils.getPosDirection(pos, dir, 3);
		TileEntity tile = world.getTileEntity(p);
		if (tile instanceof IInventory) {
			putInTile((IInventory) tile, items);
		}
		for (int i = 0; i < items.size(); i++) {
			world.addEntity(new ItemEntity(world, p.getX(), p.getY(), p.getZ(), items.get(i)));
		}
	}

	private List<ItemStack> mine(Direction dir) {
		world.destroyBlock(DirectionUtils.getOffsetPos(pos, offsetX, -offsetY, offsetZ, dir), true);
		return collectLoot(dir);
	}

	private void move(Direction dir) {
		offsetX++;
		if (offsetX == radiusX + 1) {
			offsetX = -radiusX;
			offsetZ++;
			if (offsetZ == radiusZ + 1) {
				offsetZ = -radiusZ;
				if (offsetY < pos.getY() - 1)
					offsetY++;
				else
					done = true;

			}
		}
		// System.out.println(offsetX + " " + offsetY + " " + offsetZ);
		if (world.getBlockState(DirectionUtils.getOffsetPos(pos, offsetX, -offsetY, offsetZ, dir)) == Blocks.AIR
				.getDefaultState())
			timer = maxTimer;
	}

	private void putInTile(IInventory tile, List<ItemStack> items) {
		for (int i = 0; i < items.size(); i++) {
			ItemStack stack = transferItem(tile, items.get(i));
			if (stack.isEmpty())
				items.remove(i);
		}
	}

	private ItemStack transferItem(IInventory inventory, ItemStack stack) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack inv = inventory.getStackInSlot(i);
			if (inv.isEmpty()) {
				inventory.setInventorySlotContents(i, new ItemStack(stack.getItem()));
				stack.setCount(0);
				break;
			} else if (inv.getItem() == stack.getItem() && inv.getMaxStackSize() - inv.getCount() >= stack.getCount()) {
				inv.grow(stack.getCount());
				stack.setCount(0);
				break;
			}
		}
		return stack;
	}

	private List<ItemStack> collectLoot(Direction dir) {
		List<ItemStack> r = new ArrayList<ItemStack>();
		List<ItemEntity> l = world.getEntitiesWithinAABB(ItemEntity.class,
				new AxisAlignedBB(DirectionUtils.getOffsetPos(pos, offsetX, -offsetY, offsetZ, -1, -1, -1, dir),
						DirectionUtils.getOffsetPos(pos, offsetX, -offsetY, offsetZ, 2, 2, 2, dir)));
		for (int i = 0; i < l.size(); i++) {
			ItemEntity item = l.get(i);
			r.add(item.getItem());
			item.remove();
		}
		return r;
	}

	@Override
	public boolean checkIsFormed() {
		return false;
	}

	@Override
	public void sendStructureBind(BlockPos p, Direction dir) {
	}

	@Override
	public void sendStructureUnBind(BlockPos p, Direction dir) {
	}

	@Override
	public void receiveDestroy(BlockPos pos, List<BlockPos> connectedBlock) {
		world.destroyBlock(this.pos, true);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("offx", offsetX);
		compound.putInt("offy", offsetY);
		compound.putInt("offz", offsetZ);
		return super.write(compound);
	}

	@Override
	public void read(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		offsetX = p_230337_2_.getInt("offx");
		offsetY = p_230337_2_.getInt("offy");
		offsetZ = p_230337_2_.getInt("offz");
		super.read(p_230337_1_, p_230337_2_);
	}

}
