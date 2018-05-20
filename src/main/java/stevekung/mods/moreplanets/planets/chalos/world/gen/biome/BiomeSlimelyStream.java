package stevekung.mods.moreplanets.planets.chalos.world.gen.biome;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BiomeSlimelyStream extends BiomeChalos
{
    public BiomeSlimelyStream(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}