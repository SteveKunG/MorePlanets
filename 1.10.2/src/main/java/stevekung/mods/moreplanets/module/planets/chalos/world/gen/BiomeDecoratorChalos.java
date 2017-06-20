package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenFlowersMP;

public class BiomeDecoratorChalos extends BiomeDecoratorMP
{
    public int cheeseSporeFlowerPerChunk;
    public int cheeseTallGrassPerChunk;
    public int cheeseSporeTreePerChunk;
    public int cheeseSporeStemPerChunk;

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int i;

        for (i = 0; i < this.cheeseSporeFlowerPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(ChalosBlocks.CHEESE_SPORE_FLOWER.getDefaultState()), world, rand, this.chunkPos);
        }
        for (i = 0; i < this.cheeseTallGrassPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(ChalosBlocks.CHEESE_TALL_GRASS.getDefaultState()), world, rand, this.chunkPos);
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
            DecorateHelper.generateCustomTrees(new WorldGenCheeseSporeTree(6 + rand.nextInt(4), true), world, rand, biome, this.chunkPos);
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
            DecorateHelper.generateCustomTrees(new WorldGenCheeseSporeTree(6 + rand.nextInt(4), false), world, rand, biome, this.chunkPos);
        }
    }
}