package stevekung.mods.stevekunglib.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import stevekung.mods.stevekunglib.config.ConfigManagerLib;
import stevekung.mods.stevekunglib.core.SteveKunGLib;

public class ModLogger
{
    private static final Logger LOG = LogManager.getLogger("SteveKunG's Lib");
    private static final Logger LOG_DEBUG = LogManager.getLogger("SteveKunG's Lib Debug");

    public static void info(String message)
    {
        ModLogger.LOG.info(message);
    }

    public static void error(String message)
    {
        ModLogger.LOG.error(message);
    }

    public static void warning(String message)
    {
        ModLogger.LOG.warn(message);
    }

    public static void debug(String message)
    {
        if (ConfigManagerLib.stevekung_lib_general.enableDebugLog || SteveKunGLib.isDevelopment)
        {
            ModLogger.LOG_DEBUG.info(message);
        }
    }

    public static void info(String message, Object... obj)
    {
        ModLogger.LOG.info(message, obj);
    }

    public static void error(String message, Object... obj)
    {
        ModLogger.LOG.error(message, obj);
    }

    public static void warning(String message, Object... obj)
    {
        ModLogger.LOG.warn(message, obj);
    }

    public static void debug(String message, Object... obj)
    {
        if (ConfigManagerLib.stevekung_lib_general.enableDebugLog || SteveKunGLib.isDevelopment)
        {
            ModLogger.LOG_DEBUG.info(message, obj);
        }
    }
}