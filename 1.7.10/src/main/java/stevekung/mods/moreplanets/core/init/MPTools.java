/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import stevekung.mods.moreplanets.moons.koentus.items.tools.KoentusToolsItems;
import stevekung.mods.moreplanets.planets.diona.items.tools.DionaToolsItems;
import stevekung.mods.moreplanets.planets.fronos.items.tools.FronosToolsItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.tools.KapteynBToolsItems;
import stevekung.mods.moreplanets.planets.nibiru.items.tools.NibiruToolsItems;
import stevekung.mods.moreplanets.planets.pluto.items.tools.PlutoToolsItems;
import stevekung.mods.moreplanets.planets.polongnius.items.tools.PolongniusToolsItems;
import stevekung.mods.moreplanets.planets.siriusb.items.tools.SiriusBToolsItems;

public class MPTools
{
    public static void init()
    {
        DionaToolsItems.init();
        PolongniusToolsItems.init();
        NibiruToolsItems.init();
        KoentusToolsItems.init();
        FronosToolsItems.init();
        KapteynBToolsItems.init();
        SiriusBToolsItems.init();
        PlutoToolsItems.init();
    }
}