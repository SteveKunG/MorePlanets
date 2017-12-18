package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import net.minecraft.world.biome.BiomeGenBase;
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
    protected void generate(BiomeGenBase biome)
    {
        int i;

        for (i = 0; i < this.cheeseSporeFlowerPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(ChalosBlocks.CHEESE_SPORE_FLOWER.getDefaultState()), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.cheeseTallGrassPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(ChalosBlocks.CHEESE_TALL_GRASS.getDefaultState()), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }

        int chance;
        chance = this.cheeseSporeTreePerChunk;

        if (this.randomGenerator.nextInt(1) == 0)
        {
            --chance;
        }
        if (this.randomGenerator.nextInt(6) == 0)
        {
            ++chance;
        }

        for (i = 0; i < chance; ++i)
        {
            DecorateHelper.generateCustomTrees(new WorldGenCheeseSporeTree(6 + this.randomGenerator.nextInt(4), true), this.currentWorld, this.randomGenerator, biome, this.field_180294_c);
        }

        chance = this.cheeseSporeStemPerChunk;

        if (this.randomGenerator.nextInt(1) == 0)
        {
            --chance;
        }
        if (this.randomGenerator.nextInt(8) == 0)
        {
            ++chance;
        }

        for (i = 0; i < chance; ++i)
        {
            DecorateHelper.generateCustomTrees(new WorldGenCheeseSporeTree(6 + this.randomGenerator.nextInt(4), false), this.currentWorld, this.randomGenerator, biome, this.field_180294_c);
        }
    }
}