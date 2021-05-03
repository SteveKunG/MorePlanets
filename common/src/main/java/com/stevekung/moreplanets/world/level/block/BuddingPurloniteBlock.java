package com.stevekung.moreplanets.world.level.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

public class BuddingPurloniteBlock extends PurloniteBlock
{
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingPurloniteBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState)
    {
        return PushReaction.DESTROY;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random)
    {
        if (random.nextInt(5) == 0)
        {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos2 = blockPos.relative(direction);
            BlockState blockState2 = serverLevel.getBlockState(blockPos2);
            Block block = null;

            if (canClusterGrowAtState(blockState2))
            {
                block = MPBlocks.SMALL_PURLONITE_BUD;
            }
            else if (blockState2.is(MPBlocks.SMALL_PURLONITE_BUD) && blockState2.getValue(PurloniteClusterBlock.FACING) == direction)
            {
                block = MPBlocks.MEDIUM_PURLONITE_BUD;
            }
            else if (blockState2.is(MPBlocks.MEDIUM_PURLONITE_BUD) && blockState2.getValue(PurloniteClusterBlock.FACING) == direction)
            {
                block = MPBlocks.LARGE_PURLONITE_BUD;
            }
            else if (blockState2.is(MPBlocks.LARGE_PURLONITE_BUD) && blockState2.getValue(PurloniteClusterBlock.FACING) == direction)
            {
                block = MPBlocks.PURLONITE_CLUSTER;
            }

            if (block != null)
            {
                BlockState blockState3 = block.defaultBlockState().setValue(PurloniteClusterBlock.FACING, direction).setValue(PurloniteClusterBlock.WATERLOGGED, blockState2.getFluidState().getType() == Fluids.WATER);
                serverLevel.setBlockAndUpdate(blockPos2, blockState3);
            }
        }
    }

    private static boolean canClusterGrowAtState(BlockState blockState)
    {
        return blockState.isAir() || blockState.is(Blocks.WATER) && blockState.getFluidState().getAmount() == 8;
    }
}
