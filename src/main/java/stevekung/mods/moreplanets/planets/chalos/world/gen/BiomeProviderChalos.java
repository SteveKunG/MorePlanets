package stevekung.mods.moreplanets.planets.chalos.world.gen;

import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer.GenLayerChalosBiomes;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer.GenLayerSlimelyStream;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer.GenLayerSlimelyStreamMix;

public class BiomeProviderChalos extends BiomeProvider
{
    public BiomeProviderChalos(long seed)
    {
        this.biomeCache = new BiomeCache(this);
        this.getBiomesToSpawnIn().clear();
        this.getBiomesToSpawnIn().add(MPBiomes.CHALOS_PLAINS);
        this.getBiomesToSpawnIn().add(MPBiomes.CHALOS_MOUTAINS);
        this.initLayers(seed);
    }

    private void initLayers(long seed)
    {
        GenLayer biomesLayer = new GenLayerChalosBiomes(1L);
        biomesLayer = new GenLayerZoom(1000L, biomesLayer);
        biomesLayer = new GenLayerZoom(1001L, biomesLayer);
        biomesLayer = new GenLayerZoom(1002L, biomesLayer);
        biomesLayer = new GenLayerZoom(1003L, biomesLayer);
        biomesLayer = new GenLayerZoom(1004L, biomesLayer);
        biomesLayer = new GenLayerZoom(1005L, biomesLayer);
        GenLayer riverLayer = new GenLayerSlimelyStream(1L, biomesLayer);
        riverLayer = new GenLayerSmooth(7000L, riverLayer);
        GenLayer riverMix = new GenLayerSlimelyStreamMix(100L, biomesLayer, riverLayer);
        GenLayer veronoiZoom = new GenLayerVoronoiZoom(10L, riverMix);
        riverMix.initWorldGenSeed(seed);
        veronoiZoom.initWorldGenSeed(seed);
        this.genBiomes = riverMix;
        this.biomeIndexLayer = veronoiZoom;
    }
}