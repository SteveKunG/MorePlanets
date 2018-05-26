package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernew;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerNibiruBiomeEdge extends GenLayer
{
    public GenLayerNibiruBiomeEdge(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed(j + areaX, i + areaY);
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];

                if (!this.replaceBiomeEdgeIfNecessary(aint, aint1, j, i, areaWidth, k, Biome.getIdForBiome(MPBiomes.INFECTED_MOUNTAINS), Biome.getIdForBiome(MPBiomes.INFECTED_MOUNTAINS_EDGE)) && !this.replaceBiomeEdge(aint, aint1, j, i, areaWidth, k, Biome.getIdForBiome(MPBiomes.INFECTED_WOODED_BADLANDS_PLATEAU), Biome.getIdForBiome(MPBiomes.INFECTED_BADLANDS)) && !this.replaceBiomeEdge(aint, aint1, j, i, areaWidth, k, Biome.getIdForBiome(MPBiomes.INFECTED_BADLANDS_PLATEAU), Biome.getIdForBiome(MPBiomes.INFECTED_BADLANDS)) && !this.replaceBiomeEdge(aint, aint1, j, i, areaWidth, k, Biome.getIdForBiome(Biomes.REDWOOD_TAIGA), Biome.getIdForBiome(MPBiomes.INFECTED_TAIGA)))
                {
                    if (k == Biome.getIdForBiome(MPBiomes.INFECTED_DESERT))
                    {
                        int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                        int i2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                        int j2 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                        int k2 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                        if (l1 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA) && i2 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA) && j2 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA) && k2 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA))
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_WOODED_MOUNTAINS);
                        }
                    }
                    else if (k == Biome.getIdForBiome(MPBiomes.INFECTED_SWAMP))
                    {
                        int l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                        int i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                        int j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                        int k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                        if (l != Biome.getIdForBiome(MPBiomes.INFECTED_DESERT) && i1 != Biome.getIdForBiome(MPBiomes.INFECTED_DESERT) && j1 != Biome.getIdForBiome(MPBiomes.INFECTED_DESERT) && k1 != Biome.getIdForBiome(MPBiomes.INFECTED_DESERT) && l != Biome.getIdForBiome(Biomes.COLD_TAIGA) && i1 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && j1 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && k1 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && l != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA) && i1 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA) && j1 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA) && k1 != Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_TUNDRA))
                        {
                            if (l != Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE) && k1 != Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE) && i1 != Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE) && j1 != Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE))
                            {
                                aint1[j + i * areaWidth] = k;
                            }
                            else
                            {
                                aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE_EDGE);
                            }
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_PLAINS);
                        }
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                }
            }
        }
        return aint1;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] p_151636_1_, int[] p_151636_2_, int p_151636_3_, int p_151636_4_, int p_151636_5_, int p_151636_6_, int p_151636_7_, int p_151636_8_)
    {
        if (!this.isOrMesaPlateau(p_151636_6_, p_151636_7_))
        {
            return false;
        }
        else
        {
            int i = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 - 1) * (p_151636_5_ + 2)];
            int j = p_151636_1_[p_151636_3_ + 1 + 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
            int k = p_151636_1_[p_151636_3_ + 1 - 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
            int l = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 + 1) * (p_151636_5_ + 2)];

            if (this.canBiomesBeNeighbors(i, p_151636_7_) && this.canBiomesBeNeighbors(j, p_151636_7_) && this.canBiomesBeNeighbors(k, p_151636_7_) && this.canBiomesBeNeighbors(l, p_151636_7_))
            {
                p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_6_;
            }
            else
            {
                p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_8_;
            }
            return true;
        }
    }

    private boolean replaceBiomeEdge(int[] p_151635_1_, int[] p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_)
    {
        if (p_151635_6_ != p_151635_7_)
        {
            return false;
        }
        else
        {
            int i = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 - 1) * (p_151635_5_ + 2)];
            int j = p_151635_1_[p_151635_3_ + 1 + 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
            int k = p_151635_1_[p_151635_3_ + 1 - 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
            int l = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 + 1) * (p_151635_5_ + 2)];

            if (this.isOrMesaPlateau(i, p_151635_7_) && this.isOrMesaPlateau(j, p_151635_7_) && this.isOrMesaPlateau(k, p_151635_7_) && this.isOrMesaPlateau(l, p_151635_7_))
            {
                p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_6_;
            }
            else
            {
                p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_8_;
            }
            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int id1, int id2)
    {
        if (this.isOrMesaPlateau(id1, id2))
        {
            return true;
        }
        else
        {
            Biome biome = Biome.getBiome(id1);
            Biome biome1 = Biome.getBiome(id2);

            if (biome != null && biome1 != null)
            {
                Biome.TempCategory temp = biome.getTempCategory();
                Biome.TempCategory temp1 = biome1.getTempCategory();
                return temp == temp1 || temp == Biome.TempCategory.MEDIUM || temp1 == Biome.TempCategory.MEDIUM;
            }
            else
            {
                return false;
            }
        }
    }

    private boolean isOrMesaPlateau(int biomeIDA, int biomeIDB)
    {
        if (biomeIDA == biomeIDB)
        {
            return true;
        }
        else
        {
            Biome biome = Biome.getBiome(biomeIDA);
            Biome biome1 = Biome.getBiome(biomeIDB);

            if (biome != null && biome1 != null)
            {
                if (biome != MPBiomes.INFECTED_WOODED_BADLANDS_PLATEAU && biome != MPBiomes.INFECTED_BADLANDS_PLATEAU)
                {
                    return biome == biome1 || biome.getBiomeClass() == biome1.getBiomeClass();
                }
                else
                {
                    return biome1 == MPBiomes.INFECTED_WOODED_BADLANDS_PLATEAU || biome1 == MPBiomes.INFECTED_BADLANDS_PLATEAU;
                }
            }
            else
            {
                return false;
            }
        }
    }
}