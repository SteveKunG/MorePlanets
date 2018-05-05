package stevekung.mods.moreplanets.planets.fronos.items;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ItemAllFood;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;

public class FronosItems
{
    public static Item EXTRAILONITE_INGOT;
    public static Item COMPRESSED_EXTRAILONITE;

    public static Item STRAWBERRY;
    public static Item GIANT_BLUEBERRY;

    public static Item CHOCOLATE_BAR;
    public static Item JELLY_BEANS;
    public static Item MARSHMALLOW;
    public static Item COOKED_MARSHMALLOW;

    public static Item RED_CANDY_CANE_STICK;
    public static Item GREEN_CANDY_CANE_STICK;
    public static Item BLUE_CANDY_CANE_STICK;
    public static Item ORANGE_CANDY_CANE_STICK;
    public static Item PINK_CANDY_CANE_STICK;
    public static Item YELLOW_CANDY_CANE_STICK;
    public static Item PURPLE_CANDY_CANE_STICK;
    public static Item RAINBOW_CANDY_CANE_STICK;

    public static Item GRAPE_JELLY;
    public static Item RASPBERRY_JELLY;
    public static Item STRAWBERRY_JELLY;
    public static Item BERRY_JELLY;
    public static Item LIME_JELLY;
    public static Item ORANGE_JELLY;
    public static Item GREEN_JELLY;
    public static Item LEMON_JELLY;

    //    // Tools
    //    public static Item ILLENIUM_SWORD;
    //    public static Item ILLENIUM_SHOVEL;
    //    public static Item ILLENIUM_PICKAXE;
    //    public static Item ILLENIUM_AXE;
    //    public static Item ILLENIUM_HOE;
    //
    //    // Armor
    //    public static Item ILLENIUM_HELMET;
    //    public static Item ILLENIUM_CHESTPLATE;
    //    public static Item ILLENIUM_LEGGINGS;
    //    public static Item ILLENIUM_BOOTS;
    //    public static Item BREATHABLE_ILLENIUM_HELMET;
    //
    //    // Material
    //    public static ToolMaterial ILLENIUM_TOOL = EnumHelper.addToolMaterial("ILLENIUM", 3, 1432, 8.5F, 4.5F, 10);
    //    public static ArmorMaterial ILLENIUM_ARMOR = EnumHelper.addArmorMaterial("ILLENIUM", "ILLENIUM", 40, new int[] { 5, 10, 8, 5 }, 12);

    public static void init()
    {
        /**************************************************************/
        /**********************INITIAL BASE STUFF**********************/
        /**************************************************************/

        FronosItems.EXTRAILONITE_INGOT = new ItemBaseMP("extrailonite_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        FronosItems.COMPRESSED_EXTRAILONITE = new ItemBaseMP("compressed_extrailonite").setSortCategory(EnumSortCategoryItem.PLATE);

        FronosItems.STRAWBERRY = new ItemAllFood("strawberry", ItemAllFood.ItemType.STRAWBERRY);
        FronosItems.GIANT_BLUEBERRY = new ItemAllFood("giant_blueberry", ItemAllFood.ItemType.GIANT_BLUEBERRY);

        FronosItems.CHOCOLATE_BAR = new ItemAllFood("chocolate_bar", ItemAllFood.ItemType.CHOCOLATE_BAR);
        FronosItems.JELLY_BEANS = new ItemAllFood("jelly_beans", ItemAllFood.ItemType.JELLY_BEANS);
        FronosItems.MARSHMALLOW = new ItemAllFood("marshmallow", ItemAllFood.ItemType.MARSHMALLOW);
        FronosItems.COOKED_MARSHMALLOW = new ItemAllFood("cooked_marshmallow", ItemAllFood.ItemType.COOKED_MARSHMALLOW);

        FronosItems.RED_CANDY_CANE_STICK = new ItemAllFood("red_candy_cane_stick", ItemAllFood.ItemType.RED_CANDY_CANE_STICK);
        FronosItems.GREEN_CANDY_CANE_STICK = new ItemAllFood("green_candy_cane_stick", ItemAllFood.ItemType.GREEN_CANDY_CANE_STICK);
        FronosItems.BLUE_CANDY_CANE_STICK = new ItemAllFood("blue_candy_cane_stick", ItemAllFood.ItemType.BLUE_CANDY_CANE_STICK);
        FronosItems.ORANGE_CANDY_CANE_STICK = new ItemAllFood("orange_candy_cane_stick", ItemAllFood.ItemType.ORANGE_CANDY_CANE_STICK);
        FronosItems.PINK_CANDY_CANE_STICK = new ItemAllFood("pink_candy_cane_stick", ItemAllFood.ItemType.PINK_CANDY_CANE_STICK);
        FronosItems.YELLOW_CANDY_CANE_STICK = new ItemAllFood("yellow_candy_cane_stick", ItemAllFood.ItemType.YELLOW_CANDY_CANE_STICK);
        FronosItems.PURPLE_CANDY_CANE_STICK = new ItemAllFood("purple_candy_cane_stick", ItemAllFood.ItemType.PURPLE_CANDY_CANE_STICK);
        FronosItems.RAINBOW_CANDY_CANE_STICK = new ItemAllFood("rainbow_candy_cane_stick", ItemAllFood.ItemType.RAINBOW_CANDY_CANE_STICK);

        FronosItems.GRAPE_JELLY = new ItemAllFood("grape_jelly", ItemAllFood.ItemType.GRAPE_JELLY);
        FronosItems.RASPBERRY_JELLY = new ItemAllFood("raspberry_jelly", ItemAllFood.ItemType.RASPBERRY_JELLY);
        FronosItems.STRAWBERRY_JELLY = new ItemAllFood("strawberry_jelly", ItemAllFood.ItemType.STRAWBERRY_JELLY);
        FronosItems.BERRY_JELLY = new ItemAllFood("berry_jelly", ItemAllFood.ItemType.BERRY_JELLY);
        FronosItems.LIME_JELLY = new ItemAllFood("lime_jelly", ItemAllFood.ItemType.LIME_JELLY);
        FronosItems.ORANGE_JELLY = new ItemAllFood("orange_jelly", ItemAllFood.ItemType.ORANGE_JELLY);
        FronosItems.GREEN_JELLY = new ItemAllFood("green_jelly", ItemAllFood.ItemType.GREEN_JELLY);
        FronosItems.LEMON_JELLY = new ItemAllFood("lemon_jelly", ItemAllFood.ItemType.LEMON_JELLY);

        /**************************************************************/
        /**********************INITIAL TOOL STUFF**********************/
        /**************************************************************/

        //        FronosItems.ILLENIUM_SWORD = new ItemSwordMP("illenium_sword", FronosItems.ILLENIUM_TOOL, FronosItems.DIONA_ITEM, 2);
        //        FronosItems.ILLENIUM_SHOVEL = new ItemShovelMP("illenium_shovel", FronosItems.ILLENIUM_TOOL, FronosItems.DIONA_ITEM, 2);
        //        FronosItems.ILLENIUM_PICKAXE = new ItemPickaxeMP("illenium_pickaxe", FronosItems.ILLENIUM_TOOL, FronosItems.DIONA_ITEM, 2);
        //        FronosItems.ILLENIUM_AXE = new ItemAxeMP("illenium_axe", FronosItems.ILLENIUM_TOOL, FronosItems.DIONA_ITEM, 2);
        //        FronosItems.ILLENIUM_HOE = new ItemHoeMP("illenium_hoe", FronosItems.ILLENIUM_TOOL, FronosItems.DIONA_ITEM, 2);

        /**************************************************************/
        /*********************INITIAL ARMOR STUFF**********************/
        /**************************************************************/

        //        FronosItems.ILLENIUM_HELMET = new ItemArmorIllenium("illenium_helmet", FronosItems.ILLENIUM_ARMOR, 0);
        //        FronosItems.ILLENIUM_CHESTPLATE = new ItemArmorIllenium("illenium_chestplate", FronosItems.ILLENIUM_ARMOR, 1);
        //        FronosItems.ILLENIUM_LEGGINGS = new ItemArmorIllenium("illenium_leggings", FronosItems.ILLENIUM_ARMOR, 2);
        //        FronosItems.ILLENIUM_BOOTS = new ItemArmorIllenium("illenium_boots", FronosItems.ILLENIUM_ARMOR, 3);
        //        FronosItems.BREATHABLE_ILLENIUM_HELMET = new ItemBreathableIllenium("breathable_illenium_helmet", FronosItems.ILLENIUM_ARMOR, 0);

        /**************************************************************/
        /**********************REGISTER STUFF**************************/
        /**************************************************************/

        BlocksItemsRegistry.registerItem(FronosItems.EXTRAILONITE_INGOT);
        BlocksItemsRegistry.registerItem(FronosItems.COMPRESSED_EXTRAILONITE);

        BlocksItemsRegistry.registerItem(FronosItems.STRAWBERRY);
        BlocksItemsRegistry.registerItem(FronosItems.GIANT_BLUEBERRY);

        BlocksItemsRegistry.registerItem(FronosItems.CHOCOLATE_BAR);
        BlocksItemsRegistry.registerItem(FronosItems.JELLY_BEANS);
        BlocksItemsRegistry.registerItem(FronosItems.MARSHMALLOW);
        BlocksItemsRegistry.registerItem(FronosItems.COOKED_MARSHMALLOW);

        BlocksItemsRegistry.registerItem(FronosItems.RED_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.GREEN_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.BLUE_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.ORANGE_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.PINK_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.YELLOW_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.PURPLE_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(FronosItems.RAINBOW_CANDY_CANE_STICK);

        BlocksItemsRegistry.registerItem(FronosItems.GRAPE_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.RASPBERRY_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.STRAWBERRY_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.BERRY_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.LIME_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.ORANGE_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.GREEN_JELLY);
        BlocksItemsRegistry.registerItem(FronosItems.LEMON_JELLY);

        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_SWORD);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_SHOVEL);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_PICKAXE);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_AXE);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_HOE);
        //
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_HELMET);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_CHESTPLATE);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_LEGGINGS);
        //        CommonRegisterHelper.registerItem(FronosItems.ILLENIUM_BOOTS);
        //        CommonRegisterHelper.registerItem(FronosItems.BREATHABLE_ILLENIUM_HELMET);

        /**************************************************************/
        /********************HARVEST LEVEL STUFF***********************/
        /**************************************************************/

        //        CommonRegisterHelper.setToolHarvestLevel(FronosItems.ILLENIUM_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        //        CommonRegisterHelper.setToolHarvestLevel(FronosItems.ILLENIUM_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        //        CommonRegisterHelper.setToolHarvestLevel(FronosItems.ILLENIUM_AXE, EnumHarvestLevel.AXE, 3);

        /**************************************************************/
        /********************FLUID CONTAINER STUFF*********************/
        /**************************************************************/

    }
}