package stevekung.mods.moreplanets.planets.nibiru.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.armor.ItemArmorMultalicCrystal;
import stevekung.mods.moreplanets.planets.nibiru.items.armor.ItemBreathableMultalicCrystal;
import stevekung.mods.moreplanets.planets.nibiru.items.tools.*;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.*;
import stevekung.mods.moreplanets.utils.items.tools.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;
import stevekung.mods.stevekunglib.utils.EnumToolSpeed;

public class NibiruItems
{
    public static Item INFERUMITE_CRYSTAL;
    public static Item MULTALIC_CRYSTAL_PIECES;
    public static Item INFECTED_COAL;
    public static Item SHLIME_TAIL;
    public static Item INFECTED_CHARCOAL;

    public static Item INFECTED_APPLE;
    public static Item INFECTED_GOLDEN_APPLE;
    public static Item ENCHANTED_INFECTED_GOLDEN_APPLE;
    public static Item INFECTED_MELON;
    public static Item ALIEN_BERRY;
    public static Item GOLDEN_ALIEN_BERRY;
    public static Item TERRABERRY;

    public static Item INFECTED_PRISMARINE_SHARD;
    public static Item INFECTED_PRISMARINE_CRYSTALS;

    public static Item RAW_SHLIME_MEAT;
    public static Item COOKED_SHLIME_MEAT;

    // Base
    public static Item INFECTED_WHEAT_SEEDS;
    public static Item INFECTED_OAK_DOOR;
    public static Item ALIEN_BERRY_OAK_DOOR;
    public static Item INFECTED_WHEAT;
    public static Item INFECTED_CLAY_BALL;
    public static Item INFECTED_SNOWBALL;
    public static Item NIBIRU_DUNGEON_KEY;
    public static Item NIBIRU_DUNGEON_KEY_BOW;
    public static Item NIBIRU_DUNGEON_KEY_BLADE;
    public static Item INFECTED_SUGAR_CANE;
    public static Item INFECTED_EGG;
    public static Item INFECTED_ARROW;
    public static Item INFECTED_MELON_SEEDS;
    public static Item VEIN_EYE;
    public static Item WASTE_ROD_PICKER;
    public static Item NUCLEAR_WASTE_ROD;

    // Tools
    public static Item INFECTED_WOOD_SWORD;
    public static Item INFECTED_WOOD_SHOVEL;
    public static Item INFECTED_WOOD_PICKAXE;
    public static Item INFECTED_WOOD_AXE;
    public static Item INFECTED_WOOD_HOE;
    public static Item ALIEN_BERRY_WOOD_SWORD;
    public static Item ALIEN_BERRY_WOOD_SHOVEL;
    public static Item ALIEN_BERRY_WOOD_PICKAXE;
    public static Item ALIEN_BERRY_WOOD_AXE;
    public static Item ALIEN_BERRY_WOOD_HOE;
    public static Item NIBIRU_STONE_SWORD;
    public static Item NIBIRU_STONE_SHOVEL;
    public static Item NIBIRU_STONE_PICKAXE;
    public static Item NIBIRU_STONE_AXE;
    public static Item NIBIRU_STONE_HOE;
    public static Item MULTALIC_CRYSTAL_SWORD;
    public static Item MULTALIC_CRYSTAL_SHOVEL;
    public static Item MULTALIC_CRYSTAL_PICKAXE;
    public static Item MULTALIC_CRYSTAL_AXE;
    public static Item MULTALIC_CRYSTAL_HOE;

    // Armor
    public static Item MULTALIC_CRYSTAL_HELMET;
    public static Item MULTALIC_CRYSTAL_CHESTPLATE;
    public static Item MULTALIC_CRYSTAL_LEGGINGS;
    public static Item MULTALIC_CRYSTAL_BOOTS;
    public static Item BREATHABLE_MULTALIC_CRYSTAL_HELMET;

    // Material
    public static ToolMaterial MULTALIC_CRYSTAL_TOOL = EnumHelper.addToolMaterial("MULTALIC_CRYSTAL", 3, 1951, 10.0F, 3.75F, 12);
    public static ArmorMaterial MULTALIC_CRYSTAL_ARMOR = EnumHelper.addArmorMaterial("MULTALIC_CRYSTAL", "MULTALIC_CRYSTAL", 41, new int[] { 4, 10, 7, 4 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);

    public static void init()
    {
        /**************************************************************/
        /**********************INITIAL BASE STUFF**********************/
        /**************************************************************/

        NibiruItems.INFERUMITE_CRYSTAL = new ItemBaseMP("inferumite_crystal");
        NibiruItems.MULTALIC_CRYSTAL_PIECES = new ItemBeaconPayment("multalic_crystal_pieces");
        NibiruItems.INFECTED_COAL = new ItemBaseMP("infected_coal");
        NibiruItems.SHLIME_TAIL = new ItemBaseMP("shlime_tail");
        NibiruItems.INFECTED_CHARCOAL = new ItemBaseMP("infected_charcoal");

        NibiruItems.INFECTED_APPLE = new ItemAllFood("infected_apple", ItemAllFood.ItemType.INFECTED_APPLE);
        NibiruItems.INFECTED_GOLDEN_APPLE = new ItemAllFood("infected_golden_apple", ItemAllFood.ItemType.INFECTED_GOLDEN_APPLE);
        NibiruItems.ENCHANTED_INFECTED_GOLDEN_APPLE = new ItemAllFood("enchanted_infected_golden_apple", ItemAllFood.ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE);
        NibiruItems.INFECTED_MELON = new ItemAllFood("infected_melon", ItemAllFood.ItemType.INFECTED_MELON);
        NibiruItems.ALIEN_BERRY = new ItemAllFood("alien_berry", ItemAllFood.ItemType.ALIEN_BERRY);
        NibiruItems.GOLDEN_ALIEN_BERRY = new ItemAllFood("golden_alien_berry", ItemAllFood.ItemType.GOLDEN_ALIEN_BERRY);
        NibiruItems.TERRABERRY = new ItemAllFood("terraberry", ItemAllFood.ItemType.TERRABERRY);

        NibiruItems.INFECTED_PRISMARINE_SHARD = new ItemBaseMP("infected_prismarine_shard");
        NibiruItems.INFECTED_PRISMARINE_CRYSTALS = new ItemBaseMP("infected_prismarine_crystals");

        NibiruItems.RAW_SHLIME_MEAT = new ItemAllFood("raw_shlime_meat", ItemAllFood.ItemType.RAW_SHLIME_MEAT);
        NibiruItems.COOKED_SHLIME_MEAT = new ItemAllFood("cooked_shlime_meat", ItemAllFood.ItemType.COOKED_SHLIME_MEAT);

        NibiruItems.INFECTED_WHEAT_SEEDS = new ItemInfectedWheatSeeds("infected_wheat_seeds");
        NibiruItems.INFECTED_OAK_DOOR = new ItemDoorMP("infected_oak_door", MPBlocks.INFECTED_OAK_DOOR_BLOCK);
        NibiruItems.ALIEN_BERRY_OAK_DOOR = new ItemDoorMP("alien_berry_oak_door", MPBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK);
        NibiruItems.INFECTED_WHEAT = new ItemBaseMP("infected_wheat");
        NibiruItems.INFECTED_CLAY_BALL = new ItemBaseMP("infected_clay_ball");
        NibiruItems.INFECTED_SNOWBALL = new ItemInfectedSnowball("infected_snowball");
        NibiruItems.NIBIRU_DUNGEON_KEY = new ItemDungeonKeyMP("nibiru_dungeon_key", 6);
        NibiruItems.NIBIRU_DUNGEON_KEY_BOW = new ItemBaseMP("nibiru_dungeon_key_bow").setSortCategory(EnumSortCategoryItem.DUNGEON_KEY);
        NibiruItems.NIBIRU_DUNGEON_KEY_BLADE = new ItemBaseMP("nibiru_dungeon_key_blade").setSortCategory(EnumSortCategoryItem.DUNGEON_KEY);
        NibiruItems.INFECTED_SUGAR_CANE = new ItemInfectedSugarCane("infected_sugar_cane");
        NibiruItems.INFECTED_EGG = new ItemInfectedEgg("infected_egg");
        NibiruItems.INFECTED_ARROW = new ItemArrowMP("infected_arrow", ItemArrowMP.ArrowType.INFECTED);
        NibiruItems.INFECTED_MELON_SEEDS = new ItemInfectedMelonSeeds("infected_melon_seeds");
        NibiruItems.VEIN_EYE = new ItemVeinEye("vein_eye");
        NibiruItems.WASTE_ROD_PICKER = new ItemWasteRodPicker("waste_rod_picker");
        NibiruItems.NUCLEAR_WASTE_ROD = new ItemBaseMP("nuclear_waste_rod");

        /**************************************************************/
        /**********************INITIAL TOOL STUFF**********************/
        /**************************************************************/

        NibiruItems.INFECTED_WOOD_SWORD = new ItemInfectedWoodSword("infected_wood_sword", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_SHOVEL = new ItemInfectedWoodShovel("infected_wood_shovel", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_PICKAXE = new ItemInfectedWoodPickaxe("infected_wood_pickaxe", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_AXE = new ItemInfectedWoodAxe("infected_wood_axe", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_HOE = new ItemInfectedWoodHoe("infected_wood_hoe", ToolMaterial.WOOD);
        NibiruItems.ALIEN_BERRY_WOOD_SWORD = new ItemSwordMP("alien_berry_wood_sword", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        NibiruItems.ALIEN_BERRY_WOOD_SHOVEL = new ItemShovelMP("alien_berry_wood_shovel", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        NibiruItems.ALIEN_BERRY_WOOD_PICKAXE = new ItemPickaxeMP("alien_berry_wood_pickaxe", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        NibiruItems.ALIEN_BERRY_WOOD_AXE = new ItemAxeMP("alien_berry_wood_axe", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS, EnumToolSpeed.WOOD);
        NibiruItems.ALIEN_BERRY_WOOD_HOE = new ItemHoeMP("alien_berry_wood_hoe", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        NibiruItems.NIBIRU_STONE_SWORD = new ItemNibiruStoneSword("nibiru_stone_sword", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_SHOVEL = new ItemNibiruStoneShovel("nibiru_stone_shovel", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_PICKAXE = new ItemNibiruStonePickaxe("nibiru_stone_pickaxe", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_AXE = new ItemNibiruStoneAxe("nibiru_stone_axe", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_HOE = new ItemNibiruStoneHoe("nibiru_stone_hoe", ToolMaterial.STONE);
        NibiruItems.MULTALIC_CRYSTAL_SWORD = new ItemSwordMP("multalic_crystal_sword", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.MULTALIC_CRYSTAL_PIECES);
        NibiruItems.MULTALIC_CRYSTAL_SHOVEL = new ItemShovelMP("multalic_crystal_shovel", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.MULTALIC_CRYSTAL_PIECES);
        NibiruItems.MULTALIC_CRYSTAL_PICKAXE = new ItemPickaxeMP("multalic_crystal_pickaxe", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.MULTALIC_CRYSTAL_PIECES);
        NibiruItems.MULTALIC_CRYSTAL_AXE = new ItemAxeMP("multalic_crystal_axe", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.MULTALIC_CRYSTAL_PIECES, EnumToolSpeed.COMMON);
        NibiruItems.MULTALIC_CRYSTAL_HOE = new ItemHoeMP("multalic_crystal_hoe", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.MULTALIC_CRYSTAL_PIECES);

        /**************************************************************/
        /*********************INITIAL ARMOR STUFF**********************/
        /**************************************************************/

        NibiruItems.MULTALIC_CRYSTAL_HELMET = new ItemArmorMultalicCrystal("multalic_crystal_helmet", NibiruItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.HEAD);
        NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE = new ItemArmorMultalicCrystal("multalic_crystal_chestplate", NibiruItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.CHEST);
        NibiruItems.MULTALIC_CRYSTAL_LEGGINGS = new ItemArmorMultalicCrystal("multalic_crystal_leggings", NibiruItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.LEGS);
        NibiruItems.MULTALIC_CRYSTAL_BOOTS = new ItemArmorMultalicCrystal("multalic_crystal_boots", NibiruItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.FEET);
        NibiruItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET = new ItemBreathableMultalicCrystal("breathable_multalic_crystal_helmet", NibiruItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.HEAD);

        /**************************************************************/
        /**********************REGISTER STUFF**************************/
        /**************************************************************/

        BlocksItemsRegistry.registerItem(NibiruItems.INFERUMITE_CRYSTAL);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_PIECES);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_COAL);
        BlocksItemsRegistry.registerItem(NibiruItems.SHLIME_TAIL);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_CHARCOAL);

        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_APPLE);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_GOLDEN_APPLE);
        BlocksItemsRegistry.registerItem(NibiruItems.ENCHANTED_INFECTED_GOLDEN_APPLE);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_MELON);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY);
        BlocksItemsRegistry.registerItem(NibiruItems.GOLDEN_ALIEN_BERRY);
        BlocksItemsRegistry.registerItem(NibiruItems.TERRABERRY);

        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_PRISMARINE_SHARD);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_PRISMARINE_CRYSTALS);

        BlocksItemsRegistry.registerItem(NibiruItems.RAW_SHLIME_MEAT);
        BlocksItemsRegistry.registerItem(NibiruItems.COOKED_SHLIME_MEAT);

        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WHEAT_SEEDS);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_OAK_DOOR);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY_OAK_DOOR);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WHEAT);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_CLAY_BALL);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_SNOWBALL);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_DUNGEON_KEY);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_DUNGEON_KEY_BOW);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_DUNGEON_KEY_BLADE);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_SUGAR_CANE);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_EGG);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_ARROW);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_MELON_SEEDS);
        BlocksItemsRegistry.registerItem(NibiruItems.VEIN_EYE);
        BlocksItemsRegistry.registerItem(NibiruItems.WASTE_ROD_PICKER);
        BlocksItemsRegistry.registerItem(NibiruItems.NUCLEAR_WASTE_ROD);

        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WOOD_SWORD);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WOOD_SHOVEL);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WOOD_PICKAXE);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WOOD_AXE);
        BlocksItemsRegistry.registerItem(NibiruItems.INFECTED_WOOD_HOE);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY_WOOD_SWORD);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY_WOOD_SHOVEL);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY_WOOD_PICKAXE);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY_WOOD_AXE);
        BlocksItemsRegistry.registerItem(NibiruItems.ALIEN_BERRY_WOOD_HOE);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_STONE_SWORD);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_STONE_SHOVEL);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_STONE_PICKAXE);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_STONE_AXE);
        BlocksItemsRegistry.registerItem(NibiruItems.NIBIRU_STONE_HOE);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_SWORD);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_SHOVEL);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_PICKAXE);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_AXE);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_HOE);

        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_HELMET);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS);
        BlocksItemsRegistry.registerItem(NibiruItems.MULTALIC_CRYSTAL_BOOTS);
        BlocksItemsRegistry.registerItem(NibiruItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET);

        /**************************************************************/
        /********************HARVEST LEVEL STUFF***********************/
        /**************************************************************/

        BlockUtils.setToolHarvestLevel(NibiruItems.INFECTED_WOOD_SHOVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setToolHarvestLevel(NibiruItems.INFECTED_WOOD_PICKAXE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setToolHarvestLevel(NibiruItems.INFECTED_WOOD_AXE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setToolHarvestLevel(NibiruItems.ALIEN_BERRY_WOOD_SHOVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setToolHarvestLevel(NibiruItems.ALIEN_BERRY_WOOD_PICKAXE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setToolHarvestLevel(NibiruItems.ALIEN_BERRY_WOOD_AXE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setToolHarvestLevel(NibiruItems.NIBIRU_STONE_SHOVEL, EnumHarvestLevel.SHOVEL, 1);
        BlockUtils.setToolHarvestLevel(NibiruItems.NIBIRU_STONE_PICKAXE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setToolHarvestLevel(NibiruItems.NIBIRU_STONE_AXE, EnumHarvestLevel.AXE, 1);
        BlockUtils.setToolHarvestLevel(NibiruItems.MULTALIC_CRYSTAL_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        BlockUtils.setToolHarvestLevel(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setToolHarvestLevel(NibiruItems.MULTALIC_CRYSTAL_AXE, EnumHarvestLevel.AXE, 3);

        /**************************************************************/
        /************************FLUID STUFF***************************/
        /**************************************************************/

        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.INFECTED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.HELIUM_GAS);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.NUCLEAR_WASTE_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.PURIFIED_WATER_FLUID);

        /**************************************************************/
        /************************OTHER STUFF***************************/
        /**************************************************************/

        MPBlocks.INFECTED_OAK_DOOR_BLOCK.setDoorItem(NibiruItems.INFECTED_OAK_DOOR);
        MPBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK.setDoorItem(NibiruItems.ALIEN_BERRY_OAK_DOOR);
    }
}