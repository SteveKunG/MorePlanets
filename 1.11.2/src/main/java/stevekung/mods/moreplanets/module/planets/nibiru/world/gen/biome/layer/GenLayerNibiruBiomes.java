package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruBiomes extends GenLayer
{
    private static final int RARE_BIOME_CHANCE = 15;
    private Biome commonBiomes[] = new Biome[] { MPBiomes.INFECTED_PLAINS, MPBiomes.INFECTED_DESERT, MPBiomes.INFECTED_FOREST, MPBiomes.INFECTED_DEAD_SAVANNA, MPBiomes.INFECTED_DEAD_ROOFED_FOREST, MPBiomes.INFECTED_EXTREME_HILLS, MPBiomes.INFECTED_DEAD_TAIGA, MPBiomes.INFECTED_SWAMPLAND, MPBiomes.INFECTED_JUNGLE, MPBiomes.INFECTED_ICE_PLAINS };

    public GenLayerNibiruBiomes(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    public GenLayerNibiruBiomes(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int dest[] = IntCache.getIntCache(areaWidth * areaHeight);

        for (int dz = 0; dz < areaHeight; dz++)
        {
            for (int dx = 0; dx < areaWidth; dx++)
            {
                this.initChunkSeed(dx + areaX, dz + areaY);

                if (this.nextInt(GenLayerNibiruBiomes.RARE_BIOME_CHANCE) == 0)
                {
                    dest[dx + dz * areaWidth] = Biome.getIdForBiome(MPBiomes.GREEN_VEIN);
                }
                else
                {
                    dest[dx + dz * areaWidth] = Biome.getIdForBiome(this.commonBiomes[this.nextInt(this.commonBiomes.length)]);
                }
            }
        }
        return dest;
    }
}