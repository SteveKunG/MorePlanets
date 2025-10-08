package com.stevekung.moreplanets.planets.nibiru.entity;

import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;

public class EntityTerrasquid extends EntitySquid implements IEntityBreathable
{
    public EntityTerrasquid(World world)
    {
        super(world);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        IBlockState blockState = this.world.getBlockState(new BlockPos(this).down());
        return blockState.getBlock() == MPBlocks.PURIFIED_WATER_FLUID_BLOCK;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}