/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class PlutoItems
{
    public static Item pluto_item;
    public static Item space_potato;
    public static Item xeonium_dust;
    public static Item gravity_boots;

    public static ArmorMaterial gravity = EnumHelper.addArmorMaterial("gravity", 0, new int[] { 0, 0, 0, 0 }, 0);

    public static void init()
    {
        PlutoItems.initItems();
        PlutoItems.registerItems();
    }

    private static void initItems()
    {
        PlutoItems.pluto_item = new ItemBasicPluto("pluto_item");
        PlutoItems.space_potato = new ItemSpacePotato("space_potato");
        PlutoItems.xeonium_dust = new ItemMorePlanet("xeonium_dust", "pluto");
        PlutoItems.gravity_boots = new ItemGravityBoots("gravity_boots", PlutoItems.gravity, 7, 3);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(PlutoItems.pluto_item);
        RegisterHelper.registerItem(PlutoItems.xeonium_dust);
        RegisterHelper.registerItem(PlutoItems.space_potato);
        RegisterHelper.registerItem(PlutoItems.gravity_boots);
    }
}