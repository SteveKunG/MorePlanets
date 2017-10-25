package stevekung.mods.moreplanets.module.planets.nibiru.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.layer.GenLayerNibiru;

public class BiomeProviderNibiru extends BiomeProvider
{
    public static ArrayList<Biome> allowedBiomes = new ArrayList<>(Arrays.asList(MPBiomes.INFECTED_FOREST, MPBiomes.INFECTED_PLAINS, MPBiomes.INFECTED_DEAD_TAIGA, MPBiomes.INFECTED_EXTREME_HILLS, MPBiomes.INFECTED_ICE_PLAINS, MPBiomes.INFECTED_JUNGLE, MPBiomes.INFECTED_SWAMPLAND, MPBiomes.GREEN_VEIN));
    private BiomeCache biomeCache;
    private List<Biome> biomesToSpawn;
    private GenLayer zoomedBiomes;
    private GenLayer unzoomedBiomes;

    protected BiomeProviderNibiru()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawn = new ArrayList<>();
        this.biomesToSpawn.addAll(allowedBiomes);
    }

    public BiomeProviderNibiru(long seed)
    {
        this();
        GenLayer[] agenlayer = GenLayerNibiru.initializeAllBiomeGenerators(seed);
        this.unzoomedBiomes = agenlayer[0];
        this.zoomedBiomes = agenlayer[1];
    }

    @Override
    public List<Biome> getBiomesToSpawnIn()
    {
        return this.biomesToSpawn;
    }

    @Override
    public Biome getBiome(BlockPos pos)
    {
        return this.getBiome(pos, (Biome)null);
    }

    @Override
    public Biome getBiome(BlockPos pos, Biome biome)
    {
        return this.biomeCache.getBiome(pos.getX(), pos.getZ(), biome);
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int arrayOfInts[] = this.unzoomedBiomes.getInts(x, z, width, height);

        for (int i = 0; i < width * height; i++)
        {
            if (arrayOfInts[i] >= 0)
            {
                biomes[i] = Biome.getBiome(arrayOfInts[i]);
            }
            else
            {
                biomes[i] = MPBiomes.INFECTED_PLAINS;
            }
        }
        return biomes;
    }

    @Override
    public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int height)
    {
        return this.getBiomes(oldBiomeList, x, z, width, height, true);
    }

    @Override
    public Biome[] getBiomes(Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new Biome[width * length];
        }
        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            Biome[] abiomegenbase = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase, 0, listToReuse, 0, width * length);
            return listToReuse;
        }

        int ai[] = this.zoomedBiomes.getInts(x, z, width, length);

        for (int i = 0; i < width * length; i++)
        {
            if (ai[i] >= 0)
            {
                listToReuse[i] = Biome.getBiome(ai[i]);
            }
            else
            {
                listToReuse[i] = MPBiomes.INFECTED_PLAINS;
            }
        }
        return listToReuse;
    }

    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed)
    {
        IntCache.resetIntCache();
        int i = x - radius >> 2;
        int j = z - radius >> 2;
        int k = x + radius >> 2;
        int l = z + radius >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.unzoomedBiomes.getInts(i, j, i1, j1);

        for (int k1 = 0; k1 < i1 * j1; ++k1)
        {
            Biome biomegenbase = Biome.getBiome(aint[k1]);

            if (!allowed.contains(biomegenbase))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random rand)
    {
        IntCache.resetIntCache();
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.unzoomedBiomes.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biomegenbase = Biome.getBiome(aint[l1]);

            if (biomes.contains(biomegenbase) && (blockpos == null || rand.nextInt(k1 + 1) == 0))
            {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }
        return blockpos;
    }

    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}