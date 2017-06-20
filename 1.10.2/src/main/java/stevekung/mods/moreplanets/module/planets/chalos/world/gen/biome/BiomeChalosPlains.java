package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature.WorldGenCheeseDoubleTallGrass;

public class BiomeChalosPlains extends BiomeChalos
{
    public BiomeChalosPlains(BiomeProperties properties)
    {
        super(properties);
        properties.setTemperature(0.8F);
        properties.setRainfall(0.4F);
        properties.setBaseHeight(0.125F);
        properties.setHeightVariation(0.05F);
        this.topBlock = ChalosBlocks.CHEESE_GRASS.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
        this.getBiomeDecorator().cheeseSporeFlowerPerChunk = 2;
        this.getBiomeDecorator().cheeseTallGrassPerChunk = 128;
        this.getBiomeDecorator().cheeseSporeTreePerChunk = 1;
        this.getBiomeDecorator().cheeseSporeStemPerChunk = 1;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 7; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
            new WorldGenCheeseDoubleTallGrass().generate(world, rand, pos.add(j, l, k));
        }
        super.decorate(world, rand, pos);
    }
}