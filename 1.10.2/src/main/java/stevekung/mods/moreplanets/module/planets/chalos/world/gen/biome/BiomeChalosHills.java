package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class BiomeChalosHills extends BiomeChalos
{
    public BiomeChalosHills(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = ChalosBlocks.CHEESE_GRASS.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
        this.decorator.cheeseSporeTreePerChunk = 1;
        this.decorator.cheeseSporeStemPerChunk = 1;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noise)
    {
        this.topBlock = ChalosBlocks.CHEESE_GRASS.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();

        if (noise > 1.0D)
        {
            this.topBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
            this.fillerBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
        }
        this.genChalosBiomeTerrain(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}