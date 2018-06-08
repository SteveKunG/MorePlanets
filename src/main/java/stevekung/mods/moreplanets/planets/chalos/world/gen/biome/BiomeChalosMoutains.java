package stevekung.mods.moreplanets.planets.chalos.world.gen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBlocks;

public class BiomeChalosMoutains extends BiomeChalos
{
    public BiomeChalosMoutains(BiomeProperties prop)
    {
        super(prop);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.01F;
        this.decorator.cheeseSporeStemPerChunk = 0;
        this.decorator.extraStemChance = 0.1F;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 80;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        this.topBlock = MPBlocks.CHEESE_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.CHEESE_DIRT.getDefaultState();

        if (noiseVal > 1.0D)
        {
            this.topBlock = MPBlocks.CHALOS_ROCK.getDefaultState();
            this.fillerBlock = MPBlocks.CHALOS_ROCK.getDefaultState();
        }
        super.genTerrainBlocks(world, rand, primer, chunkX, chunkZ, noiseVal);
    }
}