package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class InventorySchematicRocket implements IInventoryDefaults
{
    private ItemStack[] stackList;
    private Container eventHandler;

    public InventorySchematicRocket(Container container)
    {
        this.stackList = new ItemStack[22];
        this.eventHandler = container;
    }

    @Override
    public int getSizeInventory()
    {
        return this.stackList.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return slot >= this.getSizeInventory() ? null : this.stackList[slot];
    }

    @Override
    public String getName()
    {
        return "container.crafting";
    }

    @Override
    public ItemStack removeStackFromSlot(int slot)
    {
        if (this.stackList[slot] != null)
        {
            ItemStack itemStack = this.stackList[slot];
            this.stackList[slot] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (this.stackList[slot] != null)
        {
            ItemStack itemStack;

            if (this.stackList[slot].stackSize <= size)
            {
                itemStack = this.stackList[slot];
                this.stackList[slot] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return itemStack;
            }
            else
            {
                itemStack = this.stackList[slot].splitStack(size);

                if (this.stackList[slot].stackSize == 0)
                {
                    this.stackList[slot] = null;
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
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString(this.getName());
    }
}