package com.stevekung.moreplanets.planets.diona.world.gen;

import com.stevekung.moreplanets.planets.diona.entity.EntityAlienMiner;
import com.stevekung.moreplanets.planets.diona.entity.EntityZeliusCreeper;
import com.stevekung.moreplanets.planets.diona.entity.EntityZeliusSkeleton;
import com.stevekung.moreplanets.planets.diona.entity.EntityZeliusZombie;
import com.stevekung.moreplanets.utils.world.gen.biome.BiomeMP;

import net.minecraft.world.biome.Biome;

public class BiomeDiona extends BiomeMP
{
    public BiomeDiona(BiomeProperties prop)
    {
        super(prop);
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZeliusZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZeliusCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZeliusSkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityAlienMiner.class, 1, 1, 2));
    }
}