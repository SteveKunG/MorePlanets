package stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerSlimelyStreamMix extends GenLayer
{
    private final GenLayer patternLayer;
    private final GenLayer riverLayer;

    public GenLayerSlimelyStreamMix(long seed, GenLayer patternLayer, GenLayer riverLayer)
    {
        super(seed);
        this.patternLayer = patternLayer;
        this.riverLayer = riverLayer;
    }

    @Override
    public void initWorldGenSeed(long seed)
    {
        this.patternLayer.initWorldGenSeed(seed);
        this.riverLayer.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.patternLayer.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = this.riverLayer.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (aint1[i] == Biome.getIdForBiome(MPBiomes.SLIMELY_STREAM))
            {
                aint2[i] = aint1[i] & 255;
            }
            else
            {
                aint2[i] = aint[i];
            }
        }
        return aint2;
    }
}