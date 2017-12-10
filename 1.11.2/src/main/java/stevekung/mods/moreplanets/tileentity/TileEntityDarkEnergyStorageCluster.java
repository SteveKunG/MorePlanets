package stevekung.mods.moreplanets.tileentity;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.galacticraft.core.tile.IMachineSides;
import micdoodle8.mods.galacticraft.core.tile.IMachineSidesProperties;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import stevekung.mods.moreplanets.blocks.BlockTieredEnergyStorage;

public class TileEntityDarkEnergyStorageCluster extends TileBaseUniversalElectricalSource implements ISidedInventory, IInventoryDefaults, IConnector, IMachineSides
{
    private float darkEnergyCapacity = 12500000;
    private NonNullList<ItemStack> containingItems = NonNullList.withSize(4, ItemStack.EMPTY);
    public Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();
    public int scaledEnergyLevel;
    public int lastScaledEnergyLevel;
    private float lastEnergy = 0;
    private MachineSidePack[] machineSides;

    public TileEntityDarkEnergyStorageCluster()
    {
        this.setTierGC(4);
        this.storage.setCapacity(this.darkEnergyCapacity);
        this.storage.setMaxExtract(2000);
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public void update()
    {
        float energy = this.storage.getEnergyStoredGC();

        if (!this.world.isRemote)
        {
            if (this.lastEnergy - energy > this.storage.getMaxExtract() - 1)
            {
                this.storage.extractEnergyGC(25, false);
            }
        }

        this.lastEnergy = energy;
        super.update();
        this.scaledEnergyLevel = (int) Math.floor((this.getEnergyStoredGC() + 49) * 16 / this.getMaxEnergyStoredGC());

        if (this.scaledEnergyLevel != this.lastScaledEnergyLevel)
        {
            this.world.notifyLightSet(this.getPos());
        }
        if (!this.world.isRemote)
        {
            this.recharge(this.containingItems.get(0));
            this.recharge(this.containingItems.get(1));
            this.discharge(this.containingItems.get(2));
            this.discharge(this.containingItems.get(3));
            this.produce();
        }
        this.lastScaledEnergyLevel = this.scaledEnergyLevel;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.readMachineSidesFromNBT(nbt);
        this.containingItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.containingItems);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        this.addMachineSidesToNBT(nbt);
        ItemStackHelper.saveAllItems(nbt, this.containingItems);
        return nbt;
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.size();
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
        return GCCoreUtil.translate("container.tiered_energy_storage_cluster_0.name");
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[0];
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        if (itemStack.getItem() instanceof ItemElectricBase)
        {
            if (slot == 0 || slot == 1)
            {
                return ((ItemElectricBase) itemStack.getItem()).getTransfer(itemStack) > 0;
            }
            else if (slot == 2 || slot == 3)
            {
                return ((ItemElectricBase) itemStack.getItem()).getElectricityStored(itemStack) > 0;
            }
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        if (itemStack.getItem() instanceof ItemElectricBase)
        {
            if (slot == 0 || slot == 1)
            {
                return ((ItemElectricBase) itemStack.getItem()).getTransfer(itemStack) <= 0;
            }
            else if (slot == 2 || slot == 3)
            {
                return ((ItemElectricBase) itemStack.getItem()).getElectricityStored(itemStack) <= 0 || this.getEnergyStoredGC() >= this.getMaxEnergyStoredGC();
            }
        }
        return false;
    }

    @Override
    public EnumSet<EnumFacing> getElectricalInputDirections()
    {
        return EnumSet.of(this.getElectricInputDirection());
    }

    @Override
    public EnumSet<EnumFacing> getElectricalOutputDirections()
    {
        return EnumSet.of(this.getElectricOutputDirection());
    }

    @Override
    public boolean canConnect(EnumFacing direction, NetworkType type)
    {
        if (direction == null || type != NetworkType.POWER)
        {
            return false;
        }
        return this.getElectricalInputDirections().contains(direction) || this.getElectricalOutputDirections().contains(direction);
    }

    @Override
    public EnumFacing getFront()
    {
        return this.world.getBlockState(this.getPos()).getValue(BlockTieredEnergyStorage.FACING);
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        switch (this.getSide(MachineSide.ELECTRIC_IN))
        {
        case LEFT:
            return this.getFront().rotateY();
        case REAR:
            return this.getFront().getOpposite();
        case TOP:
            return EnumFacing.UP;
        case BOTTOM:
            return EnumFacing.DOWN;
        case RIGHT:
        default:
            return this.getFront().rotateYCCW();
        }
    }

    @Override
    public EnumFacing getElectricOutputDirection()
    {
        switch (this.getSide(MachineSide.ELECTRIC_OUT))
        {
        case RIGHT:
            return this.getFront().rotateYCCW();
        case REAR:
            return this.getFront().getOpposite();
        case TOP:
            return EnumFacing.UP;
        case BOTTOM:
            return EnumFacing.DOWN;
        case LEFT:
        default:
            return this.getFront().rotateY();
        }
    }

    //------------------
    //Added these methods and field to implement IMachineSides properly
    //------------------
    @Override
    public MachineSide[] listConfigurableSides()
    {
        return new MachineSide[] { MachineSide.ELECTRIC_IN, MachineSide.ELECTRIC_OUT };
    }

    @Override
    public Face[] listDefaultFaces()
    {
        return new Face[] { Face.RIGHT, Face.LEFT };
    }

    @Override
    public MachineSidePack[] getAllMachineSides()
    {
        if (this.machineSides == null)
        {
            this.initialiseSides();
        }
        return this.machineSides;
    }

    @Override
    public void setupMachineSides(int length)
    {
        this.machineSides = new MachineSidePack[length];
    }

    @Override
    public void onLoad()
    {
        this.clientOnLoad();
    }

    @Override
    public IMachineSidesProperties getConfigurationType()
    {
        return BlockTieredEnergyStorage.MACHINESIDES_RENDERTYPE;
    }
    //------------------END OF IMachineSides implementation

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : this.containingItems)
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
        return this.containingItems;
    }
}