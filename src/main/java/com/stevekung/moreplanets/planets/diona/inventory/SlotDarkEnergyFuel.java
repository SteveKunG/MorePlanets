package com.stevekung.moreplanets.planets.diona.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.init.MPItems;

public class SlotDarkEnergyFuel extends Slot
{
    public SlotDarkEnergyFuel(IInventory inv, int slotIndex, int x, int y)
    {
        super(inv, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() == MPItems.DARK_ENERGY_PEARL;
    }
}