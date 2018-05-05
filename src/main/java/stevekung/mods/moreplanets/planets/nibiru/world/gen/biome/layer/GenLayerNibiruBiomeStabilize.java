package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerNibiruBiomeStabilize extends GenLayer
{
    public GenLayerNibiruBiomeStabilize(long seed, GenLayer parent)
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
        int offX = areaX & 3;
        int offZ = areaY & 3;

        for (int dz = 0; dz < areaHeight; dz++)
        {
            for (int dx = 0; dx < areaWidth; dx++)
            {
                int centerX = (dx + offX + 1 & 0xFFFFFFFC) - offX;
                int centerZ = (dz + offZ + 1 & 0xFFFFFFFC) - offZ;

                if (dx <= centerX + 1 && dx >= centerX - 1 && dz <= centerZ + 1 && dz >= centerZ - 1)
                {
                    output[dx + dz * areaWidth] = input[centerX + 1 + (centerZ + 1) * nwidth];
                }
                else
                {
                    output[dx + dz * areaWidth] = input[dx + 1 + (dz + 1) * nwidth];
                }
            }
        }
        return output;
    }
}