package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.utils.client.renderer.IItemModelRender;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumBlockRenderType;

public abstract class BlockContainerMP extends BlockContainer implements ISortableBlock, IItemModelRender
{
    private String name;

    protected BlockContainerMP(Material material)
    {
        super(material);
    }

    @Override
    public Block setTranslationKey(String name)
    {
        this.name = name;
        return super.setTranslationKey(name);
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
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}