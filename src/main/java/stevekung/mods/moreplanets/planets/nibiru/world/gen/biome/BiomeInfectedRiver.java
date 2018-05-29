package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;

public class BiomeInfectedRiver extends BiomeNibiru
{
    public BiomeInfectedRiver(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTallGrassPerChunk = 2;
        this.decorator.reedsPerChunk = 10;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}