package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.utils.blocks.BlockTreasureChestMP;

public class BlockChalosTreasureChest extends BlockTreasureChestMP
{
    public BlockChalosTreasureChest(String name)
    {
        super();
        this.setUnlocalizedName(name);
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
            IInventory inv = this.getContainer(world, pos);

            if (inv != null)
            {
                player.displayGUIChest(inv);
            }
            return true;
        }
    }

    public IInventory getContainer(World world, BlockPos pos)
    {
        TileEntity tileentity = world.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityChalosTreasureChest))
        {
            return null;
        }
        else
        {
            Object object = tileentity;

            if (this.cannotOpenChest(world, pos))
            {
                return null;
            }
            else
            {
                return (IInventory)object;
            }
        }
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        return Container.calcRedstoneFromInventory(this.getContainer(world, pos));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityChalosTreasureChest();
    }
}