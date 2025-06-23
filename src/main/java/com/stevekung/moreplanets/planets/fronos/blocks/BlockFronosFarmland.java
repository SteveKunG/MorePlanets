package com.stevekung.moreplanets.planets.fronos.blocks;

import net.minecraft.block.Block;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.blocks.BlockFarmlandMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockFronosFarmland extends BlockFarmlandMP
{
    public BlockFronosFarmland(String name)
    {
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, 0));
        this.setTranslationKey(name);
    }

    @Override
    protected Block getDirtBlock()
    {
        return MPBlocks.FRONOS_DIRT;
    }
}