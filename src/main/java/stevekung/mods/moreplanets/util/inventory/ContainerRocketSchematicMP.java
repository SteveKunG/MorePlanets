package stevekung.mods.moreplanets.util.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.inventory.InventorySchematicRocket;

public class ContainerRocketSchematicMP extends Container
{
    protected InventorySchematicRocket craftMatrix = new InventorySchematicRocket(this);
    protected IInventory craftResult = new InventoryCraftResult();
    private World worldObj;

    public ContainerRocketSchematicMP(InventoryPlayer inv)
    {
        this.worldObj = inv.player.worldObj;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            for (int i = 1; i < this.craftMatrix.getSizeInventory(); ++i)
            {
                ItemStack itemStack = this.craftMatrix.removeStackFromSlot(i);

                if (itemStack != null)
                {
                    player.entityDropItem(itemStack, 0.0F);
                }
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemStack = null;
        Slot invSlot = this.inventorySlots.get(slot);

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack slotStack = invSlot.getStack();
            itemStack = slotStack.copy();
            boolean done = false;

            if (slot <= 21)
            {
                if (!this.mergeItemStack(slotStack, 22, 58, false))
                {
                    return null;
                }
                if (slot == 0)
                {
                    invSlot.onSlotChange(slotStack, itemStack);
                }
            }
            else
            {
                for (int i = 1; i < 19; i++)
                {
                    Slot testSlot = this.inventorySlots.get(i);

                    if (!testSlot.getHasStack() && testSlot.isItemValid(itemStack))
                    {
                        if (!this.mergeOneItem(slotStack, i, i + 1))
                        {
                            return null;
                        }
                        done = true;
                        break;
                    }
                }

                if (!done)
                {
                    if (itemStack.getItem() == Item.getItemFromBlock(Blocks.chest) && !this.inventorySlots.get(19).getHasStack())
                    {
                        if (!this.mergeOneItem(slotStack, 19, 20))
                        {
                            return null;
                        }
                    }
                    else if (itemStack.getItem() == Item.getItemFromBlock(Blocks.chest) && !this.inventorySlots.get(20).getHasStack())
                    {
                        if (!this.mergeOneItem(slotStack, 20, 21))
                        {
                            return null;
                        }
                    }
                    else if (itemStack.getItem() == Item.getItemFromBlock(Blocks.chest) && !this.inventorySlots.get(21).getHasStack())
                    {
                        if (!this.mergeOneItem(slotStack, 21, 22))
                        {
                            return null;
                        }
                    }
                    else if (slot >= 22 && slot < 49)
                    {
                        if (!this.mergeItemStack(slotStack, 49, 58, false))
                        {
                            return null;
                        }
                    }
                    else if (slot >= 49 && slot < 58)
                    {
                        if (!this.mergeItemStack(slotStack, 22, 49, false))
                        {
                            return null;
                        }
                    }
                    else if (!this.mergeItemStack(slotStack, 22, 58, false))
                    {
                        return null;
                    }
                }
            }
            if (slotStack.stackSize == 0)
            {
                invSlot.putStack((ItemStack) null);
            }
            else
            {
                invSlot.onSlotChanged();
            }

            if (slotStack.stackSize == itemStack.stackSize)
            {
                return null;
            }
            invSlot.onPickupFromSlot(player, slotStack);
        }
        return itemStack;
    }

    private boolean mergeOneItem(ItemStack itemStack, int slot1, int slot2)
    {
        boolean flag1 = false;

        if (itemStack.stackSize > 0)
        {
            Slot slot;
            ItemStack slotStack;

            for (int k = slot1; k < slot2; k++)
            {
                slot = this.inventorySlots.get(k);
                slotStack = slot.getStack();

                if (slotStack == null)
                {
                    ItemStack stackOneItem = itemStack.copy();
                    stackOneItem.stackSize = 1;
                    itemStack.stackSize--;
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag1 = true;
                    break;
                }
            }
        }
        return flag1;
    }
}