package com.stevekung.moreplanets.utils.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import micdoodle8.mods.galacticraft.api.item.GCRarity;

public class ItemBlockMP extends ItemBlock implements GCRarity
{
    public ItemBlockMP(Block block)
    {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this.block instanceof IItemRarity && ((IItemRarity)this.block).getRarity() != null ? ((IItemRarity)this.block).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }
}