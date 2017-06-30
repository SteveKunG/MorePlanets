package stevekung.mods.moreplanets.util.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IDarkEnergyProvider
{
    public int getDarkEnergyMultiplier(World world, BlockPos pos);
}