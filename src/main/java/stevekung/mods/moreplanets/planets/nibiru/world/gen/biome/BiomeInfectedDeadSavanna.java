package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadSavannaTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDeadSavanna extends BiomeNibiru
{
    public BiomeInfectedDeadSavanna(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = MPBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = MPBlocks.NIBIRU_ROCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 20;
        this.getBiomeDecorator().infectedTreesPerChunk = 1;
        this.getBiomeDecorator().philipyPerChunk = 4;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.getBiomeDecorator().pureHurbPerChunk = -999;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, HOT, SAVANNA, PLAINS, SPARSE, DEAD);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(5) > 0)
        {
            return new WorldGenInfectedTrees(false, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
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
                BlockPos newpos = WorldDecorateUtils.getSimplePos(world, pos, rand);

                if (world.getBlockState(newpos).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
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
            new WorldGenDoublePlantMP(MPBlocks.INFECTED_TALL_GRASS).generate(world, rand, pos.add(x, y, z));
        }
        super.decorate(world, rand, pos);
    }
}