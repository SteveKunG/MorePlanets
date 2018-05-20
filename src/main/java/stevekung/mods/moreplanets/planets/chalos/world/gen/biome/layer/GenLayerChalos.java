package stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerChalos extends GenLayer
{
    public GenLayerChalos(long seed)
    {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed)
    {
        GenLayer biomesLayer = new GenLayerChalosBiomes(200L);
        biomesLayer = new GenLayerZoom(1000L, biomesLayer);
        biomesLayer = new GenLayerZoom(1001L, biomesLayer);
        biomesLayer = new GenLayerZoom(1002L, biomesLayer);
        biomesLayer = new GenLayerZoom(1003L, biomesLayer);
        biomesLayer = new GenLayerZoom(1004L, biomesLayer);
        biomesLayer = new GenLayerZoom(1005L, biomesLayer);
        GenLayer riverLayer = new GenLayerSlimelyStream(1L, biomesLayer);
        riverLayer = new GenLayerSmooth(7000L, riverLayer);
        biomesLayer = new GenLayerSlimelyStreamMix(100L, biomesLayer, riverLayer);
        GenLayer veronoiZoom = new GenLayerVoronoiZoom(10L, biomesLayer);
        biomesLayer.initWorldGenSeed(seed);
        veronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[] { biomesLayer, veronoiZoom };
    }
}