package com.stevekung.moreplanets.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class GlowingIronSwordItem extends SwordItem
{
    public GlowingIronSwordItem(Item.Properties properties)
    {
        super(TiersMP.GLOWING_IRON, 3, -2.4F, properties);
    }
}