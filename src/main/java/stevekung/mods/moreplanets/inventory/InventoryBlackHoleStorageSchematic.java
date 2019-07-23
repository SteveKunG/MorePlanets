package stevekung.mods.moreplanets.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryBlackHoleStorageSchematic implements IInventory
{
    private NonNullList<ItemStack> stackList;
    private Container eventHandler;

    public InventoryBlackHoleStorageSchematic(Container container)
    {
        this.stackList = NonNullList.withSize(23, ItemStack.EMPTY);
        this.eventHandler = container;
    }

    @Override
    public int getSizeInventory()
    {
        return this.stackList.size();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return index >= this.getSizeInventory() ? ItemStack.EMPTY : this.stackList.get(index);
    }

    @Override
    public String getName()
    {
        return "container.crafting";
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack oldstack = ItemStackHelper.getAndRemove(this.stackList, index);

        if (!oldstack.isEmpty())
        {
            this.markDirty();
            this.eventHandler.onCraftMatrixChanged(this);
        }
        return oldstack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemStack = ItemStackHelper.getAndSplit(this.stackList, index, count);

        if (!itemStack.isEmpty())
        {
            this.markDirty();
            this.eventHandler.onCraftMatrixChanged(this);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
        if (itemStack.getCount() > this.getInventoryStackLimit())
        {
            itemStack.setCount(this.getInventoryStackLimit());
        }
        this.stackList.set(index, itemStack);
        this.markDirty();
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
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : this.stackList)
        {
            if (!itemStack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear() {}

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
    }
}