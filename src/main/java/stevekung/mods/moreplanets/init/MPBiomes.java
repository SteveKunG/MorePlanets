package stevekung.mods.moreplanets.init;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.BiomeChalosMoutains;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.BiomeChalosPlains;
import stevekung.mods.moreplanets.planets.chalos.world.gen.biome.BiomeSlimelyStream;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.*;
import stevekung.mods.moreplanets.utils.world.gen.biome.BiomeMP;

public class MPBiomes
{
    public static Biome DIONA = new BiomeMP(new Biome.BiomeProperties("Diona").setRainfall(0.0F));
    public static Biome CHALOS_PLAINS = new BiomeChalosPlains(new Biome.BiomeProperties("Chalos Plains").setTemperature(0.8F).setRainfall(0.4F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome CHALOS_MOUTAINS = new BiomeChalosMoutains(new Biome.BiomeProperties("Chalos Moutains").setTemperature(0.2F).setRainfall(0.3F).setBaseHeight(1.0F).setHeightVariation(0.5F));
    public static Biome SLIMELY_STREAM = new BiomeSlimelyStream(new Biome.BiomeProperties("Slimely Stream").setBaseHeight(0.2F).setHeightVariation(0.2F));
    public static Biome INFECTED_PLAINS = new BiomeInfectedPlains(new Biome.BiomeProperties("Infected Plains").setTemperature(0.8F).setRainfall(0.4F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_DEAD_SAVANNA = new BiomeInfectedDeadSavanna(new Biome.BiomeProperties("Infected Dead Savanna").setRainDisabled().setRainfall(0.0F).setTemperature(1.2F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_DESERT = new BiomeInfectedDesert(new Biome.BiomeProperties("Infected Desert").setRainDisabled().setRainfall(0.0F).setTemperature(2.0F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_RIVER = new BiomeInfectedRiver(new Biome.BiomeProperties("Infected River").setBaseHeight(-0.5F).setHeightVariation(0.0F));
    public static Biome INFECTED_FROZEN_RIVER = new BiomeInfectedRiver(new Biome.BiomeProperties("Infected Frozen River").setBaseHeight(-0.5F).setHeightVariation(0.0F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled());
    public static Biome INFECTED_OCEAN = new BiomeInfectedOcean(new Biome.BiomeProperties("Infected Ocean").setBaseHeight(-1.0F).setHeightVariation(0.1F));
    public static Biome INFECTED_FOREST = new BiomeInfectedForest(new Biome.BiomeProperties("Infected Forest").setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_DEEP_OCEAN = new BiomeInfectedDeepOcean(new Biome.BiomeProperties("Infected Deep Ocean").setBaseHeight(-1.8F).setHeightVariation(0.1F));
    public static Biome INFECTED_DEAD_ROOFED_FOREST = new BiomeInfectedDeadRoofedForest(new Biome.BiomeProperties("Infected Dead Roofed Forest").setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_EXTREME_HILLS = new BiomeInfectedExtremeHills(new Biome.BiomeProperties("Infected Extreme Hills").setTemperature(0.2F).setRainfall(0.3F).setBaseHeight(1.0F).setHeightVariation(0.5F), BiomeInfectedExtremeHills.Type.NORMAL);
    public static Biome INFECTED_EXTREME_HILLS_EDGE = new BiomeInfectedExtremeHills(new Biome.BiomeProperties("Infected Extreme Hills Edge").setBaseHeight(0.8F).setHeightVariation(0.3F).setTemperature(0.2F).setRainfall(0.3F), BiomeInfectedExtremeHills.Type.EXTRA_TREES);
    public static Biome INFECTED_EXTREME_HILLS_WITH_TREES = new BiomeInfectedExtremeHills(new Biome.BiomeProperties("Infected Extreme Hills+").setBaseHeight(1.0F).setHeightVariation(0.5F).setTemperature(0.2F).setRainfall(0.3F), BiomeInfectedExtremeHills.Type.EXTRA_TREES);
    public static Biome INFECTED_SWAMP = new BiomeInfectedSwamp(new Biome.BiomeProperties("Infected Swamp").setTemperature(0.8F).setRainfall(0.9F).setBaseHeight(-0.2F).setHeightVariation(0.1F));
    public static Biome INFECTED_DEAD_TAIGA = new BiomeInfectedDeadTaiga(new Biome.BiomeProperties("Infected Dead Taiga").setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(0.25F).setRainfall(0.8F));
    public static Biome INFECTED_JUNGLE = new BiomeInfectedJungle(new Biome.BiomeProperties("Infected Jungle").setTemperature(0.95F).setRainfall(0.9F), false);
    public static Biome INFECTED_JUNGLE_EDGE = new BiomeInfectedJungle(new Biome.BiomeProperties("Infected Jungle Edge").setTemperature(0.95F).setRainfall(0.8F), true);
    public static Biome INFECTED_ICE_PLAINS = new BiomeInfectedIcePlains(new Biome.BiomeProperties("Infected Ice Plains").setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome GREEN_VEIN_BADLANDS = new BiomeGreenVeinBadlands(new Biome.BiomeProperties("Green Vein Badlands").setTemperature(0.9F).setRainfall(1.0F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome GREEN_VEIN_BADLANDS_SHORE = new BiomeGreenVeinBadlands(new Biome.BiomeProperties("Green Vein Badlands Shore").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.9F).setRainfall(1.0F));

    public static void init()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.DIONA, "diona");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.CHALOS_PLAINS, "chalos_plains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.CHALOS_MOUTAINS, "chalos_moutains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.SLIMELY_STREAM, "slimely_stream");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_PLAINS, "infected_plains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEAD_SAVANNA, "infected_dead_savanna");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DESERT, "infected_desert");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_RIVER, "infected_river");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_FROZEN_RIVER, "infected_frozen_river");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_OCEAN, "infected_ocean");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_FOREST, "infected_forest");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEEP_OCEAN, "infected_deep_ocean");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEAD_ROOFED_FOREST, "infected_dead_roofed_forest");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_EXTREME_HILLS, "infected_extreme_hills");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_EXTREME_HILLS_EDGE, "infected_extreme_hills_edge");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_EXTREME_HILLS_WITH_TREES, "infected_extreme_hills_with_trees");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_SWAMP, "infected_swampland");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEAD_TAIGA, "infected_dead_taiga");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_JUNGLE, "infected_jungle");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_JUNGLE_EDGE, "infected_jungle_edge");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_ICE_PLAINS, "infected_ice_plains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.GREEN_VEIN_BADLANDS, "green_vein_badlands");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.GREEN_VEIN_BADLANDS_SHORE, "green_vein_badlands_shore");
    }

    public static void registerTypes()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.DIONA, COLD, DEAD, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.CHALOS_PLAINS, PLAINS, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.CHALOS_MOUTAINS, MOUNTAIN, HILLS, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.SLIMELY_STREAM, WASTELAND, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_PLAINS, PLAINS, DEAD, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEAD_SAVANNA, HOT, SAVANNA, PLAINS, SPARSE, DRY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DESERT, HOT, DRY, SANDY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_RIVER, RIVER, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_FROZEN_RIVER, RIVER, COLD, SNOWY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_OCEAN, OCEAN, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_FOREST, FOREST, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEEP_OCEAN, OCEAN, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEAD_ROOFED_FOREST, SPOOKY, DENSE, FOREST, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_EXTREME_HILLS, MOUNTAIN, HILLS, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_EXTREME_HILLS_EDGE, MOUNTAIN, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_EXTREME_HILLS_WITH_TREES, MOUNTAIN, FOREST, SPARSE, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_SWAMP, WET, SWAMP, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEAD_TAIGA, COLD, CONIFEROUS, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_JUNGLE, HOT, WET, DENSE, JUNGLE, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_JUNGLE_EDGE, HOT, WET, RARE, JUNGLE, FOREST, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_ICE_PLAINS, COLD, SNOWY, WASTELAND, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.GREEN_VEIN_BADLANDS, FOREST, RARE, MAGICAL);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.GREEN_VEIN_BADLANDS_SHORE, FOREST, RARE, MAGICAL, BEACH);
    }
}