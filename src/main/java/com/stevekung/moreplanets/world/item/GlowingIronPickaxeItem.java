package com.stevekung.moreplanets.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;

public class GlowingIronPickaxeItem extends PickaxeItem
{
    public GlowingIronPickaxeItem(Item.Properties properties)
    {
        super(TiersMP.GLOWING_IRON, 1, -2.8F, properties);
    }
}