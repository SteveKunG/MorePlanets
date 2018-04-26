package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public abstract class BlockContainerMP extends BlockContainer implements ISortableBlock, ISingleBlockRender
{
    private String name;

    protected BlockContainerMP(Material material)
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
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.BUILDING_BLOCK;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override //TODO Remove 1.13
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}