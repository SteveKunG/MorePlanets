package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeDecoratorBase extends BiomeDecorator
{
    @Override
    protected void genDecorations(Biome biome, World world, Random rand)
    {
        this.generate(biome, world, rand);
    }

    @Override
    protected void generateOres(World world, Random rand) {}

    protected void generateOre(WorldGenerator generator, EnumOreGen oreGen, World world, Random rand)
    {
        this.generateOre(generator, oreGen.getBlockCount(), oreGen.getMinHeight(), oreGen.getMaxHeight(), world, rand);
    }

    protected void generateOre(WorldGenerator generator, int blockCount, int minHeight, int maxHeight, World world, Random rand)
    {
        if (maxHeight < minHeight)
        {
            int i = minHeight;
            minHeight = maxHeight;
            maxHeight = i;
        }
        else if (maxHeight == minHeight)
        {
            if (minHeight < 255)
            {
                ++maxHeight;
            }
            else
            {
                --minHeight;
            }
        }

        for (int j = 0; j < blockCount; ++j)
        {
            BlockPos blockpos = this.chunkPos.add(rand.nextInt(16), rand.nextInt(maxHeight - minHeight) + minHeight, rand.nextInt(16));
            generator.generate(world, rand, blockpos);
        }
    }

    protected void generateLapis(WorldGenerator generator, EnumOreGen oreGen, World world, Random rand)
    {
        this.generateLapis(generator, oreGen.getBlockCount(), oreGen.getMinHeight(), oreGen.getMaxHeight(), world, rand);
    }

    protected void generateLapis(WorldGenerator generator, int blockCount, int centerHeight, int spread, World world, Random rand)
    {
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = this.chunkPos.add(rand.nextInt(16), rand.nextInt(spread) + rand.nextInt(spread) + centerHeight - spread, rand.nextInt(16));
            generator.generate(world, rand, blockpos);
        }
    }

    protected abstract void generate(Biome biome, World world, Random rand);
}