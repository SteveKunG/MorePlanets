package com.stevekung.moreplanets.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TickableRendererBlockEntity extends BlockEntity
{
    public int ticks;
    protected boolean initialize;

    public TickableRendererBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState)
    {
        super(blockEntityType, blockPos, blockState);
    }

    public static void clientTick(TickableRendererBlockEntity tickableRendererBlockEntity)
    {
        if (!tickableRendererBlockEntity.initialize)
        {
            tickableRendererBlockEntity.ticks = tickableRendererBlockEntity.ticks + tickableRendererBlockEntity.level.random.nextInt(100);
            tickableRendererBlockEntity.initialize = true;
        }
        tickableRendererBlockEntity.ticks++;
    }
}