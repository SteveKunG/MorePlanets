package stevekung.mods.moreplanets.planets.chalos.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPPlanets;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer.GenLayerChalos;

public class BiomeProviderChalos extends BiomeProvider
{
    public static ArrayList<Biome> allowedBiomes = new ArrayList<>(Arrays.asList(MPBiomes.CHALOS_PLAINS, MPBiomes.CHALOS_HILLS));
    private BiomeCache biomeCache;
    private List<Biome> biomesToSpawn;
    private GenLayer zoomedBiomes;
    private GenLayer unzoomedBiomes;

    protected BiomeProviderChalos()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawn = new ArrayList<>();
        this.biomesToSpawn.addAll(allowedBiomes);
    }

    public BiomeProviderChalos(long seed)
    {
        this();
        GenLayer[] agenlayer = GenLayerChalos.initializeAllBiomeGenerators(seed);
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
        BiomeAdaptive.setBodyMultiBiome(MPPlanets.CHALOS);
        return this.biomeCache.getBiome(pos.getX(), pos.getZ(), biome);
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        BiomeAdaptive.setBodyMultiBiome(MPPlanets.CHALOS);
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int[] aint = this.unzoomedBiomes.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i)
        {
            biomes[i] = Biome.getBiome(aint[i], BiomeAdaptive.biomeDefault);
        }
        return biomes;
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth)
    {
        return this.getBiomes(oldBiomeList, x, z, width, depth, true);
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        BiomeAdaptive.setBodyMultiBiome(MPPlanets.CHALOS);
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
        else
        {
            int[] aint = this.zoomedBiomes.getInts(x, z, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = Biome.getBiome(aint[i], BiomeAdaptive.biomeDefault);
            }
            return listToReuse;
        }
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
            Biome biome = Biome.getBiome(aint[k1]);

            if (!allowed.contains(biome))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random)
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

            if (biomes.contains(biomegenbase) && (blockpos == null || random.nextInt(k1 + 1) == 0))
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