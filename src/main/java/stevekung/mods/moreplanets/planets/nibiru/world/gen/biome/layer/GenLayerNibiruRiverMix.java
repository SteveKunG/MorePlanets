package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruRiverMix extends GenLayer
{
    private final GenLayer biomePatternGeneratorChain;
    private final GenLayer riverPatternGeneratorChain;

    public GenLayerNibiruRiverMix(long seed, GenLayer biomeLayer, GenLayer riverLayer)
    {
        super(seed);
        this.biomePatternGeneratorChain = biomeLayer;
        this.riverPatternGeneratorChain = riverLayer;
    }

    @Override
    public void initWorldGenSeed(long seed)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(seed);
        this.riverPatternGeneratorChain.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (aint[i] != Biome.getIdForBiome(MPBiomes.INFECTED_OCEAN) && aint[i] != Biome.getIdForBiome(MPBiomes.INFECTED_DEEP_OCEAN))
            {
                if (aint1[i] == Biome.getIdForBiome(MPBiomes.INFECTED_RIVER))
                {
                    if (aint[i] == Biome.getIdForBiome(MPBiomes.INFECTED_ICE_SPIKES))
                    {
                        aint2[i] = Biome.getIdForBiome(MPBiomes.INFECTED_FROZEN_RIVER);
                    }
                    else if (aint[i] != Biome.getIdForBiome(MPBiomes.GREEN_VEIN_FIELDS) && aint[i] != Biome.getIdForBiome(MPBiomes.GREEN_VEIN_FIELD_SHORE))
                    {
                        aint2[i] = aint1[i] & 255;
                    }
                    else
                    {
                        aint2[i] = Biome.getIdForBiome(MPBiomes.GREEN_VEIN_FIELD_SHORE);
                    }
                }
                else
                {
                    aint2[i] = aint[i];
                }
            }
            else
            {
                aint2[i] = aint[i];
            }
        }
        return aint2;
    }
}