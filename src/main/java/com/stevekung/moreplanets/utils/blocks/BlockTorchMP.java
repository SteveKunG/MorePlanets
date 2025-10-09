package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;

public abstract class BlockTorchMP extends BlockTorch implements MorePlanetsBlock
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
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.TORCH;
    }
}