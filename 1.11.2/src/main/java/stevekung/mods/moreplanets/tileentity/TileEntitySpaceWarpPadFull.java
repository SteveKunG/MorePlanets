package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.init.MPBlocks;

public class TileEntitySpaceWarpPadFull extends TileEntityDummy implements IMultiBlock, IInventoryDefaults, ISidedInventory
{
    public int dimensionID;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean receiveData;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean checkInvalid;
    public BlockPos pos;
    private NonNullList<ItemStack> containingItems = NonNullList.withSize(1, ItemStack.EMPTY);
    private boolean initialised;

    public TileEntitySpaceWarpPadFull()
    {
        this.storage.setMaxExtract(75);
        this.storage.setCapacity(20000.0F);
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
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public void update()
    {
        if (!this.initialised)
        {
            if (!this.world.isRemote && this.world.getWorldType() != WorldType.DEBUG_WORLD)
            {
                this.onCreate(this.world, this.getPos());
            }
            this.initialiseMultiTiles(this.getPos(), this.world);
            this.initialised = true;
        }

        /*if (this.pos != null) TODO
        {
            if (this.worldObj.getBlockState(this.pos).getBlock() == MPBlocks.SPACE_WARP_PAD_FULL)
            {
                this.checkInvalid = false;
            }
            else
            {
                this.checkInvalid = true;
            }
        }*/
        super.update();
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return MPBlocks.SPACE_WARP_PAD_FULL.onBlockActivated(this.world, this.mainBlockPosition, MPBlocks.SPACE_WARP_PAD_FULL.getDefaultState(), player, player.getActiveHand(), player.getHorizontalFacing(), 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void onCreate(World world, BlockPos placedPosition)
    {
        this.mainBlockPosition = placedPosition;

        for (int x = -1; x < 2; x++)
        {
            for (int z = -1; z < 2; z++)
            {
                BlockPos vecToAdd = new BlockPos(placedPosition.getX() + x, placedPosition.getY(), placedPosition.getZ() + z);

                if (!vecToAdd.equals(placedPosition))
                {
                    MPBlocks.DUMMY_BLOCK.makeFakeBlock(world, vecToAdd, placedPosition, BlockDummy.BlockType.WARP_PAD);
                }
            }
        }
    }

    @Override
    public void getPositions(BlockPos placedPosition, List<BlockPos> positions) {}

    @Override
    public void onDestroy(TileEntity callingBlock)
    {
        BlockPos thisBlock = this.getPos();
        this.world.destroyBlock(thisBlock, true);

        for (int x = -1; x < 2; x++)
        {
            for (int z = -1; z < 2; z++)
            {
                BlockPos pos = new BlockPos(thisBlock.getX() + x, thisBlock.getY(), thisBlock.getZ() + z);

                if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
                {
                    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(pos, MPBlocks.SPACE_WARP_PAD.getDefaultState());
                }
                this.world.destroyBlock(pos, false);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagCompound tag = nbt.getCompoundTag("TeleportPos");
        this.pos = new BlockPos(tag.getInteger("X"), tag.getInteger("Y"), tag.getInteger("Z"));
        this.dimensionID = nbt.getInteger("DimensionID");
        this.receiveData = nbt.getBoolean("ReceiveData");
        this.checkInvalid = nbt.getBoolean("CheckInvalid");
        this.containingItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.containingItems);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("DimensionID", this.dimensionID);
        nbt.setBoolean("ReceiveData", this.receiveData);
        nbt.setBoolean("CheckInvalid", this.checkInvalid);

        if (this.pos != null)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("X", this.pos.getX());
            tag.setInteger("Y", this.pos.getY());
            tag.setInteger("Z", this.pos.getZ());
            nbt.setTag("TeleportPos", tag);
        }
        ItemStackHelper.saveAllItems(nbt, this.containingItems);
        return nbt;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 1, this.getPos().getX() + 2, this.getPos().getY() + 0.4D, this.getPos().getZ() + 2);
    }

    public void setTeleportData(BlockPos pos, int dimension, boolean receiveData)
    {
        this.pos = pos;
        this.dimensionID = dimension;
        this.receiveData = receiveData;
    }

    public boolean getHasReceiveData()
    {
        return this.receiveData;
    }

    public boolean getCheckInvalid()
    {
        return this.checkInvalid;
    }

    public int getDimensionID()
    {
        return this.dimensionID;
    }

    public BlockPos getBlockPos()
    {
        return this.pos;
    }

    @Override
    public boolean shouldUseEnergy()
    {
        return this.receiveData && !this.disabled;
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        return EnumFacing.DOWN;
    }

    @Override
    public ItemStack getBatteryInSlot()
    {
        return this.getStackInSlot(0);
    }

    @Override
    public EnumFacing getFront()
    {
        return EnumFacing.DOWN;
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
        return GCCoreUtil.translate("container.space_warp_pad.name");
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation(this.getName());
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
        return slotID == 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return slotID == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    public String getGUIStatus()
    {
        if (this.getEnergyStoredGC() == 0)
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.missingpower.name");
        }
        if (!this.getHasReceiveData())
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.no_warp_pos.name");
        }
        if (this.getCheckInvalid())
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.missing_warp_pad.name");
        }
        if (this.getDisabled(0))
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.ready.name");
        }
        if (this.getEnergyStoredGC() < this.storage.getMaxExtract())
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.missingpower.name");
        }
        return EnumColor.DARK_GREEN + GCCoreUtil.translate("gui.status.active.name");
    }

    private boolean initialiseMultiTiles(BlockPos pos, World world)
    {
        IMultiBlock thisTile = this;

        //Client can create its own fake blocks and tiles - no need for networking in 1.8+
        if (world.isRemote)
        {
            thisTile.onCreate(world, pos);
        }

        List<BlockPos> positions = new ArrayList<>();
        thisTile.getPositions(pos, positions);
        boolean result = true;

        for (BlockPos vecToAdd : positions)
        {
            TileEntity tile = world.getTileEntity(vecToAdd);

            if (tile instanceof TileEntityDummy)
            {
                ((TileEntityDummy) tile).mainBlockPosition = pos;
            }
            else
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
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