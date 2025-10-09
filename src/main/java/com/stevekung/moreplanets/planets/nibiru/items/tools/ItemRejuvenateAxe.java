package com.stevekung.moreplanets.planets.nibiru.items.tools;

import com.stevekung.lib.utils.enums.EnumToolSpeed;
import com.stevekung.moreplanets.utils.items.tools.ItemAxeMP;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRejuvenateAxe extends ItemAxeMP implements RejuvenateTool
{
    public ItemRejuvenateAxe(String name, ToolMaterial material, Block block, EnumToolSpeed toolSpeed)
    {
        super(name, material, block, toolSpeed);
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