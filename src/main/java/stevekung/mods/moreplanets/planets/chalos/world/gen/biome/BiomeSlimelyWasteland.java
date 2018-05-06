package stevekung.mods.moreplanets.planets.chalos.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.WASTELAND;

import java.util.LinkedList;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BiomeSlimelyWasteland extends BiomeChalos
{
    public BiomeSlimelyWasteland(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.stoneBlock = MPBlocks.CHALOS_ROCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, WASTELAND, DRY);
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}