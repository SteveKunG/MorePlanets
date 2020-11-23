package stevekung.mods.moreplanets.planets.fronos.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BiomeFronosMellow extends BiomeFronos
{
    //    private static final WorldGenDoublePlantMP TALL_GRASS = new WorldGenDoublePlantMP(MPBlocks.INFECTED_TALL_GRASS);

    public BiomeFronosMellow(BiomeProperties prop)
    {
        super(prop);
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 120;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return this == MPBiomes.FRONOS_MELLOW ? ColorUtils.rgbToDecimal(100, 173, 54) : ColorUtils.rgbToDecimal(124, 173, 38);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        int base = this == MPBiomes.FRONOS_MELLOW ? 200 : 100;
        double grassColorNoise = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (grassColorNoise < -0.8D)
        {
            this.decorator.grassPerChunk = base;
        }
        else
        {
            this.decorator.grassPerChunk = base + 20;

            //            for (int i = 0; i < 7; ++i)
            //            {
            //                BiomeFronosMellow.TALL_GRASS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
            //            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return this == MPBiomes.FRONOS_MELLOW ? FROLIA : rand.nextInt(3) == 0 ? FROLIA : OSCALEA;
    }
}