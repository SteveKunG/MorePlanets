package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.inventory.PersistantInventoryCrafting;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.blocks.BlockRocketCrusher;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;
import stevekung.mods.moreplanets.util.recipes.ShapedRecipesMP;

public class TileEntityRocketCrusher extends TileBaseElectricBlock
{
    public static int PROCESS_TIME_REQUIRED_BASE = 200;
    @NetworkedField(targetSide = Side.CLIENT)
    public int processTimeRequired = PROCESS_TIME_REQUIRED_BASE;
    @NetworkedField(targetSide = Side.CLIENT)
    public int processTicks = 0;
    private ItemStack producingStack = ItemStack.EMPTY;
    private long ticks;
    public PersistantInventoryCrafting compressingCraftMatrix = new PersistantInventoryCrafting();

    public TileEntityRocketCrusher()
    {
        super("tile.rocket_crusher.name");
        this.inventory = NonNullList.withSize(3, ItemStack.EMPTY);
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

        if (!this.world.isRemote)
        {
            boolean updateInv = false;
            int speed = !this.getInventory().get(2).isEmpty() ? 1 + this.getInventory().get(2).getCount() : 1;

            if (this.hasEnoughEnergyToRun)
            {
                if (this.canCompress())
                {
                    ++this.processTicks;
                    this.processTimeRequired = TileEntityRocketCrusher.PROCESS_TIME_REQUIRED_BASE * 2 / (1 + this.poweredByTierGC) / speed;

                    if (this.processTicks % 45 - speed == 0)
                    {
                        this.world.playSound(null, this.getPos(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, 0.5F);
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

    private boolean canCompress()
    {
        ItemStack itemstack = this.producingStack;

        if (itemstack.isEmpty())
        {
            return false;
        }
        if (this.getInventory().get(1).isEmpty())
        {
            return true;
        }
        if (!this.getInventory().get(1).isEmpty() && !this.getInventory().get(1).isItemEqual(itemstack))
        {
            return false;
        }
        int result = this.getInventory().get(1).isEmpty() ? 0 : this.getInventory().get(1).getCount() + itemstack.getCount();
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

            if (this.getInventory().get(slot).isEmpty())
            {
                this.getInventory().set(slot, resultItemStack);
            }
            else if (this.getInventory().get(slot).isItemEqual(resultItemStack))
            {
                if (this.getInventory().get(slot).getCount() + resultItemStack.getCount() > 64)
                {
                    for (int i = 0; i < this.getInventory().get(slot).getCount() + resultItemStack.getCount() - 64; i++)
                    {
                        float var = 0.7F;
                        double dx = this.world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
                        double dy = this.world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
                        double dz = this.world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
                        EntityItem entityitem = new EntityItem(this.world, this.getPos().getX() + dx, this.getPos().getY() + dy, this.getPos().getZ() + dz, new ItemStack(resultItemStack.getItem(), 1, resultItemStack.getItemDamage()));
                        entityitem.setPickupDelay(10);
                        this.world.spawnEntity(entityitem);
                    }
                    this.getInventory().get(slot).setCount(64);
                }
                else
                {
                    this.getInventory().get(slot).grow(resultItemStack.getCount());
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
        this.inventory = NonNullList.withSize(this.getSizeInventory() - this.compressingCraftMatrix.getSizeInventory(), ItemStack.EMPTY);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventory.size())
            {
                this.inventory.set(j, new ItemStack(nbttagcompound));
            }
            else if (j < this.inventory.size() + this.compressingCraftMatrix.getSizeInventory())
            {
                this.compressingCraftMatrix.setInventorySlotContents(j - this.inventory.size(), new ItemStack(nbttagcompound));
            }
        }
        this.updateInput();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("ProcessTicks", this.processTicks);
        NBTTagList list = new NBTTagList();
        int i;

        for (i = 0; i < this.inventory.size(); ++i)
        {
            if (!this.inventory.get(i).isEmpty())
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.inventory.get(i).writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        for (i = 0; i < this.compressingCraftMatrix.getSizeInventory(); ++i)
        {
            if (!this.compressingCraftMatrix.getStackInSlot(i).isEmpty())
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) (i + this.inventory.size()));
                this.compressingCraftMatrix.getStackInSlot(i).writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        nbt.setTag("Items", list);
        return nbt;
    }

    @Override
    public int getSizeInventory()
    {
        return this.inventory.size() + this.compressingCraftMatrix.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        if (slot >= this.inventory.size())
        {
            return this.compressingCraftMatrix.getStackInSlot(slot - this.inventory.size());
        }
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (slot >= this.inventory.size())
        {
            ItemStack result = this.compressingCraftMatrix.decrStackSize(slot - this.inventory.size(), size);

            if (!result.isEmpty())
            {
                this.updateInput();
            }
            this.markDirty();
            return result;
        }

        if (!this.inventory.get(slot).isEmpty())
        {
            ItemStack itemStack;

            if (this.inventory.get(slot).getCount() <= size)
            {
                itemStack = this.inventory.get(slot);
                this.inventory.set(slot, ItemStack.EMPTY);
                this.markDirty();
                return itemStack;
            }
            else
            {
                itemStack = this.inventory.get(slot).splitStack(size);

                if (this.inventory.get(slot).isEmpty())
                {
                    this.inventory.set(slot, ItemStack.EMPTY);
                }
                this.markDirty();
                return itemStack;
            }
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        if (index >= this.inventory.size())
        {
            this.markDirty();
            return this.compressingCraftMatrix.removeStackFromSlot(index - this.inventory.size());
        }
        if (!this.inventory.get(index).isEmpty())
        {
            ItemStack itemStack = this.inventory.get(index);
            this.inventory.set(index, ItemStack.EMPTY);
            this.markDirty();
            return itemStack;
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        if (slot >= this.inventory.size())
        {
            this.compressingCraftMatrix.setInventorySlotContents(slot - this.inventory.size(), itemStack);
            this.updateInput();
        }
        else
        {
            this.inventory.set(slot, itemStack);

            if (!itemStack.isEmpty() && itemStack.getCount() > this.getInventoryStackLimit())
            {
                itemStack.setCount(this.getInventoryStackLimit());
            }
        }
        this.markDirty();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot == 0)
        {
            return !itemStack.isEmpty() && ItemElectricBase.isElectricItem(itemStack.getItem());
        }
        else if (slot >= 3)
        {
            if (!this.producingStack.isEmpty())
            {
                ItemStack stackInSlot = this.getStackInSlot(slot);
                return !stackInSlot.isEmpty() && stackInSlot.isItemEqual(itemStack);
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
        ArrayList<Integer> removeSlots = new ArrayList<>();

        for (int i = 3; i < 12; i++)
        {
            if (removeSlots.contains(i))
            {
                continue;
            }

            ItemStack stack1 = this.getStackInSlot(i);

            if (stack1.isEmpty() || stack1.getCount() <= 0)
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

                if (stack2.isEmpty())
                {
                    continue;
                }
                if (stack1.isItemEqual(stack2))
                {
                    if (stack2.getCount() >= stack1.getCount())
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
        return this.world.getBlockState(this.getPos()).getValue(BlockRocketCrusher.FACING);
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

    private boolean isItemCompressorInput(ItemStack itemStack, int id)
    {
        for (IRecipe recipe : RocketCrusherRecipes.getRecipeList())
        {
            if (recipe instanceof ShapedRecipesMP)
            {
                if (id >= ((ShapedRecipesMP) recipe).recipeItems.length)
                {
                    continue;
                }

                ItemStack itemstack1 = ((ShapedRecipesMP) recipe).recipeItems[id];

                if (itemStack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767 || itemStack.getItemDamage() == itemstack1.getItemDamage()))
                {
                    for (int i = 0; i < ((ShapedRecipesMP) recipe).recipeItems.length; i++)
                    {
                        if (i == id)
                        {
                            continue;
                        }

                        ItemStack itemstack2 = ((ShapedRecipesMP) recipe).recipeItems[i];

                        if (itemStack.getItem() == itemstack2.getItem() && (itemstack2.getItemDamage() == 32767 || itemStack.getItemDamage() == itemstack2.getItemDamage()))
                        {
                            ItemStack is3 = this.getStackInSlot(id + 3);
                            ItemStack is4 = this.getStackInSlot(i + 3);
                            return is3 == ItemStack.EMPTY || is4 != ItemStack.EMPTY && is3.getCount() < is4.getCount();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}