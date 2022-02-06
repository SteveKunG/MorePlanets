package stevekung.mods.moreplanets.utils.tileentity;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.galacticraft.core.tile.IMachineSides;
import micdoodle8.mods.galacticraft.core.tile.IMachineSidesProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import stevekung.mods.moreplanets.blocks.BlockTieredEnergyStorageCluster;

public abstract class TileEntityEnergyStorageClusterMP extends TileBaseUniversalElectricalSource implements IConnector, IMachineSides
{
    public int scaledEnergyLevel;
    public int lastScaledEnergyLevel;
    private float lastEnergy = 0;
    private MachineSidePack[] machineSides;
    public String containerName = "";

    public TileEntityEnergyStorageClusterMP(float capacity, float extract, int tier, String containerName)
    {
        super("container." + containerName + ".name");
        this.inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        this.setTierGC(tier);
        this.storage.setCapacity(capacity);
        this.storage.setMaxExtract(extract);
        this.containerName = containerName;
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
            this.recharge(this.getInventory().get(0));
            this.recharge(this.getInventory().get(1));
            this.discharge(this.getInventory().get(2));
            this.discharge(this.getInventory().get(3));
            this.produce();
        }
        this.lastScaledEnergyLevel = this.scaledEnergyLevel;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.readMachineSidesFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        this.addMachineSidesToNBT(nbt);
        return nbt;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[0];
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
    public EnumFacing byIndex()
    {
        return this.world.getBlockState(this.getPos()).getValue(BlockTieredEnergyStorageCluster.FACING);
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        switch (this.getSide(MachineSide.ELECTRIC_IN))
        {
        case LEFT:
            return this.byIndex().rotateY();
        case REAR:
            return this.byIndex().getOpposite();
        case TOP:
            return EnumFacing.UP;
        case BOTTOM:
            return EnumFacing.DOWN;
        case RIGHT:
        default:
            return this.byIndex().rotateYCCW();
        }
    }

    @Override
    public EnumFacing getElectricOutputDirection()
    {
        switch (this.getSide(MachineSide.ELECTRIC_OUT))
        {
        case RIGHT:
            return this.byIndex().rotateYCCW();
        case REAR:
            return this.byIndex().getOpposite();
        case TOP:
            return EnumFacing.UP;
        case BOTTOM:
            return EnumFacing.DOWN;
        case LEFT:
        default:
            return this.byIndex().rotateY();
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
    public synchronized MachineSidePack[] getAllMachineSides()
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
        return BlockTieredEnergyStorageCluster.MACHINESIDES_RENDERTYPE;
    }
    //------------------END OF IMachineSides implementation
}