package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InventoryBlackHoleStorageSchematic implements IInventoryDefaults
{
    private final NonNullList<ItemStack> stackList;
    private final Container container;

    public InventoryBlackHoleStorageSchematic(Container container)
    {
        this.stackList = NonNullList.withSize(23, ItemStack.EMPTY);
        this.container = container;
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
            this.container.onCraftMatrixChanged(this);
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
            this.container.onCraftMatrixChanged(this);
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
        this.container.onCraftMatrixChanged(this);
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
}