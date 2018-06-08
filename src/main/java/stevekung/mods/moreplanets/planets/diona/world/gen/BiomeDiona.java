package stevekung.mods.moreplanets.planets.diona.world.gen;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusCreeper;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusSkeleton;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.utils.world.gen.biome.BiomeMP;

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