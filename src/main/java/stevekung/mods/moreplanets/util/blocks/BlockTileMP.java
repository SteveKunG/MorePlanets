package stevekung.mods.moreplanets.util.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public abstract class BlockTileMP extends BlockTileGC implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockTileMP(Material material)
    {
        super(material);
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
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
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}