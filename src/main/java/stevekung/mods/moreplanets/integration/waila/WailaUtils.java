package stevekung.mods.moreplanets.integration.waila;

import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaUtils
{
    public static String WAILA_CONSTANT = "MorePlanets";
    static IWailaRegistrar register;
    static IWailaDataProvider dataProvider;

    public static void register(Class<?> clazz, boolean nbt, boolean head, boolean body, boolean tail, boolean stack)
    {
        IWailaRegistrar register = WailaUtils.register;
        IWailaDataProvider dataProvider = WailaUtils.dataProvider;

        if (nbt)
        {
            register.registerNBTProvider(dataProvider, clazz);
        }
        if (head)
        {
            register.registerHeadProvider(dataProvider, clazz);
        }
        if (body)
        {
            register.registerBodyProvider(dataProvider, clazz);
        }
        if (tail)
        {
            register.registerTailProvider(dataProvider, clazz);
        }
        if (stack)
        {
            register.registerStackProvider(dataProvider, clazz);
        }
    }

    public static void register(Class<?> clazz, IWailaDataProvider dataProvider, boolean nbt, boolean head, boolean body, boolean tail, boolean stack)
    {
        IWailaRegistrar register = WailaUtils.register;

        if (nbt)
        {
            register.registerNBTProvider(dataProvider, clazz);
        }
        if (head)
        {
            register.registerHeadProvider(dataProvider, clazz);
        }
        if (body)
        {
            register.registerBodyProvider(dataProvider, clazz);
        }
        if (tail)
        {
            register.registerTailProvider(dataProvider, clazz);
        }
        if (stack)
        {
            register.registerStackProvider(dataProvider, clazz);
        }
    }
}