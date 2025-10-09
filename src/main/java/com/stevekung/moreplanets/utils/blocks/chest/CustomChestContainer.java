package com.stevekung.moreplanets.utils.blocks.chest;

import net.minecraft.block.Block;

public interface CustomChestContainer
{
    default String getCustomContainerName(Block block)
    {
        return null;
    }
}