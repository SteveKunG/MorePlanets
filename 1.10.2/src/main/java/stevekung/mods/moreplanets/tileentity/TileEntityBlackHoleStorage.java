package stevekung.mods.moreplanets.tileentity;

import java.util.List;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.entity.EntityBlackHoleStorage;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPSounds;

public class TileEntityBlackHoleStorage extends TileEntityAdvanced implements IInventoryDefaults, ISidedInventory
{
    public ItemStack[] inventory = new ItemStack[108];
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean disableBlackHole = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean useHopper = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public String ownerUUID = "";
    @NetworkedField(targetSide = Side.CLIENT)
    public int xp = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public String collectMode = "item";
    public int age = 0;

    @Override
    public void update()
    {
        super.update();

        ++this.age;
        this.age = this.age + this.worldObj.rand.nextInt(100);

        if (this.ticks % 20 == 0)
        {
            this.worldObj.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.BLACK_HOLE_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        if (this.worldObj != null && !this.worldObj.isRemote)
        {
            this.updateStorage();
            List<EntityBlackHoleStorage> blackHoleList = this.worldObj.getEntitiesWithinAABB(EntityBlackHoleStorage.class, new AxisAlignedBB(this.pos.getX(), this.pos.getY() + 2, this.pos.getZ(), this.pos.getX() + 1.0D, this.pos.getY() + 3, this.pos.getZ() + 1.0D));

            for (EntityBlackHoleStorage bh : blackHoleList)
            {
                bh.setDisable(this.disableBlackHole);
                bh.setCollectMode(this.collectMode);
            }
            if (this.xp >= this.getMaxXP())
            {
                this.xp = this.getMaxXP();
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int slot = compound.getByte("Slot");

            if (slot >= 0 && slot < this.inventory.length)
            {
                this.inventory[slot] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
        this.disableBlackHole = nbt.getBoolean("DisableBlackHole");
        this.useHopper = nbt.getBoolean("UseHopper");
        this.collectMode = nbt.getString("CollectMode");
        this.ownerUUID = nbt.getString("OwnerUUID");
        this.xp = nbt.getInteger("XP");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        if (this.ownerUUID != null)
        {
            nbt.setString("OwnerUUID", this.ownerUUID);
        }
        if (this.collectMode != null)
        {
            nbt.setString("CollectMode", this.collectMode);
        }
        nbt.setBoolean("DisableBlackHole", this.disableBlackHole);
        nbt.setBoolean("UseHopper", this.useHopper);
        nbt.setInteger("XP", this.xp);
        nbt.setTag("Items", list);
        return nbt;
    }

    @Override
    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.inventory[index] != null)
        {
            if (this.inventory[index].stackSize <= count)
            {
                ItemStack itemstack1 = this.inventory[index];
                this.inventory[index] = null;
                return itemstack1;
            }
            else
            {
                ItemStack itemstack = this.inventory[index].splitStack(count);

                if (this.inventory[index].stackSize == 0)
                {
                    this.inventory[index] = null;
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
    public ItemStack removeStackFromSlot(int index)
    {
        if (this.inventory[index] != null)
        {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getName()
    {
        return "container.black_hole_storage";
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemStack)
    {
        return itemStack.getItem() != Item.getItemFromBlock(MPBlocks.BLACK_HOLE_STORAGE);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
    }

    @Override
    public double getPacketRange()
    {
        return 32;
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing facing)
    {
        return new int[] { 0, 1 };
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing facing)
    {
        return this.useHopper;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack itemStack, EnumFacing facing)
    {
        return this.useHopper;
    }

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    public int getMaxXP()
    {
        return 1000000;
    }

    private boolean updateStorage()
    {
        if (!this.isFull())
        {
            double range = 2.5D;

            if (!this.disableBlackHole)
            {
                boolean collectAll = this.collectMode.equals("item_and_xp");

                if (this.collectMode.equals("item") || collectAll)
                {
                    for (EntityItem item : this.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.getPos().getX() - range, this.getPos().getY() + 2, this.getPos().getZ() - range, this.getPos().getX() + range, this.getPos().getY() + 4, this.getPos().getZ() + range)))
                    {
                        if (this.putDropInInventoryAllSlots(this, item))
                        {
                            return true;
                        }
                    }
                }
                if (this.collectMode.equals("xp") || collectAll)
                {
                    for (EntityXPOrb xp : this.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(this.getPos().getX() - range, this.getPos().getY() + 2, this.getPos().getZ() - range, this.getPos().getX() + range, this.getPos().getY() + 4, this.getPos().getZ() + range)))
                    {
                        if (this.putXPValue(xp))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isFull()
    {
        for (ItemStack itemStack : this.inventory)
        {
            if (itemStack == null || itemStack.stackSize != itemStack.getMaxStackSize())
            {
                return false;
            }
        }
        if (this.xp >= this.getMaxXP())
        {
            return false;
        }
        return true;
    }

    private boolean putXPValue(EntityXPOrb xpOrb)
    {
        if (xpOrb == null || this.xp >= this.getMaxXP())
        {
            return false;
        }
        else
        {
            this.xp += xpOrb.xpValue;
            xpOrb.setDead();
            return true;
        }
    }

    private boolean putDropInInventoryAllSlots(IInventory inventory, EntityItem entityItem)
    {
        boolean flag = false;

        if (entityItem == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = entityItem.getEntityItem().copy();
            ItemStack itemstack1 = this.putStackInInventoryAllSlots(inventory, itemstack);

            if (itemstack1 != null && itemstack1.stackSize != 0)
            {
                entityItem.setEntityItemStack(itemstack1);
            }
            else
            {
                flag = true;
                entityItem.setDead();
            }
            return flag;
        }
    }

    private ItemStack putStackInInventoryAllSlots(IInventory inventory, ItemStack itemStack)
    {
        int i = inventory.getSizeInventory();

        for (int index = 0; index < i && itemStack != null && itemStack.stackSize > 0; ++index)
        {
            itemStack = this.insertStack(inventory, itemStack, index);
        }
        if (itemStack != null && itemStack.stackSize == 0)
        {
            itemStack = null;
        }
        return itemStack;
    }

    private ItemStack insertStack(IInventory inventory, ItemStack itemStack, int index)
    {
        ItemStack itemstack = inventory.getStackInSlot(index);

        if (this.canInsertItemInSlot(inventory, itemStack, index))
        {
            boolean flag = false;

            if (itemstack == null)
            {
                int max = Math.min(itemStack.getMaxStackSize(), inventory.getInventoryStackLimit());

                if (max >= itemStack.stackSize)
                {
                    inventory.setInventorySlotContents(index, itemStack);
                    itemStack = null;
                }
                else
                {
                    inventory.setInventorySlotContents(index, itemStack.splitStack(max));
                }
                flag = true;
            }
            else if (this.canCombine(itemstack, itemStack))
            {
                int max = Math.min(itemStack.getMaxStackSize(), inventory.getInventoryStackLimit());

                if (max > itemstack.stackSize)
                {
                    int i = max - itemstack.stackSize;
                    int j = Math.min(itemStack.stackSize, i);
                    itemStack.stackSize -= j;
                    itemstack.stackSize += j;
                    flag = j > 0;
                }
            }
            if (flag)
            {
                inventory.markDirty();
            }
        }
        return itemStack;
    }

    private boolean canInsertItemInSlot(IInventory inventory, ItemStack itemStack, int index)
    {
        return !inventory.isItemValidForSlot(index, itemStack) ? false : true;
    }

    private boolean canCombine(ItemStack itemStack1, ItemStack itemStack2)
    {
        return itemStack1.getItem() != itemStack2.getItem() ? false : itemStack1.getMetadata() != itemStack2.getMetadata() ? false : itemStack1.stackSize > itemStack1.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(itemStack1, itemStack2);
    }
}