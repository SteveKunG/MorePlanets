package com.stevekung.moreplanets.utils.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;

public class BlockMineableOre extends BlockBaseMP implements IDetectableResource
{
    public BlockMineableOre(String name)
    {
        super(name, Material.ROCK);
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.ORE;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }
}