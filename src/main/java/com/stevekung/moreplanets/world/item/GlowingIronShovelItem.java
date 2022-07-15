package com.stevekung.moreplanets.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class GlowingIronShovelItem extends ShovelItem
{
    public GlowingIronShovelItem(Item.Properties properties)
    {
        super(TiersMP.GLOWING_IRON, 1.5F, -3.0F, properties);
    }
}