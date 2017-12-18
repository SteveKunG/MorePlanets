/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import stevekung.mods.moreplanets.core.items.IPowerCrystal;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;

public class ItemNameriumCrystal extends ItemMorePlanet implements IPowerCrystal
{
    public ItemNameriumCrystal(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setTextureName("kapteynb:namerium_crystal");
    }

    @Override
    public boolean isPowerCrystal(int meta)
    {
        return true;
    }

    @Override
    public int getPowerCrystalBurnTime(int meta)
    {
        return 480;
    }
}