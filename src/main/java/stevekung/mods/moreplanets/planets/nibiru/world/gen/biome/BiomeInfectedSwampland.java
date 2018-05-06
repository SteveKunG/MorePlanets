package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedSwampTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenNibiruFossils;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedSwampland extends BiomeNibiru
{
    public BiomeInfectedSwampland(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = MPBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = MPBlocks.NIBIRU_ROCK.getDefaultState();
        this.getBiomeDecorator().infectedTreesPerChunk = 2;
        this.getBiomeDecorator().infectedTallGrassPerChunk = 5;
        this.getBiomeDecorator().waterlilyPerChunk = 4;
        this.getBiomeDecorator().whiteTailPerChunk = 4;
        this.getBiomeDecorator().clayPerChunk = 1;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.getBiomeDecorator().sandPatchesPerChunk = 0;
        this.getBiomeDecorator().gravelPatchesPerChunk = 0;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, WET, SWAMP, DEAD);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        if (rand.nextInt(64) == 0)
        {
            new WorldGenNibiruFossils().generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
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
                    if (k == 62 && chunkPrimer.getBlockState(j, k, i).getBlock() != MPBlocks.INFECTED_WATER_FLUID_BLOCK)
                    {
                        chunkPrimer.setBlockState(j, k, i, MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());

                        if (d0 < 0.12D)
                        {
                            chunkPrimer.setBlockState(j, k + 1, i, MPBlocks.SPORELILY.getDefaultState());
                        }
                    }
                    break;
                }
            }
        }
        this.genPlanetTerrain(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}