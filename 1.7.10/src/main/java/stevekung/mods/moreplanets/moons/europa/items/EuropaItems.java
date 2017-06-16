/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.core.items.ItemDoorMP;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;

public class EuropaItems
{
    public static Item europa_water_bucket;
    public static Item europa_apple;
    public static Item europa_door;
    public static Item europa_food;
    public static Item europa_gunpowder;

    public static void init()
    {
        EuropaItems.initItems();
        EuropaItems.registerItems();
        EuropaItems.registerFluidContainer();
    }

    private static void initItems()
    {
        EuropaItems.europa_water_bucket = new ItemEuropaWaterBucket("europa_water_bucket");
        EuropaItems.europa_apple = new ItemEuropaApple("europa_apple");
        EuropaItems.europa_door = new ItemDoorMP("europa_door", DoorType.EUROPA);
        EuropaItems.europa_food = new ItemEuropaFood("europa_food");
        EuropaItems.europa_gunpowder = new ItemMorePlanet("europa_gunpowder", "europa");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(EuropaItems.europa_water_bucket);
        RegisterHelper.registerItem(EuropaItems.europa_apple);
        RegisterHelper.registerItem(EuropaItems.europa_door);
        RegisterHelper.registerItem(EuropaItems.europa_food);
        RegisterHelper.registerItem(EuropaItems.europa_gunpowder);
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(EuropaBlocks.europa_water_fluid, new ItemStack(EuropaItems.europa_water_bucket), new ItemStack(Items.bucket));
    }
}