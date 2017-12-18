package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.inventory.PersistantInventoryCrafting;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import stevekung.mods.moreplanets.blocks.BlockRocketCrusher;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;

public class TileEntityRocketCrusher extends TileBaseElectricBlock implements IInventory, ISidedInventory
{
    public static int PROCESS_TIME_REQUIRED_BASE = 200;
    @NetworkedField(targetSide = Side.CLIENT)
    public int processTimeRequired = PROCESS_TIME_REQUIRED_BASE;
    @NetworkedField(targetSide = Side.CLIENT)
    public int processTicks = 0;
    private ItemStack producingStack = null;
    private long ticks;
    private ItemStack[] containingItems = new ItemStack[2];
    public PersistantInventoryCrafting compressingCraftMatrix = new PersistantInventoryCrafting();

    public TileEntityRocketCrusher()
    {
        this.storage.setMaxExtract(ConfigManagerCore.hardMode ? 125 : 100);
        this.setTierGC(3);
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public void update()
    {
        super.update();

        if (!this.worldObj.isRemote)
        {
            boolean updateInv = false;

            if (this.hasEnoughEnergyToRun)
            {
                if (this.canCompress())
                {
                    ++this.processTicks;

                    this.processTimeRequired = TileEntityRocketCrusher.PROCESS_TIME_REQUIRED_BASE * 2 / (1 + this.poweredByTierGC);

                    if (this.processTicks == 40)
                    {
                        this.worldObj.playSoundEffect(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), "random.anvil_land", 0.2F, 0.5F);
                    }
                    if (this.processTicks == 80)
                    {
                        this.worldObj.playSoundEffect(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), "random.anvil_land", 0.2F, 0.5F);
                    }
                    if (this.processTicks == 130)
                    {
                        this.worldObj.playSoundEffect(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), "random.anvil_land", 0.2F, 0.5F);
                    }
                    if (this.processTicks >= this.processTimeRequired)
                    {
                        this.processTicks = 0;
                        this.compressItems();
                        updateInv = true;
                    }
                }
                else
                {
                    this.processTicks = 0;
                }
            }
            else
            {
                this.processTicks = 0;
            }
            if (updateInv)
            {
                this.markDirty();
            }
        }
        if (this.ticks >= Long.MAX_VALUE)
        {
            this.ticks = 0;
        }
        this.ticks++;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    private boolean canCompress()
    {
        ItemStack itemstack = this.producingStack;

        if (itemstack == null)
        {
            return false;
        }
        if (this.containingItems[1] == null)
        {
            return true;
        }
        if (this.containingItems[1] != null && !this.containingItems[1].isItemEqual(itemstack))
        {
            return false;
        }
        int result = this.containingItems[1] == null ? 0 : this.containingItems[1].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    public void updateInput()
    {
        this.producingStack = RocketCrusherRecipes.findMatchingRecipe(this.compressingCraftMatrix);
    }

    public void compressItems()
    {
        this.compressIntoSlot(1);
    }

    private void compressIntoSlot(int slot)
    {
        if (this.canCompress())
        {
            ItemStack resultItemStack = this.producingStack.copy();

            if (this.containingItems[slot] == null)
            {
                this.containingItems[slot] = resultItemStack;
            }
            else if (this.containingItems[slot].isItemEqual(resultItemStack))
            {
                if (this.containingItems[slot].stackSize + resultItemStack.stackSize > 64)
                {
                    for (int i = 0; i < this.containingItems[slot].stackSize + resultItemStack.stackSize - 64; i++)
                    {
                        float var = 0.7F;
                        double dx = this.worldObj.rand.nextFloat() * var + (1.0F - var) * 0.5D;
                        double dy = this.worldObj.rand.nextFloat() * var + (1.0F - var) * 0.5D;
                        double dz = this.worldObj.rand.nextFloat() * var + (1.0F - var) * 0.5D;
                        EntityItem entityitem = new EntityItem(this.worldObj, this.getPos().getX() + dx, this.getPos().getY() + dy, this.getPos().getZ() + dz, new ItemStack(resultItemStack.getItem(), 1, resultItemStack.getItemDamage()));
                        entityitem.setPickupDelay(10);
                        this.worldObj.spawnEntityInWorld(entityitem);
                    }
                    this.containingItems[slot].stackSize = 64;
                }
                else
                {
                    this.containingItems[slot].stackSize += resultItemStack.stackSize;
                }
            }

            for (int i = 0; i < this.compressingCraftMatrix.getSizeInventory(); i++)
            {
                this.compressingCraftMatrix.decrStackSize(i, 1);
            }
            this.updateInput();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.processTicks = nbt.getInteger("ProcessTicks");
        NBTTagList list = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory() - this.compressingCraftMatrix.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int slot = compound.getByte("Slot") & 255;

            if (slot < this.containingItems.length)
            {
                this.containingItems[slot] = ItemStack.loadItemStackFromNBT(compound);
            }
            else if (slot < this.containingItems.length + this.compressingCraftMatrix.getSizeInventory())
            {
                this.compressingCraftMatrix.setInventorySlotContents(slot - this.containingItems.length, ItemStack.loadItemStackFromNBT(compound));
            }
        }
        this.updateInput();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("ProcessTicks", this.processTicks);
        NBTTagList list = new NBTTagList();
        int i;

        for (i = 0; i < this.containingItems.length; ++i)
        {
            if (this.containingItems[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.containingItems[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        for (i = 0; i < this.compressingCraftMatrix.getSizeInventory(); ++i)
        {
            if (this.compressingCraftMatrix.getStackInSlot(i) != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) (i + this.containingItems.length));
                this.compressingCraftMatrix.getStackInSlot(i).writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length + this.compressingCraftMatrix.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        if (slot >= this.containingItems.length)
        {
            return this.compressingCraftMatrix.getStackInSlot(slot - this.containingItems.length);
        }
        return this.containingItems[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (slot >= this.containingItems.length)
        {
            ItemStack result = this.compressingCraftMatrix.decrStackSize(slot - this.containingItems.length, size);

            if (result != null)
            {
                this.updateInput();
            }
            return result;
        }
        if (this.containingItems[slot] != null)
        {
            ItemStack itemStack;

            if (this.containingItems[slot].stackSize <= size)
            {
                itemStack = this.containingItems[slot];
                this.containingItems[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.containingItems[slot].splitStack(size);

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
        if (slot >= this.containingItems.length)
        {
            return this.compressingCraftMatrix.removeStackFromSlot(slot - this.containingItems.length);
        }
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
        if (slot >= this.containingItems.length)
        {
            this.compressingCraftMatrix.setInventorySlotContents(slot - this.containingItems.length, itemStack);
            this.updateInput();
        }
        else
        {
            this.containingItems[slot] = itemStack;

            if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
            {
                itemStack.stackSize = this.getInventoryStackLimit();
            }
        }
    }

    @Override
    public String getName()
    {
        return StatCollector.translateToLocal("tile.rocket_crusher.name");
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
    public boolean hasCustomName()
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot == 0)
        {
            return itemStack != null && ItemElectricBase.isElectricItem(itemStack.getItem());
        }
        else if (slot >= 3)
        {
            if (this.producingStack != null)
            {
                ItemStack stackInSlot = this.getStackInSlot(slot);
                return stackInSlot != null && stackInSlot.isItemEqual(itemStack);
            }
            return this.isItemCompressorInput(itemStack, slot - 3);
        }
        return false;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return new int[] { 1, 2 };
        }

        int[] slots = new int[] { 0, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        ArrayList<Integer> removeSlots = Lists.newArrayList();

        for (int i = 3; i < 12; i++)
        {
            if (removeSlots.contains(i))
            {
                continue;
            }

            ItemStack stack1 = this.getStackInSlot(i);

            if (stack1 == null || stack1.stackSize <= 0)
            {
                continue;
            }
            for (int j = i + 1; j < 12; j++)
            {
                if (removeSlots.contains(j))
                {
                    continue;
                }

                ItemStack stack2 = this.getStackInSlot(j);

                if (stack2 == null)
                {
                    continue;
                }
                if (stack1.isItemEqual(stack2))
                {
                    if (stack2.stackSize >= stack1.stackSize)
                    {
                        removeSlots.add(j);
                    }
                    else
                    {
                        removeSlots.add(i);
                    }
                    break;
                }
            }
        }

        if (removeSlots.size() > 0)
        {
            int[] returnSlots = new int[slots.length - removeSlots.size()];
            int j = 0;

            for (int i = 0; i < slots.length; i++)
            {
                if (i > 0 && removeSlots.contains(slots[i]))
                {
                    continue;
                }
                returnSlots[j] = slots[i];
                j++;
            }
            return returnSlots;
        }
        return slots;
    }

    @Override
    public void slowDischarge() {}

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        return this.isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        return slot == 1 || slot == 2;
    }

    @Override
    public boolean shouldUseEnergy()
    {
        return this.processTicks > 0;
    }

    @Override
    public EnumFacing getFront()
    {
        return this.worldObj.getBlockState(this.getPos()).getValue(BlockRocketCrusher.FACING);
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        return this.getFront().rotateY();
    }

    @Override
    public ItemStack getBatteryInSlot()
    {
        return this.getStackInSlot(0);
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

    private boolean isItemCompressorInput(ItemStack itemStack, int id)
    {
        for (IRecipe recipe : RocketCrusherRecipes.getRecipeList())
        {
            if (recipe instanceof ShapedRecipes)
            {
                if (id >= ((ShapedRecipes) recipe).recipeItems.length)
                {
                    continue;
                }

                ItemStack itemstack1 = ((ShapedRecipes) recipe).recipeItems[id];

                if (itemStack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767 || itemStack.getItemDamage() == itemstack1.getItemDamage()))
                {
                    for (int i = 0; i < ((ShapedRecipes) recipe).recipeItems.length; i++)
                    {
                        if (i == id)
                        {
                            continue;
                        }

                        ItemStack itemstack2 = ((ShapedRecipes) recipe).recipeItems[i];

                        if (itemStack.getItem() == itemstack2.getItem() && (itemstack2.getItemDamage() == 32767 || itemStack.getItemDamage() == itemstack2.getItemDamage()))
                        {
                            ItemStack is3 = this.getStackInSlot(id + 3);
                            ItemStack is4 = this.getStackInSlot(i + 3);
                            return is3 == null || is4 != null && is3.stackSize < is4.stackSize;
                        }
                    }
                    return true;
                }
            }
            else if (recipe instanceof ShapelessOreRecipe)
            {
                @SuppressWarnings("unchecked")
                ArrayList<Object> required = Lists.newArrayList(((ShapelessOreRecipe) recipe).getInput());
                Iterator<Object> req = required.iterator();
                int match = 0;

                while (req.hasNext())
                {
                    Object next = req.next();

                    if (next instanceof ItemStack)
                    {
                        if (OreDictionary.itemMatches((ItemStack)next, itemStack, false))
                        {
                            match++;
                        }
                    }
                    else if (next instanceof List)
                    {
                        Iterator<ItemStack> itr = ((List<ItemStack>)next).iterator();

                        while (itr.hasNext())
                        {
                            if (OreDictionary.itemMatches(itr.next(), itemStack, false))
                            {
                                match++;
                                break;
                            }
                        }
                    }
                }

                if (match == 0)
                {
                    continue;
                }
                if (match == 1)
                {
                    return true;
                }

                int slotsFilled = 0;

                for (int i = 3; i < 12; i++)
                {
                    ItemStack inMatrix = this.getStackInSlot(i);

                    if (inMatrix != null && inMatrix.isItemEqual(itemStack))
                    {
                        slotsFilled++;
                    }
                }
                if (slotsFilled < match)
                {
                    return this.getStackInSlot(id + 3) == null;
                }
                Random rand = new Random();
                return rand.nextInt(match) == 0;
            }
        }
        return false;
    }
}