package stevekung.mods.moreplanets.planets.nibiru.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityAlienBerryChest;
import stevekung.mods.moreplanets.utils.blocks.BlockChestMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockAlienBerryChest extends BlockChestMP
{
    public BlockAlienBerryChest(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityAlienBerryChest)
        {
            tile.updateContainingBlockInfo();
        }
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityAlienBerryChest();
    }

    @Override
    protected ILockableContainer getLockableContainer(World world, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (!(tile instanceof TileEntityAlienBerryChest))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntityAlienBerryChest)tile;

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

                        if (tileentity1 instanceof TileEntityAlienBerryChest)
                        {
                            if (facing != EnumFacing.WEST && facing != EnumFacing.NORTH)
                            {
                                ilockablecontainer = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), ilockablecontainer, (TileEntityAlienBerryChest)tileentity1);
                            }
                            else
                            {
                                ilockablecontainer = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), (TileEntityAlienBerryChest)tileentity1, ilockablecontainer);
                            }
                        }
                    }
                }
                return ilockablecontainer;
            }
        }
    }
}