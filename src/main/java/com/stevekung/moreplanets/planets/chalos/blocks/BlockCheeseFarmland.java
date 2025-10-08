package com.stevekung.moreplanets.planets.chalos.blocks;

import com.stevekung.lib.utils.BlockStateProperty;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.blocks.BlockFarmlandMP;

import net.minecraft.block.Block;

public class BlockCheeseFarmland extends BlockFarmlandMP
{
    public BlockCheeseFarmland(String name)
    {
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, 0));
        this.setTranslationKey(name);
    }

    @Override
    protected Block getDirtBlock()
    {
        return MPBlocks.CHEESE_DIRT;
    }

    @Override
    protected Block getSourceBlock()
    {
        return MPBlocks.CHEESE_MILK_FLUID_BLOCK;
    }
}