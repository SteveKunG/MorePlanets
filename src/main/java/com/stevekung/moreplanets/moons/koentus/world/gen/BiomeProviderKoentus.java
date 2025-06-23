package com.stevekung.moreplanets.moons.koentus.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;
import com.stevekung.moreplanets.init.MPBiomes;

public class BiomeProviderKoentus extends BiomeProviderSpace
{
    @Override
    public Biome getBiome()
    {
        return MPBiomes.KOENTUS;
    }
}