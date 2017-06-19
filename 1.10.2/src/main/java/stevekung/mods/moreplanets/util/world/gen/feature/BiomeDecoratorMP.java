package stevekung.mods.moreplanets.util.world.gen.feature;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeDecoratorMP extends BiomeDecorator
{
    @Override
    protected void genDecorations(BiomeGenBase biome)
    {
        this.generate(biome);
    }

    @Override
    protected void generateOres() {}

    protected void generateOre(WorldGenerator generator, EnumOreGen oreGen)
    {
        this.generateOre(generator, oreGen.getBlockCount(), oreGen.getMinHeight(), oreGen.getMaxHeight());
    }

    protected void generateOre(WorldGenerator generator, int blockCount, int minHeight, int maxHeight)
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
            BlockPos blockpos = this.field_180294_c.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(maxHeight - minHeight) + minHeight, this.randomGenerator.nextInt(16));
            generator.generate(this.currentWorld, this.randomGenerator, blockpos);
        }
    }

    protected void generateLapis(WorldGenerator generator, EnumOreGen oreGen)
    {
        this.generateLapis(generator, oreGen.getBlockCount(), oreGen.getMinHeight(), oreGen.getMaxHeight());
    }

    protected void generateLapis(WorldGenerator generator, int blockCount, int centerHeight, int spread)
    {
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = this.field_180294_c.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(spread) + this.randomGenerator.nextInt(spread) + centerHeight - spread, this.randomGenerator.nextInt(16));
            generator.generate(this.currentWorld, this.randomGenerator, blockpos);
        }
    }

    protected abstract void generate(BiomeGenBase biome);
}