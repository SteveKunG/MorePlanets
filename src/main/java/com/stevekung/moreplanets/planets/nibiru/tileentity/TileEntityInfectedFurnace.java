package com.stevekung.moreplanets.planets.nibiru.tileentity;

import com.stevekung.moreplanets.utils.blocks.BlockFurnaceMP;
import com.stevekung.moreplanets.utils.tileentity.TileEntityFurnaceMP;

public class TileEntityInfectedFurnace extends TileEntityFurnaceMP
{
    @Override
    protected void setState()
    {
        BlockFurnaceMP.setState(BlockFurnaceMP.BlockType.INFECTED, this.isBurning(), this.world, this.pos);
    }
}