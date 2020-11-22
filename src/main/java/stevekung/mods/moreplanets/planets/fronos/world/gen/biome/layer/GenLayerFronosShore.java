package stevekung.mods.moreplanets.planets.fronos.world.gen.biome.layer;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.init.MPBiomes;

public class GenLayerFronosShore extends GenLayer
{
    public GenLayerFronosShore(long seed, GenLayer parent)
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
                Biome biome = Biome.getBiome(k);

                //                if (k == Biome.getIdForBiome(MPBiomes.GREEN_VEIN_FIELDS))
                //                {
                //                    int j2 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                //                    int i3 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                //                    int l3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                //                    int k4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                //
                //                    if (j2 != Biome.getIdForBiome(Biomes.OCEAN) && i3 != Biome.getIdForBiome(Biomes.OCEAN) && l3 != Biome.getIdForBiome(Biomes.OCEAN) && k4 != Biome.getIdForBiome(Biomes.OCEAN))
                //                    {
                //                        aint1[j + i * areaWidth] = k;
                //                    }
                //                    else
                //                    {
                //                        aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.GREEN_VEIN_FIELD_SHORE);
                //                    }
                //                }
                //                else if (biome != null && biome.getBiomeClass() == BiomeInfectedJungle.class)
                //                {
                //                    int i2 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                //                    int l2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                //                    int k3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                //                    int j4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                //
                //                    if (this.isJungleCompatible(i2) && this.isJungleCompatible(l2) && this.isJungleCompatible(k3) && this.isJungleCompatible(j4))
                //                    {
                //                        if (!this.isOcean(i2) && !this.isOcean(l2) && !this.isOcean(k3) && !this.isOcean(j4))
                //                        {
                //                            aint1[j + i * areaWidth] = k;
                //                        }
                //                        else
                //                        {
                //                            aint1[j + i * areaWidth] = Biome.getIdForBiome(Biomes.BEACH);
                //                        }
                //                    }
                //                    else
                //                    {
                //                        aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE_EDGE);
                //                    }
                //                }
                //                else if (k != Biome.getIdForBiome(MPBiomes.INFECTED_MOUNTAINS) && k != Biome.getIdForBiome(MPBiomes.INFECTED_WOODED_MOUNTAINS) && k != Biome.getIdForBiome(MPBiomes.INFECTED_MOUNTAINS_EDGE))
                {
                    if (biome != null && biome.isSnowyBiome())
                    {
                        this.replaceIfNeighborOcean(aint, aint1, j, i, areaWidth, k, Biome.getIdForBiome(MPBiomes.INFECTED_SNOWY_BEACH));
                    }
                    else if (k != Biome.getIdForBiome(MPBiomes.INFECTED_BADLANDS) && k != Biome.getIdForBiome(MPBiomes.INFECTED_WOODED_BADLANDS_PLATEAU))
                    {
                        if (k != Biome.getIdForBiome(Biomes.OCEAN) && k != Biome.getIdForBiome(Biomes.DEEP_OCEAN) && k != Biome.getIdForBiome(MPBiomes.FRONOS_RIVER) && k != Biome.getIdForBiome(MPBiomes.INFECTED_SWAMP))
                        {
                            int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                            int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                            int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                            int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                            if (!this.isOcean(l1) && !this.isOcean(k2) && !this.isOcean(j3) && !this.isOcean(i4))
                            {
                                aint1[j + i * areaWidth] = k;
                            }
                            else
                            {
                                aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.FRONOS_BEACHES);
                            }
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                    }
                    else
                    {
                        int l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                        int i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                        int j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                        int k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                        if (!this.isOcean(l) && !this.isOcean(i1) && !this.isOcean(j1) && !this.isOcean(k1))
                        {
                            //                            if (this.isMesa(l) && this.isMesa(i1) && this.isMesa(j1) && this.isMesa(k1))
                            //                            {
                            //                                aint1[j + i * areaWidth] = k;
                            //                            }
                            //                            else
                            //                            {
                            //                                aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_DESERT);
                            //                            }
                        }
                        else
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                    }
                }
                //                else
                //                {
                //                    this.replaceIfNeighborOcean(aint, aint1, j, i, areaWidth, k, Biome.getIdForBiome(MPBiomes.INFECTED_STONE_SHORE));
                //                }
            }
        }
        return aint1;
    }

    private void replaceIfNeighborOcean(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_)
    {
        if (this.isOcean(p_151632_6_))
        {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        }
        else
        {
            int i = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int j = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int k = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];

            if (!this.isOcean(i) && !this.isOcean(j) && !this.isOcean(k) && !this.isOcean(l))
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            }
            else
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
            }
        }
    }

    //    private boolean isJungleCompatible(int id)
    //    {
    //        if (Biome.getBiome(id) != null && Biome.getBiome(id).getBiomeClass() == BiomeInfectedJungle.class)
    //        {
    //            return true;
    //        }
    //        else
    //        {
    //            return id == Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE_EDGE) || id == Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE) || id == Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE_HILLS) || id == Biome.getIdForBiome(MPBiomes.INFECTED_FOREST) || id == Biome.getIdForBiome(MPBiomes.INFECTED_TAIGA) || this.isOcean(id);
    //        }
    //    }

    //    private boolean isMesa(int id)
    //    {
    //        return Biome.getBiome(id) instanceof BiomeInfectedBadlands;
    //    }

    private boolean isOcean(int id)
    {
        return Biome.getBiome(id) == Biomes.OCEAN || Biome.getBiome(id) == Biomes.DEEP_OCEAN;
    }
}