package com.stevekung.moreplanets.planets.nibiru.world.gen.feature;

import com.stevekung.lib.world.gen.WorldGenAbstractShrub;
import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.block.Block;

public class WorldGenInfectedShrub extends WorldGenAbstractShrub
{
    public WorldGenInfectedShrub()
    {
        super(MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
    }
}