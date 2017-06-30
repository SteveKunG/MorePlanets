package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruDeepOcean extends GenLayer
{
    public GenLayerNibiruDeepOcean(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int nx = areaX - 1;
        int nz = areaY - 1;
        int nwidth = areaWidth + 2;
        int ndepth = areaHeight + 2;
        int input[] = this.parent.getInts(nx, nz, nwidth, ndepth);
        int output[] = IntCache.getIntCache(areaWidth * areaHeight);

        for (int dz = 0; dz < areaHeight; dz++)
        {
            for (int dx = 0; dx < areaWidth; dx++)
            {
                int right = input[dx + 0 + (dz + 1) * nwidth];
                int left = input[dx + 2 + (dz + 1) * nwidth];
                int up = input[dx + 1 + (dz + 0) * nwidth];
                int down = input[dx + 1 + (dz + 2) * nwidth];
                int center = input[dx + 1 + (dz + 1) * nwidth];
                int ur = input[dx + 0 + (dz + 0) * nwidth];
                int ul = input[dx + 2 + (dz + 0) * nwidth];
                int dr = input[dx + 0 + (dz + 2) * nwidth];
                int dl = input[dx + 2 + (dz + 2) * nwidth];

                if (this.onBorder(Biome.getIdForBiome(MPBiomes.INFECTED_SWAMPLAND), center, right, left, up, down))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_DEEP_OCEAN);
                }
                else if (this.onBorder(Biome.getIdForBiome(MPBiomes.INFECTED_SWAMPLAND), center, ur, ul, dr, dl))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_OCEAN);
                }
                else if (this.onBorder(Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE), center, right, left, up, down))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_DEEP_OCEAN);
                }
                else if (this.onBorder(Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE), center, ur, ul, dr, dl))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_OCEAN);
                }
                else
                {
                    output[dx + dz * areaWidth] = center;
                }
            }
        }
        return output;
    }

    private boolean onBorder(int biomeID, int center, int right, int left, int up, int down)
    {
        if (center == biomeID)
        {
            return false;
        }
        else if (right == biomeID)
        {
            return true;
        }
        else if (left == biomeID)
        {
            return true;
        }
        else if (up == biomeID)
        {
            return true;
        }
        else if (down == biomeID)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}