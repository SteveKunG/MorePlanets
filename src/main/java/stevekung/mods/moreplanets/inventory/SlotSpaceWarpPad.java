package stevekung.mods.moreplanets.inventory;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;

public class SlotSpaceWarpPad extends Slot
{
    public SlotSpaceWarpPad(IInventory inv, int slotIndex, int x, int y)
    {
        super(inv, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack itemStack)
    {
        return itemStack.getItem() == MPItems.SPACE_WARPER_CORE;
    }
}