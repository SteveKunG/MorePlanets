package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumBlockRenderType;

public abstract class BlockContainerMP extends BlockContainer implements MorePlanetsBlock
{
    private final String name;

    protected BlockContainerMP(String name, Material material)
    {
        super(material);
        this.setTranslationKey(name);
        this.name = name;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.BUILDING_BLOCK;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}