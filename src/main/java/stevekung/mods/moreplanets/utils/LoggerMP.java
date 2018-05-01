package stevekung.mods.moreplanets.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class LoggerMP
{
    private static final Logger LOG = LogManager.getLogger("More Planets");
    private static final Logger LOG_DEBUG = LogManager.getLogger("More Planets Debug");

    public static void info(String message)
    {
        LoggerMP.LOG.info(message);
    }

    public static void error(String message)
    {
        LoggerMP.LOG.error(message);
    }

    public static void warning(String message)
    {
        LoggerMP.LOG.warn(message);
    }

    public static void debug(String message)
    {
        if (ConfigManagerMP.moreplanets_general.enableDebug || MorePlanetsMod.isDevelopment)
        {
            LoggerMP.LOG_DEBUG.info(message);
        }
    }

    public static void info(String message, Object... obj)
    {
        LoggerMP.LOG.info(message, obj);
    }

    public static void error(String message, Object... obj)
    {
        LoggerMP.LOG.error(message, obj);
    }

    public static void warning(String message, Object... obj)
    {
        LoggerMP.LOG.warn(message, obj);
    }

    public static void debug(String message, Object... obj)
    {
        if (ConfigManagerMP.moreplanets_general.enableDebug || MorePlanetsMod.isDevelopment)
        {
            LoggerMP.LOG_DEBUG.info(message, obj);
        }
    }
}