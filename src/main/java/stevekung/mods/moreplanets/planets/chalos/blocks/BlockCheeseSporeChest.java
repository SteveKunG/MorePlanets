package stevekung.mods.moreplanets.planets.chalos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.utils.blocks.BlockChestMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockCheeseSporeChest extends BlockChestMP
{
    public BlockCheeseSporeChest(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityCheeseSporeChest)
        {
            tile.updateContainingBlockInfo();
        }
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityCheeseSporeChest();
    }

    @Override
    protected ILockableContainer getLockableContainer(World world, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (!(tile instanceof TileEntityCheeseSporeChest))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntityCheeseSporeChest)tile;

            if (!allowBlocking && this.isBlocked(world, pos))
            {
                return null;
            }
            else
            {
                for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
                {
                    BlockPos blockpos = pos.offset(facing);
                    Block block = world.getBlockState(blockpos).getBlock();

                    if (block == this)
                    {
                        if (!allowBlocking && this.isBlocked(world, blockpos))
                        {
                            return null;
                        }

                        TileEntity tileentity1 = world.getTileEntity(blockpos);

                        if (tileentity1 instanceof TileEntityCheeseSporeChest)
                        {
                            if (facing != EnumFacing.WEST && facing != EnumFacing.NORTH)
                            {
                                ilockablecontainer = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), ilockablecontainer, (TileEntityCheeseSporeChest)tileentity1);
                            }
                            else
                            {
                                ilockablecontainer = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), (TileEntityCheeseSporeChest)tileentity1, ilockablecontainer);
                            }
                        }
                    }
                }
                return ilockablecontainer;
            }
        }
    }
}