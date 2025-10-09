package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTrapdoorMP extends BlockTrapDoor implements MorePlanetsBlock
{
    private final String name;

    public BlockTrapdoorMP(String name)
    {
        super(Material.WOOD);
        this.setTranslationKey(name);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3.0F);
        this.name = name;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.TRAPDOOR;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}