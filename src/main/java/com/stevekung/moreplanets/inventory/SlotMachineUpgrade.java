package com.stevekung.moreplanets.inventory;

import com.stevekung.moreplanets.init.MPItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMachineUpgrade extends Slot
{
    public SlotMachineUpgrade(IInventory inventory, int index, int xPosition, int yPosition)
    {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() == MPItems.MACHINE_SPEED_UPGRADE;
    }

    @Override
    public int getSlotStackLimit()
    {
        return 8;
    }
}