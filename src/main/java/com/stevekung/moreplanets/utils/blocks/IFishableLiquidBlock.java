package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.init.MPLootTables;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public interface IFishableLiquidBlock
{
    @Nullable
    default ResourceLocation getLootTable()
    {
        return MPLootTables.SPACE_FISHING;
    }
}