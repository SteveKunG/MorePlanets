package com.stevekung.moreplanets.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class DarkEnergyCoreBlockEntity extends TickableRendererBlockEntity
{
    public DarkEnergyCoreBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        super(MPBlockEntities.DARK_ENERGY_CORE, blockPos, blockState);
    }
}