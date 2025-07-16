package com.stevekung.moreplanets.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.core.config.ConfigManagerMP;

public class LoggerMP
{
    private static final Logger LOGGER = LogManager.getLogger("More Planets");

    public static void info(String message)
    {
        LoggerMP.LOGGER.info(message);
    }

    public static void error(String message)
    {
        LoggerMP.LOGGER.error(message);
    }

    public static void warning(String message)
    {
        LoggerMP.LOGGER.warn(message);
    }

    public static void debug(String message)
    {
        if (ConfigManagerMP.moreplanets_general.enableDebug || MorePlanetsMod.isDevelopment)
        {
            LoggerMP.LOGGER.debug(message);
        }
    }

    public static void info(String message, Object... obj)
    {
        LoggerMP.LOGGER.info(message, obj);
    }

    public static void error(String message, Object... obj)
    {
        LoggerMP.LOGGER.error(message, obj);
    }

    public static void warning(String message, Object... obj)
    {
        LoggerMP.LOGGER.warn(message, obj);
    }

    public static void debug(String message, Object... obj)
    {
        if (ConfigManagerMP.moreplanets_general.enableDebug || MorePlanetsMod.isDevelopment)
        {
            LoggerMP.LOGGER.info(message, obj);
        }
    }
}