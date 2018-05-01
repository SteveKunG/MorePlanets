package stevekung.mods.moreplanets.module.planets.chalos.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.armor.ItemArmorDiremsium;
import stevekung.mods.moreplanets.module.planets.chalos.items.armor.ItemBreathableDiremsium;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.*;
import stevekung.mods.moreplanets.utils.items.tools.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;
import stevekung.mods.stevekunglib.utils.EnumToolSpeed;

public class ChalosItems
{
    public static Item DIREMSIUM_INGOT;
    public static Item ZYPTORIUM_INGOT;
    public static Item COMPRESSED_DIREMSIUM;
    public static Item COMPRESSED_ZYPTORIUM;

    public static Item CHEESE_MILK_CURD;
    public static Item RAW_CHEESE_BEEF;
    public static Item COOKED_CHEESE_BEEF;
    public static Item CHEESE_SPORE_BERRY;

    // Base
    public static Item CHEESE_SLIMEBALL;
    public static Item CHALOS_DUNGEON_KEY;
    public static Item CHEESE_SPORE;
    public static Item CHEESE_SPORE_SEED;
    public static Item CHEESE_SPORE_DOOR;

    // Tools
    public static Item DIREMSIUM_SWORD;
    public static Item DIREMSIUM_SHOVEL;
    public static Item DIREMSIUM_PICKAXE;
    public static Item DIREMSIUM_AXE;
    public static Item DIREMSIUM_HOE;
    public static Item CHEESE_SPORE_WOOD_SWORD;
    public static Item CHEESE_SPORE_WOOD_SHOVEL;
    public static Item CHEESE_SPORE_WOOD_PICKAXE;
    public static Item CHEESE_SPORE_WOOD_AXE;
    public static Item CHEESE_SPORE_WOOD_HOE;

    // Armor
    public static Item DIREMSIUM_HELMET;
    public static Item DIREMSIUM_CHESTPLATE;
    public static Item DIREMSIUM_LEGGINGS;
    public static Item DIREMSIUM_BOOTS;
    public static Item BREATHABLE_DIREMSIUM_HELMET;

    // Material
    public static final ToolMaterial DIREMSIUM_TOOL = EnumHelper.addToolMaterial("DIREMSIUM", 3, 1532, 9.0F, 5.0F, 10);
    public static final ArmorMaterial DIREMSIUM_ARMOR = EnumHelper.addArmorMaterial("DIREMSIUM", "DIREMSIUM", 48, new int[] { 7, 12, 10, 7 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);

    public static void init()
    {
        /**************************************************************/
        /**********************INITIAL BASE STUFF**********************/
        /**************************************************************/

        ChalosItems.DIREMSIUM_INGOT = new ItemBaseMP("diremsium_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        ChalosItems.ZYPTORIUM_INGOT = new ItemBaseMP("zyptorium_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        ChalosItems.COMPRESSED_DIREMSIUM = new ItemCompressedMetal("compressed_diremsium").setSortCategory(EnumSortCategoryItem.PLATE);
        ChalosItems.COMPRESSED_ZYPTORIUM = new ItemCompressedMetal("compressed_zyptorium").setSortCategory(EnumSortCategoryItem.PLATE);

        ChalosItems.CHEESE_MILK_CURD = new ItemAllFood("cheese_milk_curd", ItemAllFood.ItemType.CHEESE_MILK_CURD);
        ChalosItems.RAW_CHEESE_BEEF = new ItemAllFood("raw_cheese_beef", ItemAllFood.ItemType.RAW_CHEESE_BEEF);
        ChalosItems.COOKED_CHEESE_BEEF = new ItemAllFood("cooked_cheese_beef", ItemAllFood.ItemType.COOKED_CHEESE_BEEF);
        ChalosItems.CHEESE_SPORE_BERRY = new ItemAllFood("cheese_spore_berry", ItemAllFood.ItemType.CHEESE_SPORE_BERRY);

        ChalosItems.CHEESE_SLIMEBALL = new ItemBaseMP("cheese_slimeball");
        ChalosItems.CHALOS_DUNGEON_KEY = new ItemDungeonKeyMP("chalos_dungeon_key", 5);
        ChalosItems.CHEESE_SPORE = new ItemCheeseSpore("cheese_spore_item");
        ChalosItems.CHEESE_SPORE_SEED = new ItemCheeseSporeSeed("cheese_spore_seed");
        ChalosItems.CHEESE_SPORE_DOOR = new ItemDoorMP("cheese_spore_door", ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK);

        /**************************************************************/
        /**********************INITIAL TOOL STUFF**********************/
        /**************************************************************/

        ChalosItems.DIREMSIUM_SWORD = new ItemSwordMP("diremsium_sword", ChalosItems.DIREMSIUM_TOOL, ChalosItems.COMPRESSED_DIREMSIUM);
        ChalosItems.DIREMSIUM_SHOVEL = new ItemShovelMP("diremsium_shovel", ChalosItems.DIREMSIUM_TOOL, ChalosItems.COMPRESSED_DIREMSIUM);
        ChalosItems.DIREMSIUM_PICKAXE = new ItemPickaxeMP("diremsium_pickaxe", ChalosItems.DIREMSIUM_TOOL, ChalosItems.COMPRESSED_DIREMSIUM);
        ChalosItems.DIREMSIUM_AXE = new ItemAxeMP("diremsium_axe", ChalosItems.DIREMSIUM_TOOL, ChalosItems.COMPRESSED_DIREMSIUM, EnumToolSpeed.COMMON);
        ChalosItems.DIREMSIUM_HOE = new ItemHoeMP("diremsium_hoe", ChalosItems.DIREMSIUM_TOOL, ChalosItems.COMPRESSED_DIREMSIUM);
        ChalosItems.CHEESE_SPORE_WOOD_SWORD = new ItemSwordMP("cheese_spore_wood_sword", ToolMaterial.WOOD, ChalosBlocks.CHEESE_SPORE_PLANKS);
        ChalosItems.CHEESE_SPORE_WOOD_SHOVEL = new ItemShovelMP("cheese_spore_wood_shovel", ToolMaterial.WOOD, ChalosBlocks.CHEESE_SPORE_PLANKS);
        ChalosItems.CHEESE_SPORE_WOOD_PICKAXE = new ItemPickaxeMP("cheese_spore_wood_pickaxe", ToolMaterial.WOOD, ChalosBlocks.CHEESE_SPORE_PLANKS);
        ChalosItems.CHEESE_SPORE_WOOD_AXE = new ItemAxeMP("cheese_spore_wood_axe", ToolMaterial.WOOD, ChalosBlocks.CHEESE_SPORE_PLANKS, EnumToolSpeed.WOOD);
        ChalosItems.CHEESE_SPORE_WOOD_HOE = new ItemHoeMP("cheese_spore_wood_hoe", ToolMaterial.WOOD, ChalosBlocks.CHEESE_SPORE_PLANKS);

        /**************************************************************/
        /*********************INITIAL ARMOR STUFF**********************/
        /**************************************************************/

        ChalosItems.DIREMSIUM_HELMET = new ItemArmorDiremsium("diremsium_helmet", ChalosItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.HEAD);
        ChalosItems.DIREMSIUM_CHESTPLATE = new ItemArmorDiremsium("diremsium_chestplate", ChalosItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.CHEST);
        ChalosItems.DIREMSIUM_LEGGINGS = new ItemArmorDiremsium("diremsium_leggings", ChalosItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.LEGS);
        ChalosItems.DIREMSIUM_BOOTS = new ItemArmorDiremsium("diremsium_boots", ChalosItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.FEET);
        ChalosItems.BREATHABLE_DIREMSIUM_HELMET = new ItemBreathableDiremsium("breathable_diremsium_helmet", ChalosItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.HEAD);

        /**************************************************************/
        /**********************REGISTER STUFF**************************/
        /**************************************************************/

        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_INGOT);
        BlocksItemsRegistry.registerItem(ChalosItems.ZYPTORIUM_INGOT);
        BlocksItemsRegistry.registerItem(ChalosItems.COMPRESSED_DIREMSIUM);
        BlocksItemsRegistry.registerItem(ChalosItems.COMPRESSED_ZYPTORIUM);

        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_MILK_CURD);
        BlocksItemsRegistry.registerItem(ChalosItems.RAW_CHEESE_BEEF);
        BlocksItemsRegistry.registerItem(ChalosItems.COOKED_CHEESE_BEEF);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_BERRY);

        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SLIMEBALL);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_SEED);
        BlocksItemsRegistry.registerItem(ChalosItems.CHALOS_DUNGEON_KEY);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_DOOR);

        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_SWORD);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_SHOVEL);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_PICKAXE);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_AXE);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_HOE);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_WOOD_SWORD);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_WOOD_SHOVEL);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_WOOD_PICKAXE);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_WOOD_AXE);
        BlocksItemsRegistry.registerItem(ChalosItems.CHEESE_SPORE_WOOD_HOE);

        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_HELMET);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_CHESTPLATE);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_LEGGINGS);
        BlocksItemsRegistry.registerItem(ChalosItems.DIREMSIUM_BOOTS);
        BlocksItemsRegistry.registerItem(ChalosItems.BREATHABLE_DIREMSIUM_HELMET);

        /**************************************************************/
        /********************HARVEST LEVEL STUFF***********************/
        /**************************************************************/

        BlockUtils.setToolHarvestLevel(ChalosItems.DIREMSIUM_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        BlockUtils.setToolHarvestLevel(ChalosItems.DIREMSIUM_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setToolHarvestLevel(ChalosItems.DIREMSIUM_AXE, EnumHarvestLevel.AXE, 3);

        /**************************************************************/
        /************************FLUID STUFF***************************/
        /**************************************************************/

        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(ChalosBlocks.CHEESE_MILK_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(ChalosBlocks.GASEOUS_CHEESE_MILK);

        /**************************************************************/
        /************************OTHER STUFF***************************/
        /**************************************************************/

        ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK.setDoorItem(ChalosItems.CHEESE_SPORE_DOOR);
    }
}