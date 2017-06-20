package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedBigTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenMultalicCrystal;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenMinableMP;

public class BiomeInfectedExtremeHills extends BiomeNibiru
{
    public BiomeInfectedExtremeHills(BiomeProperties properties)
    {
        super(properties);
        properties.setSnowEnabled();
        properties.setTemperature(0.2F);
        properties.setRainfall(0.3F);
        properties.setBaseHeight(1.0F);
        properties.setHeightVariation(0.5F);
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 2;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        if (rand.nextInt(3) > 0)
        {
            return new WorldGenInfectedDeadTaiga2(true);
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? new WorldGenInfectedBigTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : new WorldGenInfectedTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        }
        else
        {
            return super.genBigTreeChance(rand);
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int k = 3 + rand.nextInt(6);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = rand.nextInt(16);
            j1 = rand.nextInt(28) + 4;
            int k1 = rand.nextInt(16);

            if (world.getBlockState(pos.add(i1, j1, k1)) == NibiruBlocks.NIBIRU_BLOCK.getDefaultState())
            {
                world.setBlockState(pos.add(i1, j1, k1), NibiruBlocks.NIBIRU_ORE.getStateFromMeta(6), 2);
            }
        }
        for (k = 0; k < 7; ++k)
        {
            l = rand.nextInt(16);
            i1 = rand.nextInt(64);
            j1 = rand.nextInt(16);
            new WorldGenMinableMP(NibiruBlocks.NIBIRU_SILVERFISH_STONE.getDefaultState(), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), 8).generate(world, rand, pos.add(l, i1, j1));
        }

        for (int i = 0; i < 24; i++)
        {
            new WorldGenMultalicCrystal().generate(world, rand, pos.add(rand.nextInt(16), rand.nextInt(28), rand.nextInt(16)));
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noise)
    {
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();

        if (noise > 1.0D)
        {
            this.topBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
            this.fillerBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        }
        this.genPlanetTerrain(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}