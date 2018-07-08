package stevekung.mods.moreplanets.utils.world.capability;

import java.lang.ref.WeakReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Objects;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;

public class DoubleChestItemHandlerMP extends WeakReference<TileEntityChestMP> implements IItemHandlerModifiable
{
    public static final DoubleChestItemHandlerMP NO_ADJACENT_CHESTS_INSTANCE = new DoubleChestItemHandlerMP(null, null, false);
    private final boolean mainChestIsUpper;
    private final TileEntityChestMP mainChest;
    private final int hashCode;

    public DoubleChestItemHandlerMP(@Nullable TileEntityChestMP mainChest, @Nullable TileEntityChestMP other, boolean mainChestIsUpper)
    {
        super(other);
        this.mainChest = mainChest;
        this.mainChestIsUpper = mainChestIsUpper;
        this.hashCode = Objects.hashCode(mainChestIsUpper ? mainChest : other) * 31 + Objects.hashCode(!mainChestIsUpper ? mainChest : other);
    }

    @Override
    public int getSlots()
    {
        return 54;
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getStackInSlot(targetSlot) : ItemStack.EMPTY;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack itemStack)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityChestMP chest = this.getChest(accessingUpperChest);

        if (chest != null)
        {
            IItemHandler singleHandler = chest.getSingleChestHandler();

            if (singleHandler instanceof IItemHandlerModifiable)
            {
                ((IItemHandlerModifiable) singleHandler).setStackInSlot(targetSlot, itemStack);
            }
        }

        chest = this.getChest(!accessingUpperChest);

        if (chest != null)
        {
            chest.markDirty();
        }
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack itemStack, boolean simulate)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityChestMP chest = this.getChest(accessingUpperChest);

        if (chest == null)
        {
            return itemStack;
        }

        int starting = itemStack.getCount();
        ItemStack ret = chest.getSingleChestHandler().insertItem(targetSlot, itemStack, simulate);

        if (ret.getCount() != starting && !simulate)
        {
            chest = this.getChest(!accessingUpperChest);

            if (chest != null)
            {
                chest.markDirty();
            }
        }
        return ret;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityChestMP chest = this.getChest(accessingUpperChest);

        if (chest == null)
        {
            return ItemStack.EMPTY;
        }

        ItemStack ret = chest.getSingleChestHandler().extractItem(targetSlot, amount, simulate);

        if (!ret.isEmpty() && !simulate)
        {
            chest = this.getChest(!accessingUpperChest);

            if (chest != null)
            {
                chest.markDirty();
            }
        }
        return ret;
    }

    @Override
    public int getSlotLimit(int slot)
    {
        boolean accessingUpperChest = slot < 27;
        return this.getChest(accessingUpperChest).getInventoryStackLimit();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }

        DoubleChestItemHandlerMP that = (DoubleChestItemHandlerMP) o;

        if (this.hashCode != that.hashCode)
        {
            return false;
        }

        final TileEntityChestMP otherChest = this.getOtherChest();

        if (this.mainChestIsUpper == that.mainChestIsUpper)
        {
            return Objects.equal(this.mainChest, that.mainChest) && Objects.equal(otherChest, that.getOtherChest());
        }
        else
        {
            return Objects.equal(this.mainChest, that.getOtherChest()) && Objects.equal(otherChest, that.mainChest);
        }
    }

    @Override
    public int hashCode()
    {
        return this.hashCode;
    }

    @Nullable
    public static DoubleChestItemHandlerMP get(TileEntityChestMP chest)
    {
        World world = chest.getWorld();
        BlockPos pos = chest.getPos();

        if (world == null || pos == null || !world.isBlockLoaded(pos))
        {
            return null; // Still loading
        }

        Block blockType = chest.getBlockType();
        EnumFacing[] horizontals = EnumFacing.HORIZONTALS;

        for (int i = horizontals.length - 1; i >= 0; i--)   // Use reverse order so we can return early
        {
            EnumFacing enumfacing = horizontals[i];
            BlockPos blockpos = pos.offset(enumfacing);
            Block block = world.getBlockState(blockpos).getBlock();

            if (block == blockType)
            {
                TileEntity otherTE = world.getTileEntity(blockpos);

                if (otherTE instanceof TileEntityChestMP)
                {
                    TileEntityChestMP otherChest = (TileEntityChestMP) otherTE;
                    return new DoubleChestItemHandlerMP(chest, otherChest, enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH);
                }
            }
        }
        return NO_ADJACENT_CHESTS_INSTANCE; //All alone
    }

    public boolean needsRefresh()
    {
        if (this == NO_ADJACENT_CHESTS_INSTANCE)
        {
            return false;
        }
        TileEntityChestMP TileEntityChestMP = this.get();
        return TileEntityChestMP == null || TileEntityChestMP.isInvalid();
    }

    @Nullable
    private TileEntityChestMP getChest(boolean accessingUpper)
    {
        if (accessingUpper == this.mainChestIsUpper)
        {
            return this.mainChest;
        }
        else
        {
            return this.getOtherChest();
        }
    }

    @Nullable
    private TileEntityChestMP getOtherChest()
    {
        TileEntityChestMP TileEntityChestMP = this.get();
        return TileEntityChestMP != null && !TileEntityChestMP.isInvalid() ? TileEntityChestMP : null;
    }
}