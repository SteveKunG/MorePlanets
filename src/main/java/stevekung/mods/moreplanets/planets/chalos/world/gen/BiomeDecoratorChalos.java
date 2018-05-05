package stevekung.mods.moreplanets.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeDecoratorChalos extends BiomeDecoratorMP
{
    public int cheeseSporeFlowerPerChunk = 4;
    public int cheeseTallGrassPerChunk = 120;
    public int cheeseSporeTreePerChunk = 1;
    public int cheeseSporeStemPerChunk = 1;

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int i;

        for (i = 0; i < this.cheeseSporeFlowerPerChunk; ++i)
        {
            WorldDecorateUtils.generatePlants(new WorldGenFlowersBase(ChalosBlocks.CHEESE_SPORE_FLOWER.getDefaultState()), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.cheeseTallGrassPerChunk; ++i)
        {
            WorldDecorateUtils.generatePlants(new WorldGenFlowersBase(ChalosBlocks.CHEESE_GRASS.getDefaultState()), world, rand, this.chunkPos);
        }

        int chance;
        chance = this.cheeseSporeTreePerChunk;

        if (rand.nextInt(1) == 0)
        {
            --chance;
        }
        if (rand.nextInt(6) == 0)
        {
            ++chance;
        }

        for (i = 0; i < chance; ++i)
        {
            WorldDecorateUtils.generateCustomTrees(new WorldGenCheeseSporeTree(6 + rand.nextInt(4), true), world, rand, biome, this.chunkPos);
        }

        chance = this.cheeseSporeStemPerChunk;

        if (rand.nextInt(1) == 0)
        {
            --chance;
        }
        if (rand.nextInt(8) == 0)
        {
            ++chance;
        }

        for (i = 0; i < chance; ++i)
        {
            WorldDecorateUtils.generateCustomTrees(new WorldGenCheeseSporeTree(6 + rand.nextInt(4), false), world, rand, biome, this.chunkPos);
        }
    }
}