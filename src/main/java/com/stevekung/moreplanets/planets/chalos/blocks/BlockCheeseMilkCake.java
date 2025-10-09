package com.stevekung.moreplanets.planets.chalos.blocks;

import com.stevekung.moreplanets.utils.BlocksItemsRegistry;
import com.stevekung.moreplanets.utils.IDescription;
import com.stevekung.moreplanets.utils.ItemDescription;
import com.stevekung.moreplanets.utils.blocks.BlockCakeMP;

public class BlockCheeseMilkCake extends BlockCakeMP implements IDescription
{
    public BlockCheeseMilkCake(String name)
    {
        super(name);
    }

    @Override
    public int getFoodAmount()
    {
        return 4;
    }

    @Override
    public float getSaturationAmount()
    {
        return 0.6F;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> list.addAll(BlocksItemsRegistry.getDescription(this.getTranslationKey() + ".description"));
    }
}