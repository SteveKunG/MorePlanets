package stevekung.mods.moreplanets.tileentity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.tile.FluidTankGC;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.wrappers.FluidHandlerWrapper;
import micdoodle8.mods.galacticraft.core.wrappers.IFluidHandlerWrapper;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.entity.EntityBlackHoleStorage;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAdvancedMP;

public class TileEntityBlackHoleStorage extends TileEntityAdvancedMP implements IFluidHandlerWrapper, IConnector
{
    private static final int[] SLOTS = new int[108];
    @NetworkedField(targetSide = Side.CLIENT)
    public FluidTankGC fluidTank = new FluidTankGC(1000000, this);
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean disableBlackHole = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean useHopper = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public String ownerUUID = "";
    @NetworkedField(targetSide = Side.CLIENT)
    public int xp = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public int xpTemp = 0;
    @NetworkedField(targetSide = Side.CLIENT)
    public String collectMode = "item";
    public int renderTicks;
    public int modeInt;
    private boolean initialize = true;

    static
    {
        for (int i = 0; i < TileEntityBlackHoleStorage.SLOTS.length; TileEntityBlackHoleStorage.SLOTS[i] = i++) {}
    }

    public TileEntityBlackHoleStorage()
    {
        super("container.black_hole_storage.name");
        this.inventory = NonNullList.withSize(108, ItemStack.EMPTY);
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
            if (blackHoleList.isEmpty())
            {
                JsonUtil json = new JsonUtil();
                EntityPlayer player = this.world.getPlayerEntityByUUID(UUID.fromString(this.ownerUUID));
                this.destroyBlock();

                if (player != null)
                {
                    player.sendMessage(json.text(GCCoreUtil.translate("gui.black_hole_disappear.message")).setStyle(json.red()));
                }
            }
            this.xpTemp = this.fluidTank.getFluidAmount();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (nbt.hasKey("XpFluid"))
        {
            this.fluidTank.readFromNBT(nbt.getCompoundTag("XpFluid"));
        }
        else
        {
            NBTTagCompound fluidNbt = new NBTTagCompound();
            fluidNbt.setString("FluidName", "xpjuice");
            fluidNbt.setInteger("Amount", nbt.getInteger("XP"));
            this.fluidTank.readFromNBT(fluidNbt);
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

        if (this.ownerUUID != null)
        {
            nbt.setString("OwnerUUID", this.ownerUUID);
        }
        if (this.collectMode != null)
        {
            nbt.setString("CollectMode", this.collectMode);
        }
        if (this.fluidTank.getFluid() != null)
        {
            nbt.setTag("XpFluid", this.fluidTank.writeToNBT(new NBTTagCompound()));
        }
        nbt.setBoolean("DisableBlackHole", this.disableBlackHole);
        nbt.setBoolean("UseHopper", this.useHopper);
        nbt.setInteger("XP", this.xp);
        return nbt;
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
    public boolean isItemValidForSlot(int index, ItemStack itemStack)
    {
        return itemStack.getItem() != Item.getItemFromBlock(MPBlocks.BLACK_HOLE_STORAGE);
    }

    @Override
    public double getPacketRange()
    {
        return 32;
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
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return (T) new FluidHandlerWrapper(this, facing);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill)
    {
        if (from == EnumFacing.UP)
        {
            return 0;
        }
        else
        {
            return this.fluidTank.fill(resource.copy(), doFill);
        }
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
    {
        if (resource == null)
        {
            return null;
        }
        if (from == EnumFacing.UP)
        {
            return null;
        }
        else
        {
            return this.drain(from, resource.amount, doDrain);
        }
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
    {
        if (from == EnumFacing.UP)
        {
            return null;
        }
        else
        {
            return this.fluidTank.drain(maxDrain, doDrain);
        }
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid)
    {
        if (from == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            return fluid == null || fluid.getName().equals("xpjuice");
        }
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid)
    {
        if (from == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            return fluid == null || this.fluidTank.getFluid() != null && this.fluidTank.getFluid().getFluid() == fluid;
        }
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from)
    {
        if (from == EnumFacing.UP)
        {
            return new FluidTankInfo[0];
        }
        else
        {
            FluidTank compositeTank = new FluidTank(this.fluidTank.getCapacity());
            int capacity = this.fluidTank.getCapacity();
            compositeTank.setCapacity(capacity);
            return new FluidTankInfo[] { compositeTank.getInfo() };
        }
    }

    @Override
    public boolean canConnect(EnumFacing facing, NetworkType type)
    {
        return facing != EnumFacing.UP;
    }

    public int getMaxXP()
    {
        return 1000000;
    }

    public boolean drainExp(EntityPlayer player)
    {
        FluidStack fluid = this.fluidTank.getFluid();
        boolean isXP = false;

        if (CompatibilityManagerMP.isModAddedXpFluid())
        {
            isXP = fluid.isFluidEqual(new FluidStack(FluidRegistry.getFluid("xpjuice"), 0));
        }
        else
        {
            isXP = fluid.getFluid().getName().equals("xpjuice");
        }

        if (fluid != null && isXP)
        {
            int requiredXp = MathHelper.ceil(player.xpBarCap() * (1 - player.experience));
            int requiredXPJuice = this.xpToLiquidRatio(requiredXp);
            FluidStack drained = this.fluidTank.drain(requiredXPJuice, false);

            if (drained != null)
            {
                int xp = this.liquidToXpRatio(drained.amount);

                if (xp > 0)
                {
                    int actualDrain = this.xpToLiquidRatio(xp);
                    this.addPlayerXP(player, xp);
                    this.fluidTank.drain(actualDrain, true);
                    return true;
                }
            }
        }
        return false;
    }

    private int liquidToXpRatio(int liquid)
    {
        return liquid / 20;
    }

    private int xpToLiquidRatio(int xp)
    {
        return xp * 20;
    }

    private void addPlayerXP(EntityPlayer player, int amount)
    {
        int experience = this.getPlayerXP(player) + amount;
        player.experienceTotal = experience;
        player.experienceLevel = this.getLevelForExperience(experience);
        int expForLevel = this.getExperienceForLevel(player.experienceLevel);
        player.experience = (float)(experience - expForLevel) / (float)player.xpBarCap();
    }

    private int getPlayerXP(EntityPlayer player)
    {
        return (int)(this.getExperienceForLevel(player.experienceLevel) + player.experience * player.xpBarCap());
    }

    private int getExperienceForLevel(int level)
    {
        if (level == 0)
        {
            return 0;
        }
        if (level <= 15)
        {
            return this.sum(level, 7, 2);
        }
        if (level <= 30)
        {
            return 315 + this.sum(level - 15, 37, 5);
        }
        return 1395 + this.sum(level - 30, 112, 9);
    }

    private int getLevelForExperience(int targetXp)
    {
        int level = 0;

        while (true)
        {
            final int xpToNextLevel = this.xpBarCap(level);

            if (targetXp < xpToNextLevel)
            {
                return level;
            }
            level++;
            targetXp -= xpToNextLevel;
        }
    }

    private int xpBarCap(int level)
    {
        if (level >= 30)
        {
            return 112 + (level - 30) * 9;
        }
        if (level >= 15)
        {
            return 37 + (level - 15) * 5;
        }
        return 7 + level * 2;
    }

    private int sum(int n, int a0, int d)
    {
        return n * (2 * a0 + (n - 1) * d) / 2;
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
        for (ItemStack itemStack : this.getInventory())
        {
            if (itemStack.isEmpty() || itemStack.getCount() != itemStack.getMaxStackSize())
            {
                return false;
            }
        }
        if (this.fluidTank.getFluidAmount() >= this.getMaxXP())
        {
            return false;
        }
        return true;
    }

    private boolean putXPValue(EntityXPOrb xpOrb)
    {
        if (xpOrb == null || this.fluidTank.getFluidAmount() >= this.getMaxXP())
        {
            return false;
        }
        else
        {
            if (this.fluidTank.getFluidAmount() < this.fluidTank.getCapacity() - xpOrb.xpValue)
            {
                this.fluidTank.fill(new FluidStack(FluidRegistry.getFluid("xpjuice"), xpOrb.xpValue), true);
                xpOrb.setDead();
            }
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
            ItemStack itemstack = entityItem.getItem().copy();
            ItemStack itemstack1 = this.putStackInInventoryAllSlots(inventory, itemstack);

            if (!itemstack1.isEmpty() && itemstack1.getCount() != 0)
            {
                entityItem.setItem(itemstack1);
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

    private boolean destroyBlock()
    {
        IBlockState iblockstate = this.world.getBlockState(this.pos);
        Block block = iblockstate.getBlock();

        if (block.isAir(iblockstate, this.world, this.pos))
        {
            return false;
        }
        else
        {
            this.world.playEvent(2001, this.pos, Block.getStateId(iblockstate));
            ItemStack itemStack = new ItemStack(MPBlocks.BLACK_HOLE_STORAGE);
            NBTTagCompound nbt = new NBTTagCompound();
            ItemStackHelper.saveAllItems(nbt, this.getInventory());
            nbt.setBoolean("Disable", this.disableBlackHole);
            nbt.setBoolean("Hopper", this.useHopper);
            nbt.setString("Mode", this.collectMode);

            if (this.fluidTank.getFluid() != null)
            {
                NBTTagCompound fluidNbt = new NBTTagCompound();
                fluidNbt.setString("FluidName", "xpjuice");
                fluidNbt.setInteger("Amount", this.fluidTank.getFluidAmount());
                nbt.setTag("XpFluid", fluidNbt);
            }
            itemStack.setTagCompound(nbt);
            Block.spawnAsEntity(this.world, this.pos, itemStack);
            return this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
}