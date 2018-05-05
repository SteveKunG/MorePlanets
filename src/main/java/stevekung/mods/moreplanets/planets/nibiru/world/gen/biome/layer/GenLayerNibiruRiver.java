package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruRiver extends GenLayer
{
    public GenLayerNibiruRiver(long seed, GenLayer parent)
    {
        super(seed);
        super.parent = parent;
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
                int left = input[dx + 0 + (dz + 1) * nwidth];
                int right = input[dx + 2 + (dz + 1) * nwidth];
                int down = input[dx + 1 + (dz + 0) * nwidth];
                int up = input[dx + 1 + (dz + 2) * nwidth];
                int mid = input[dx + 1 + (dz + 1) * nwidth];

                if (this.shouldStream(mid, left, down, right, up))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_RIVER);
                }
                else
                {
                    output[dx + dz * areaWidth] = -1;
                }
            }
        }
        return output;
    }

    private boolean shouldStream(int mid, int left, int down, int right, int up)
    {
        if (this.shouldStream(mid, left))
        {
            return true;
        }
        else if (this.shouldStream(mid, right))
        {
            return true;
        }
        else if (this.shouldStream(mid, down))
        {
            return true;
        }
        else if (this.shouldStream(mid, up))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean shouldStream(int biome1, int biome2)
    {
        if (biome1 == biome2)
        {
            return false;
        }
        if (biome1 == -biome2)
        {
            return false;
        }
        return true;
    }
}