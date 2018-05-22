package stevekung.mods.moreplanets.planets.chalos.world.gen.biome.layer;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.stevekunglib.utils.CachedEnum;

public class GenLayerChalosBiomes extends GenLayer
{
    private final List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[CachedEnum.biomeValues.length];
    private final ArrayList<BiomeManager.BiomeEntry>[] biomesList = this.setupBiomes();

    public GenLayerChalosBiomes(long seed)
    {
        super(seed);

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
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] dest = IntCache.getIntCache(areaWidth * areaHeight);

        for (int dz = 0; dz < areaHeight; dz++)
        {
            for (int dx = 0; dx < areaWidth; dx++)
            {
                this.initChunkSeed(dx + areaX, dz + areaY);
                dest[dx + dz * areaWidth] = Biome.getIdForBiome(this.getWeightedBiomeEntry(BiomeManager.BiomeType.WARM).biome);
            }
        }
        return dest;
    }

    private ArrayList<BiomeManager.BiomeEntry>[] setupBiomes()
    {
        ArrayList<BiomeManager.BiomeEntry>[] currentBiomes = new ArrayList[CachedEnum.biomeValues.length];
        List<BiomeManager.BiomeEntry> list = new ArrayList<>();
        list.add(new BiomeManager.BiomeEntry(MPBiomes.CHALOS_PLAINS, 30));
        list.add(new BiomeManager.BiomeEntry(MPBiomes.CHALOS_MOUTAINS, 20));
        currentBiomes[BiomeManager.BiomeType.WARM.ordinal()] = new ArrayList<>(list);
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