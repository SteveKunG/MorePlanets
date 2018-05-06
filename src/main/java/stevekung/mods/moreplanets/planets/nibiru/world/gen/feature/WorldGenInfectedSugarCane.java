package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedSugarCane extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 20; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (world.isAirBlock(blockpos))
            {
                BlockPos blockpos1 = blockpos.down();

                if (world.getBlockState(blockpos1.west()).getBlock() == MPBlocks.INFECTED_WATER_FLUID_BLOCK || world.getBlockState(blockpos1.east()).getBlock() == MPBlocks.INFECTED_WATER_FLUID_BLOCK || world.getBlockState(blockpos1.north()).getBlock() == MPBlocks.INFECTED_WATER_FLUID_BLOCK || world.getBlockState(blockpos1.south()).getBlock() == MPBlocks.INFECTED_WATER_FLUID_BLOCK)
                {
                    int j = 2 + rand.nextInt(rand.nextInt(3) + 1);

                    for (int k = 0; k < j; ++k)
                    {
                        if (MPBlocks.INFECTED_SUGAR_CANE_BLOCK.canPlaceBlockAt(world, blockpos))
                        {
                            world.setBlockState(blockpos.up(k), MPBlocks.INFECTED_SUGAR_CANE_BLOCK.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}