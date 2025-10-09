package com.stevekung.moreplanets.planets.nibiru.items.tools;

import com.stevekung.moreplanets.utils.items.tools.ItemHoeMP;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRejuvenateHoe extends ItemHoeMP implements RejuvenateTool
{
    public ItemRejuvenateHoe(String name, ToolMaterial material, Block block)
    {
        super(name, material, block);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        this.onTick(itemStack, entity.ticksExisted, null);
        super.onUpdate(itemStack, world, entity, itemSlot, isSelected);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem itemEntity)
    {
        this.onTick(itemEntity.getItem(), itemEntity.ticksExisted, itemEntity);
        return super.onEntityItemUpdate(itemEntity);
    }
}