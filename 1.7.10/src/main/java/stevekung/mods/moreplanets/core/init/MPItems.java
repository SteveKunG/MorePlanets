/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.init;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.asteroids.darkasteroids.items.DarkAsteroidsItems;
import stevekung.mods.moreplanets.core.items.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;
import stevekung.mods.moreplanets.moons.io.items.IoItems;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.items.ItemFlagMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class MPItems
{
    public static Item flag;
    public static Item spawn_egg_mp;
    public static Item feces;
    public static Item achievement_temp;
    public static Item desh_thermal_cloth;
    public static Item tier_2_thermal_padding;
    public static Item tier_3_thermal_padding;

    public static void init()
    {
        DionaItems.init();
        PolongniusItems.init();
        NibiruItems.init();
        KoentusItems.init();
        FronosItems.init();
        KapteynBItems.init();
        SiriusBItems.init();

        MercuryItems.init();
        VenusItems.init();
        PlutoItems.init();
        EuropaItems.init();
        IoItems.init();
        DarkAsteroidsItems.init();

        MPItems.initItems();
        MPItems.registerItems();
    }

    private static void initItems()
    {
        MPItems.flag = new ItemFlagMP("mp_flag");
        MPItems.spawn_egg_mp = new ItemMonsterPlacerMP("spawn_egg_mp").setTextureName("spawn_egg");
        MPItems.feces = new ItemFeces("feces");
        MPItems.achievement_temp = new ItemAchievementTemp("achievement_temp");
        MPItems.desh_thermal_cloth = new ItemMorePlanet("desh_thermal_cloth", "mpcore");
        MPItems.tier_2_thermal_padding = new ItemTier2ThermalPadding("tier_2_thermal_padding");
        MPItems.tier_3_thermal_padding = new ItemTier3ThermalPadding("tier_3_thermal_padding");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(MPItems.flag);
        RegisterHelper.registerItem(MPItems.spawn_egg_mp);
        RegisterHelper.registerItem(MPItems.feces);
        RegisterHelper.registerItem(MPItems.achievement_temp);
        RegisterHelper.registerItem(MPItems.desh_thermal_cloth);
        RegisterHelper.registerItem(MPItems.tier_2_thermal_padding);
        RegisterHelper.registerItem(MPItems.tier_3_thermal_padding);
    }
}