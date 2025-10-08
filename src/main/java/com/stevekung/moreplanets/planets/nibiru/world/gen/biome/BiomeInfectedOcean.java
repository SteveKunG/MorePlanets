package com.stevekung.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class BiomeInfectedOcean extends BiomeNibiru
{
    public BiomeInfectedOcean(BiomeProperties prop)
    {
        super(prop);
        this.decorator.flowersPerChunk = 4;
    }

    @Override
    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return MPBlocks.INFECTED_SEAWEED.getDefaultState();
    }

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(MPBlocks.INFECTED_SEAWEED.getDefaultState(), 20);
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }
}