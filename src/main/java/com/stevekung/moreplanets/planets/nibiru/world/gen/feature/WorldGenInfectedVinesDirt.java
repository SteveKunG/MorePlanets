package com.stevekung.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenInfectedVinesDirt extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos1) && world.getBlockState(blockpos1.down()).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
            {
                world.setBlockState(blockpos1.down(), MPBlocks.INFECTED_VINES_DIRT.getDefaultState(), 2);
            }
        }
        return true;
    }
}