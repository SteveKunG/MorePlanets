package stevekung.mods.moreplanets.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class MPLog
{
    private static final Logger LOG = LogManager.getLogger("More Planets");
    private static final Logger LOG_DEBUG = LogManager.getLogger("More Planets Debug");

    public static void info(String message)
    {
        MPLog.LOG.info(message);
    }

    public static void error(String message)
    {
        MPLog.LOG.error(message);
    }

    public static void warning(String message)
    {
        MPLog.LOG.warn(message);
    }

    public static void debug(String message)
    {
        if (ConfigManagerMP.enableDebug || MorePlanetsCore.isObfuscatedEnvironment())
        {
            MPLog.LOG_DEBUG.info(message);
        }
    }

    public static void info(String message, Object... obj)
    {
        MPLog.LOG.info(message, obj);
    }

    public static void error(String message, Object... obj)
    {
        MPLog.LOG.error(message, obj);
    }

    public static void warning(String message, Object... obj)
    {
        MPLog.LOG.warn(message, obj);
    }

    public static void debug(String message, Object... obj)
    {
        if (ConfigManagerMP.enableDebug || MorePlanetsCore.isObfuscatedEnvironment())
        {
            MPLog.LOG_DEBUG.info(message, obj);
        }
    }
}