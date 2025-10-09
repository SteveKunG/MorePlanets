package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.utils.ModelNameGatherer;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;

public interface MorePlanetsBlock extends ModelNameGatherer, ItemRarity
{
    EnumSortCategoryBlock getBlockCategory();
}