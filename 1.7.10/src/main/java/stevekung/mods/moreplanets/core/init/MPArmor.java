/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.moons.koentus.items.armor.KoentusArmorItems;
import stevekung.mods.moreplanets.planets.diona.items.armor.DionaArmorItems;
import stevekung.mods.moreplanets.planets.fronos.items.armor.FronosArmorItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.armor.KapteynBArmorItems;
import stevekung.mods.moreplanets.planets.nibiru.items.armor.NibiruArmorItems;
import stevekung.mods.moreplanets.planets.polongnius.items.armor.PolongniusArmorItems;
import stevekung.mods.moreplanets.planets.siriusb.items.armor.SiriusBArmorItems;

public class MPArmor
{
    public static void init()
    {
        DionaArmorItems.init();
        PolongniusArmorItems.init();
        NibiruArmorItems.init();
        KoentusArmorItems.init();
        FronosArmorItems.init();
        KapteynBArmorItems.init();
        SiriusBArmorItems.init();
    }
}