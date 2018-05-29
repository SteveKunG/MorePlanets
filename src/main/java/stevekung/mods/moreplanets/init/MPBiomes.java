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
    public static Biome INFECTED_PLAINS = new BiomeInfectedPlains(new Biome.BiomeProperties("Infected Plains").setTemperature(0.8F).setRainfall(0.4F).setBaseHeight(0.125F).setHeightVariation(0.05F), false);
    public static Biome INFECTED_INFESTED_PLAINS = new BiomeInfectedPlains(new Biome.BiomeProperties("Infected Infested Plains").setBaseBiome("infected_plains").setTemperature(0.8F).setRainfall(0.4F).setBaseHeight(0.025F).setHeightVariation(0.025F), true);
    public static Biome INFECTED_DEAD_SAVANNA = new BiomeInfectedDeadSavanna(new Biome.BiomeProperties("Infected Dead Savanna").setRainDisabled().setRainfall(0.0F).setTemperature(1.2F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_DEAD_SAVANNA_PLATEAU = new BiomeInfectedDeadSavanna(new Biome.BiomeProperties("Infected Dead Savanna Plateau").setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(1.0F).setRainfall(0.0F).setRainDisabled());
    public static Biome INFECTED_DESERT = new BiomeInfectedDesert(new Biome.BiomeProperties("Infected Desert").setRainDisabled().setRainfall(0.0F).setTemperature(2.0F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome INFECTED_DESERT_HILLS = new BiomeInfectedDesert(new Biome.BiomeProperties("Infected Desert Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static Biome INFECTED_RIVER = new BiomeInfectedRiver(new Biome.BiomeProperties("Infected River").setBaseHeight(-0.5F).setHeightVariation(0.0F));
    public static Biome INFECTED_FROZEN_RIVER = new BiomeInfectedRiver(new Biome.BiomeProperties("Infected Frozen River").setBaseHeight(-0.5F).setHeightVariation(0.0F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled());
    public static Biome INFECTED_OCEAN = new BiomeInfectedOcean(new Biome.BiomeProperties("Infected Ocean").setBaseHeight(-1.0F).setHeightVariation(0.1F));
    public static Biome INFECTED_FOREST = new BiomeInfectedForest(new Biome.BiomeProperties("Infected Forest").setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_WOODED_HILLS = new BiomeInfectedForest(new Biome.BiomeProperties("Infected Wooded Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_DEEP_OCEAN = new BiomeInfectedOcean(new Biome.BiomeProperties("Infected Deep Ocean").setBaseHeight(-1.8F).setHeightVariation(0.1F));
    public static Biome INFECTED_DEAD_DARK_FOREST = new BiomeInfectedDeadDarkForest(new Biome.BiomeProperties("Infected Dead Dark Forest").setTemperature(0.7F).setRainfall(0.8F));
    public static Biome INFECTED_MOUNTAINS = new BiomeInfectedMountains(new Biome.BiomeProperties("Infected Mountains").setTemperature(0.2F).setRainfall(0.3F).setBaseHeight(1.0F).setHeightVariation(0.5F), BiomeInfectedMountains.Type.NORMAL);
    public static Biome INFECTED_MOUNTAINS_EDGE = new BiomeInfectedMountains(new Biome.BiomeProperties("Infected Mountains Edge").setBaseHeight(0.8F).setHeightVariation(0.3F).setTemperature(0.2F).setRainfall(0.3F), BiomeInfectedMountains.Type.EXTRA_TREES);
    public static Biome INFECTED_WOODED_MOUNTAINS = new BiomeInfectedMountains(new Biome.BiomeProperties("Infected Wooded Mountains").setBaseHeight(1.0F).setHeightVariation(0.5F).setTemperature(0.2F).setRainfall(0.3F), BiomeInfectedMountains.Type.EXTRA_TREES);
    public static Biome INFECTED_SWAMP = new BiomeInfectedSwamp(new Biome.BiomeProperties("Infected Swamp").setTemperature(0.8F).setRainfall(0.9F).setBaseHeight(-0.2F).setHeightVariation(0.1F));
    public static Biome INFECTED_TAIGA = new BiomeInfectedTaiga(new Biome.BiomeProperties("Infected Taiga").setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(0.25F).setRainfall(0.8F), BiomeInfectedTaiga.Type.NORMAL);
    public static Biome INFECTED_TAIGA_HILLS = new BiomeInfectedTaiga(new Biome.BiomeProperties("Infected Taiga Hills").setTemperature(0.25F).setRainfall(0.8F).setBaseHeight(0.45F).setHeightVariation(0.3F), BiomeInfectedTaiga.Type.NORMAL);
    public static Biome INFECTED_JUNGLE = new BiomeInfectedJungle(new Biome.BiomeProperties("Infected Jungle").setTemperature(0.95F).setRainfall(0.9F), false);
    public static Biome INFECTED_JUNGLE_HILLS = new BiomeInfectedJungle(new Biome.BiomeProperties("Infected Jungle Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.95F).setRainfall(0.9F), false);
    public static Biome INFECTED_JUNGLE_EDGE = new BiomeInfectedJungle(new Biome.BiomeProperties("Infected Jungle Edge").setTemperature(0.95F).setRainfall(0.8F), true);
    public static Biome INFECTED_SNOWY_TUNDRA = new BiomeInfectedSnow(new Biome.BiomeProperties("Infected Snowy Tundra").setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F).setBaseHeight(0.125F).setHeightVariation(0.05F), false);
    public static Biome INFECTED_SNOWY_MOUNTAINS = new BiomeInfectedSnow(new Biome.BiomeProperties("Infected Snowy Mountains").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled(), false);
    public static Biome GREEN_VEIN_FIELDS = new BiomeGreenVeinFields(new Biome.BiomeProperties("Green Vein Fields").setTemperature(0.9F).setRainfall(1.0F).setBaseHeight(0.125F).setHeightVariation(0.05F));
    public static Biome GREEN_VEIN_FIELD_SHORE = new BiomeGreenVeinFields(new Biome.BiomeProperties("Green Vein Field Shore").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.9F).setRainfall(1.0F));
    public static Biome INFECTED_BEACHES = new BiomeInfectedBeach(new Biome.BiomeProperties("Infected Beach").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F), false);
    public static Biome INFECTED_STONE_SHORE = new BiomeInfectedBeach(new Biome.BiomeProperties("Infected Stone Shore").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F), true);
    public static Biome INFECTED_SNOWY_BEACH = new BiomeInfectedBeach(new Biome.BiomeProperties("Infected Snowy Beach").setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.05F).setRainfall(0.3F).setSnowEnabled(), false);
    public static Biome INFECTED_BADLANDS = new BiomeInfectedBadlands(new Biome.BiomeProperties("Infected Badlands").setTemperature(2.0F).setRainfall(0.0F).setRainDisabled(), false, false);
    public static Biome INFECTED_WOODED_BADLANDS_PLATEAU = new BiomeInfectedBadlands(new Biome.BiomeProperties("Infected Wooded Badlands Plateau").setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled(), false, true);
    public static Biome INFECTED_BADLANDS_PLATEAU = new BiomeInfectedBadlands(new Biome.BiomeProperties("Infected Badlands Plateau").setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled(), false, false);

    public static void init()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.DIONA, "diona");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.CHALOS_PLAINS, "chalos_plains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.CHALOS_MOUTAINS, "chalos_moutains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.SLIMELY_STREAM, "slimely_stream");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_PLAINS, "infected_plains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_INFESTED_PLAINS, "infected_infested_plains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEAD_SAVANNA, "infected_dead_savanna");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEAD_SAVANNA_PLATEAU, "infected_dead_savanna_plateau");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DESERT, "infected_desert");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DESERT_HILLS, "infected_desert_hills");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_RIVER, "infected_river");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_FROZEN_RIVER, "infected_frozen_river");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_OCEAN, "infected_ocean");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_FOREST, "infected_forest");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_WOODED_HILLS, "infected_wooded_hills");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEEP_OCEAN, "infected_deep_ocean");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_DEAD_DARK_FOREST, "infected_dead_dark_forest");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_MOUNTAINS, "infected_mountains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_MOUNTAINS_EDGE, "infected_mountains_edge");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_WOODED_MOUNTAINS, "infected_wooded_mountains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_SWAMP, "infected_swamp");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_TAIGA, "infected_taiga");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_TAIGA_HILLS, "infected_taiga_hills");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_JUNGLE, "infected_jungle");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_JUNGLE_HILLS, "infected_jungle_hills");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_JUNGLE_EDGE, "infected_jungle_edge");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_SNOWY_TUNDRA, "infected_snowy_tundra");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_SNOWY_MOUNTAINS, "infected_snowy_mountains");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.GREEN_VEIN_FIELDS, "green_vein_fields");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.GREEN_VEIN_FIELD_SHORE, "green_vein_field_shore");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_BEACHES, "infected_beaches");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_STONE_SHORE, "infected_stone_shore");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_SNOWY_BEACH, "infected_snowy_beach");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_BADLANDS, "infected_badlands");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_WOODED_BADLANDS_PLATEAU, "infected_wooded_badlands_plateau");
        MorePlanetsMod.COMMON_REGISTRY.registerBiome(MPBiomes.INFECTED_BADLANDS_PLATEAU, "infected_badlands_plateau");
    }

    public static void registerTypes()
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.DIONA, COLD, DEAD, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.CHALOS_PLAINS, PLAINS, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.CHALOS_MOUTAINS, MOUNTAIN, HILLS, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.SLIMELY_STREAM, WASTELAND, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_PLAINS, PLAINS, DEAD, DRY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_INFESTED_PLAINS, PLAINS, DEAD, DRY, RARE);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEAD_SAVANNA, HOT, SAVANNA, PLAINS, SPARSE, DRY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEAD_SAVANNA_PLATEAU, HOT, SAVANNA, PLAINS, SPARSE, DRY, DEAD, RARE);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DESERT, HOT, DRY, SANDY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DESERT_HILLS, HOT, DRY, SANDY, DEAD, HILLS);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_RIVER, RIVER, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_FROZEN_RIVER, RIVER, COLD, SNOWY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_OCEAN, OCEAN, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_FOREST, FOREST, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_WOODED_HILLS, FOREST, DEAD, HILLS);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEEP_OCEAN, OCEAN, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_DEAD_DARK_FOREST, SPOOKY, DENSE, FOREST, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_MOUNTAINS, MOUNTAIN, HILLS, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_MOUNTAINS_EDGE, MOUNTAIN, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_WOODED_MOUNTAINS, MOUNTAIN, FOREST, SPARSE, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_SWAMP, WET, SWAMP, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_TAIGA, COLD, CONIFEROUS, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_TAIGA_HILLS, COLD, CONIFEROUS, DEAD, HILLS);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_JUNGLE, HOT, WET, DENSE, JUNGLE, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_JUNGLE_HILLS, HOT, WET, DENSE, JUNGLE, DEAD, HILLS);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_JUNGLE_EDGE, HOT, WET, RARE, JUNGLE, FOREST, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_SNOWY_TUNDRA, COLD, SNOWY, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_SNOWY_MOUNTAINS, COLD, SNOWY, DEAD, MOUNTAIN);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.GREEN_VEIN_FIELDS, FOREST, RARE, MAGICAL);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.GREEN_VEIN_FIELD_SHORE, FOREST, RARE, MAGICAL, BEACH);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_BEACHES, BEACH, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_STONE_SHORE, BEACH, DEAD);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_SNOWY_BEACH, BEACH, DEAD, COLD, SNOWY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_BADLANDS, MESA, DEAD, SANDY);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_WOODED_BADLANDS_PLATEAU, MESA, DEAD, SANDY, SPARSE);
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(MPBiomes.INFECTED_BADLANDS_PLATEAU, MESA, DEAD, SANDY);
    }
}