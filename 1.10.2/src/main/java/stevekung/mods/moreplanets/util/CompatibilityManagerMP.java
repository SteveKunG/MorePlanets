package stevekung.mods.moreplanets.util;

import net.minecraftforge.fml.common.Loader;

public class CompatibilityManagerMP
{
    private static boolean isCTMLoaded = Loader.isModLoaded("ctm");

    public static void init()
    {
        if (CompatibilityManagerMP.isCTMLoaded)
        {
            MPLog.info("Enabled CTM integrations");
        }
    }

    public static boolean isCTMLoaded()
    {
        return CompatibilityManagerMP.isCTMLoaded;
    }
}