package stevekung.mods.moreplanets.planets.nibiru.world.gen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layer.*;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernew.GenLayerNibiruBiome;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernew.GenLayerNibiruBiomeEdge;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernew.GenLayerNibiruHills;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernewtest.GenLayerNibiruAddIsland;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernewtest.GenLayerNibiruIsland;

public class BiomeProviderNibiru extends BiomeProvider
{
    public BiomeProviderNibiru(long seed)
    {
        this.biomeCache = new BiomeCache(this);
        this.getBiomesToSpawnIn().clear();
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_PLAINS);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_FOREST);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_TAIGA);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_MOUNTAINS);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_SNOWY_TUNDRA);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_JUNGLE);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_SWAMP);
        this.getBiomesToSpawnIn().add(MPBiomes.GREEN_VEIN_BADLANDS);
        this.initLayers(seed);
    }

    @Override
    public Biome getBiome(BlockPos pos)
    {
        return this.getBiome(pos, null);
    }

    @Override
    public Biome getBiome(BlockPos pos, Biome defaultBiome)
    {
        return this.biomeCache.getBiome(pos.getX(), pos.getZ(), defaultBiome);
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        try
        {
            for (int i = 0; i < width * height; ++i)
            {
                biomes[i] = Biome.getBiome(aint[i], MPBiomes.INFECTED_OCEAN);
            }
            return biomes;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("w", Integer.valueOf(width));
            crashreportcategory.addCrashSection("h", Integer.valueOf(height));
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth)
    {
        return this.getBiomes(oldBiomeList, x, z, width, depth, true);
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new Biome[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            Biome[] abiome = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiome, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = Biome.getBiome(aint[i], MPBiomes.INFECTED_OCEAN);
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
            int[] aint = this.genBiomes.getInts(i, j, i1, j1);

            try
            {
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
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
                crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
                crashreportcategory.addCrashSection("x", Integer.valueOf(x));
                crashreportcategory.addCrashSection("z", Integer.valueOf(z));
                crashreportcategory.addCrashSection("radius", Integer.valueOf(radius));
                crashreportcategory.addCrashSection("allowed", allowed);
                throw new ReportedException(crashreport);
            }
    }

    @Override
    @Nullable
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random)
    {
        IntCache.resetIntCache();
        int i = x - range >> 2;
                int j = z - range >> 2;
                int k = x + range >> 2;
                int l = z + range >> 2;
                int i1 = k - i + 1;
                int j1 = l - j + 1;
                int[] aint = this.genBiomes.getInts(i, j, i1, j1);
                BlockPos blockpos = null;
                int k1 = 0;

                for (int l1 = 0; l1 < i1 * j1; ++l1)
                {
                    int i2 = i + l1 % i1 << 2;
                    int j2 = j + l1 / i1 << 2;
                    Biome biome = Biome.getBiome(aint[l1]);

                    if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0))
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

    private void initLayers(long seed)
    {
        int i = 4;
        GenLayer genlayer = new GenLayerNibiruIsland(1L);
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        GenLayer genlayeraddisland = new GenLayerNibiruAddIsland(1L, genlayer);
        GenLayer genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        GenLayer genlayeraddisland1 = new GenLayerNibiruAddIsland(2L, genlayerzoom);
        genlayeraddisland1 = new GenLayerNibiruAddIsland(50L, genlayeraddisland1);
        genlayeraddisland1 = new GenLayerNibiruAddIsland(70L, genlayeraddisland1);
        GenLayer genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland1);
        GenLayer genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        GenLayer genlayeraddisland2 = new GenLayerNibiruAddIsland(3L, genlayeraddsnow);
        GenLayer genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        GenLayer genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
        GenLayer genlayeraddisland3 = new GenLayerNibiruAddIsland(4L, genlayerzoom1);
        GenLayer genlayeraddmushroomisland = new GenLayerAddGreenVeinBadlands(5L, genlayeraddisland3);
        GenLayer genlayerdeepocean = new GenLayerInfectedDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer4 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        GenLayer lvt_7_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
        GenLayer genlayerriverinit = new GenLayerRiverInit(100L, lvt_7_1_);
        GenLayer genlayerbiomeedge = this.getBiomeLayer(genlayer4);
        GenLayer lvt_9_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayer genlayerhills = new GenLayerNibiruHills(1000L, genlayerbiomeedge, lvt_9_1_);
        GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, i);
        GenLayer genlayerriver = new GenLayerInfectedRiver(1L, genlayer5);
        GenLayer genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        genlayerhills = new GenLayerNibiruRareBiome(1001L, genlayerhills);

        for (int k = 0; k < i; ++k)
        {
            genlayerhills = new GenLayerZoom(1000L + k, genlayerhills);

            if (k == 0)
            {
                genlayerhills = new GenLayerNibiruAddIsland(3L, genlayerhills);
            }

            if (k == 1 || i == 1)
            {
                genlayerhills = new GenLayerNibiruShore(1000L, genlayerhills);
            }
        }

        GenLayer genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
        GenLayer genlayerrivermix = new GenLayerNibiruRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayer3.initWorldGenSeed(seed);
        this.genBiomes = genlayerrivermix;
        this.biomeIndexLayer = genlayer3;
    }

    private GenLayer getBiomeLayer(GenLayer parent)
    {
        GenLayer layer = new GenLayerNibiruBiome(200L, parent);
        layer = GenLayerZoom.magnify(1000L, layer, 2);
        layer = new GenLayerNibiruBiomeEdge(1000L, layer);
        return layer;
    }
}