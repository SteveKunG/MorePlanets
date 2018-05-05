package stevekung.mods.moreplanets.planets.chalos.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.planets.chalos.blocks.ChalosBlocks;

public class BiomeChalosHills extends BiomeChalos
{
    public BiomeChalosHills(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = ChalosBlocks.CHEESE_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_ROCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, MOUNTAIN, HILLS);
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noise)
    {
        this.topBlock = ChalosBlocks.CHEESE_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();

        if (noise > 1.0D)
        {
            this.topBlock = ChalosBlocks.CHALOS_ROCK.getDefaultState();
            this.fillerBlock = ChalosBlocks.CHALOS_ROCK.getDefaultState();
        }
        this.genChalosBiomeTerrain(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}