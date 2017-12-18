package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruRiverMix extends GenLayer
{
    private GenLayer biomeLayer;
    private GenLayer riverLayer;

    public GenLayerNibiruRiverMix(long seed, GenLayer biomeLayer, GenLayer riverLayer)
    {
        super(seed);
        this.biomeLayer = biomeLayer;
        this.riverLayer = riverLayer;
    }

    @Override
    public void initWorldGenSeed(long seed)
    {
        this.biomeLayer.initWorldGenSeed(seed);
        this.riverLayer.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeInputs = this.biomeLayer.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] riverInputs = this.riverLayer.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] outputs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (riverInputs[i] == Biome.getIdForBiome(MPBiomes.INFECTED_RIVER))
            {
                outputs[i] = riverInputs[i] & 255;
            }
            else
            {
                outputs[i] = biomeInputs[i];
            }
        }
        return outputs;
    }
}