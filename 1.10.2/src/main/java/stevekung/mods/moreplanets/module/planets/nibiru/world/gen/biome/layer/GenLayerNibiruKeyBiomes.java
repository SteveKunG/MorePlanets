package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruKeyBiomes extends GenLayer
{
    public GenLayerNibiruKeyBiomes(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    public GenLayerNibiruKeyBiomes(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int src[] = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int dest[] = IntCache.getIntCache(areaWidth * areaHeight);

        for (int dz = 0; dz < areaHeight; dz++)
        {
            for (int dx = 0; dx < areaWidth; dx++)
            {
                this.initChunkSeed(dx + areaX | 3, dz + areaY | 3);
                int ox = this.nextInt(3) + 1;
                int oz = this.nextInt(3) + 1;

                if ((dx + areaX & 3) == ox && (dz + areaY & 3) == oz)
                {
                    if ((dx + areaX & 4) == 0)
                    {
                        if ((dz + areaY & 4) == 0)
                        {
                            dest[dx + dz * areaWidth] = this.getKeyBiomeFor(dx + areaX, dz + areaY, 0);
                        }
                        else
                        {
                            dest[dx + dz * areaWidth] = this.getKeyBiomeFor(dx + areaX, dz + areaY, 1);
                        }
                    }
                    else
                    {
                        if ((dz + areaY & 4) == 0)
                        {
                            dest[dx + dz * areaWidth] = this.getKeyBiomeFor(dx + areaX, dz + areaY, 2);
                        }
                        else
                        {
                            dest[dx + dz * areaWidth] = this.getKeyBiomeFor(dx + areaX, dz + areaY, 3);
                        }
                    }
                }
                else
                {
                    dest[dx + dz * areaWidth] = src[dx + dz * areaWidth];
                }
            }
        }
        return dest;
    }

    private int getKeyBiomeFor(int mapX, int mapZ, int index)
    {
        int regionX = mapX + 4 >> 3;
            int regionZ = mapZ + 4 >> 3;
            int offset = this.nextInt(4);
            this.initChunkSeed(regionX, regionZ);

            switch ((index + offset) % 4)
            {
            case 0:
            default:
                return MPBiomes.INFECTED_PLAINS.biomeID;
            case 1:
                return MPBiomes.INFECTED_FOREST.biomeID;
            case 2:
                return MPBiomes.INFECTED_DESERT.biomeID;
            case 3:
                return MPBiomes.INFECTED_JUNGLE.biomeID;
            }
    }
}