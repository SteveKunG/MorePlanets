package com.stevekung.moreplanets.planets.diona.world.gen;

import com.stevekung.moreplanets.init.MPBiomes;

import net.minecraft.world.biome.Biome;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;

public class BiomeProviderDiona extends BiomeProviderSpace
{
    @Override
    public Biome getBiome()
    {
        return MPBiomes.DIONA;
    }
}