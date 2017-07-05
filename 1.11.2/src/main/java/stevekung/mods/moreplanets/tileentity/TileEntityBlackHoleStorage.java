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
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
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
    private static final int[] SLOTS = new int[108];
    public NonNullList<ItemStack> inventory = NonNullList.withSize(108, ItemStack.EMPTY);
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
    public int modeInt;

    static
    {
        for (int i = 0; i < TileEntityBlackHoleStorage.SLOTS.length; TileEntityBlackHoleStorage.SLOTS[i] = i++) {}
    }

    @Override
    public void update()
    {
        super.update();

        ++this.age;
        this.age = this.age + this.world.rand.nextInt(100);

        if (this.ticks % 20 == 0)
        {
            this.world.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.BLACK_HOLE_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        if (this.world != null && !this.world.isRemote)
        {
            this.updateStorage();
            List<EntityBlackHoleStorage> blackHoleList = this.world.getEntitiesWithinAABB(EntityBlackHoleStorage.class, new AxisAlignedBB(this.pos.getX(), this.pos.getY() + 2, this.pos.getZ(), this.pos.getX() + 1.0D, this.pos.getY() + 3, this.pos.getZ() + 1.0D));

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
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.inventory);
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
        ItemStackHelper.saveAllItems(nbt, this.inventory);

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
        return nbt;
    }

    @Override
    public int getSizeInventory()
    {
        return this.inventory.size();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.getItems().get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemStack = ItemStackHelper.getAndSplit(this.getItems(), index, count);

        if (!itemStack.isEmpty())
        {
            this.markDirty();
        }
        return itemStack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.getItems(), index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack)
    {
        this.getItems().set(index, itemStack);

        if (itemStack.getCount() > this.getInventoryStackLimit())
        {
            itemStack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    @Override
    public String getName()
    {
        return "container.black_hole_storage.name";
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
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
        return TileEntityBlackHoleStorage.SLOTS;
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

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : this.inventory)
        {
            if (!itemStack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    protected NonNullList<ItemStack> getItems()
    {
        return this.inventory;
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
                    for (EntityItem item : this.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.getPos().getX() - range, this.getPos().getY() + 2, this.getPos().getZ() - range, this.getPos().getX() + range, this.getPos().getY() + 4, this.getPos().getZ() + range)))
                    {
                        if (this.putDropInInventoryAllSlots(this, item))
                        {
                            return true;
                        }
                    }
                }
                if (this.collectMode.equals("xp") || collectAll)
                {
                    for (EntityXPOrb xp : this.world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(this.getPos().getX() - range, this.getPos().getY() + 2, this.getPos().getZ() - range, this.getPos().getX() + range, this.getPos().getY() + 4, this.getPos().getZ() + range)))
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
            if (itemStack.isEmpty() || itemStack.getCount() != itemStack.getMaxStackSize())
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

            if (!itemstack1.isEmpty() && itemstack1.getCount() != 0)
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

        for (int index = 0; index < i && !itemStack.isEmpty() && itemStack.getCount() > 0; ++index)
        {
            itemStack = this.insertStack(inventory, itemStack, index);
        }
        if (!itemStack.isEmpty() && itemStack.getCount() == 0)
        {
            itemStack = ItemStack.EMPTY;
        }
        return itemStack;
    }

    private ItemStack insertStack(IInventory inventory, ItemStack itemStack, int index)
    {
        ItemStack itemstack = inventory.getStackInSlot(index);

        if (this.canInsertItemInSlot(inventory, itemStack, index))
        {
            boolean flag = false;

            if (itemstack.isEmpty())
            {
                int max = Math.min(itemStack.getMaxStackSize(), inventory.getInventoryStackLimit());

                if (max >= itemStack.getCount())
                {
                    inventory.setInventorySlotContents(index, itemStack);
                    itemStack = ItemStack.EMPTY;
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

                if (max > itemstack.getCount())
                {
                    int i = max - itemstack.getCount();
                    int j = Math.min(itemStack.getCount(), i);
                    itemStack.shrink(j);
                    itemstack.grow(j);
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
        return itemStack1.getItem() != itemStack2.getItem() ? false : itemStack1.getMetadata() != itemStack2.getMetadata() ? false : itemStack1.getCount() > itemStack1.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(itemStack1, itemStack2);
    }
}