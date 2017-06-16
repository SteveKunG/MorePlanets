package stevekung.mods.moreplanets.util.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class BlockTileMP extends BlockTileGC implements ISortableBlock
{
    public BlockTileMP(Material material)
    {
        super(material);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
        }

        if (this.hasTileEntity(state) && !(world.getBlockState(pos).getBlock() instanceof BlockContainer))
        {
            world.removeTileEntity(pos);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }
}