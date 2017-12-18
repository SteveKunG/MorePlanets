package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.api.tile.IDisableableMachine;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.dimension.IDarkEnergyProvider;

public class TileEntityDarkEnergyGenerator extends TileBaseUniversalElectricalSource implements IDisableableMachine, ISidedInventory, IConnector
{
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean disabled = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public int disableCooldown = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int generateWatts = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int darkEnergyFuel = 0;
    private ItemStack[] containingItems = new ItemStack[1];
    public int age = 0;
    private int facing;

    public TileEntityDarkEnergyGenerator()
    {
        this.storage.setMaxExtract(2500);
        this.storage.setMaxReceive(2500);
        this.storage.setCapacity(500000);
    }

    public void setFacing(int facing)
    {
        this.facing = facing;
    }

    public int getFacing()
    {
        return this.facing;
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

        ++this.age;
        this.age = this.age + this.worldObj.rand.nextInt(100);

        if (!this.worldObj.isRemote)
        {
            this.receiveEnergyGC(null, this.generateWatts, false);
            this.recharge(this.containingItems[0]);

            if (!this.disabled)
            {
                if (this.ticks % 20 == 0 && this.darkEnergyFuel > 0)
                {
                    this.darkEnergyFuel--;
                }
            }
            if (this.disableCooldown > 0)
            {
                this.disableCooldown--;
            }
            if (this.getGenerate() > 0.0F)
            {
                this.ticks = this.ticks + this.worldObj.rand.nextInt(2);

                if (this.ticks % 33 == 0)
                {
                    this.worldObj.playSoundEffect(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "moreplanets:ambient.generator.ambient", 0.05F, 1.0F);
                }
                this.generateWatts = Math.min(Math.max(this.getGenerate(), 0), 1500);
            }
            else
            {
                this.generateWatts = 0;
            }
        }
        this.produce();
    }

    public int getGenerate()
    {
        if (this.getDisabled(0))
        {
            return 0;
        }
        return this.worldObj.provider instanceof IDarkEnergyProvider ? ((IDarkEnergyProvider) this.worldObj.provider).getDarkEnergyMultiplier(this.worldObj, this.pos) + this.darkEnergyFuel : 100 + this.darkEnergyFuel;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.storage.setCapacity(nbt.getFloat("MaxEnergy"));
        this.setDisabled(0, nbt.getBoolean("Disabled"));
        this.disableCooldown = nbt.getInteger("DisabledCooldown");
        NBTTagList list = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];
        this.facing = nbt.getInteger("Facing");
        this.darkEnergyFuel = nbt.getInteger("DarkEnergyFuel");

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int slot = compound.getByte("Slot") & 255;

            if (slot < this.containingItems.length)
            {
                this.containingItems[slot] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("MaxEnergy", this.getMaxEnergyStoredGC());
        nbt.setInteger("DisabledCooldown", this.disableCooldown);
        nbt.setBoolean("Disabled", this.getDisabled(0));
        nbt.setInteger("Facing", this.facing);
        nbt.setInteger("DarkEnergyFuel", this.darkEnergyFuel);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.containingItems.length; ++i)
        {
            if (this.containingItems[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.containingItems[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Facing", this.facing);
        return new S35PacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        if (pkt.getTileEntityType() == -1)
        {
            NBTTagCompound nbt = pkt.getNbtCompound();
            this.facing = nbt.getInteger("Facing");
        }
    }

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    public EnumSet<EnumFacing> getElectricalInputDirections()
    {
        return EnumSet.noneOf(EnumFacing.class);
    }

    public EnumFacing getFront()
    {
        return EnumFacing.DOWN;
    }

    @Override
    public EnumSet<EnumFacing> getElectricalOutputDirections()
    {
        return EnumSet.of(this.getFront());
    }

    @Override
    public EnumFacing getElectricOutputDirection()
    {
        return this.getFront();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.fromBounds(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 1, this.getPos().getX() + 2, this.getPos().getY() + 4, this.getPos().getZ() + 2);
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return GCCoreUtil.translate("container.dark_energy_generator.name");
    }

    @Override
    public void setDisabled(int index, boolean disabled)
    {
        if (this.disableCooldown == 0)
        {
            this.disabled = disabled;
            this.disableCooldown = 0;
        }
    }

    @Override
    public boolean getDisabled(int index)
    {
        return this.disabled;
    }

    public int getScaledElecticalLevel(int i)
    {
        return (int) Math.floor(this.getEnergyStoredGC() * i / this.getMaxEnergyStoredGC());
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
    public ItemStack decrStackSize(int slot, int count)
    {
        if (this.containingItems[slot] != null)
        {
            ItemStack itemStack;

            if (this.containingItems[slot].stackSize <= count)
            {
                itemStack = this.containingItems[slot];
                this.containingItems[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.containingItems[slot].splitStack(count);

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
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return this.isItemValidForSlot(slotID, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return slotID == 0;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return slotID == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    @Override
    public boolean canConnect(EnumFacing direction, NetworkType type)
    {
        if (direction == null || type != NetworkType.POWER)
        {
            return false;
        }
        return direction == this.getElectricOutputDirection();
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