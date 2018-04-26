package stevekung.mods.stevekunglib.config;

import net.minecraftforge.common.config.Config;
import stevekung.mods.stevekunglib.core.SteveKunGLib;

@Config(modid = SteveKunGLib.MOD_ID)
public class ConfigManagerLib
{
    @Config.LangKey(value = "stevekung_lib_general")
    public static final General stevekung_lib_general = new General();

    // General Settings
    public static class General
    {
        @Config.Name(value = "Enable Debug Log")
        public boolean enableDebugLog = true;
    }
}