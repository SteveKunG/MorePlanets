package com.stevekung.moreplanets.planets.nibiru.world.gen.feature;

import com.stevekung.lib.world.gen.WorldGenAbstractBigTree;
import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.block.Block;

public class WorldGenAlienBerryBigTree extends WorldGenAbstractBigTree
{
    public WorldGenAlienBerryBigTree()
    {
        super(MPBlocks.ALIEN_BERRY_OAK_LOG.getDefaultState(), MPBlocks.ALIEN_BERRY_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.GREEN_VEIN_GRASS_BLOCK;
    }
}