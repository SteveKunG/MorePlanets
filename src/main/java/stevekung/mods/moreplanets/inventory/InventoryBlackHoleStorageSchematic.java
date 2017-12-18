package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class InventoryBlackHoleStorageSchematic implements IInventoryDefaults
{
    private ItemStack[] stackList;
    private Container eventHandler;

    public InventoryBlackHoleStorageSchematic(Container container)
    {
        this.stackList = new ItemStack[23];
        this.eventHandler = container;
    }

    @Override
    public int getSizeInventory()
    {
        return this.stackList.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return index >= this.getSizeInventory() ? null : this.stackList[index];
    }

    @Override
    public String getName()
    {
        return "container.crafting";
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        if (this.stackList[index] != null)
        {
            ItemStack itemStack = this.stackList[index];
            this.stackList[index] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.stackList[index] != null)
        {
            ItemStack itemStack;

            if (this.stackList[index].stackSize <= count)
            {
                itemStack = this.stackList[index];
                this.stackList[index] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return itemStack;
            }
            else
            {
                itemStack = this.stackList[index].splitStack(count);

                if (this.stackList[index].stackSize == 0)
                {
                    this.stackList[index] = null;
                }
                this.eventHandler.onCraftMatrixChanged(this);
                return itemStack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.stackList[slot] = itemStack;
        this.eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return false;
    }
}