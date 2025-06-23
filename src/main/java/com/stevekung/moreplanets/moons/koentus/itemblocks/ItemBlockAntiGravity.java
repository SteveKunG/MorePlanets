package com.stevekung.moreplanets.moons.koentus.itemblocks;

import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import com.stevekung.moreplanets.utils.itemblocks.ItemBlockMP;

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