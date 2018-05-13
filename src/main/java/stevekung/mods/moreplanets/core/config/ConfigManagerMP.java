package stevekung.mods.moreplanets.core.config;

import net.minecraftforge.common.config.Config;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

@Config(modid = MorePlanetsMod.MOD_ID)
public class ConfigManagerMP
{
    @Config.LangKey(value = "moreplanets_general")
    @Config.Comment(value = "Based More Planets Configuration. Version Checker, Debug, Mod Option. etc.")
    public static final General moreplanets_general = new General();

    @Config.LangKey(value = "moreplanets_dimension")
    @Config.Comment(value = "Planet or Moon Dimension IDs Configuration.")
    public static final Dimension moreplanets_dimension = new Dimension();

    @Config.LangKey(value = "moreplanets_planet_settings")
    @Config.Comment(value = "Planet Configuration.")
    public static final PlanetSettings moreplanets_planet_settings = new PlanetSettings();

    @Config.LangKey(value = "moreplanets_other")
    @Config.Comment(value = "Others Configuration.")
    public static final Other moreplanets_other = new Other();

    // General
    public static class General
    {
        @Config.Comment("Put planet name that you would like to spawn (Also you need to enable started planet in the config). For example \"planet.nibiru\", \"moon.moon\", \"satellite.spacestation.overworld\" ")
        @Config.Name(value = "Planet to Start")
        public String startedPlanet = "planet.";

        @Config.Name(value = "Enable Debug Logging")
        public boolean enableDebug = false;

        @Config.Name(value = "Enable Version Checker")
        @Config.RequiresMcRestart
        public boolean enableVersionChecker = true;

        @Config.Name(value = "Enable Change Log in Game")
        public boolean enableChangeLogInGame = true;

        @Config.Name(value = "Enable Night Vision Effect while boss fight")
        public boolean enableNightVisionEffect = false;

        @Config.Name(value = "Enable Black Hole Explosion")
        public boolean enableBlackHoleExplosion = true;

        @Config.Name(value = "Enable Started Planet")
        public boolean enableStartedPlanet = false;

        @Config.Name(value = "Use 3D Item Model for Torch")
        @Config.RequiresMcRestart
        public boolean use3DTorchItemModel = true;

        @Config.Name(value = "Network Handler ID")
        @Config.RequiresMcRestart
        public int idNetworkHandler = 2542;
    }

    // Dimensions
    public static class Dimension
    {
        @Config.Name(value = "Diona Dimension ID")
        public int idDimensionDiona = -2542;

        @Config.Name(value = "Chalos Dimension ID")
        public int idDimensionChalos = -2543;

        @Config.Name(value = "Nibiru Dimension ID")
        public int idDimensionNibiru = -2544;

        @Config.Name(value = "Fronos Dimension ID")
        public int idDimensionFronos = -2545;
    }

    // Planet Settings
    public static class PlanetSettings
    {
        @Config.Name(value = "Diona Planet Tier")
        @Config.RequiresMcRestart
        public int planetDionaTier = 3;

        @Config.Name(value = "Chalos Planet Tier")
        @Config.RequiresMcRestart
        public int planetChalosTier = 3;

        @Config.Name(value = "Nibiru Planet Tier")
        @Config.RequiresMcRestart
        public int planetNibiruTier = 3;
    }

    // Others
    public static class Other
    {
        @Config.Name(value = "Base Schematic ID")
        public int idBaseSchematic = 850;

        @Config.Name(value = "Base Schematic GUI ID")
        public int idBaseSchematicGui = 550;

        @Config.Name(value = "Enable Description in Waila Tooltip")
        public boolean enableDescriptionInWaila = false;
    }
}