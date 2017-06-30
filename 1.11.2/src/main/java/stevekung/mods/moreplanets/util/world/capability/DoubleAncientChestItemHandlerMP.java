package stevekung.mods.moreplanets.util.world.capability;

import java.lang.ref.WeakReference;

import com.google.common.base.Objects;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAncientChestMP;

public class DoubleAncientChestItemHandlerMP extends WeakReference<TileEntityAncientChestMP> implements IItemHandler
{
    public static DoubleAncientChestItemHandlerMP NO_ADJACENT_CHESTS_INSTANCE = new DoubleAncientChestItemHandlerMP(null, null, false);
    private boolean mainChestIsUpper;
    private TileEntityAncientChestMP mainChest;
    private int hashCode;

    public DoubleAncientChestItemHandlerMP(TileEntityAncientChestMP mainChest, TileEntityAncientChestMP other, boolean mainChestIsUpper)
    {
        super(other);
        this.mainChest = mainChest;
        this.mainChestIsUpper = mainChestIsUpper;
        this.hashCode = Objects.hashCode(mainChestIsUpper ? mainChest : other) * 31 + Objects.hashCode(!mainChestIsUpper ? mainChest : other);
    }

    public static DoubleAncientChestItemHandlerMP get(TileEntityAncientChestMP chest)
    {
        World world = chest.getWorld();
        BlockPos pos = chest.getPos();

        if (world == null || pos == null || !world.isBlockLoaded(pos))
        {
            return null;
        }

        Block blockType = chest.getBlockType();
        EnumFacing[] horizontals = EnumFacing.HORIZONTALS;

        for (int i = horizontals.length - 1; i >= 0; i--)
        {
            EnumFacing enumfacing = horizontals[i];
            BlockPos blockpos = pos.offset(enumfacing);
            Block block = world.getBlockState(blockpos).getBlock();

            if (block == blockType)
            {
                TileEntity otherTE = world.getTileEntity(blockpos);

                if (otherTE instanceof TileEntityAncientChestMP)
                {
                    TileEntityAncientChestMP otherChest = (TileEntityAncientChestMP) otherTE;
                    return new DoubleAncientChestItemHandlerMP(chest, otherChest, enumfacing != net.minecraft.util.EnumFacing.WEST && enumfacing != net.minecraft.util.EnumFacing.NORTH);
                }
            }
        }
        return NO_ADJACENT_CHESTS_INSTANCE;
    }

    public TileEntityAncientChestMP getChest(boolean accessingUpper)
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

    private TileEntityAncientChestMP getOtherChest()
    {
        TileEntityAncientChestMP tileEntityChest = this.get();
        return tileEntityChest != null && !tileEntityChest.isInvalid() ? tileEntityChest : null;
    }

    @Override
    public int getSlots()
    {
        return 27 * 2;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityAncientChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getStackInSlot(targetSlot) : null;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityAncientChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getSingleChestHandler().insertItem(targetSlot, stack, simulate) : stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityAncientChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getSingleChestHandler().extractItem(targetSlot, amount, simulate) : null;
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

        DoubleAncientChestItemHandlerMP that = (DoubleAncientChestItemHandlerMP) o;

        if (this.hashCode != that.hashCode)
        {
            return false;
        }

        TileEntityAncientChestMP otherChest = this.getOtherChest();

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

    public boolean needsRefresh()
    {
        if (this == NO_ADJACENT_CHESTS_INSTANCE)
        {
            return false;
        }
        TileEntityAncientChestMP tileEntityChest = this.get();
        return tileEntityChest == null || tileEntityChest.isInvalid();
    }

    @Override
    public int getSlotLimit(int slot)
    {
        boolean accessingUpperChest = slot < 27;
        return this.getChest(accessingUpperChest).getInventoryStackLimit();
    }
}