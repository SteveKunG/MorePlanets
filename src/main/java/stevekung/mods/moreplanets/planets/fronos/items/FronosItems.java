/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.core.items.ItemDoorMP;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class FronosItems
{
    public static Item fronos_food;
    public static Item strawberry_seed;
    public static Item bearry_egg;
    public static Item cream_ball;
    public static Item pearl;
    public static Item fronos_dungeon_key;
    public static Item candy_cane;
    public static Item fronos_bucket;
    public static Item jelly;
    public static Item fronos_food2;
    public static Item fruits;
    public static Item candy_bow;
    public static Item poison_arrow;
    public static Item golden_seeds;
    public static Item glass_gem_corn;
    public static Item cup;
    public static Item cream_golem;
    public static Item fronos_item;
    public static Item tier7_rocket;
    public static Item coconut_door;
    public static Item maple_door;
    public static Item tier7_rocket_schematic;
    public static Item fruits_juice;
    public static Item tier8_rocket_module;

    public static void init()
    {
        FronosItems.initItems();
        FronosItems.registerItems();
        FronosItems.registerFluidContainer();
    }

    private static void initItems()
    {
        FronosItems.fronos_food = new ItemFronosFood("fronos_food");
        FronosItems.strawberry_seed = new ItemStrawberrySeed("strawberry_seed");
        FronosItems.bearry_egg = new ItemBearryEgg("bearry_egg");
        FronosItems.cream_ball = new ItemCreamBall("cream_ball");
        FronosItems.pearl = new ItemPearl("pearl");
        FronosItems.fronos_dungeon_key = new ItemFronosDungeonKey("fronos_dungeon_key");
        FronosItems.candy_cane = new ItemCandyCane("candy_cane");
        FronosItems.fronos_bucket = new ItemFronosBucket("fronos_bucket");
        FronosItems.jelly = new ItemJelly("jelly");
        FronosItems.fronos_food2 = new ItemCandyFood("fronos_food2");
        FronosItems.fruits = new ItemFruits("fruits");
        FronosItems.candy_bow = new ItemCandyBow("candy_bow");
        FronosItems.poison_arrow = new ItemPoisonArrow("poison_arrow");
        FronosItems.golden_seeds = new ItemGoldenSeeds("golden_seeds");
        FronosItems.glass_gem_corn = new ItemGlassGemCorn("glass_gem_corn_item");
        FronosItems.cup = new ItemCup("cup");
        FronosItems.cream_golem = new ItemCreamGolem("cream_golem");
        FronosItems.fronos_item = new ItemFronosBasic("fronos_item");
        FronosItems.tier7_rocket = new ItemTier7Rocket("tier_7_rocket");
        FronosItems.tier7_rocket_schematic = new ItemTier7Schematic("tier_7_rocket_schematic");
        FronosItems.coconut_door = new ItemDoorMP("coconut_door", DoorType.COCONUT);
        FronosItems.maple_door = new ItemDoorMP("red_maple_door", DoorType.MAPLE);
        FronosItems.fruits_juice = new ItemFruitsJuice("fruits_juice");
        FronosItems.tier8_rocket_module = new ItemTier8RocketModule("tier_8_rocket_module");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(FronosItems.fronos_food);
        RegisterHelper.registerItem(FronosItems.fronos_food2);
        RegisterHelper.registerItem(FronosItems.candy_cane);
        RegisterHelper.registerItem(FronosItems.fruits);
        RegisterHelper.registerItem(FronosItems.glass_gem_corn);
        RegisterHelper.registerItem(FronosItems.jelly);
        RegisterHelper.registerItem(FronosItems.fruits_juice);
        RegisterHelper.registerItem(FronosItems.cream_ball);
        RegisterHelper.registerItem(FronosItems.strawberry_seed);
        RegisterHelper.registerItem(FronosItems.golden_seeds);
        RegisterHelper.registerItem(FronosItems.pearl);
        RegisterHelper.registerItem(FronosItems.poison_arrow);
        RegisterHelper.registerItem(FronosItems.bearry_egg);
        RegisterHelper.registerItem(FronosItems.cream_golem);
        RegisterHelper.registerItem(FronosItems.fronos_item);
        RegisterHelper.registerItem(FronosItems.tier8_rocket_module);
        RegisterHelper.registerItem(FronosItems.candy_bow);
        RegisterHelper.registerItem(FronosItems.fronos_bucket);
        RegisterHelper.registerItem(FronosItems.coconut_door);
        RegisterHelper.registerItem(FronosItems.maple_door);
        RegisterHelper.registerItem(FronosItems.cup);
        RegisterHelper.registerItem(FronosItems.tier7_rocket);
        RegisterHelper.registerItem(FronosItems.tier7_rocket_schematic);
        RegisterHelper.registerItem(FronosItems.fronos_dungeon_key);

        OreDictionary.registerOre("blackDiamond", new ItemStack(FronosItems.fronos_item, 1, 2));
        OreDictionary.registerOre("ingotIridium", new ItemStack(FronosItems.fronos_item, 1, 3));
        OreDictionary.registerOre("compressedBlackDiamond", new ItemStack(FronosItems.fronos_item, 1, 4));
        OreDictionary.registerOre("compressedIridium", new ItemStack(FronosItems.fronos_item, 1, 5));
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(FronosBlocks.coconut_milk_fluid, new ItemStack(FronosItems.fronos_bucket, 1, 0), new ItemStack(Items.bucket, 1, 0));
        RegisterHelper.registerFluidContainer(FronosBlocks.mineral_water_fluid, new ItemStack(FronosItems.fronos_bucket, 1, 1), new ItemStack(Items.bucket, 1, 0));
        RegisterHelper.registerFluidContainer(FronosBlocks.ovantine_fluid, new ItemStack(FronosItems.fronos_bucket, 1, 2), new ItemStack(Items.bucket, 1, 0));
        RegisterHelper.registerFluidContainer(FronosBlocks.tea_fluid, new ItemStack(FronosItems.fronos_bucket, 1, 3), new ItemStack(Items.bucket, 1, 0));
        RegisterHelper.registerFluidContainer(FronosBlocks.caramel_fluid, new ItemStack(FronosItems.fronos_bucket, 1, 4), new ItemStack(Items.bucket, 1, 0));
    }
}