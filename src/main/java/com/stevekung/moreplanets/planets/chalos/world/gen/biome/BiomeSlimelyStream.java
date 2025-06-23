package com.stevekung.moreplanets.planets.chalos.world.gen.biome;

import com.stevekung.moreplanets.init.MPBlocks;

public class BiomeSlimelyStream extends BiomeChalos
{
    public BiomeSlimelyStream(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.decorator.treesPerChunk = -999;
        this.decorator.flowersPerChunk = -999;
        this.decorator.cheeseSporeStemPerChunk = -999;
        this.decorator.grassPerChunk = -999;
    }
}