package stevekung.mods.moreplanets.planets.nibiru.world.gen;

import javax.annotation.Nullable;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layer.*;

public class BiomeProviderNibiru extends BiomeProvider
{
    public BiomeProviderNibiru(long seed)
    {
        this.biomeCache = new BiomeCache(this);
        this.getBiomesToSpawnIn().clear();
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_FOREST);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_WOODED_HILLS);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_PLAINS);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_TAIGA);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_TAIGA_HILLS);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_JUNGLE);
        this.getBiomesToSpawnIn().add(MPBiomes.INFECTED_JUNGLE_HILLS);
        this.getBiomesToSpawnIn().add(MPBiomes.GREEN_VEIN_FIELDS);
        this.initLayers(seed);
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();
        int size = width * height;
        int[] aint = this.genBiomes.getInts(x, z, width, height);

        if (biomes == null || biomes.length < size)
        {
            biomes = new Biome[size];
        }
        for (int i = 0; i < size; ++i)
        {
            biomes[i] = Biome.getBiome(aint[i], MPBiomes.INFECTED_OCEAN);
        }
        return biomes;
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();
        int size = width * length;

        if (listToReuse == null || listToReuse.length < size)
        {
            listToReuse = new Biome[size];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            Biome[] abiome = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiome, 0, listToReuse, 0, size);
            return listToReuse;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i = 0; i < size; ++i)
            {
                listToReuse[i] = Biome.getBiome(aint[i], MPBiomes.INFECTED_OCEAN);
            }
            return listToReuse;
        }
    }

    private void initLayers(long seed)
    {
        int i = 4;
        GenLayer genlayer = new GenLayerIsland(1L);
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        GenLayer genlayeraddisland = new GenLayerAddIsland(1L, genlayer);
        GenLayer genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        GenLayer genlayeraddisland1 = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
        genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
        GenLayer genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland1);
        GenLayer genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        GenLayer genlayeraddisland2 = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayer genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        GenLayer genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
        GenLayer genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
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
                genlayerhills = new GenLayerAddIsland(3L, genlayerhills);
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