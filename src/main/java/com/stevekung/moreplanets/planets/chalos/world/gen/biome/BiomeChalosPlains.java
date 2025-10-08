package com.stevekung.moreplanets.planets.chalos.world.gen.biome;

import java.util.Random;

import com.stevekung.lib.utils.WorldDecorateUtils;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BiomeChalosPlains extends BiomeChalos
{
    private static final WorldGenDoublePlantMP TALL_GRASS = new WorldGenDoublePlantMP(MPBlocks.CHEESE_TALL_GRASS);

    public BiomeChalosPlains(BiomeProperties prop)
    {
        super(prop);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.15F;
        this.decorator.cheeseSporeStemPerChunk = 0;
        this.decorator.extraStemChance = 0.01F;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 120;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 7; ++i)
        {
            BiomeChalosPlains.TALL_GRASS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }
}