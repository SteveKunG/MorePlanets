package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenMultalicCrystal;
import stevekung.mods.stevekunglib.world.gen.WorldGenMinableBase;

public class BiomeInfectedExtremeHills extends BiomeNibiru
{
    private static final WorldGenMultalicCrystal CRYSTAL = new WorldGenMultalicCrystal();
    private static final WorldGenMinableBase INFESTED_ROCK = new WorldGenMinableBase(MPBlocks.INFESTED_NIBIRU_ROCK.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), 8);

    public BiomeInfectedExtremeHills(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTallGrassPerChunk = 2;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(3) > 0)
        {
            return new WorldGenInfectedDeadTaiga2(true);
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? BiomeNibiru.BIG_TREE_NO_LEAVES : BiomeNibiru.TREE_NO_LEAVES;
        }
        else
        {
            return super.getRandomTreeFeature(rand);
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int count = 3 + rand.nextInt(6);

        for (int i = 0; i < count; i++)
        {
            BlockPos blockpos = pos.add(rand.nextInt(16), rand.nextInt(28) + 4, rand.nextInt(16));

            if (world.getBlockState(blockpos) == MPBlocks.NIBIRU_ROCK.getDefaultState())
            {
                world.setBlockState(blockpos, MPBlocks.INFECTED_EMERALD_ORE.getDefaultState(), 2);
            }
        }
        for (int i = 0; i < 7; ++i)
        {
            BiomeInfectedExtremeHills.INFESTED_ROCK.generate(world, rand, pos.add(rand.nextInt(16), rand.nextInt(64), rand.nextInt(16)));
        }
        for (int i = 0; i < 24; i++)
        {
            BiomeInfectedExtremeHills.CRYSTAL.generate(world, rand, pos.add(rand.nextInt(16), rand.nextInt(28) + 4, rand.nextInt(16)));
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        this.topBlock = MPBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.INFECTED_DIRT.getDefaultState();

        if (noiseVal > 1.0D)
        {
            this.topBlock = MPBlocks.NIBIRU_ROCK.getDefaultState();
            this.fillerBlock = MPBlocks.NIBIRU_ROCK.getDefaultState();
        }
        super.genTerrainBlocks(world, rand, primer, chunkX, chunkZ, noiseVal);
    }
}