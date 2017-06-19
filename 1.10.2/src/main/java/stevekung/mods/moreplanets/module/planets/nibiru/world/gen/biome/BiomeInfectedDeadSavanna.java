package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadSavannaTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruDoublePlant;

public class BiomeInfectedDeadSavanna extends BiomeNibiru
{
    public BiomeInfectedDeadSavanna()
    {
        super(ConfigManagerMP.idBiomeInfectedDeadSavanna);
        this.enableRain = false;
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.setTemperatureRainfall(1.2F, 0.0F);
        this.setHeight(new Height(0.125F, 0.05F));
        this.getBiomeDecorator().infectedTallGrassPerChunk = 128;
        this.getBiomeDecorator().infectedTreesPerChunk = 0;
        this.getBiomeDecorator().pureHurbPerChunk = -999;
        this.getBiomeDecorator().philipyPerChunk = 4;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.theBiomeDecorator.treesPerChunk = -999;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        if (rand.nextInt(5) > 0)
        {
            return new WorldGenInfectedTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        }
        else
        {
            return new WorldGenInfectedDeadSavannaTree();
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.func_151601_a((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (d0 > -0.8D)
        {
            for (int i = 0; i < 12; ++i)
            {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
                BlockPos newpos = pos.add(j, l, k);

                if (world.getBlockState(newpos).getBlock() == NibiruBlocks.INFECTED_GRASS)
                {
                    new WorldGenInfectedVinesDirt().generate(world, rand, newpos);
                }
            }
        }
        for (int i = 0; i < 7; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
            new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_GRASS).generate(world, rand, pos.add(j, l, k));
        }
        super.decorate(world, rand, pos);
    }
}