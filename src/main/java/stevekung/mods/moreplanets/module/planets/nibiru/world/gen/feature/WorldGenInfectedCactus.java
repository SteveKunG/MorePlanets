package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenInfectedCactus extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int l = 0; l < 10; ++l)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos))
            {
                int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2)
                {
                    if (NibiruBlocks.INFECTED_CACTUS.canPlaceBlockAt(world, blockpos))
                    {
                        world.setBlockState(blockpos, NibiruBlocks.INFECTED_CACTUS.getDefaultState(), 2);
                    }
                }
            }
        }
        return true;
    }
}