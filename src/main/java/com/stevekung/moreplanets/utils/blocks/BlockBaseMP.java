package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBaseMP extends Block implements MorePlanetsBlock
{
    private MPBlockCategory category;
    private String name;
    private ColorUtils.RGB rgb;

    public BlockBaseMP(Material material)
    {
        super(material);
    }

    public BlockBaseMP(String name, Material material)
    {
        super(material);
        this.setTranslationKey(name);
    }

    @Override
    public Block setTranslationKey(String name)
    {
        this.name = name;
        return super.setTranslationKey(name);
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
        return this.category == null ? MPBlockCategory.BUILDING_BLOCK : this.category;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return this.rgb != null ? this.rgb : null;
    }

    public BlockBaseMP setSortCategory(MPBlockCategory category)
    {
        this.category = category;
        return this;
    }

    public BlockBaseMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}