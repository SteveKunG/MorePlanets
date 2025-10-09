package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvanced;

public abstract class BlockAdvancedMP extends BlockAdvanced implements ITileEntityProvider, MorePlanetsBlock
{
    private final String name;

    public BlockAdvancedMP(String name, Material material)
    {
        super(material);
        this.setHardness(0.6F);
        this.setResistance(2.5F);
        this.name = name;
        this.setTranslationKey(name);
    }

    @Override
    public Block setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.BUILDING_BLOCK;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}