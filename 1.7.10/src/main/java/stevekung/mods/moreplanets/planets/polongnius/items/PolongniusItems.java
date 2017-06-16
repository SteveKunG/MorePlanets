/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class PolongniusItems
{
    public static Item polongnius_item;
    public static Item purple_crystal_solar_module;
    public static Item polongnius_food;
    public static Item polongnius_dungeon_key;
    public static Item cheese_slimeball;
    public static Item tier5_rocket;
    public static Item tier6_rocket_module;
    public static Item tier5_rocket_schematic;
    public static Item cheese_of_milk_bucket;
    public static Item polongnius_meteor_chunk;

    public static void init()
    {
        PolongniusItems.initItems();
        PolongniusItems.registerItems();
        PolongniusItems.registerFluidContainer();
    }

    private static void initItems()
    {
        PolongniusItems.polongnius_item = new ItemBasicPolongnius("polongnius_item");
        PolongniusItems.purple_crystal_solar_module = new ItemPurpleCrystalSolarModule("purple_crystal_solar_module");
        PolongniusItems.polongnius_food = new ItemCheeseFood("polongnius_food");
        PolongniusItems.polongnius_dungeon_key = new ItemPolongniusDungeonKey("polongnius_dungeon_key");
        PolongniusItems.cheese_slimeball = new ItemCheeseSlimeball("cheese_slimeball");
        PolongniusItems.tier5_rocket = new ItemTier5Rocket("tier_5_rocket");
        PolongniusItems.tier6_rocket_module = new ItemTier6RocketModule("tier_6_rocket_module");
        PolongniusItems.tier5_rocket_schematic = new ItemTier5Schematic("tier_5_rocket_schematic");
        PolongniusItems.cheese_of_milk_bucket = new ItemCheeseOfMilkBucket("cheese_of_milk_bucket");
        PolongniusItems.polongnius_meteor_chunk = new ItemPolongniusMeteorChunk("polongnius_meteor_chunk");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(PolongniusItems.polongnius_item);
        RegisterHelper.registerItem(PolongniusItems.purple_crystal_solar_module);
        RegisterHelper.registerItem(PolongniusItems.tier5_rocket_schematic);
        RegisterHelper.registerItem(PolongniusItems.tier6_rocket_module);
        RegisterHelper.registerItem(PolongniusItems.cheese_slimeball);
        RegisterHelper.registerItem(PolongniusItems.polongnius_food);
        RegisterHelper.registerItem(PolongniusItems.cheese_of_milk_bucket);
        RegisterHelper.registerItem(PolongniusItems.tier5_rocket);
        RegisterHelper.registerItem(PolongniusItems.polongnius_meteor_chunk);
        RegisterHelper.registerItem(PolongniusItems.polongnius_dungeon_key);

        OreDictionary.registerOre("ingotPolongniusMeteoricIron", new ItemStack(PolongniusItems.polongnius_item, 1, 4));
        OreDictionary.registerOre("ingotPalladium", new ItemStack(PolongniusItems.polongnius_item, 1, 5));
        OreDictionary.registerOre("compressedPolongniusMeteoricIron", new ItemStack(PolongniusItems.polongnius_item, 1, 6));
        OreDictionary.registerOre("compressedPalladium", new ItemStack(PolongniusItems.polongnius_item, 1, 7));
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(PolongniusBlocks.cheese_of_milk_fluid, new ItemStack(PolongniusItems.cheese_of_milk_bucket), new ItemStack(Items.bucket));
    }
}