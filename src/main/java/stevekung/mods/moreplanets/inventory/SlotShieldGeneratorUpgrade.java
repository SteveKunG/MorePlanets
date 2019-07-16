package stevekung.mods.moreplanets.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;

public class SlotShieldGeneratorUpgrade extends Slot
{
    private final String type;

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
            return itemStack.getItem() == MPItems.SHIELD_DAMAGE_UPGRADE;
        }
        else if (this.type.equals("size"))
        {
            return itemStack.getItem() == MPItems.SHIELD_SIZE_UPGRADE;
        }
        else
        {
            return itemStack.getItem() == MPItems.SHIELD_CAPACITY_UPGRADE;
        }
    }

    @Override
    public int getSlotStackLimit()
    {
        if (this.type.equals("damage"))
        {
            return 4;
        }
        else if (this.type.equals("size"))
        {
            return 16;
        }
        else
        {
            return 4;
        }
    }
}