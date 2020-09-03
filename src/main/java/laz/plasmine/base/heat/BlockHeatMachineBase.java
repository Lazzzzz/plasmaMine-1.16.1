package laz.plasmine.base.heat;

import java.util.List;

import laz.plasmine.api.information.HeatInformationBase;
import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockHeatMachineBase extends BlockRotationBase implements ICanWrench {

	public static final BooleanProperty WORKING = BooleanProperty.create("working");
	public static final BooleanProperty POWER = BooleanProperty.create("power");

	public int maxCelcius;
	public float thermo;

	public BlockHeatMachineBase(int maxCelcius, float thermo) {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
				.with(WORKING, Boolean.valueOf(false)).with(POWER, Boolean.valueOf(false)));

		this.maxCelcius = maxCelcius;
		this.thermo = thermo;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WORKING, POWER);
	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		HeatInformationBase.info(maxCelcius, tooltip);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		if (side == blockState.get(FACING) && blockState.get(POWER)) return 15;
		return 0;
	}
	
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileHeatMachineBase) {
				InventoryHelper.dropInventoryItems(worldIn, pos, ((TileHeatMachineBase) te));
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);

	}
}
