package com.stevekung.moreplanets.world.level.block;

import com.stevekung.moreplanets.registry.MPBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PurloniteBlock extends AmethystBlock
{
    public PurloniteBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getVisualShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
    {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos)
    {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos)
    {
        return true;
    }

    @Override
    public boolean skipRendering(BlockState blockState, BlockState adjacentBlockState, Direction direction)
    {
        return adjacentBlockState.getBlock() == MPBlocks.PURLONITE_BLOCK || adjacentBlockState.getBlock() == MPBlocks.BUDDING_PURLONITE;
    }
}