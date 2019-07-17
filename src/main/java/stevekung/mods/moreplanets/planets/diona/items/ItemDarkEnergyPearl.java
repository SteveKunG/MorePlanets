package stevekung.mods.moreplanets.planets.diona.items;

import stevekung.mods.moreplanets.utils.items.IDarkEnergyFuel;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;

public class ItemDarkEnergyPearl extends ItemBaseMP implements IDarkEnergyFuel
{
    public ItemDarkEnergyPearl(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public int getDarkEnergyFuel()
    {
        return 1000;
    }
}