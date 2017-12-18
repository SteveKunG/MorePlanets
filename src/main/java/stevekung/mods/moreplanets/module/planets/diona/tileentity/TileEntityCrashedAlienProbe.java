package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityRenderTickable;

public class TileEntityCrashedAlienProbe extends TileEntityRenderTickable implements IInventory
{
    private ItemStack[] containingItems = new ItemStack[5];

    @Override
    public void update()
    {
        if (this.worldObj.getBlockState(this.getPos()) == DionaBlocks.CRASHED_ALIEN_PROBE.getDefaultState().withProperty(BlockCrashedAlienProbe.HAS_ALIEN, true))
        {
            super.update();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList items = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < items.tagCount(); ++i)
        {
            NBTTagCompound tagAt = items.getCompoundTagAt(i);
            int slot = tagAt.getByte("Slot") & 255;

            if (slot < this.containingItems.length)
            {
                this.containingItems[slot] = ItemStack.loadItemStackFromNBT(tagAt);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.containingItems.length; ++i)
        {
            if (this.containingItems[i] != null)
            {
                NBTTagCompound tagAt = new NBTTagCompound();
                tagAt.setByte("Slot", (byte) i);
                this.containingItems[i].writeToNBT(tagAt);
                list.appendTag(tagAt);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return GCCoreUtil.translate("container.crashed_probe.name");
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.containingItems[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.containingItems[slot] != null)
        {
            ItemStack itemStack;

            if (this.containingItems[slot].stackSize <= amount)
            {
                itemStack = this.containingItems[slot];
                this.containingItems[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.containingItems[slot].splitStack(amount);

                if (this.containingItems[slot].stackSize == 0)
                {
                    this.containingItems[slot] = null;
                }
                return itemStack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int slot)
    {
        if (this.containingItems[slot] != null)
        {
            ItemStack itemStack = this.containingItems[slot];
            this.containingItems[slot] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.containingItems[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return true;
    }

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
    public IChatComponent getDisplayName()
    {
        return new ChatComponentTranslation(this.getName());
    }
}