package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.api.tile.IDisableableMachine;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectricalSource;
import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class TileEntityNuclearWasteGenerator extends TileBaseUniversalElectricalSource implements IConnector, IDisableableMachine, ISidedInventory, IInventoryDefaults
{
    public int maxGenerate = 10000;
    private ItemStack[] containingItems = new ItemStack[1];
    @NetworkedField(targetSide = Side.CLIENT)
    public float generateTick;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean disabled = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public int disableCooldown = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean missingTank;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean missingWaste;
    private int alertTick;

    public TileEntityNuclearWasteGenerator()
    {
        this.setTierGC(4);
        this.storage.setCapacity(100000000.0F);
        this.storage.setMaxExtract(this.maxGenerate);
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
        this.alertTick++;

        if (!this.worldObj.isRemote)
        {
            if (!this.disabled)
            {
                if (this.generateTick > 0)
                {
                    this.receiveEnergyGC(null, this.generateTick, false);

                    for (int x = -1; x < 2; x++)
                    {
                        for (int z = -1; z < 2; z++)
                        {
                            if (this.getWaste(this.getPos().add(x, -1, z)))
                            {
                                if (this.worldObj.rand.nextInt(5000000) == 0)
                                {
                                    this.worldObj.setBlockState(this.getPos().add(x, -1, z), Blocks.OBSIDIAN.getDefaultState());
                                }
                            }
                        }
                    }
                    for (int x = -3; x < 4; x++)
                    {
                        for (int z = -3; z < 4; z++)
                        {
                            if (this.getTank(this.getPos().add(x, 0, z)))
                            {
                                this.worldObj.setBlockState(this.getPos().add(x, 0, z), NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, this.worldObj.rand.nextInt(5000000) == 0));
                            }
                        }
                    }

                    this.ticks = this.ticks + this.worldObj.rand.nextInt(2);

                    if (this.ticks % 33 == 0)
                    {
                        this.worldObj.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.MACHINE_GENERATOR_AMBIENT, SoundCategory.BLOCKS, 0.05F, 1.0F);
                    }
                    if (this.alertTick % 100 == 0)
                    {
                        if (this.missingWaste || this.missingTank)
                        {
                            this.worldObj.playSound(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), MPSounds.MACHINE_ALERT, SoundCategory.BLOCKS, 5.0F, 1.0F);
                        }
                    }
                }

                if (!(this.getWaste(this.getPos().add(1, -1, 0)) && this.getWaste(this.getPos().add(-1, -1, 0)) && this.getWaste(this.getPos().add(0, -1, 1)) && this.getWaste(this.getPos().add(0, -1, -1)) && this.getWaste(this.getPos().add(1, -1, 1)) && this.getWaste(this.getPos().add(-1, -1, 1)) && this.getWaste(this.getPos().add(-1, -1, -1)) && this.getWaste(this.getPos().add(1, -1, -1))))
                {
                    this.missingWaste = true;
                }
                else
                {
                    this.missingWaste = false;
                }
                if (!(this.getTank(this.getPos().add(3, 0, 0)) && this.getTank(this.getPos().add(-3, 0, 0)) && this.getTank(this.getPos().add(0, 0, 3)) && this.getTank(this.getPos().add(0, 0, -3)) && this.getTank(this.getPos().add(2, 0, 2)) && this.getTank(this.getPos().add(-2, 0, 2)) && this.getTank(this.getPos().add(2, 0, -2)) && this.getTank(this.getPos().add(-2, 0, -2))))
                {
                    this.missingTank = true;
                }
                else
                {
                    this.missingTank = false;
                }

                if (this.getWaste(this.getPos().add(1, -1, 0)) && this.getWaste(this.getPos().add(-1, -1, 0)) && this.getWaste(this.getPos().add(0, -1, 1)) && this.getWaste(this.getPos().add(0, -1, -1)) && this.getWaste(this.getPos().add(1, -1, 1)) && this.getWaste(this.getPos().add(-1, -1, 1)) && this.getWaste(this.getPos().add(-1, -1, -1)) && this.getWaste(this.getPos().add(1, -1, -1)) && this.getTank(this.getPos().add(3, 0, 0)) && this.getTank(this.getPos().add(-3, 0, 0)) && this.getTank(this.getPos().add(0, 0, 3)) && this.getTank(this.getPos().add(0, 0, -3)) && this.getTank(this.getPos().add(2, 0, 2)) && this.getTank(this.getPos().add(-2, 0, 2)) && this.getTank(this.getPos().add(2, 0, -2)) && this.getTank(this.getPos().add(-2, 0, -2)))
                {
                    this.generateTick = Math.min(this.generateTick + Math.max(this.generateTick * 0.005F, 1.0F), this.maxGenerate);
                }
                else
                {
                    this.generateTick = Math.max(this.generateTick - 10.0F, 0);
                }
                this.generateTick = Math.min(Math.max(this.generateTick, 0.0F), this.getMaxEnergyStoredGC());
            }
            this.produce();
            this.recharge(this.containingItems[0]);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.generateTick = nbt.getFloat("GenerateTick");
        this.setDisabled(0, nbt.getBoolean("Disabled"));
        this.disableCooldown = nbt.getInteger("DisabledCooldown");
        this.missingTank = nbt.getBoolean("MissingTank");
        this.missingWaste = nbt.getBoolean("MissingWaste");
        NBTTagList list = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];

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
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setFloat("GenerateTick", this.generateTick);
        nbt.setInteger("DisabledCooldown", this.disableCooldown);
        nbt.setBoolean("Disabled", this.getDisabled(0));
        nbt.setBoolean("MissingTank", this.missingTank);
        nbt.setBoolean("MissingWaste", this.missingWaste);
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
        return super.writeToNBT(nbt);
    }

    private boolean getWaste(BlockPos pos)
    {
        if (this.worldObj.getBlockState(pos) == NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean getTank(BlockPos pos)
    {
        if (this.worldObj.getBlockState(pos) == NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, this.worldObj.rand.nextFloat() == 0.99F))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getScaledElecticalLevel(int i)
    {
        return (int) Math.floor(this.getEnergyStoredGC() * i / this.getMaxEnergyStoredGC());
    }

    @Override
    public float receiveElectricity(EnumFacing from, float energy, int tier, boolean doReceive)
    {
        return 0;
    }

    @Override
    public EnumSet<EnumFacing> getElectricalInputDirections()
    {
        return EnumSet.noneOf(EnumFacing.class);
    }

    @Override
    public EnumSet<EnumFacing> getElectricalOutputDirections()
    {
        return EnumSet.of(this.getElectricOutputDirection());
    }

    @Override
    public EnumFacing getElectricOutputDirection()
    {
        return EnumFacing.DOWN;
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
    public String getName()
    {
        return GCCoreUtil.translate("container.nuclear_waste_generator.name");
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
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.containingItems[index] != null)
        {
            ItemStack itemStack;

            if (this.containingItems[index].stackSize <= count)
            {
                itemStack = this.containingItems[index];
                this.containingItems[index] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.containingItems[index].splitStack(count);

                if (this.containingItems[index].stackSize == 0)
                {
                    this.containingItems[index] = null;
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
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        return this.isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side)
    {
        return slot == 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return slot == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
    }
}