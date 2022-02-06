package stevekung.mods.moreplanets.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienChest;
import stevekung.mods.moreplanets.utils.blocks.BlockChestMP;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockAlienChest extends BlockChestMP implements IItemRarity
{
    public BlockAlienChest(String name)
    {
        super(Material.IRON);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.METAL);
        this.setTranslationKey(name);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityAlienChest)
        {
            tile.updateContainingBlockInfo();
        }
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityAlienChest();
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToRGB(IItemRarity.ALIEN);
    }

    @Override
    protected ILockableContainer getLockableContainer(World world, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (!(tile instanceof TileEntityAlienChest))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntityAlienChest)tile;

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

                        if (tileentity1 instanceof TileEntityAlienChest)
                        {
                            if (facing != EnumFacing.WEST && facing != EnumFacing.NORTH)
                            {
                                ilockablecontainer = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), ilockablecontainer, (TileEntityAlienChest)tileentity1);
                            }
                            else
                            {
                                ilockablecontainer = new InventoryLargeChest(LangUtils.translate("container.chestDouble"), (TileEntityAlienChest)tileentity1, ilockablecontainer);
                            }
                        }
                    }
                }
                return ilockablecontainer;
            }
        }
    }
}