package stevekung.mods.moreplanets.util.tileentity;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class TileEntityFurnaceMP extends TileEntityLockable implements ITickable, ISidedInventory
{
    private int[] slotsTop = new int[] {0};
    private int[] slotsBottom = new int[] {2, 1};
    private int[] slotsSides = new int[] {1};
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;
    private IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    private IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    private IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

    @Override
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.size();
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : this.furnaceItemStacks)
        {
            if (!itemStack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.furnaceItemStacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.furnaceItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.furnaceItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.furnaceCustomName : "container.furnace";
    }

    @Override
    public boolean hasCustomName()
    {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
    }

    public void setCustomInventoryName(String name)
    {
        this.furnaceCustomName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }
        return compound;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    @Override
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.furnaceItemStacks.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !this.furnaceItemStacks.get(0).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.furnaceItemStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    private int getCookTime(ItemStack itemStack)
    {
        return 200;
    }

    private boolean canSmelt()
    {
        if (this.furnaceItemStacks.get(0).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(0));

            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.furnaceItemStacks.get(2);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                int result = itemstack1.getCount() + itemstack.getCount();
                return result <= this.getInventoryStackLimit() && result <= itemstack1.getMaxStackSize();
            }
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.furnaceItemStacks.get(0);
            ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstack2 = this.furnaceItemStacks.get(2);

            if (itemstack2.isEmpty())
            {
                this.furnaceItemStacks.set(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !this.furnaceItemStacks.get(1).isEmpty() && this.furnaceItemStacks.get(1).getItem() == Items.BUCKET)
            {
                this.furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }
            itemstack.shrink(1);
        }
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemStack)
    {
        return index == 2 ? false : index != 1 ? true : TileEntityFurnace.isItemFuel(itemStack) || SlotFurnaceFuel.isBucket(itemStack);
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? this.slotsBottom : side == EnumFacing.UP ? this.slotsTop : this.slotsSides;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack itemStack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = itemStack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getGuiID()
    {
        return "minecraft:furnace";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
    {
        return new ContainerFurnace(playerInventory, this);
    }

    @Override
    public int getField(int id)
    {
        switch (id)
        {
        case 0:
            return this.furnaceBurnTime;
        case 1:
            return this.currentItemBurnTime;
        case 2:
            return this.cookTime;
        case 3:
            return this.totalCookTime;
        default:
            return 0;
        }
    }

    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
        case 0:
            this.furnaceBurnTime = value;
            break;
        case 1:
            this.currentItemBurnTime = value;
            break;
        case 2:
            this.cookTime = value;
            break;
        case 3:
            this.totalCookTime = value;
        }
    }

    @Override
    public int getFieldCount()
    {
        return 4;
    }

    @Override
    public void clear()
    {
        this.furnaceItemStacks.clear();
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if (facing == EnumFacing.DOWN)
            {
                return (T) this.handlerBottom;
            }
            else if (facing == EnumFacing.UP)
            {
                return (T) this.handlerTop;
            }
            else
            {
                return (T) this.handlerSide;
            }
        }
        return super.getCapability(capability, facing);
    }

    protected abstract void setState();
}