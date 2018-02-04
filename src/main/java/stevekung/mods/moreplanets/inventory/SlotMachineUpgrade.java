package stevekung.mods.moreplanets.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;

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