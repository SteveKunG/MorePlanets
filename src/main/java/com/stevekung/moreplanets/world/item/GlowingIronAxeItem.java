package com.stevekung.moreplanets.world.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class GlowingIronAxeItem extends AxeItem
{
    public GlowingIronAxeItem(Item.Properties properties)
    {
        super(TiersMP.GLOWING_IRON, 6.0F, -3.1F, properties);
    }
}