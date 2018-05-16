package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenNibiruMelon extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (MPBlocks.INFECTED_MELON.canPlaceBlockAt(world, blockpos) && world.getBlockState(blockpos.down()).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
            {
                world.setBlockState(blockpos, MPBlocks.INFECTED_MELON.getDefaultState(), 2);
            }
        }
        return true;
    }
}