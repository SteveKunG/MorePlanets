package com.stevekung.moreplanets.utils.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;

public class BlockTerraformable extends BlockBaseMP implements ITerraformableBlock
{
    public BlockTerraformable(String name)
    {
        this(name, Material.ROCK);
    }

    public BlockTerraformable(String name, Material material)
    {
        super(name, material);
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        return !world.getBlockState(pos.up()).isOpaqueCube();
    }
}