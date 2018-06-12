package stevekung.mods.moreplanets.utils.tileentity;

import java.util.Iterator;
import java.util.List;

import micdoodle8.mods.galacticraft.core.inventory.IInventoryDefaults;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import stevekung.mods.moreplanets.utils.world.capability.DoubleAncientChestItemHandlerMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public abstract class TileEntityAncientChestMP extends TileEntityLockableLoot implements ITickable, IInventoryDefaults
{
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
    public boolean adjacentChestChecked;
    public TileEntityAncientChestMP adjacentChestZNeg;
    public TileEntityAncientChestMP adjacentChestXPos;
    public TileEntityAncientChestMP adjacentChestXNeg;
    public TileEntityAncientChestMP adjacentChestZPos;
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    public DoubleAncientChestItemHandlerMP doubleChestHandler;
    private Block block;
    private String name;

    public TileEntityAncientChestMP(Block block, String name)
    {
        this.block = block;
        this.name = name;
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
        return LangUtils.translate("container." + this.name + ".ancientchest.name");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

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
        float f;

        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            f = 5.0F;
            List list = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(i - f, j - f, k - f, i + 1 + f, j + 1 + f, k + 1 + f));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = 0.1F;
        double d2;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            double d1 = i + 0.5D;
            d2 = k + 0.5D;

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
            float f1 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += f;
            }
            else
            {
                this.lidAngle -= f;
            }
            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
            {
                d2 = i + 0.5D;
                double d0 = k + 0.5D;

                if (this.adjacentChestZPos != null)
                {
                    d0 += 0.5D;
                }
                if (this.adjacentChestXPos != null)
                {
                    d2 += 0.5D;
                }
                this.world.playSound(null, d2, j + 0.5D, d0, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
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
        return "moreplanets:ancient_chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
    {
        this.fillWithLoot(player);
        return new ContainerChest(playerInventory, this, player);
    }

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if (this.doubleChestHandler == null || this.doubleChestHandler.needsRefresh())
            {
                this.doubleChestHandler = DoubleAncientChestItemHandlerMP.get(this);
            }
            if (this.doubleChestHandler != null && this.doubleChestHandler != DoubleAncientChestItemHandlerMP.NO_ADJACENT_CHESTS_INSTANCE)
            {
                return (T) this.doubleChestHandler;
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(this.getPos().add(-1, 0, -1), this.getPos().add(2, 2, 2));
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

    public abstract void checkForAdjacentChests();
}