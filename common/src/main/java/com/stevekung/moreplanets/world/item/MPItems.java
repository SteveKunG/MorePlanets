package com.stevekung.moreplanets.world.item;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import net.minecraft.world.item.Item;

public class MPItems
{
    public static final Item GLOWING_IRON_INGOT = new Item(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item RAW_GLOWING_IRON = new Item(new Item.Properties().tab(MorePlanetsMod.TAB));

    public static void init()
    {
        MorePlanetsMod.COMMON.registerItem("glowing_iron_ingot", GLOWING_IRON_INGOT);
        MorePlanetsMod.COMMON.registerItem("raw_glowing_iron", RAW_GLOWING_IRON);
    }
}