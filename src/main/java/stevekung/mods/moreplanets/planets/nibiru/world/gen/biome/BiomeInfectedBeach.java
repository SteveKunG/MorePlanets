package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BiomeInfectedBeach extends BiomeNibiru
{
    public BiomeInfectedBeach(BiomeProperties prop, boolean isStone)
    {
        super(prop);
        this.topBlock = isStone ? MPBlocks.NIBIRU_ROCK.getDefaultState() : MPBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = isStone ? MPBlocks.NIBIRU_ROCK.getDefaultState() : MPBlocks.INFECTED_SAND.getDefaultState();
        this.decorator.reedsPerChunk = 0;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}