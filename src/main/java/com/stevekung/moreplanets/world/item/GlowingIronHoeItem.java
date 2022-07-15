package com.stevekung.moreplanets.world.item;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;

public class GlowingIronHoeItem extends HoeItem
{
    public GlowingIronHoeItem(Item.Properties properties)
    {
        super(TiersMP.GLOWING_IRON, -2, -1.0F, properties);
    }
}