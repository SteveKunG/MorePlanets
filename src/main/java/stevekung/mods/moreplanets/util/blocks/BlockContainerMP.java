package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class BlockContainerMP extends BlockContainer implements ISortableBlock
{
    protected BlockContainerMP(Material material)
    {
        super(material);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.BUILDING_BLOCK;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }
}