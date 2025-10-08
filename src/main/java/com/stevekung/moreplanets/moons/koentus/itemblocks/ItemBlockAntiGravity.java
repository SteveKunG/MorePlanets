package com.stevekung.moreplanets.moons.koentus.itemblocks;

import com.stevekung.moreplanets.utils.itemblocks.ItemBlockMP;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;

import micdoodle8.mods.galacticraft.core.TransformerHooks;

public class ItemBlockAntiGravity extends ItemBlockMP
{
    public ItemBlockAntiGravity(Block block)
    {
        super(block);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        entityItem.motionY += TransformerHooks.getItemGravity(entityItem);
        return false;
    }
}