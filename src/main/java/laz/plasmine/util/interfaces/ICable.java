package laz.plasmine.util.interfaces;

import java.util.List;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface ICable {
	
	List<BlockPos> getCableAround(Direction from_dir, int iterration, List<BlockPos> passed, List<BlockPos> connect);
	
}

