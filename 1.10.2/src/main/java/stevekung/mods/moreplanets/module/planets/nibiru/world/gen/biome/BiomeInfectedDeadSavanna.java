package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadSavannaTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruDoublePlant;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;

public class BiomeInfectedDeadSavanna extends BiomeNibiru
{
    public BiomeInfectedDeadSavanna(BiomeProperties properties)
    {
        super(properties);
        properties.setRainDisabled();
        properties.setRainfall(0.0F);
        properties.setTemperature(1.2F);
        properties.setBaseHeight(0.125F);
        properties.setHeightVariation(0.05F);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 20;
        this.getBiomeDecorator().infectedTreesPerChunk = 1;
        this.getBiomeDecorator().philipyPerChunk = 4;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.getBiomeDecorator().pureHurbPerChunk = -999;
        this.theBiomeDecorator.treesPerChunk = -999;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        if (rand.nextInt(5) > 0)
        {
            return new WorldGenInfectedTrees(false, NibiruBlocks.NIBIRU_LOG.getDefaultState(), NibiruBlocks.NIBIRU_LEAVES.getDefaultState());
        }
        else
        {
            return new WorldGenInfectedDeadSavannaTree();
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (d0 > -0.8D)
        {
            for (int i = 0; i < 12; ++i)
            {
                BlockPos newpos = DecorateHelper.getSimplePos(world, pos, rand);

                if (world.getBlockState(newpos).getBlock() == NibiruBlocks.INFECTED_GRASS)
                {
                    new WorldGenInfectedVinesDirt().generate(world, rand, newpos);
                }
            }
        }
        for (int i = 0; i < 7; ++i)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_GRASS).generate(world, rand, pos.add(x, y, z));
        }
        super.decorate(world, rand, pos);
    }
}