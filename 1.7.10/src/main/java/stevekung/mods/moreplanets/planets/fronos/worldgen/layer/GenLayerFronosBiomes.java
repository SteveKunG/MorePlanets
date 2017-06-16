/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.planets.fronos.worldgen.biome.BiomeGenBaseFronos;

public class GenLayerFronosBiomes extends GenLayer
{
    protected BiomeGenBase[] commonBiomes = { BiomeGenBaseFronos.coconutForest, BiomeGenBaseFronos.purpleMapleForest, BiomeGenBaseFronos.mapleForest, BiomeGenBaseFronos.grassyPlains };
    protected BiomeGenBase[] rareBiomes = { BiomeGenBaseFronos.goldenField, BiomeGenBaseFronos.candyLand };

    public GenLayerFronosBiomes(long seed, GenLayer genlayer)
    {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerFronosBiomes(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; dz++)
        {
            for (int dx = 0; dx < width; dx++)
            {
                this.initChunkSeed(dx + x, dz + z);

                if (this.nextInt(20) == 0)
                {
                    dest[dx + dz * width] = this.rareBiomes[this.nextInt(this.rareBiomes.length)].biomeID;
                }
                else
                {
                    dest[dx + dz * width] = this.commonBiomes[this.nextInt(this.commonBiomes.length)].biomeID;
                }
            }
        }
        return dest;
    }
}
