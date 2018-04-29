package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityInfectedChest;
import stevekung.mods.moreplanets.utils.blocks.BlockChestMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockInfectedChest extends BlockChestMP
{
    public BlockInfectedChest(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof TileEntityInfectedChest)
        {
            tileentity.updateContainingBlockInfo();
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer lock = this.getLockableContainer(world, pos);

            if (lock != null)
            {
                player.displayGUIChest(lock);
            }
        }
        return true;
    }

    @Override
    public ILockableContainer getLockableContainer(World world, BlockPos pos)
    {
        TileEntity tileentity = world.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityInfectedChest))
        {
            return null;
        }
        else
        {
            Object object = tileentity;

            if (this.isBlocked(world, pos))
            {
                return null;
            }
            else
            {
                Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

                while (iterator.hasNext())
                {
                    EnumFacing enumfacing = (EnumFacing)iterator.next();
                    BlockPos blockpos1 = pos.offset(enumfacing);
                    Block block = world.getBlockState(blockpos1).getBlock();

                    if (block == this)
                    {
                        if (this.isBlocked(world, blockpos1))
                        {
                            return null;
                        }

                        TileEntity tileentity1 = world.getTileEntity(blockpos1);

                        if (tileentity1 instanceof TileEntityInfectedChest)
                        {
                            if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH)
                            {
                                object = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), (ILockableContainer)object, (TileEntityInfectedChest)tileentity1);
                            }
                            else
                            {
                                object = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), (TileEntityInfectedChest)tileentity1, (ILockableContainer)object);
                            }
                        }
                    }
                }
                return (ILockableContainer)object;
            }
        }
    }

    @Override
    public TileEntity getChestTile()
    {
        return new TileEntityInfectedChest();
    }
}