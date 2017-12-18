package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome.layer;

import net.minecraft.world.gen.layer.GenLayer;
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
        GenLayer biomes = new GenLayerChalosBiomes(200L);
        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerZoom(1002L, biomes);
        biomes = new GenLayerZoom(1003L, biomes);
        GenLayer genLayerVeronoiZoom = new GenLayerVoronoiZoom(10L, biomes);
        biomes.initWorldGenSeed(seed);
        genLayerVeronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[] { biomes, genLayerVeronoiZoom };
    }
}