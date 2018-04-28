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
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;
import stevekung.mods.moreplanets.util.items.ItemDoorMP;
import stevekung.mods.moreplanets.util.items.ItemDungeonKeyMP;
import stevekung.mods.moreplanets.util.items.tools.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;
import stevekung.mods.stevekunglib.utils.EnumToolSpeed;

public class ChalosItems
{
    // Base
    public static Item CHALOS_ITEM;
    public static Item CHEESE_FOOD;
    public static Item CHEESE_SLIMEBALL;
    public static Item CHALOS_DUNGEON_KEY;
    public static Item TIER_5_ROCKET_PART;
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
    public static ToolMaterial DIREMSIUM_TOOL = EnumHelper.addToolMaterial("DIREMSIUM", 3, 1532, 9.0F, 5.0F, 10);
    public static ArmorMaterial DIREMSIUM_ARMOR = EnumHelper.addArmorMaterial("DIREMSIUM", "DIREMSIUM", 48, new int[] { 7, 12, 10, 7 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);

    public static void init()
    {
        /**************************************************************/
        /**********************INITIAL BASE STUFF**********************/
        /**************************************************************/

        ChalosItems.CHALOS_ITEM = new ItemChalos("chalos_item");
        ChalosItems.CHEESE_FOOD = new ItemCheeseFood("cheese_food");
        ChalosItems.CHEESE_SLIMEBALL = new ItemBaseMP("cheese_slimeball");
        ChalosItems.TIER_5_ROCKET_PART = new ItemBaseMP("tier_5_rocket_part").setSortCategory(EnumSortCategoryItem.HEAVY_PLATE);
        ChalosItems.CHALOS_DUNGEON_KEY = new ItemDungeonKeyMP("chalos_dungeon_key", 5);
        ChalosItems.CHEESE_SPORE = new ItemCheeseSpore("cheese_spore_item");
        ChalosItems.CHEESE_SPORE_SEED = new ItemCheeseSporeSeed("cheese_spore_seed");
        ChalosItems.CHEESE_SPORE_DOOR = new ItemDoorMP("cheese_spore_door", ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK);

        /**************************************************************/
        /**********************INITIAL TOOL STUFF**********************/
        /**************************************************************/

        ChalosItems.DIREMSIUM_SWORD = new ItemSwordMP("diremsium_sword", ChalosItems.DIREMSIUM_TOOL, ChalosItems.CHALOS_ITEM, 2);
        ChalosItems.DIREMSIUM_SHOVEL = new ItemShovelMP("diremsium_shovel", ChalosItems.DIREMSIUM_TOOL, ChalosItems.CHALOS_ITEM, 2);
        ChalosItems.DIREMSIUM_PICKAXE = new ItemPickaxeMP("diremsium_pickaxe", ChalosItems.DIREMSIUM_TOOL, ChalosItems.CHALOS_ITEM, 2);
        ChalosItems.DIREMSIUM_AXE = new ItemAxeMP("diremsium_axe", ChalosItems.DIREMSIUM_TOOL, ChalosItems.CHALOS_ITEM, 2, EnumToolSpeed.COMMON);
        ChalosItems.DIREMSIUM_HOE = new ItemHoeMP("diremsium_hoe", ChalosItems.DIREMSIUM_TOOL, ChalosItems.CHALOS_ITEM, 2);
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

        CommonRegisterHelper.registerItem(ChalosItems.CHALOS_ITEM);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_FOOD);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SLIMEBALL);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_SEED);
        CommonRegisterHelper.registerItem(ChalosItems.TIER_5_ROCKET_PART);
        CommonRegisterHelper.registerItem(ChalosItems.CHALOS_DUNGEON_KEY);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_DOOR);

        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_SWORD);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_SHOVEL);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_PICKAXE);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_AXE);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_HOE);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_WOOD_SWORD);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_WOOD_SHOVEL);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_WOOD_PICKAXE);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_WOOD_AXE);
        CommonRegisterHelper.registerItem(ChalosItems.CHEESE_SPORE_WOOD_HOE);

        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_HELMET);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_CHESTPLATE);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_LEGGINGS);
        CommonRegisterHelper.registerItem(ChalosItems.DIREMSIUM_BOOTS);
        CommonRegisterHelper.registerItem(ChalosItems.BREATHABLE_DIREMSIUM_HELMET);

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