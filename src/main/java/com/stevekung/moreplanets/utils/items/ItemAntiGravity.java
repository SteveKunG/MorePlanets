package com.stevekung.moreplanets.utils.items;

import net.minecraft.entity.item.EntityItem;

import micdoodle8.mods.galacticraft.core.TransformerHooks;

public class ItemAntiGravity extends ItemBaseMP
{
    public ItemAntiGravity(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        entityItem.motionY += TransformerHooks.getItemGravity(entityItem);
        return false;
    }
}