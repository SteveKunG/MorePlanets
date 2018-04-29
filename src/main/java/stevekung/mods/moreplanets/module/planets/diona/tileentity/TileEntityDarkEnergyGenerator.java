package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.api.tile.IDisableableMachine;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.dimension.IDarkEnergyProvider;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class TileEntityDarkEnergyGenerator extends TileBaseUniversalElectricalSource implements IDisableableMachine, ISidedInventory, IConnector, IInventoryDefaults
{
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean disabled = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public int disableCooldown = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int generateWatts = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int darkEnergyFuel = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int prevDarkEnergyFuel = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int facing;
    public NonNullList<ItemStack> containingItems = NonNullList.withSize(3, ItemStack.EMPTY);
    public int renderTicks;
    private boolean initialize = true;

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

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;

        if (this.initialize)
        {
            this.renderTicks = this.renderTicks + this.world.rand.nextInt(100);
            this.initialize = false;
        }
        if (!this.world.isRemote)
        {
            this.receiveEnergyGC(null, this.generateWatts, false);
            this.recharge(this.containingItems.get(0));
            this.recharge(this.containingItems.get(1));

            if (!this.disabled)
            {
                if (this.ticks % 20 == 0 && this.darkEnergyFuel > 0)
                {
                    this.darkEnergyFuel--;
                }
                if (!this.containingItems.get(2).isEmpty() && this.darkEnergyFuel <= 0)
                {
                    this.darkEnergyFuel = 1000;//TODO More dark energy fuel
                    this.prevDarkEnergyFuel = this.darkEnergyFuel;
                    this.containingItems.get(2).shrink(1);
                }
            }
            if (this.disableCooldown > 0)
            {
                this.disableCooldown--;
            }
            if (this.getGenerate() > 0.0F)
            {
                this.ticks = this.ticks + this.world.rand.nextInt(2);

                if (this.ticks % 33 == 0)
                {
                    this.world.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.MACHINE_GENERATOR_AMBIENT, SoundCategory.BLOCKS, 0.05F, 1.0F);
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
        return this.world.provider instanceof IDarkEnergyProvider ? ((IDarkEnergyProvider) this.world.provider).getDarkEnergyMultiplier(this.world, this.pos) + this.darkEnergyFuel : 100 + this.darkEnergyFuel;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.storage.setCapacity(nbt.getFloat("MaxEnergy"));
        this.setDisabled(0, nbt.getBoolean("Disabled"));
        this.disableCooldown = nbt.getInteger("DisabledCooldown");
        this.facing = nbt.getInteger("Facing");
        this.darkEnergyFuel = nbt.getInteger("DarkEnergyFuel");
        this.prevDarkEnergyFuel = nbt.getInteger("PrevDarkEnergyFuel");
        this.containingItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.containingItems);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("MaxEnergy", this.getMaxEnergyStoredGC());
        nbt.setInteger("DisabledCooldown", this.disableCooldown);
        nbt.setBoolean("Disabled", this.getDisabled(0));
        nbt.setInteger("Facing", this.facing);
        nbt.setInteger("DarkEnergyFuel", this.darkEnergyFuel);
        nbt.setInteger("PrevDarkEnergyFuel", this.prevDarkEnergyFuel);
        ItemStackHelper.saveAllItems(nbt, this.containingItems);
        return nbt;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Facing", this.facing);
        return new SPacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
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
        return new AxisAlignedBB(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 1, this.getPos().getX() + 2, this.getPos().getY() + 4, this.getPos().getZ() + 2);
    }

    @Override
    public String getName()
    {
        return LangUtils.translate("container.dark_energy_generator.name");
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
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

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
        return slotID == 0 || slotID == 1;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        if (slotID == 2)
        {
            return itemStack.getItem() == DionaItems.DARK_ENERGY_PEARL;
        }
        return (slotID == 0 || slotID == 1) && ItemElectricBase.isElectricItem(itemStack.getItem());
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
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
    }

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