package com.stevekung.moreplanets.planets.nibiru.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.lib.world.gen.WorldGenAbstractBigTree;

public class WorldGenInfectedBigTree extends WorldGenAbstractBigTree
{
    public WorldGenInfectedBigTree(boolean genLeaves, IBlockState log, IBlockState leaves)
    {
        super(log, leaves);
        this.genLeaves = genLeaves;
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
    }
}