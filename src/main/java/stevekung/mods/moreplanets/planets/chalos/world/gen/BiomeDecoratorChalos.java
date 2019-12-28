package stevekung.mods.moreplanets.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.utils.blocks.BlockSaplingMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeDecoratorChalos extends BiomeDecoratorMP
{
    public int cheeseSporeStemPerChunk = 1;
    public float extraStemChance = 0.1F;

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        for (int i = 0; i < this.flowersPerChunk; ++i)
        {
            WorldDecorateUtils.generatePlants(new WorldGenFlowersBase(MPBlocks.CHEESE_SPORE_FLOWER.getDefaultState().withProperty(BlockSaplingMP.NATURAL_GEN, true)), world, rand, this.chunkPos);
        }
        for (int i = 0; i < this.grassPerChunk; ++i)
        {
            WorldDecorateUtils.generatePlants(new WorldGenFlowersBase(MPBlocks.CHEESE_GRASS.getDefaultState()), world, rand, this.chunkPos);
        }

        int chance = this.treesPerChunk;

        if (rand.nextFloat() < this.extraTreeChance)
        {
            ++chance;
        }

        for (int i = 0; i < chance; ++i)
        {
            WorldDecorateUtils.generateCustomTrees(new WorldGenCheeseSporeTree(6 + rand.nextInt(4), true), world, rand, biome, this.chunkPos);
        }

        chance = this.cheeseSporeStemPerChunk;

        if (rand.nextFloat() < this.extraStemChance)
        {
            ++chance;
        }

        for (int i = 0; i < chance; ++i)
        {
            WorldDecorateUtils.generateCustomTrees(new WorldGenCheeseSporeTree(6 + rand.nextInt(4), false), world, rand, biome, this.chunkPos);
        }
    }
}