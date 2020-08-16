package laz.plasmine.content.tiles.storage.put;

import java.util.List;

import laz.plasmine.base.generator.BlockGeneratorBase;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockPlasmaOutput extends BlockGeneratorBase {

	public BlockPlasmaOutput() {
		super(0, 0, 0);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TilePlasmaOutput();
	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			ISlave tile = (ISlave) worldIn.getTileEntity(pos);
			if (!BlockPosUtil.areSame(tile.getBlockPosMaster(), pos)) {
				IMaster master = (IMaster) worldIn.getTileEntity(tile.getBlockPosMaster());
				tile.sendMasterDestroy(pos, master);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
}
