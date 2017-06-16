/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.tileentities;

import java.util.EnumSet;

import cpw.mods.fml.relauncher.Side;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.items.IPowerCrystal;

public class TileEntityPowerCrystalGenerator extends TileBaseUniversalElectricalSource implements IInventory, ISidedInventory, IConnector
{
    public static int MAX_GENERATE_GJ_PER_TICK = 200;
    public static int MIN_GENERATE_GJ_PER_TICK = 50;

    private static float BASE_ACCELERATION = 0.5F;

    public float prevGenerateWatts = 0;

    @NetworkedField(targetSide = Side.CLIENT)
    public float heatGJperTick = 0;

    @NetworkedField(targetSide = Side.CLIENT)
    public int itemCookTime = 0;

    private ItemStack[] containingItems = new ItemStack[1];

    public TileEntityPowerCrystalGenerator()
    {
        this.storage.setMaxExtract(TileEntityPowerCrystalGenerator.MAX_GENERATE_GJ_PER_TICK - TileEntityPowerCrystalGenerator.MIN_GENERATE_GJ_PER_TICK);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!this.worldObj.isRemote)
        {
            if (this.heatGJperTick - TileEntityPowerCrystalGenerator.MIN_GENERATE_GJ_PER_TICK > 0)
            {
                this.receiveEnergyGC(null, this.heatGJperTick - TileEntityPowerCrystalGenerator.MIN_GENERATE_GJ_PER_TICK, false);
            }
            if (this.itemCookTime > 0)
            {
                this.itemCookTime--;
                this.heatGJperTick = Math.min(this.heatGJperTick + Math.max(this.heatGJperTick * 0.005F, TileEntityPowerCrystalGenerator.BASE_ACCELERATION), TileEntityPowerCrystalGenerator.MAX_GENERATE_GJ_PER_TICK);
            }

            if (this.itemCookTime <= 0 && this.containingItems[0] != null)
            {
                boolean isCrystal = false;

                if (TileEntityPowerCrystalGenerator.isCrystal(this.containingItems[0], isCrystal))
                {
                    if (this.itemCookTime <= 0)
                    {
                        this.itemCookTime = ((IPowerCrystal)this.containingItems[0].getItem()).getPowerCrystalBurnTime(this.containingItems[0].getItemDamage());
                        this.decrStackSize(0, 1);
                    }
                }
            }

            this.produce();

            if (this.itemCookTime <= 0)
            {
                this.heatGJperTick = Math.max(this.heatGJperTick - 0.3F, 0);
            }
            this.heatGJperTick = Math.min(Math.max(this.heatGJperTick, 0.0F), this.getMaxEnergyStoredGC());
        }
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
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.itemCookTime = nbt.getInteger("itemCookTime");
        this.heatGJperTick = nbt.getInteger("generateRateInt");
        NBTTagList var2 = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.containingItems.length)
            {
                this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("itemCookTime", this.itemCookTime);
        nbt.setFloat("generateRate", this.heatGJperTick);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.containingItems.length; ++var3)
        {
            if (this.containingItems[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.containingItems[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        nbt.setTag("Items", var2);
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.containingItems[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.containingItems[par1] != null)
        {
            ItemStack var3;

            if (this.containingItems[par1].stackSize <= par2)
            {
                var3 = this.containingItems[par1];
                this.containingItems[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.containingItems[par1].splitStack(par2);

                if (this.containingItems[par1].stackSize == 0)
                {
                    this.containingItems[par1] = null;
                }
                return var3;
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
        if (this.containingItems[par1] != null)
        {
            ItemStack var2 = this.containingItems[par1];
            this.containingItems[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.containingItems[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return StatCollector.translateToLocal("container.powercrystal.name");
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemstack)
    {
        return itemstack.getItem() instanceof IPowerCrystal;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int slotID, ItemStack itemstack, int j)
    {
        return this.isItemValidForSlot(slotID, itemstack);
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack itemstack, int j)
    {
        return slotID == 0;
    }

    @Override
    public float receiveElectricity(ForgeDirection from, float energy, int tier, boolean doReceive)
    {
        return 0;
    }

    @Override
    public EnumSet<ForgeDirection> getElectricalInputDirections()
    {
        return EnumSet.noneOf(ForgeDirection.class);
    }

    @Override
    public EnumSet<ForgeDirection> getElectricalOutputDirections()
    {
        return EnumSet.of(ForgeDirection.getOrientation(this.getBlockMetadata() + 2));
    }

    @Override
    public ForgeDirection getElectricalOutputDirectionMain()
    {
        return ForgeDirection.getOrientation(this.getBlockMetadata() + 2);
    }

    @Override
    public boolean canConnect(ForgeDirection direction, NetworkType type)
    {
        if (direction == null || direction.equals(ForgeDirection.UNKNOWN) || type != NetworkType.POWER)
        {
            return false;
        }
        return direction == this.getElectricalOutputDirectionMain();
    }

    public static boolean isCrystal(ItemStack itemStack, boolean isCrystal)
    {
        Item item = itemStack.getItem();

        if (item instanceof IPowerCrystal && ((IPowerCrystal) item).isPowerCrystal(itemStack.getItemDamage()))
        {
            return true;
        }
        return isCrystal;
    }
}