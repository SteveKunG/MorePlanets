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
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;

public class SpaceDoubleChestItemHandlerMP extends WeakReference<TileEntityChestMP> implements IItemHandler
{
    public static SpaceDoubleChestItemHandlerMP NO_ADJACENT_CHESTS_INSTANCE = new SpaceDoubleChestItemHandlerMP(null, null, false);
    private boolean mainChestIsUpper;
    private TileEntityChestMP mainChest;
    private int hashCode;

    public SpaceDoubleChestItemHandlerMP(TileEntityChestMP mainChest, TileEntityChestMP other, boolean mainChestIsUpper)
    {
        super(other);
        this.mainChest = mainChest;
        this.mainChestIsUpper = mainChestIsUpper;
        this.hashCode = Objects.hashCode(mainChestIsUpper ? mainChest : other) * 31 + Objects.hashCode(!mainChestIsUpper ? mainChest : other);
    }

    public static SpaceDoubleChestItemHandlerMP get(TileEntityChestMP chest)
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

                if (otherTE instanceof TileEntityChestMP)
                {
                    TileEntityChestMP otherChest = (TileEntityChestMP) otherTE;
                    return new SpaceDoubleChestItemHandlerMP(chest, otherChest, enumfacing != net.minecraft.util.EnumFacing.WEST && enumfacing != net.minecraft.util.EnumFacing.NORTH);
                }
            }
        }
        return NO_ADJACENT_CHESTS_INSTANCE;
    }

    public TileEntityChestMP getChest(boolean accessingUpper)
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

    private TileEntityChestMP getOtherChest()
    {
        TileEntityChestMP tileEntityChest = this.get();
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
        TileEntityChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getStackInSlot(targetSlot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getSingleChestHandler().insertItem(targetSlot, stack, simulate) : stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityChestMP chest = this.getChest(accessingUpperChest);
        return chest != null ? chest.getSingleChestHandler().extractItem(targetSlot, amount, simulate) : ItemStack.EMPTY;
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

        SpaceDoubleChestItemHandlerMP that = (SpaceDoubleChestItemHandlerMP) o;

        if (this.hashCode != that.hashCode)
        {
            return false;
        }

        TileEntityChestMP otherChest = this.getOtherChest();

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
        TileEntityChestMP tileEntityChest = this.get();
        return tileEntityChest == null || tileEntityChest.isInvalid();
    }

    @Override
    public int getSlotLimit(int slot)
    {
        boolean accessingUpperChest = slot < 27;
        return this.getChest(accessingUpperChest).getInventoryStackLimit();
    }
}