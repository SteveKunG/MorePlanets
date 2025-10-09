package com.stevekung.moreplanets.planets.nibiru.items.tools;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public interface RejuvenateTool
{
    default void onTick(ItemStack itemStack, int ticksExisted, @Nullable EntityItem itemEntity)
    {
        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
        {
            if (ticksExisted % 600 == 0)
            {
                int i = itemStack.getItemDamage();
                itemStack.setItemDamage(--i);

                if (itemEntity != null)
                {
                    itemEntity.lifespan = 6000;
                    itemEntity.world.playEvent(2005, new BlockPos(itemEntity.posX, itemEntity.posY, itemEntity.posZ), 0);
                }
            }
        }
    }
}