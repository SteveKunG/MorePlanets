package com.stevekung.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import com.stevekung.lib.utils.WorldDecorateUtils;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeInfectedPlains extends BiomeNibiru
{
    private final boolean infested;
    private static final WorldGenDoublePlantMP TALL_GRASS = new WorldGenDoublePlantMP(MPBlocks.INFECTED_TALL_GRASS);

    public BiomeInfectedPlains(BiomeProperties prop, boolean infested)
    {
        super(prop);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 120;
        this.infested = infested;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double grassColorNoise = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (grassColorNoise < -0.8D)
        {
            this.decorator.grassPerChunk = 100;
        }
        else
        {
            this.decorator.grassPerChunk = 120;

            for (int i = 0; i < 7; ++i)
            {
                BiomeInfectedPlains.TALL_GRASS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
            }
        }
        if (this.infested)
        {
            for (int i = 0; i < 16; ++i)
            {
                BlockPos blockPos = WorldDecorateUtils.getSimplePos(world, pos, rand);

                if (world.getBlockState(blockPos).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
                {
                    BiomeNibiru.SCATTERED_DIRT.generate(world, rand, blockPos);
                }
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(3) == 0 ? BiomeNibiru.BIG_TREE : BiomeNibiru.TREE;
    }
}