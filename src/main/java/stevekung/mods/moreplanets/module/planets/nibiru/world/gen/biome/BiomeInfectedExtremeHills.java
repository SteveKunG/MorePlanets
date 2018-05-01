package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;
import static net.minecraftforge.common.BiomeDictionary.Type.MOUNTAIN;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedBigTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenMultalicCrystal;
import stevekung.mods.stevekunglib.world.gen.WorldGenMinableBase;

public class BiomeInfectedExtremeHills extends BiomeNibiru
{
    public BiomeInfectedExtremeHills(BiomeProperties properties)
    {
        super(properties);
        this.stoneBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 2;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, MOUNTAIN, HILLS, DEAD);
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
            return rand.nextInt(5) == 0 ? new WorldGenInfectedBigTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : new WorldGenInfectedTrees(false, NibiruBlocks.NIBIRU_LOG.getDefaultState(), NibiruBlocks.NIBIRU_LEAVES.getDefaultState());
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

            if (world.getBlockState(blockpos) == NibiruBlocks.NIBIRU_ROCK.getDefaultState())
            {
                world.setBlockState(blockpos, NibiruBlocks.NIBIRU_ORE.getStateFromMeta(6), 2);
            }
        }
        for (int i = 0; i < 7; ++i)
        {
            int j1 = rand.nextInt(16);
            int k1 = rand.nextInt(64);
            int l1 = rand.nextInt(16);
            new WorldGenMinableBase(NibiruBlocks.INFESTED_NIBIRU_ROCK.getDefaultState(), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), 8).generate(world, rand, pos.add(j1, k1, l1));
        }
        for (int i = 0; i < 24; i++)
        {
            BlockPos blockpos = pos.add(rand.nextInt(16), rand.nextInt(28) + 4, rand.nextInt(16));
            new WorldGenMultalicCrystal().generate(world, rand, blockpos);
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noise)
    {
        this.topBlock = NibiruBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();

        if (noise > 1.0D)
        {
            this.topBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
            this.fillerBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
        }
        super.genTerrainBlocks(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}