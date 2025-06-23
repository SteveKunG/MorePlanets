package com.stevekung.moreplanets.utils.blocks;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;
import com.stevekung.moreplanets.init.MPLootTables;

public interface IFishableLiquidBlock
{
    @Nullable
    default ResourceLocation getLootTable()
    {
        return MPLootTables.SPACE_FISHING;
    }
}