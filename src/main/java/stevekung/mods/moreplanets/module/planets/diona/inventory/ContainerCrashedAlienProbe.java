package stevekung.mods.moreplanets.module.planets.diona.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrashedAlienProbe extends Container
{
    private IInventory hopperInventory;

    public ContainerCrashedAlienProbe(InventoryPlayer playerInventory, IInventory hopperInventory, EntityPlayer player)
    {
        this.hopperInventory = hopperInventory;
        hopperInventory.openInventory(player);
        int i = 51;

        for (int j = 0; j < hopperInventory.getSizeInventory(); ++j)
        {
            this.addSlotToContainer(new Slot(hopperInventory, j, 44 + j * 18, 20));
        }
        for (int l = 0; l < 3; ++l)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + i));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 58 + i));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.hopperInventory.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.hopperInventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.hopperInventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.hopperInventory.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        this.hopperInventory.closeInventory(player);
    }
}