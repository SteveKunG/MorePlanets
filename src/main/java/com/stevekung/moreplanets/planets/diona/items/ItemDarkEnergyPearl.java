package com.stevekung.moreplanets.planets.diona.items;

import com.stevekung.moreplanets.utils.items.IDarkEnergyFuel;
import com.stevekung.moreplanets.utils.items.ItemBaseMP;

public class ItemDarkEnergyPearl extends ItemBaseMP implements IDarkEnergyFuel
{
    public ItemDarkEnergyPearl(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public int getDarkEnergyFuel()
    {
        return 1000;
    }
}