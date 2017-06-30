package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFossils;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedSwampTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;

public class BiomeInfectedSwampland extends BiomeNibiru
{
    public BiomeInfectedSwampland(BiomeProperties properties)
    {
        super(properties);
        properties.setTemperature(0.8F);
        properties.setRainfall(0.9F);
        properties.setBaseHeight(-0.2F);
        properties.setHeightVariation(0.1F);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTreesPerChunk = 2;
        this.getBiomeDecorator().infectedTallGrassPerChunk = 5;
        this.getBiomeDecorator().waterlilyPerChunk = 4;
        this.getBiomeDecorator().whiteTailPerChunk = 4;
        this.getBiomeDecorator().clayPerChunk = 1;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.getBiomeDecorator().sandPerChunk2 = 0;
        this.getBiomeDecorator().sandPerChunk = 0;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, DecorateHelper.getSimplePos(world, pos, rand));
        }
        if (rand.nextInt(64) == 0)
        {
            new WorldGenFossils().generate(world, rand, DecorateHelper.getSimplePos(world, pos, rand));//TODO Reformat structure files
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return rand.nextInt(20) == 0 ? new WorldGenInfectedSwampTree(false) : new WorldGenInfectedSwampTree(true);
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noise)
    {
        double d0 = Biome.GRASS_COLOR_NOISE.getValue(chunkX * 0.25D, chunkZ * 0.25D);

        if (d0 > 0.0D)
        {
            int i = chunkX & 15;
            int j = chunkZ & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (chunkPrimer.getBlockState(j, k, i).getMaterial() != Material.AIR)
                {
                    if (k == 62 && chunkPrimer.getBlockState(j, k, i).getBlock() != NibiruBlocks.INFECTED_WATER_FLUID_BLOCK)
                    {
                        chunkPrimer.setBlockState(j, k, i, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());

                        if (d0 < 0.12D)
                        {
                            chunkPrimer.setBlockState(j, k + 1, i, NibiruBlocks.SPORELILY.getDefaultState());
                        }
                    }
                    break;
                }
            }
        }
        this.genPlanetTerrain(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}