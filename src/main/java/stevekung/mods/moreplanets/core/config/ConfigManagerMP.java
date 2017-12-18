/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.config.IConfigElement;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import stevekung.mods.moreplanets.core.util.MPLog;

public class ConfigManagerMP
{
    private static Configuration config;
    public static String DIMENSIONS = "dimensions";
    public static String SCHEMATICS = "schematics";
    public static String GENERAL = "general";
    public static String CELESTIAL = "celestial";
    public static String BIOMES = "biomes";
    public static String POTIONS = "potions";
    public static String GUIS = "guis";

    // Dimensions
    public static int idDimensionDiona;
    public static int idDimensionPolongnius;
    public static int idDimensionKoentus;
    public static int idDimensionNibiru;
    public static int idDimensionFronos;
    public static int idDimensionKapteynB;
    public static int idDimensionSiriusB;
    public static int idDimensionMercury;
    public static int idDimensionVenus;
    public static int idDimensionPluto;
    public static int idDimensionDeimos;
    public static int idDimensionPhobos;
    public static int idDimensionJupiter;
    public static int idDimensionDarkAsteroids;
    public static int idDimensionJupiterSpaceStation;
    public static int idDimensionStaticJupiterSpaceStation;
    public static int idDimensionMarsSpaceStation;
    public static int idDimensionStaticMarsSpaceStation;

    // General
    @Deprecated //TODO: Removed in 1.8
    public static boolean enableRocketWithThaiFlag;
    @Deprecated //TODO: Removed in 1.8
    public static boolean enableThaiFlagAndCanvas;
    public static boolean enableMultiCandyCaneRecipe;
    public static boolean enableHumanFecesSpawningOnMars;
    public static boolean disableInfectedGas;
    public static boolean allowMobCreatureSpawningOnFronos;
    public static boolean enableVersionCheck;
    public static boolean enableChangeLogInGame;
    public static boolean enableNewMainMenu;
    public static boolean enableDebug;
    public static String homePlanetName;

    // Celestial
    public static boolean enableMercuryPlanet;
    public static boolean enableVenusPlanet;
    public static boolean enablePlutoPlanet;
    public static boolean enablePhobosMoon;
    public static boolean enableDeimosMoon;
    public static boolean enableJupiterPlanet;
    public static boolean enableJupiterSpaceStation;
    public static boolean enableMarsSpaceStation;

    // Biomes
    @Deprecated //TODO: Removed in 1.8
    public static int idDionaBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idPolongniusBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idKoentusBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idNibiruBiome;
    public static int idCoconutForestBiome;
    public static int idGoldenFieldBiome;
    public static int idPurpleMapleForestBiome;
    public static int idMapleForestBiome;
    public static int idGrassyPlainsBiome;
    public static int idCandyLandBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idKapteynBBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idSiriusBBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idMercuryBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idVenusBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idPlutoBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idDeimosBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idPhobosBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idIoBiome;
    public static int idMPSpaceStationBiome;
    @Deprecated //TODO: Removed in 1.8
    public static int idDarkAsteroidsBiome;

    // Schematics
    public static int idTier4RocketSchematic;
    @Deprecated //TODO: Removed in 1.8
    public static int idSchematicTier4RocketNoFlag;
    public static int idTier5RocketSchematic;
    @Deprecated //TODO: Removed in 1.8
    public static int idSchematicTier5RocketNoFlag;
    public static int idTier6RocketSchematic;
    @Deprecated //TODO: Removed in 1.8
    public static int idSchematicTier6RocketNoFlag;
    public static int idTier7RocketSchematic;
    public static int idTier8RocketSchematic;

    // GUIs
    public static int idTier4RocketSchematicGui;
    @Deprecated //TODO: Removed in 1.8
    public static int idGuiSchematicTier4RocketNoFlag;
    public static int idTier5RocketSchematicGui;
    @Deprecated //TODO: Removed in 1.8
    public static int idGuiSchematicTier5RocketNoFlag;
    public static int idTier6RocketSchematicGui;
    @Deprecated //TODO: Removed in 1.8
    public static int idGuiSchematicTier6RocketNoFlag;
    public static int idTier7RocketSchematicGui;
    public static int idTier8RocketSchematicGui;

    // Potions
    public static int idPotionInfectedGas;
    public static int idPotionChemical;
    public static int idPotionEMP;
    public static int idPotionIcyPoison;

    public static void init(File file)
    {
        ConfigManagerMP.config = new Configuration(file);
        ConfigManagerMP.syncConfig(true);
    }

    public static void syncConfig(boolean load)
    {
        List<String> propOrder = new ArrayList<String>();

        try
        {
            Property prop;

            if (!ConfigManagerMP.config.isChild)
            {
                if (load)
                {
                    ConfigManagerMP.config.load();
                }
            }

            // Dimensions
            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Diona Dimension ID", -2542);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionDiona = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Koentus Dimension ID", -2543);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionKoentus = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Polongnius Dimension ID", -2544);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionPolongnius = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Nibiru Dimension ID", -2545);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionNibiru = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Fronos Dimension ID", -2546);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionFronos = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Kapteyn B Dimension ID", -2547);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionKapteynB = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Sirius B Dimension ID", -2548);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionSiriusB = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Mercury Dimension ID", -2549);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionMercury = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Venus Dimension ID", -2550);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionVenus = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Pluto Dimension ID", -2551);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionPluto = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Deimos Dimension ID", -2552);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionDeimos = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Phobos Dimension ID", -2553);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionPhobos = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Dark Asteroids Dimension ID", -2554);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionDarkAsteroids = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Jupiter Space Station Dimension ID", -2600);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionJupiterSpaceStation = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Jupiter Space Station (Static) Dimension ID", -2601);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionStaticJupiterSpaceStation = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Mars Space Station Dimension ID", -2602);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionMarsSpaceStation = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Mars Space Station (Static) Dimension ID", -2603);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionStaticMarsSpaceStation = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Jupiter Dimension ID", -5000);
            prop.comment = "Dimension ID Template for the Jupiter";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDimensionJupiter = prop.getInt();
            propOrder.add(prop.getName());

            // General
            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Rocket with Thai Flag", false);
            prop.comment = "If true, Rocket with Thai Flag will display in the creative tabs. And can be craft it.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableRocketWithThaiFlag = prop.getBoolean(false);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Thai Flag and Thai Canvas", false);
            prop.comment = "If true, Thai Flag and Thai Canvas will show in the creative tabs. And can be craft it.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableThaiFlagAndCanvas = prop.getBoolean(false);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Disable Infected Gas on Nibiru", false);
            prop.comment = "If true, When you mining any blocks on the Nibiru you will getting Infected Gas and Infected Gas does not affected when equipped Tier 2 Thermal Armor.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.disableInfectedGas = prop.getBoolean(false);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Multi Candy Tools Recipe", false);
            prop.comment = "If true, Candy Tools or Candy Bow or Poison Arrow can crafted by any type of candy cane.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableMultiCandyCaneRecipe = prop.getBoolean(false);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Human Feces Spawning on Mars", true);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableHumanFecesSpawningOnMars = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Allow Overworld Mobs/Creatures Spawning on Fronos", false);
            prop.comment = "If true, Overworld mobs and creatures will be spawning on Fronos. But this will disable Galacticraft Evolved Mob from spawning.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.allowMobCreatureSpawningOnFronos = prop.getBoolean(false);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Version Check", true);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableVersionCheck = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Change Log in Game", true);
            ConfigManagerMP.enableChangeLogInGame = prop.getBoolean();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable New Main Menu Screen", true);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableNewMainMenu = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Debug Logging", false);
            prop.comment = "If true, debug messages will appear in the console. This is useful for finding bugs in the mod.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableDebug = prop.getBoolean(false);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Home Planet Name Selection", "planet.");
            prop.comment = "Put your home planet name to survival on your selection planet. For example : planet.fronos";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.homePlanetName = prop.getString();
            propOrder.add(prop.getName());

            // Celestial
            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Mercury Planet", true);
            prop.comment = "If you playing with other GC Add-on you can disable Mercury to prevent crash and conflict.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableMercuryPlanet = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Venus Planet", true);
            prop.comment = "If you playing with other GC Add-on you can disable Venus to prevent crash and conflict.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableVenusPlanet = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Pluto Planet", true);
            prop.comment = "If you playing with other GC Add-on you can disable Pluto to prevent crash and conflict.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enablePlutoPlanet = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Jupiter Planet", true);
            prop.comment = "If you playing with other GC Add-on you can disable Jupiter to prevent crash and conflict. But if disable this option and enabled Jupiter Space Station. Game will getting crash!";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableJupiterPlanet = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Phobos Moon", true);
            prop.comment = "If you playing with other GC Add-on you can disable Phobos to prevent crash and conflict.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enablePhobosMoon = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Deimos Moon", true);
            prop.comment = "If you playing with other GC Add-on you can disable Deimos to prevent crash and conflict.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableDeimosMoon = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Jupiter Space Station", true);
            prop.comment = "If you playing with other GC Add-on you can disable Jupiter Space Station to prevent crash and conflict. But if disabled Jupiter and enabled Jupiter Space Station option. Game will getting crash!";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableJupiterSpaceStation = prop.getBoolean(true);
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.CELESTIAL, "Enable Mars Space Station", true);
            prop.comment = "If you playing with other GC Add-on you can disable Mars Space Station to prevent crash and conflict.";
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.enableMarsSpaceStation = prop.getBoolean(true);
            propOrder.add(prop.getName());

            // Biomes
            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Diona Biome ID", 220);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDionaBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Polongnius Biome ID", 221);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPolongniusBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Nibiru Biome ID", 222);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idNibiruBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Koentus Biome ID", 223);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idKoentusBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Coconut Forest (Fronos) Biome ID", 224);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idCoconutForestBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Kapteyn B Biome ID", 225);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idKapteynBBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Sirius B Biome ID", 226);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idSiriusBBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Golden Field (Fronos) Biome ID", 244);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idGoldenFieldBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Purple Maple Forest (Fronos) Biome ID", 245);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPurpleMapleForestBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Maple Forest (Fronos) Biome ID", 246);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idMapleForestBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Grassy Plains (Fronos) Biome ID", 247);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idGrassyPlainsBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Candy Land (Fronos) Biome ID", 248);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idCandyLandBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Mercury Biome ID", 240);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idMercuryBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Venus Biome ID", 241);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idVenusBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Pluto Biome ID", 242);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPlutoBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Deimos Biome ID", 210);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDeimosBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Phobos Biome ID", 211);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPhobosBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Io Biome ID", 212);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idIoBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "Dark Asteroids Biome ID", 213);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idDarkAsteroidsBiome = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.BIOMES, "More Planets Space Station Biome ID", 130);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idMPSpaceStationBiome = prop.getInt();
            propOrder.add(prop.getName());

            // Schematics
            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 4 Rocket Schematic ID", 805);
            ConfigManagerMP.idTier4RocketSchematic = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 5 Rocket Schematic ID", 806);
            ConfigManagerMP.idTier5RocketSchematic = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 6 Rocket Schematic ID", 807);
            ConfigManagerMP.idTier6RocketSchematic = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 7 Rocket Schematic ID", 803);
            ConfigManagerMP.idTier7RocketSchematic = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 8 Rocket Schematic ID", 804);
            ConfigManagerMP.idTier8RocketSchematic = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 4 Rocket No Flag Schematic ID", 800);
            ConfigManagerMP.idSchematicTier4RocketNoFlag = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 5 Rocket No Flag Schematic ID", 801);
            ConfigManagerMP.idSchematicTier5RocketNoFlag = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.SCHEMATICS, "Tier 6 Rocket No Flag Schematic ID", 802);
            ConfigManagerMP.idSchematicTier6RocketNoFlag = prop.getInt();
            propOrder.add(prop.getName());

            // GUIs
            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 4 Rocket Schematic GUI ID", 500);
            ConfigManagerMP.idTier4RocketSchematicGui = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 5 Rocket Schematic GUI ID", 501);
            ConfigManagerMP.idTier5RocketSchematicGui = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 6 Rocket Schematic GUI ID", 502);
            ConfigManagerMP.idTier6RocketSchematicGui = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 7 Rocket Schematic GUI ID", 503);
            ConfigManagerMP.idTier7RocketSchematicGui = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 8 Rocket Schematic GUI ID", 504);
            ConfigManagerMP.idTier8RocketSchematicGui = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 4 Rocket No Flag Schematic GUI ID", 505);
            ConfigManagerMP.idGuiSchematicTier4RocketNoFlag = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 5 Rocket No Flag Schematic GUI ID", 506);
            ConfigManagerMP.idGuiSchematicTier5RocketNoFlag = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.GUIS, "Tier 6 Rocket No Flag Schematic GUI ID", 507);
            ConfigManagerMP.idGuiSchematicTier6RocketNoFlag = prop.getInt();
            propOrder.add(prop.getName());

            // Potions
            prop = ConfigManagerMP.config.get(ConfigManagerMP.POTIONS, "Infected Gas Potion ID", 60);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPotionInfectedGas = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.POTIONS, "Chemical Potion ID", 61);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPotionChemical = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.POTIONS, "EMP Potion ID", 62);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPotionEMP = prop.getInt();
            propOrder.add(prop.getName());

            prop = ConfigManagerMP.config.get(ConfigManagerMP.POTIONS, "Icy Poison Potion ID", 63);
            prop.setRequiresMcRestart(true);
            ConfigManagerMP.idPotionIcyPoison = prop.getInt();
            propOrder.add(prop.getName());

            ConfigManagerMP.config.setCategoryPropertyOrder(Configuration.CATEGORY_GENERAL, propOrder);

            if (ConfigManagerMP.config.hasChanged())
            {
                ConfigManagerMP.config.save();
            }
        }
        catch (Exception e)
        {
            MPLog.error("More Planets has a problem loading it's configuration");
        }
    }

    public static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.GENERAL)).getChildElements());
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.CELESTIAL)).getChildElements());
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.DIMENSIONS)).getChildElements());
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.BIOMES)).getChildElements());
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.POTIONS)).getChildElements());
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.SCHEMATICS)).getChildElements());
        list.addAll(new ConfigElement(ConfigManagerMP.config.getCategory(ConfigManagerMP.GUIS)).getChildElements());
        return list;
    }
}