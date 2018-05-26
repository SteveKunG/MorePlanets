package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;

public class BiomeInfectedOcean extends BiomeNibiru
{
    public BiomeInfectedOcean(BiomeProperties prop)
    {
        super(prop);
        this.decorator.seaweedPerChunk = 4;
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}