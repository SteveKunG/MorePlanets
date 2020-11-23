package stevekung.mods.moreplanets.planets.fronos.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BiomeFronosForest extends BiomeFronos
{
    public BiomeFronosForest(BiomeProperties prop)
    {
        super(prop);
        this.decorator.treesPerChunk = 10;
        this.decorator.grassPerChunk = 80;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return ColorUtils.rgbToDecimal(78, 140, 36);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(20) > 0)
        {
            return OSCALEA;
        }
        else
        {
            return FROLIA;
        }
    }
}