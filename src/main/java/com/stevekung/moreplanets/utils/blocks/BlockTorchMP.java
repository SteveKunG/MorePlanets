package com.stevekung.moreplanets.utils.blocks;

import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.utils.client.renderer.IItemModelRender;

public abstract class BlockTorchMP extends BlockTorch implements ISortableBlock, IItemModelRender
{
    public BlockTorchMP()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
        this.setHardness(0.0F);
        this.setTickRandomly(true);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.TORCH;
    }
}