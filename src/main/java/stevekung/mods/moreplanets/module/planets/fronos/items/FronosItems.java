package stevekung.mods.moreplanets.module.planets.fronos.items;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class FronosItems
{
    // Base
    public static Item FRONOS_ITEM;
    public static Item FRONOS_FRUITS;
    public static Item FRONOS_FOOD;
    public static Item JELLY;
    public static Item CANDY_CANE;

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

        FronosItems.FRONOS_ITEM = new ItemFronos("fronos_item");
        FronosItems.FRONOS_FRUITS = new ItemFronosFruits("fronos_fruits");
        FronosItems.FRONOS_FOOD = new ItemFronosFood("fronos_food");
        FronosItems.JELLY = new ItemJelly("jelly");
        FronosItems.CANDY_CANE = new ItemCandyCane("candy_cane");

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

        CommonRegisterHelper.registerItem(FronosItems.FRONOS_ITEM);
        CommonRegisterHelper.registerItem(FronosItems.FRONOS_FRUITS);
        CommonRegisterHelper.registerItem(FronosItems.FRONOS_FOOD);
        CommonRegisterHelper.registerItem(FronosItems.JELLY);
        CommonRegisterHelper.registerItem(FronosItems.CANDY_CANE);

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
        /********************ORE DICTIONARY STUFF**********************/
        /**************************************************************/

        /**************************************************************/
        /********************FLUID CONTAINER STUFF*********************/
        /**************************************************************/

    }
}