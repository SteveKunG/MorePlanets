package stevekung.mods.moreplanets.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import stevekung.mods.moreplanets.util.MPLog;

public class ConfigManagerMP
{
    public static Configuration config;
    public static String GENERAL = "config_moreplanets_general";
    public static String DIMENSIONS = "config_moreplanets_dimensions";
    public static String GC_ADDON_COMPAT = "config_moreplanets_gc_addon_compat";
    public static String OTHERS = "config_moreplanets_others";

    // General
    public static String startedPlanet;
    public static boolean enableDebug;
    public static boolean enableVersionChecker;
    public static boolean enableChangeLogInGame;
    public static boolean enableNightVisionEffect;
    public static boolean enableBlackHoleExplosion;
    public static boolean enableStartedPlanet;
    public static boolean use3DTorchItemModel;
    public static int idNetworkHandler;

    // Dimensions
    public static int idDimensionDiona;
    public static int idDimensionChalos;
    public static int idDimensionNibiru;
    public static int idDimensionFronos;

    // Others
    public static int idBaseRocketSchematic;
    public static int idBaseSchematic;
    public static int idBaseRocketSchematicGui;
    public static int idBaseSchematicGui;
    public static boolean enableDescriptionInWaila;

    // GC-Addon Compatibility stuffs
    public static boolean enableTier4RocketSchematic;
    public static boolean enableTier5RocketSchematic;
    public static boolean enableTier6RocketSchematic;
    public static boolean enableTier4RocketRecipe;
    public static boolean enableTier5RocketRecipe;
    public static boolean enableTier6RocketRecipe;

    public static void init(File file)
    {
        ConfigManagerMP.config = new Configuration(file);
        ConfigManagerMP.syncConfig(true);
    }

    public static void syncConfig(boolean load)
    {
        try
        {
            if (!ConfigManagerMP.config.isChild)
            {
                if (load)
                {
                    ConfigManagerMP.config.load();
                }
            }

            ConfigManagerMP.config.setCategoryPropertyOrder(ConfigManagerMP.GENERAL, ConfigManagerMP.addGeneralConfig());
            ConfigManagerMP.config.setCategoryPropertyOrder(ConfigManagerMP.DIMENSIONS, ConfigManagerMP.addDimensionIDConfig());
            ConfigManagerMP.config.setCategoryPropertyOrder(ConfigManagerMP.GC_ADDON_COMPAT, ConfigManagerMP.addGCAddonCompatConfig());
            ConfigManagerMP.config.setCategoryPropertyOrder(ConfigManagerMP.OTHERS, ConfigManagerMP.addOtherConfig());

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

    private static ArrayList<String> addGeneralConfig()
    {
        ArrayList<String> propOrder = new ArrayList<>();
        Property prop;
        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Planet to Start", "planet.");
        prop.setComment("Put planet name that you would like to spawn (Also you need to enable started planet in the config). For example \"planet.nibiru\", \"moon.moon\", \"satellite.spacestation.overworld\" ");
        ConfigManagerMP.startedPlanet = prop.getString();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Started Planet", false);
        ConfigManagerMP.enableStartedPlanet = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Night Vision Effect while boss fight", false);
        ConfigManagerMP.enableNightVisionEffect = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Black Hole Explosion", true);
        ConfigManagerMP.enableBlackHoleExplosion = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Use 3D Item Model for Torch", true);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.use3DTorchItemModel = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Debug Logging", false);
        ConfigManagerMP.enableDebug = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Version Checker", true);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.enableVersionChecker = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Enable Change Log in Game", true);
        ConfigManagerMP.enableChangeLogInGame = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GENERAL, "Network Handler ID", 2542);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.idNetworkHandler = prop.getInt();
        propOrder.add(prop.getName());
        return propOrder;
    }

    private static ArrayList<String> addDimensionIDConfig()
    {
        ArrayList<String> propOrder = new ArrayList<>();
        Property prop;
        prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Diona Dimension ID", -2542);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.idDimensionDiona = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Chalos Dimension ID", -2543);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.idDimensionChalos = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Nibiru Dimension ID", -2544);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.idDimensionNibiru = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.DIMENSIONS, "Fronos Dimension ID", -2545);
        prop.setRequiresMcRestart(true);
        ConfigManagerMP.idDimensionFronos = prop.getInt();
        propOrder.add(prop.getName());
        return propOrder;
    }

    private static ArrayList<String> addGCAddonCompatConfig()
    {
        ArrayList<String> propOrder = new ArrayList<>();
        Property prop;
        prop = ConfigManagerMP.config.get(ConfigManagerMP.GC_ADDON_COMPAT, "Enable Tier 4 Rocket Schematic", true);
        ConfigManagerMP.enableTier4RocketSchematic = prop.getBoolean();
        prop.setRequiresMcRestart(true);
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GC_ADDON_COMPAT, "Enable Tier 5 Rocket Schematic", true);
        ConfigManagerMP.enableTier5RocketSchematic = prop.getBoolean();
        prop.setRequiresMcRestart(true);
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GC_ADDON_COMPAT, "Enable Tier 6 Rocket Schematic", true);
        ConfigManagerMP.enableTier6RocketSchematic = prop.getBoolean();
        prop.setRequiresMcRestart(true);
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GC_ADDON_COMPAT, "Enable Tier 4 Rocket Recipe", true);
        ConfigManagerMP.enableTier4RocketRecipe = prop.getBoolean();
        prop.setRequiresMcRestart(true);
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GC_ADDON_COMPAT, "Enable Tier 5 Rocket Recipe", true);
        ConfigManagerMP.enableTier5RocketRecipe = prop.getBoolean();
        prop.setRequiresMcRestart(true);
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.GC_ADDON_COMPAT, "Enable Tier 6 Rocket Recipe", true);
        ConfigManagerMP.enableTier6RocketRecipe = prop.getBoolean();
        prop.setRequiresMcRestart(true);
        propOrder.add(prop.getName());
        return propOrder;
    }

    private static ArrayList<String> addOtherConfig()
    {
        ArrayList<String> propOrder = new ArrayList<>();
        Property prop;
        prop = ConfigManagerMP.config.get(ConfigManagerMP.OTHERS, "Tier 4 Rocket Schematic ID", 800);
        ConfigManagerMP.idBaseRocketSchematic = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.OTHERS, "Base Schematic ID", 850);
        ConfigManagerMP.idBaseSchematic = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.OTHERS, "Tier 4 Rocket Schematic GUI ID", 500);
        ConfigManagerMP.idBaseRocketSchematicGui = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.OTHERS, "Base Schematic GUI ID", 550);
        ConfigManagerMP.idBaseSchematicGui = prop.getInt();
        propOrder.add(prop.getName());

        prop = ConfigManagerMP.config.get(ConfigManagerMP.OTHERS, "Enable Description in Waila Tooltip", true);
        ConfigManagerMP.enableDescriptionInWaila = prop.getBoolean();
        propOrder.add(prop.getName());
        return propOrder;
    }

    public static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<>();
        ConfigCategory configGeneral = ConfigManagerMP.config.getCategory(ConfigManagerMP.GENERAL);
        configGeneral.setComment(GCCoreUtil.translate("gui.config.mp.general"));
        list.add(new ConfigElement(configGeneral));

        ConfigCategory configDimension = ConfigManagerMP.config.getCategory(ConfigManagerMP.DIMENSIONS);
        configDimension.setComment(GCCoreUtil.translate("gui.config.mp.dimension"));
        list.add(new ConfigElement(configDimension));

        ConfigCategory configGCAddon = ConfigManagerMP.config.getCategory(ConfigManagerMP.GC_ADDON_COMPAT);
        configGCAddon.setComment(GCCoreUtil.translate("gui.config.mp.gcaddon"));
        list.add(new ConfigElement(configGCAddon));

        ConfigCategory configGUIs = ConfigManagerMP.config.getCategory(ConfigManagerMP.OTHERS);
        configGUIs.setComment(GCCoreUtil.translate("gui.config.mp.other"));
        list.add(new ConfigElement(configGUIs));
        return list;
    }
}