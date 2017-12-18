package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenInfectedVinesDirt extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos1) && world.getBlockState(blockpos1.down()).getBlock() == NibiruBlocks.INFECTED_GRASS)
            {
                world.setBlockState(blockpos1.down(), NibiruBlocks.INFECTED_VINES_DIRT.getDefaultState(), 2);
            }
        }
        return true;
    }
}