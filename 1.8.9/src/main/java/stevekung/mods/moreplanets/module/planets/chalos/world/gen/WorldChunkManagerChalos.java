package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.layer.GenLayerChalos;

public class WorldChunkManagerChalos extends WorldChunkManager
{
    public static ArrayList<BiomeGenBase> allowedBiomes = Lists.newArrayList(Arrays.asList(MPBiomes.CHALOS_PLAINS, MPBiomes.CHALOS_HILLS));
    private BiomeCache biomeCache;
    private List<BiomeGenBase> biomesToSpawn;
    private GenLayer zoomedBiomes;
    private GenLayer unzoomedBiomes;

    protected WorldChunkManagerChalos()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawn = Lists.<BiomeGenBase>newArrayList();
        this.biomesToSpawn.addAll(allowedBiomes);
    }

    public WorldChunkManagerChalos(long seed)
    {
        this();
        GenLayer[] agenlayer = GenLayerChalos.initializeAllBiomeGenerators(seed);
        this.unzoomedBiomes = agenlayer[0];
        this.zoomedBiomes = agenlayer[1];
    }

    @Override
    public List<BiomeGenBase> getBiomesToSpawnIn()
    {
        return this.biomesToSpawn;
    }

    @Override
    public BiomeGenBase getBiomeGenerator(BlockPos pos)
    {
        return this.getBiomeGenerator(pos, (BiomeGenBase)null);
    }

    @Override
    public BiomeGenBase getBiomeGenerator(BlockPos pos, BiomeGenBase biome)
    {
        return this.biomeCache.func_180284_a(pos.getX(), pos.getZ(), biome);
    }

    @Override
    public float[] getRainfall(float[] listToReuse, int x, int z, int width, int length)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new float[width * length];
        }

        int[] aint = this.zoomedBiomes.getInts(x, z, width, length);

        for (int i = 0; i < width * length; ++i)
        {
            float f = BiomeGenBase.getBiomeFromBiomeList(aint[i], MPBiomes.CHALOS_PLAINS).getIntRainfall() / 65536.0F;

            if (f > 1.0F)
            {
                f = 1.0F;
            }
            listToReuse[i] = f;
        }
        return listToReuse;
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new BiomeGenBase[width * height];
        }

        int[] aint = this.unzoomedBiomes.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i)
        {
            biomes[i] = BiomeGenBase.getBiomeFromBiomeList(aint[i], MPBiomes.CHALOS_PLAINS);
        }
        return biomes;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] oldBiomeList, int x, int z, int width, int depth)
    {
        return this.getBiomeGenAt(oldBiomeList, x, z, width, depth, true);
    }

    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new BiomeGenBase[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            BiomeGenBase[] abiomegenbase = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else
        {
            int[] aint = this.zoomedBiomes.getInts(x, z, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = BiomeGenBase.getBiomeFromBiomeList(aint[i], MPBiomes.CHALOS_PLAINS);
            }
            return listToReuse;
        }
    }

    @Override
    public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List<BiomeGenBase> p_76940_4_)
    {
        IntCache.resetIntCache();
        int i = p_76940_1_ - p_76940_3_ >> 2;
            int j = p_76940_2_ - p_76940_3_ >> 2;
        int k = p_76940_1_ + p_76940_3_ >> 2;
        int l = p_76940_2_ + p_76940_3_ >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.unzoomedBiomes.getInts(i, j, i1, j1);

        for (int k1 = 0; k1 < i1 * j1; ++k1)
        {
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k1]);

            if (!p_76940_4_.contains(biomegenbase))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<BiomeGenBase> biomes, Random random)
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
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[l1]);

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