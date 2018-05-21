package stevekung.mods.moreplanets.utils.tileentity;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import stevekung.mods.moreplanets.utils.world.capability.SpaceDoubleChestItemHandlerMP;

public class TileEntityChestMP extends TileEntityLockableLoot implements ITickable, IInventoryDefaults
{
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
    public boolean adjacentChestChecked;
    public TileEntityChestMP adjacentChestZNeg;
    public TileEntityChestMP adjacentChestXPos;
    public TileEntityChestMP adjacentChestXNeg;
    public TileEntityChestMP adjacentChestZPos;
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private String customName;
    public SpaceDoubleChestItemHandlerMP doubleChestHandler;
    private Block block;

    public TileEntityChestMP(Block block)
    {
        this.block = block;
    }

    @Override
    public int getSizeInventory()
    {
        return 27;
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : this.chestContents)
        {
            if (!itemStack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    protected NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.chest";
    }

    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public void setCustomName(String name)
    {
        this.customName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (nbt.hasKey("CustomName", 8))
        {
            this.customName = nbt.getString("CustomName");
        }
        if (!this.checkLootAndRead(nbt))
        {
            ItemStackHelper.loadAllItems(nbt, this.chestContents);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (!this.checkLootAndWrite(nbt))
        {
            ItemStackHelper.saveAllItems(nbt, this.chestContents);
        }
        if (this.hasCustomName())
        {
            nbt.setString("CustomName", this.customName);
        }
        return nbt;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
        this.adjacentChestChecked = false;
        this.doubleChestHandler = null;
    }

    @SuppressWarnings("incomplete-switch")
    private void setNeighbor(TileEntityChestMP chestTe, EnumFacing side)
    {
        if (chestTe.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (side)
            {
            case NORTH:
                if (this.adjacentChestZNeg != chestTe)
                {
                    this.adjacentChestChecked = false;
                }
                break;
            case SOUTH:
                if (this.adjacentChestZPos != chestTe)
                {
                    this.adjacentChestChecked = false;
                }
                break;
            case EAST:
                if (this.adjacentChestXPos != chestTe)
                {
                    this.adjacentChestChecked = false;
                }
                break;
            case WEST:
                if (this.adjacentChestXNeg != chestTe)
                {
                    this.adjacentChestChecked = false;
                }
            }
        }
    }

    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
            this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
            this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
            this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
        }
    }

    protected TileEntityChestMP getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChestMP)
            {
                TileEntityChestMP tileentitychest = (TileEntityChestMP)tileentity;
                tileentitychest.setNeighbor(this, side.getOpposite());
                return tileentitychest;
            }
        }
        return null;
    }

    protected boolean isChestAt(BlockPos pos)
    {
        if (this.world == null)
        {
            return false;
        }
        else
        {
            Block block = this.world.getBlockState(pos).getBlock();
            return block == this.block;
        }
    }

    @Override
    public void update()
    {
        this.checkForAdjacentChests();
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;

        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            float f = 5.0F;

            this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(i - f, j - f, k - f, i + 1 + f, j + 1 + f, k + 1 + f)).forEach(player ->
            {
                if (player.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)player.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numPlayersUsing;
                    }
                }
            });
        }

        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            double d1 = i + 0.5D;
            double d2 = k + 0.5D;

            if (this.adjacentChestZPos != null)
            {
                d2 += 0.5D;
            }
            if (this.adjacentChestXPos != null)
            {
                d1 += 0.5D;
            }
            this.world.playSound(null, d1, j + 0.5D, d2, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += f1;
            }
            else
            {
                this.lidAngle -= f1;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f3 = 0.5F;

            if (this.lidAngle < f3 && f2 >= f3 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
            {
                double d3 = i + 0.5D;
                double d0 = k + 0.5D;

                if (this.adjacentChestZPos != null)
                {
                    d0 += 0.5D;
                }
                if (this.adjacentChestXPos != null)
                {
                    d3 += 0.5D;
                }
                this.world.playSound(null, d3, j + 0.5D, d0, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
        if (!player.isSpectator())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }
            ++this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        if (!player.isSpectator() && this.getBlockType() == this.block)
        {
            --this.numPlayersUsing;
            this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
        this.checkForAdjacentChests();
    }

    @Override
    public String getGuiID()
    {
        return "minecraft:chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
    {
        this.fillWithLoot(player);
        return new ContainerChest(playerInventory, this, player);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if (this.doubleChestHandler == null || this.doubleChestHandler.needsRefresh())
            {
                this.doubleChestHandler = SpaceDoubleChestItemHandlerMP.get(this);
            }
            if (this.doubleChestHandler != null && this.doubleChestHandler != SpaceDoubleChestItemHandlerMP.NO_ADJACENT_CHESTS_INSTANCE)
            {
                return (T) this.doubleChestHandler;
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public IItemHandler getSingleChestHandler()
    {
        return super.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
    }
}