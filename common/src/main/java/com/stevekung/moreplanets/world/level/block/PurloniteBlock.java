package com.stevekung.moreplanets.world.level.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PurloniteBlock extends Block
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
    @Environment(EnvType.CLIENT)
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
    @Environment(EnvType.CLIENT)
    public boolean skipRendering(BlockState blockState, BlockState blockState2, Direction direction)
    {
        return blockState2.getBlock() == MPBlocks.PURLONITE_BLOCK || blockState2.getBlock() == MPBlocks.BUDDING_PURLONITE;
    }
}