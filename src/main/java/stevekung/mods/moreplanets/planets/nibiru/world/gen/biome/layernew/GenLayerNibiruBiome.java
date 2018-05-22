package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.layernew;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.init.Biomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.stevekunglib.utils.CachedEnum;

public class GenLayerNibiruBiome extends GenLayer
{
    private final List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[CachedEnum.biomeValues.length];
    private final ArrayList<BiomeManager.BiomeEntry>[] biomesList = this.setupBiomes();

    public GenLayerNibiruBiome(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;

        for (BiomeManager.BiomeType type : CachedEnum.biomeValues)
        {
            ImmutableList<BiomeManager.BiomeEntry> biomesToAdd = this.getBiomes(type);
            int idx = type.ordinal();

            if (this.biomes[idx] == null)
            {
                this.biomes[idx] = new ArrayList<>();
            }
            if (biomesToAdd != null)
            {
                this.biomes[idx].addAll(biomesToAdd);
            }
        }

        int desertIdx = BiomeManager.BiomeType.DESERT.ordinal();
        this.biomes[desertIdx].add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_DESERT, 30));
        this.biomes[desertIdx].add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_DEAD_SAVANNA, 20));
        this.biomes[desertIdx].add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_PLAINS, 10));
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed(j + areaX, i + areaY);
                int k = aint[j + i * areaWidth];
                int l = (k & 3840) >> 8;
            k = k & -3841;

            if (isBiomeOceanic(k))
            {
                aint1[j + i * areaWidth] = k;
            }
            else if (k == Biome.getIdForBiome(MPBiomes.GREEN_VEIN_BADLANDS))
            {
                aint1[j + i * areaWidth] = k;
            }
            else if (k == 1)
            {
                if (l > 0)
                {
                    if (this.nextInt(3) == 0)
                    {
                        aint1[j + i * areaWidth] = Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK);
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = Biome.getIdForBiome(Biomes.MESA_ROCK);
                    }
                }
                else
                {
                    aint1[j + i * areaWidth] = Biome.getIdForBiome(this.getWeightedBiomeEntry(BiomeManager.BiomeType.DESERT).biome);
                }
            }
            else if (k == 2)
            {
                if (l > 0)
                {
                    aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.INFECTED_JUNGLE);
                }
                else
                {
                    aint1[j + i * areaWidth] = Biome.getIdForBiome(this.getWeightedBiomeEntry(BiomeManager.BiomeType.WARM).biome);
                }
            }
            else if (k == 3)
            {
                if (l > 0)
                {
                    aint1[j + i * areaWidth] = Biome.getIdForBiome(Biomes.REDWOOD_TAIGA);
                }
                else
                {
                    aint1[j + i * areaWidth] = Biome.getIdForBiome(this.getWeightedBiomeEntry(BiomeManager.BiomeType.COOL).biome);
                }
            }
            else if (k == 4)
            {
                aint1[j + i * areaWidth] = Biome.getIdForBiome(this.getWeightedBiomeEntry(BiomeManager.BiomeType.ICY).biome);
            }
            else
            {
                aint1[j + i * areaWidth] = Biome.getIdForBiome(MPBiomes.GREEN_VEIN_BADLANDS);
            }
            }
        }
        return aint1;
    }

    private ArrayList<BiomeManager.BiomeEntry>[] setupBiomes()
    {
        ArrayList<BiomeManager.BiomeEntry>[] currentBiomes = new ArrayList[BiomeManager.BiomeType.values().length];
        List<BiomeManager.BiomeEntry> list = new ArrayList<>();
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_FOREST, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_DEAD_ROOFED_FOREST, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_EXTREME_HILLS, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_PLAINS, 10));
        list.add(new BiomeManager.BiomeEntry(Biomes.BIRCH_FOREST, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_SWAMP, 10));
        currentBiomes[BiomeManager.BiomeType.WARM.ordinal()] = new ArrayList<>(list);
        list.clear();
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_FOREST, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_EXTREME_HILLS, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_DEAD_TAIGA, 10));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_PLAINS, 10));
        currentBiomes[BiomeManager.BiomeType.COOL.ordinal()] = new ArrayList<>(list);
        list.clear();
        list.add(new BiomeManager.BiomeEntry(MPBiomes.INFECTED_ICE_PLAINS, 30));
        list.add(new BiomeManager.BiomeEntry(Biomes.COLD_TAIGA, 10));
        currentBiomes[BiomeManager.BiomeType.ICY.ordinal()] = new ArrayList<>(list);
        list.clear();
        currentBiomes[BiomeManager.BiomeType.DESERT.ordinal()] = new ArrayList<>(list);
        return currentBiomes;
    }

    private ImmutableList<BiomeManager.BiomeEntry> getBiomes(BiomeManager.BiomeType type)
    {
        int idx = type.ordinal();
        List<BiomeManager.BiomeEntry> list = idx >= this.biomesList.length ? null : this.biomesList[idx];
        return list != null ? ImmutableList.copyOf(list) : null;
    }

    private BiomeManager.BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type)
    {
        List<BiomeManager.BiomeEntry> biomeList = this.biomes[type.ordinal()];
        int totalWeight = WeightedRandom.getTotalWeight(biomeList);
        int weight = this.nextInt(totalWeight / 10) * 10;
        return WeightedRandom.getRandomItem(biomeList, weight);
    }
}