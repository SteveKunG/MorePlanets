package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFenceGateMP extends BlockFenceGate implements MorePlanetsBlock
{
    private final String name;

    public BlockFenceGateMP(String name)
    {
        super(BlockPlanks.EnumType.OAK);
        this.name = name;
        this.setTranslationKey(name);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.FENCE_GATE;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}