package stevekung.mods.moreplanets.inventory;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotShieldGeneratorUpgrade extends Slot
{
    private String type;

    public SlotShieldGeneratorUpgrade(IInventory inventory, int index, int xPosition, int yPosition, String type)
    {
        super(inventory, index, xPosition, yPosition);
        this.type = type;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        if (this.type.equals("damage"))
        {
            return itemStack.getItem() == Items.REDSTONE;
        }
        else if (this.type.equals("size"))
        {
            return itemStack.getItem() == Items.ENDER_PEARL;
        }
        else
        {
            return itemStack.getItem() == Items.DIAMOND;
        }
    }

    @Override
    public int getSlotStackLimit()
    {
        if (this.type.equals("damage"))
        {
            return 8;
        }
        else if (this.type.equals("size"))
        {
            return 10;
        }
        else
        {
            return 4;
        }
    }
}