package laz.plasmine.util.interfaces;

import java.util.List;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface ICable {
	
	int getCableAround(Direction from, int amount, int iterration, List<BlockPos> passed);
	
}

