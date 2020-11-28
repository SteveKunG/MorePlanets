package stevekung.mods.moreplanets.planets.fronos.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.fronos.world.gen.feature.WorldGenFronosShrub;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeFronosMeadow extends BiomeFronos
{
    private static final WorldGenDoublePlantMP TALL_GRASS = new WorldGenDoublePlantMP(MPBlocks.FRONOS_TALL_GRASS);
    private static final WorldGenFronosShrub SHRUB = new WorldGenFronosShrub();
    private boolean isMeadow;

    public BiomeFronosMeadow(BiomeProperties prop, boolean isMeadow)
    {
        super(prop);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 120;
        this.decorator.largeWheatPerChunk = 1;
        this.isMeadow = isMeadow;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return this.isMeadow ? ColorUtils.rgbToDecimal(100, 173, 54) : ColorUtils.rgbToDecimal(124, 173, 38);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        int base = this.isMeadow ? 200 : 100;
        double grassColorNoise = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 250.0D, (pos.getZ() + 8) / 250.0D);

        if (grassColorNoise < -0.8D)
        {
            this.decorator.grassPerChunk = base;
        }
        else
        {
            this.decorator.grassPerChunk = base + 20;

            for (int i = 0; i < 7; ++i)
            {
                BiomeFronosMeadow.TALL_GRASS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
            }
        }

        if (this.isMeadow && rand.nextInt(2) == 0)
        {
            BiomeFronosMeadow.SHRUB.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return this.isMeadow ? FROLIA : rand.nextInt(3) == 0 ? FROLIA : OSCALEA;
    }
}