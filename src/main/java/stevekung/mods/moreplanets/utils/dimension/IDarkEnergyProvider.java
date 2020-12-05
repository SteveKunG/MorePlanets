package stevekung.mods.moreplanets.utils.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IDarkEnergyProvider
{
    int getDarkEnergyMultiplier(World world, BlockPos pos);
}