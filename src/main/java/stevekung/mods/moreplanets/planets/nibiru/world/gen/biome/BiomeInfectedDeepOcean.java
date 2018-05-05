package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedDeepOcean extends BiomeNibiru
{
    public BiomeInfectedDeepOcean(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
        this.getBiomeDecorator().seaweedPerChunk = 4;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, OCEAN, DEAD);
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}