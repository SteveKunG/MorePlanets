package com.stevekung.moreplanets.inventory;

import com.stevekung.moreplanets.init.MPItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpaceWarpPad extends Slot
{
    public SlotSpaceWarpPad(IInventory inv, int slotIndex, int x, int y)
    {
        super(inv, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() == MPItems.SPACE_WARPER_CORE;
    }
}