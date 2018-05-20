package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruCompanionBiomes extends GenLayer
{
    public GenLayerNibiruCompanionBiomes(long seed, GenLayer parent)
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

                if (this.isKey(Biome.getIdForBiome(MPBiomes.INFECTED_DESERT), center, right, left, up, down))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_DEAD_SAVANNA);
                }
                else if (this.isKey(Biome.getIdForBiome(MPBiomes.INFECTED_DEAD_SAVANNA), center, right, left, up, down))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_FOREST);
                }
                else if (this.isKey(Biome.getIdForBiome(MPBiomes.INFECTED_ICE_PLAINS), center, right, left, up, down))
                {
                    output[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.GREEN_VEIN_BADLANDS);
                }
                else
                {
                    output[dx + dz * areaWidth] = center;
                }
            }
        }
        return output;
    }

    private boolean isKey(int biome, int center, int right, int left, int up, int down)
    {
        return center != biome && (right == biome || left == biome || up == biome || down == biome);
    }
}