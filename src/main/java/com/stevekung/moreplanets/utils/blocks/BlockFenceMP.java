package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFenceMP extends BlockFence implements MorePlanetsBlock
{
    private final String name;

    public BlockFenceMP(String name)
    {
        super(Material.WOOD, null);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
        this.setTranslationKey(name);
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
        return MPBlockCategory.FENCE;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return this.name.equals("infected_purlonite_fence") ? ColorUtils.stringToRGB(ItemRarity.ALIEN) : null;
    }
}