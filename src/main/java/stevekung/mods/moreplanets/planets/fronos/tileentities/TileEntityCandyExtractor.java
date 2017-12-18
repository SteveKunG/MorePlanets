/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.recipe.CandyExtractorRecipes;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class TileEntityCandyExtractor extends TileEntity implements ISidedInventory
{
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};

    private ItemStack[] extractorItemStacks = new ItemStack[3];

    public int extractorTime;
    public int currentItemExtractTime;
    public int candyCookTime;

    @Override
    public int getSizeInventory()
    {
        return this.extractorItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.extractorItemStacks[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.extractorItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.extractorItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.extractorItemStacks[par1];
                this.extractorItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.extractorItemStacks[par1].splitStack(par2);

                if (this.extractorItemStacks[par1].stackSize == 0)
                {
                    this.extractorItemStacks[par1] = null;
                }
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.extractorItemStacks[par1] != null)
        {
            final ItemStack itemstack = this.extractorItemStacks[par1];
            this.extractorItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.extractorItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("container.candy.extractor.name");
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    public void setGuiDisplayName(String par1Str)
    {
        return;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        final NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.extractorItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            final NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            final byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.extractorItemStacks.length)
            {
                this.extractorItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.extractorTime = par1NBTTagCompound.getShort("ExtractTime");
        this.candyCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemExtractTime = TileEntityCandyExtractor.getItemExtractTime(this.extractorItemStacks[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("ExtractTime", (short)this.extractorTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.candyCookTime);
        final NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.extractorItemStacks.length; ++i)
        {
            if (this.extractorItemStacks[i] != null)
            {
                final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.extractorItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int par1)
    {
        return this.candyCookTime * par1 / 200;
    }

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemExtractTime == 0)
        {
            this.currentItemExtractTime = 200;
        }
        return this.extractorTime * par1 / this.currentItemExtractTime;
    }

    public boolean isBurning()
    {
        return this.extractorTime > 0;
    }

    @Override
    public void updateEntity()
    {
        final boolean flag = this.extractorTime > 0;
        boolean flag1 = false;

        if (this.extractorTime > 0)
        {
            --this.extractorTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.extractorTime == 0 && this.canExtract())
            {
                this.currentItemExtractTime = this.extractorTime = TileEntityCandyExtractor.getItemExtractTime(this.extractorItemStacks[1]);

                if (this.extractorTime > 0)
                {
                    flag1 = true;

                    if (this.extractorItemStacks[1] != null)
                    {
                        --this.extractorItemStacks[1].stackSize;

                        if (this.extractorItemStacks[1].stackSize == 0)
                        {
                            this.extractorItemStacks[1] = this.extractorItemStacks[1].getItem().getContainerItem(this.extractorItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canExtract())
            {
                ++this.candyCookTime;

                if (this.candyCookTime == 200)
                {
                    this.candyCookTime = 0;
                    this.extractItem();
                    flag1 = true;
                }
            }
            else
            {
                this.candyCookTime = 0;
            }

            if (flag != this.extractorTime > 0)
            {
                flag1 = true;
                BlockCandyExtractor.updateExtractorBlockState(this.extractorTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    private boolean canExtract()
    {
        if (this.extractorItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            final ItemStack itemstack = CandyExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0]);
            if (itemstack == null) {
                return false;
            }
            if (this.extractorItemStacks[2] == null) {
                return true;
            }
            if (!this.extractorItemStacks[2].isItemEqual(itemstack)) {
                return false;
            }
            final int result = this.extractorItemStacks[2].stackSize + itemstack.stackSize;
            return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
        }
    }

    public void extractItem()
    {
        if (this.canExtract())
        {
            final ItemStack itemstack = CandyExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0]);

            if (this.extractorItemStacks[2] == null)
            {
                this.extractorItemStacks[2] = itemstack.copy();
            }
            else if (this.extractorItemStacks[2].isItemEqual(itemstack))
            {
                this.extractorItemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.extractorItemStacks[0].stackSize;

            if (this.extractorItemStacks[0].stackSize <= 0)
            {
                this.extractorItemStacks[0] = null;
            }
        }
    }

    public static int getItemExtractTime(ItemStack itemStack)
    {
        if (itemStack == null)
        {
            return 0;
        }
        else
        {
            if (itemStack.getItem() == FronosItems.fronos_item && itemStack.getItemDamage() == 1)
            {
                return 500;
            }
        }
        return 0;
    }

    public static boolean isItemFuel(ItemStack itemStack)
    {
        return TileEntityCandyExtractor.getItemExtractTime(itemStack) > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return slot == 2 ? false : slot == 1 ? TileEntityCandyExtractor.isItemFuel(itemStack) : true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? TileEntityCandyExtractor.slots_bottom : par1 == 1 ? TileEntityCandyExtractor.slots_top : TileEntityCandyExtractor.slots_sides;
    }

    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return false;
    }
}