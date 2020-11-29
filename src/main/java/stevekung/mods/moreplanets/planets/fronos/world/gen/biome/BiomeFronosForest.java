package stevekung.mods.moreplanets.planets.fronos.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeFronosForest extends BiomeFronos
{
    private static final WorldGenFlowersBase FERN = new WorldGenFlowersBase(MPBlocks.FRONOS_FERN.getDefaultState());
    private static final WorldGenFlowersBase BUSH = new WorldGenFlowersBase(MPBlocks.PURPLE_BUSH.getDefaultState());

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
    public void decorate(World world, Random rand, BlockPos pos)
    {
        BUSH.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        FERN.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        super.decorate(world, rand, pos);
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