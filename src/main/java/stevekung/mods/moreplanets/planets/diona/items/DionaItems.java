package stevekung.mods.moreplanets.planets.diona.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.items.armor.ItemArmorIllenium;
import stevekung.mods.moreplanets.planets.diona.items.armor.ItemBreathableIllenium;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.*;
import stevekung.mods.moreplanets.utils.items.tools.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;
import stevekung.mods.stevekunglib.utils.EnumToolSpeed;

public class DionaItems
{
    public static Item ILLENIUM_INGOT;
    public static Item SETRORIUM_SHARD;
    public static Item COMPRESSED_ILLENIUM;
    public static Item COMPRESSED_SETRORIUM;
    public static Item INFECTED_CRYSTALLIZED_SHARD;
    public static Item ALIEN_MINER_PART;
    public static Item GLOWING_IRON_INGOT;
    public static Item BLACK_HOLE_FRAGMENTS;
    public static Item ANTI_GRAVITY_FRAGMENTS;

    // Base
    public static Item DIONA_DUNGEON_KEY;
    public static Item INFECTED_CRYSTALLIZED_BOMB;
    public static Item INFECTED_CRYSTALLIZED_SLIMEBALL;
    public static Item INFECTED_CRYSTALLIZED_ARROW;
    public static Item DARK_ENERGY_PEARL;
    public static Item ANTI_GRAVITY_ARROW;

    // Tools
    public static Item ILLENIUM_SWORD;
    public static Item ILLENIUM_SHOVEL;
    public static Item ILLENIUM_PICKAXE;
    public static Item ILLENIUM_AXE;
    public static Item ILLENIUM_HOE;

    // Armor
    public static Item ILLENIUM_HELMET;
    public static Item ILLENIUM_CHESTPLATE;
    public static Item ILLENIUM_LEGGINGS;
    public static Item ILLENIUM_BOOTS;
    public static Item BREATHABLE_ILLENIUM_HELMET;

    // Material
    public static final ToolMaterial ILLENIUM_TOOL = EnumHelper.addToolMaterial("ILLENIUM", 3, 1432, 8.5F, 4.5F, 10);
    public static final ArmorMaterial ILLENIUM_ARMOR = EnumHelper.addArmorMaterial("ILLENIUM", "ILLENIUM", 40, new int[] { 5, 10, 8, 5 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.25F);

    public static void init()
    {
        /**************************************************************/
        /**********************INITIAL BASE STUFF**********************/
        /**************************************************************/

        DionaItems.ILLENIUM_INGOT = new ItemBaseMP("illenium_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        DionaItems.SETRORIUM_SHARD = new ItemBaseMP("setrorium_shard");
        DionaItems.COMPRESSED_ILLENIUM = new ItemCompressedMetal("compressed_illenium").setSortCategory(EnumSortCategoryItem.PLATE);
        DionaItems.COMPRESSED_SETRORIUM = new ItemCompressedMetal("compressed_setrorium").setSortCategory(EnumSortCategoryItem.PLATE);
        DionaItems.INFECTED_CRYSTALLIZED_SHARD = new ItemBaseMP("infected_crystallized_shard");
        DionaItems.ALIEN_MINER_PART = new ItemBaseMP("alien_miner_part");
        DionaItems.GLOWING_IRON_INGOT = new ItemBaseMP("glowing_iron_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        DionaItems.BLACK_HOLE_FRAGMENTS = new ItemBaseMP("black_hole_fragments");
        DionaItems.ANTI_GRAVITY_FRAGMENTS = new ItemAntiGravity("anti_gravity_fragments");

        DionaItems.DIONA_DUNGEON_KEY = new ItemDungeonKeyMP("diona_dungeon_key", 4);
        DionaItems.INFECTED_CRYSTALLIZED_BOMB = new ItemInfectedCrystallizedBomb("infected_crystallized_bomb");
        DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL = new ItemBaseMP("infected_crystallized_slimeball");
        DionaItems.INFECTED_CRYSTALLIZED_ARROW = new ItemArrowMP("infected_crystallized_arrow", ItemArrowMP.ArrowType.INFECTED_CRYSTALLIZED);
        DionaItems.DARK_ENERGY_PEARL = new ItemBaseMP("dark_energy_pearl").setSortCategory(EnumSortCategoryItem.GENERAL);
        DionaItems.ANTI_GRAVITY_ARROW = new ItemArrowMP("anti_gravity_arrow", ItemArrowMP.ArrowType.ANTI_GRAVITY);

        /**************************************************************/
        /**********************INITIAL TOOL STUFF**********************/
        /**************************************************************/

        DionaItems.ILLENIUM_SWORD = new ItemSwordMP("illenium_sword", DionaItems.ILLENIUM_TOOL, DionaItems.COMPRESSED_ILLENIUM);
        DionaItems.ILLENIUM_SHOVEL = new ItemShovelMP("illenium_shovel", DionaItems.ILLENIUM_TOOL, DionaItems.COMPRESSED_ILLENIUM);
        DionaItems.ILLENIUM_PICKAXE = new ItemPickaxeMP("illenium_pickaxe", DionaItems.ILLENIUM_TOOL, DionaItems.COMPRESSED_ILLENIUM);
        DionaItems.ILLENIUM_AXE = new ItemAxeMP("illenium_axe", DionaItems.ILLENIUM_TOOL, DionaItems.COMPRESSED_ILLENIUM, EnumToolSpeed.COMMON);
        DionaItems.ILLENIUM_HOE = new ItemHoeMP("illenium_hoe", DionaItems.ILLENIUM_TOOL, DionaItems.COMPRESSED_ILLENIUM);

        /**************************************************************/
        /*********************INITIAL ARMOR STUFF**********************/
        /**************************************************************/

        DionaItems.ILLENIUM_HELMET = new ItemArmorIllenium("illenium_helmet", DionaItems.ILLENIUM_ARMOR, EntityEquipmentSlot.HEAD);
        DionaItems.ILLENIUM_CHESTPLATE = new ItemArmorIllenium("illenium_chestplate", DionaItems.ILLENIUM_ARMOR, EntityEquipmentSlot.CHEST);
        DionaItems.ILLENIUM_LEGGINGS = new ItemArmorIllenium("illenium_leggings", DionaItems.ILLENIUM_ARMOR, EntityEquipmentSlot.LEGS);
        DionaItems.ILLENIUM_BOOTS = new ItemArmorIllenium("illenium_boots", DionaItems.ILLENIUM_ARMOR, EntityEquipmentSlot.FEET);
        DionaItems.BREATHABLE_ILLENIUM_HELMET = new ItemBreathableIllenium("breathable_illenium_helmet", DionaItems.ILLENIUM_ARMOR, EntityEquipmentSlot.HEAD);

        /**************************************************************/
        /**********************REGISTER STUFF**************************/
        /**************************************************************/

        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_INGOT);
        BlocksItemsRegistry.registerItem(DionaItems.SETRORIUM_SHARD);
        BlocksItemsRegistry.registerItem(DionaItems.COMPRESSED_ILLENIUM);
        BlocksItemsRegistry.registerItem(DionaItems.COMPRESSED_SETRORIUM);
        BlocksItemsRegistry.registerItem(DionaItems.INFECTED_CRYSTALLIZED_SHARD);
        BlocksItemsRegistry.registerItem(DionaItems.ALIEN_MINER_PART);
        BlocksItemsRegistry.registerItem(DionaItems.GLOWING_IRON_INGOT);
        BlocksItemsRegistry.registerItem(DionaItems.BLACK_HOLE_FRAGMENTS);
        BlocksItemsRegistry.registerItem(DionaItems.ANTI_GRAVITY_FRAGMENTS);

        BlocksItemsRegistry.registerItem(DionaItems.INFECTED_CRYSTALLIZED_BOMB);
        BlocksItemsRegistry.registerItem(DionaItems.DIONA_DUNGEON_KEY);
        BlocksItemsRegistry.registerItem(DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL);
        BlocksItemsRegistry.registerItem(DionaItems.INFECTED_CRYSTALLIZED_ARROW);
        BlocksItemsRegistry.registerItem(DionaItems.DARK_ENERGY_PEARL);
        BlocksItemsRegistry.registerItem(DionaItems.ANTI_GRAVITY_ARROW);

        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_SWORD);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_SHOVEL);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_PICKAXE);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_AXE);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_HOE);

        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_HELMET);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_CHESTPLATE);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_LEGGINGS);
        BlocksItemsRegistry.registerItem(DionaItems.ILLENIUM_BOOTS);
        BlocksItemsRegistry.registerItem(DionaItems.BREATHABLE_ILLENIUM_HELMET);

        /**************************************************************/
        /********************HARVEST LEVEL STUFF***********************/
        /**************************************************************/

        BlockUtils.setToolHarvestLevel(DionaItems.ILLENIUM_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        BlockUtils.setToolHarvestLevel(DionaItems.ILLENIUM_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setToolHarvestLevel(DionaItems.ILLENIUM_AXE, EnumHarvestLevel.AXE, 3);

        /**************************************************************/
        /************************FLUID STUFF***************************/
        /**************************************************************/

        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(DionaBlocks.CRYSTALLIZED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(DionaBlocks.CRYSTALLIZED_LAVA_FLUID);
    }
}