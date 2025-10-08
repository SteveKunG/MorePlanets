package com.stevekung.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import com.stevekung.lib.world.gen.WorldGenAbstractTree;
import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.block.Block;

public class WorldGenAlienBerryTree extends WorldGenAbstractTree
{
    public WorldGenAlienBerryTree()
    {
        super(MPBlocks.ALIEN_BERRY_OAK_LOG.getDefaultState(), MPBlocks.ALIEN_BERRY_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected int getTreeHeight(Random rand)
    {
        return rand.nextInt(3) + 5;
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.GREEN_VEIN_GRASS_BLOCK;
    }
}