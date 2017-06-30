package stevekung.mods.moreplanets.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;

public class SlotBlackHoleStorage extends Slot
{
    public SlotBlackHoleStorage(IInventory inventory, int index, int xPosition, int yPosition)
    {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() != Item.getItemFromBlock(MPBlocks.BLACK_HOLE_STORAGE);
    }
}