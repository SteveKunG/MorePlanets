package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernewtest;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruIsland extends GenLayer
{
    public GenLayerNibiruIsland(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed(areaX + j, areaY + i);
                //                aint[j + i * areaWidth] = this.nextInt(10) == 0 ? Biome.getIdForBiome(MPBiomes.INFECTED_PLAINS) : Biome.getIdForBiome(MPBiomes.INFECTED_OCEAN);
                aint[j + i * areaWidth] = this.nextInt(10) == 0 ? Biome.getIdForBiome(MPBiomes.INFECTED_PLAINS) : 0;
                //                aint[j + i * areaWidth] = this.nextInt(10) == 0 ? 1 : Biome.getIdForBiome(MPBiomes.INFECTED_OCEAN);
                //                aint[j + i * areaWidth] = this.nextInt(10) == 0 ? 1 : 0;
            }
        }

        if (areaX > -areaWidth && areaX <= 0 && areaY > -areaHeight && areaY <= 0)
        {
            aint[-areaX + -areaY * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_PLAINS);
            //            aint[-areaX + -areaY * areaWidth] = 1;
        }
        return aint;
    }
}