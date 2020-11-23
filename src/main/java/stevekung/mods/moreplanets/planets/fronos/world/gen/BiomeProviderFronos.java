package stevekung.mods.moreplanets.planets.fronos.world.gen;

import javax.annotation.Nullable;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.planets.fronos.world.gen.biome.layer.GenLayerFronosBiome;
import stevekung.mods.moreplanets.planets.fronos.world.gen.biome.layer.GenLayerFronosRiver;
import stevekung.mods.moreplanets.planets.fronos.world.gen.biome.layer.GenLayerFronosRiverMix;

public class BiomeProviderFronos extends BiomeProvider
{
    public BiomeProviderFronos(long seed)
    {
        this.biomeCache = new BiomeCache(this);
        this.getBiomesToSpawnIn().clear();
        this.getBiomesToSpawnIn().add(MPBiomes.FRONOS_MELLOW);
        this.getBiomesToSpawnIn().add(MPBiomes.FRONOS_PLAINS);
        this.getBiomesToSpawnIn().add(MPBiomes.FRONOS_FOREST);
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
            biomes[i] = Biome.getBiome(aint[i], Biomes.OCEAN);
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
                listToReuse[i] = Biome.getBiome(aint[i], Biomes.OCEAN);
            }
            return listToReuse;
        }
    }

    private void initLayers(long seed)
    {
        int i = 4;
        GenLayer genlayer = new GenLayerIsland(1L);
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        GenLayer genlayerzoom = new GenLayerZoom(2001L, genlayer);
        GenLayer genlayerzoom1 = new GenLayerZoom(2002L, genlayerzoom);
        genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
        GenLayer genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
        GenLayer genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland3);
        GenLayer genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer4 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        GenLayer lvt_7_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
        GenLayer genlayerriverinit = new GenLayerRiverInit(100L, lvt_7_1_);
        GenLayer genlayerbiomeedge = this.getBiomeLayer(genlayer4);
        GenLayer lvt_9_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayer genlayerhills = new GenLayerHills(1000L, genlayerbiomeedge, lvt_9_1_);
        GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, i);
        GenLayer genlayerriver = new GenLayerFronosRiver(1L, genlayer5);
        GenLayer genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        genlayerhills = new GenLayerRareBiome(1001L, genlayerhills);

        for (int k = 0; k < i; ++k)
        {
            genlayerhills = new GenLayerZoom(1000L + k, genlayerhills);
        }

        GenLayer genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
        GenLayer genlayerrivermix = new GenLayerFronosRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayer3.initWorldGenSeed(seed);
        this.genBiomes = genlayerrivermix;
        this.biomeIndexLayer = genlayer3;
    }

    private GenLayer getBiomeLayer(GenLayer parent)
    {
        GenLayer layer = new GenLayerFronosBiome(200L, parent);
        layer = GenLayerZoom.magnify(1000L, layer, 2);
        return layer;
    }
}