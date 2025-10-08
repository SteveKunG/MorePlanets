package com.stevekung.moreplanets.utils.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;

public abstract class BlockGrassBlockMP extends BlockBaseMP implements ITerraformableBlock
{
    public BlockGrassBlockMP()
    {
        super(Material.GRASS);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.6F);
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        return !world.getBlockState(pos.up()).isOpaqueCube();
    }
}