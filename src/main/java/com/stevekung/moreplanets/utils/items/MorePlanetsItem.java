package com.stevekung.moreplanets.utils.items;

import com.stevekung.moreplanets.utils.ModelNameGatherer;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;

import micdoodle8.mods.galacticraft.api.item.GCRarity;

public interface MorePlanetsItem extends ModelNameGatherer, ItemRarity, GCRarity
{
    MPItemCategory getItemCategory();
}