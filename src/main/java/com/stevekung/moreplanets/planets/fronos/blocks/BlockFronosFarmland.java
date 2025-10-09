package com.stevekung.moreplanets.planets.fronos.blocks;

import com.stevekung.lib.utils.BlockStateProperty;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.blocks.BlockFarmlandMP;

import net.minecraft.block.Block;

public class BlockFronosFarmland extends BlockFarmlandMP
{
    public BlockFronosFarmland(String name)
    {
        super(name);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, 0));
    }

    @Override
    protected Block getDirtBlock()
    {
        return MPBlocks.FRONOS_DIRT;
    }
}