package com.stevekung.moreplanets.world.level.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickableBlockEntity;

public class TickableRendererBlockEntity extends BlockEntity implements TickableBlockEntity
{
    public int ticks;
    protected boolean initialize;

    public TickableRendererBlockEntity(BlockEntityType<?> blockEntityType)
    {
        super(blockEntityType);
    }

    @Override
    public void tick()
    {
        if (!this.initialize)
        {
            this.ticks = this.ticks + this.level.random.nextInt(100);
            this.initialize = true;
        }
        this.ticks++;
    }
}