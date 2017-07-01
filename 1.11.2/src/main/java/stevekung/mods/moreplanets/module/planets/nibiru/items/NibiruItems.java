package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.armor.ItemArmorMultalicCrystal;
import stevekung.mods.moreplanets.module.planets.nibiru.items.armor.ItemBreathableMultalicCrystal;
import stevekung.mods.moreplanets.module.planets.nibiru.items.tools.*;
import stevekung.mods.moreplanets.util.EnumHarvestLevel;
import stevekung.mods.moreplanets.util.EnumToolSpeed;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;
import stevekung.mods.moreplanets.util.items.ItemBucketMP;
import stevekung.mods.moreplanets.util.items.ItemDoorMP;
import stevekung.mods.moreplanets.util.items.ItemGasBucketMP;
import stevekung.mods.moreplanets.util.items.tools.*;

public class NibiruItems
{
    // Base
    public static Item NIBIRU_ITEM;
    public static Item INFECTED_WHEAT_SEEDS;
    public static Item INFECTED_DOOR;
    public static Item INFECTED_DEAD_OAK_DOOR;
    public static Item ALIEN_BERRY_OAK_DOOR;
    public static Item INFECTED_WATER_FLUID_BUCKET;
    public static Item NIBIRU_FRUITS;
    public static Item INFECTED_WHEAT;
    public static Item INFECTED_CLAY_BALL;
    public static Item INFECTED_SNOWBALL;
    public static Item NIBIRU_DUNGEON_KEY;
    public static Item INFECTED_SUGAR_CANE;
    public static Item INFECTED_PRISMARINE;
    public static Item INFECTED_EGG;
    public static Item HELIUM_GAS_BUCKET;
    public static Item INFECTED_ARROW;
    public static Item INFECTED_MELON_SEEDS;
    public static Item NUCLEAR_WASTE_BUCKET;
    public static Item VEIN_EYE;
    public static Item PURIFY_WATER_BUCKET;
    public static Item NIBIRU_FOOD;
    public static Item WASTE_ROD_PICKER;
    public static Item NUCLEAR_WASTE_ROD;
    public static Item TIER_6_ROCKET;

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

        NibiruItems.NIBIRU_ITEM = new ItemNibiru("nibiru_item");
        NibiruItems.INFECTED_WHEAT_SEEDS = new ItemInfectedWheatSeeds("infected_wheat_seeds");
        NibiruItems.INFECTED_DOOR = new ItemDoorMP("infected_door", NibiruBlocks.INFECTED_DOOR_BLOCK);
        NibiruItems.INFECTED_DEAD_OAK_DOOR = new ItemDoorMP("infected_dead_oak_door", NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK);
        NibiruItems.ALIEN_BERRY_OAK_DOOR = new ItemDoorMP("alien_berry_oak_door", NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK);
        NibiruItems.INFECTED_WATER_FLUID_BUCKET = new ItemBucketMP("infected_water_bucket", NibiruBlocks.INFECTED_WATER_FLUID_BLOCK);
        NibiruItems.NIBIRU_FRUITS = new ItemNibiruFruits("nibiru_fruits");
        NibiruItems.INFECTED_WHEAT = new ItemBaseMP("infected_wheat");
        NibiruItems.INFECTED_CLAY_BALL = new ItemBaseMP("infected_clay_ball");
        NibiruItems.INFECTED_SNOWBALL = new ItemInfectedSnowball("infected_snowball");
        NibiruItems.NIBIRU_DUNGEON_KEY = new ItemNibiruDungeonKey("nibiru_dungeon_key");
        NibiruItems.INFECTED_SUGAR_CANE = new ItemInfectedSugarCane("infected_sugar_cane");
        NibiruItems.INFECTED_PRISMARINE = new ItemInfectedPrismarine("infected_prismarine_item");
        NibiruItems.INFECTED_EGG = new ItemInfectedEgg("infected_egg");
        NibiruItems.HELIUM_GAS_BUCKET = new ItemGasBucketMP("helium_gas_bucket", NibiruBlocks.HELIUM_GAS_BLOCK);
        NibiruItems.INFECTED_ARROW = new ItemInfectedArrow("infected_arrow");
        NibiruItems.INFECTED_MELON_SEEDS = new ItemInfectedMelonSeeds("infected_melon_seeds");
        NibiruItems.NUCLEAR_WASTE_BUCKET = new ItemBucketMP("nuclear_waste_bucket", NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK);
        NibiruItems.VEIN_EYE = new ItemVeinEye("vein_eye");
        NibiruItems.PURIFY_WATER_BUCKET = new ItemBucketMP("purify_water_bucket", NibiruBlocks.PURIFY_WATER_FLUID_BLOCK);
        NibiruItems.NIBIRU_FOOD = new ItemNibiruFood("nibiru_food");
        NibiruItems.WASTE_ROD_PICKER = new ItemWasteRodPicker("waste_rod_picker");
        NibiruItems.NUCLEAR_WASTE_ROD = new ItemBaseMP("nuclear_waste_rod");
        NibiruItems.TIER_6_ROCKET = new ItemTier6Rocket("tier_6_rocket");

        /**************************************************************/
        /**********************INITIAL TOOL STUFF**********************/
        /**************************************************************/

        NibiruItems.INFECTED_WOOD_SWORD = new ItemInfectedWoodSword("infected_wood_sword", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_SHOVEL = new ItemInfectedWoodShovel("infected_wood_shovel", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_PICKAXE = new ItemInfectedWoodPickaxe("infected_wood_pickaxe", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_AXE = new ItemInfectedWoodAxe("infected_wood_axe", ToolMaterial.WOOD);
        NibiruItems.INFECTED_WOOD_HOE = new ItemInfectedWoodHoe("infected_wood_hoe", ToolMaterial.WOOD);
        NibiruItems.ALIEN_BERRY_WOOD_SWORD = new ItemSwordMP("alien_berry_wood_sword", ToolMaterial.WOOD, NibiruBlocks.NIBIRU_PLANKS, 2);
        NibiruItems.ALIEN_BERRY_WOOD_SHOVEL = new ItemShovelMP("alien_berry_wood_shovel", ToolMaterial.WOOD, NibiruBlocks.NIBIRU_PLANKS, 2);
        NibiruItems.ALIEN_BERRY_WOOD_PICKAXE = new ItemPickaxeMP("alien_berry_wood_pickaxe", ToolMaterial.WOOD, NibiruBlocks.NIBIRU_PLANKS, 2);
        NibiruItems.ALIEN_BERRY_WOOD_AXE = new ItemAxeMP("alien_berry_wood_axe", ToolMaterial.WOOD, NibiruBlocks.NIBIRU_PLANKS, 2, EnumToolSpeed.WOOD);
        NibiruItems.ALIEN_BERRY_WOOD_HOE = new ItemHoeMP("alien_berry_wood_hoe", ToolMaterial.WOOD, NibiruBlocks.NIBIRU_PLANKS, 2);
        NibiruItems.NIBIRU_STONE_SWORD = new ItemNibiruStoneSword("nibiru_stone_sword", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_SHOVEL = new ItemNibiruStoneShovel("nibiru_stone_shovel", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_PICKAXE = new ItemNibiruStonePickaxe("nibiru_stone_pickaxe", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_AXE = new ItemNibiruStoneAxe("nibiru_stone_axe", ToolMaterial.STONE);
        NibiruItems.NIBIRU_STONE_HOE = new ItemNibiruStoneHoe("nibiru_stone_hoe", ToolMaterial.STONE);
        NibiruItems.MULTALIC_CRYSTAL_SWORD = new ItemSwordMP("multalic_crystal_sword", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.NIBIRU_ITEM, 1);
        NibiruItems.MULTALIC_CRYSTAL_SHOVEL = new ItemShovelMP("multalic_crystal_shovel", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.NIBIRU_ITEM, 1);
        NibiruItems.MULTALIC_CRYSTAL_PICKAXE = new ItemPickaxeMP("multalic_crystal_pickaxe", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.NIBIRU_ITEM, 1);
        NibiruItems.MULTALIC_CRYSTAL_AXE = new ItemAxeMP("multalic_crystal_axe", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.NIBIRU_ITEM, 1, EnumToolSpeed.COMMON);
        NibiruItems.MULTALIC_CRYSTAL_HOE = new ItemHoeMP("multalic_crystal_hoe", NibiruItems.MULTALIC_CRYSTAL_TOOL, NibiruItems.NIBIRU_ITEM, 1);

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

        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_ITEM);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WHEAT_SEEDS);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_DOOR);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_DEAD_OAK_DOOR);
        CommonRegisterHelper.registerItem(NibiruItems.ALIEN_BERRY_OAK_DOOR);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WATER_FLUID_BUCKET);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_FRUITS);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WHEAT);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_CLAY_BALL);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_SNOWBALL);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_DUNGEON_KEY);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_SUGAR_CANE);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_PRISMARINE);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_EGG);
        CommonRegisterHelper.registerItem(NibiruItems.HELIUM_GAS_BUCKET);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_ARROW);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_MELON_SEEDS);
        CommonRegisterHelper.registerItem(NibiruItems.NUCLEAR_WASTE_BUCKET);
        CommonRegisterHelper.registerItem(NibiruItems.VEIN_EYE);
        CommonRegisterHelper.registerItem(NibiruItems.PURIFY_WATER_BUCKET);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_FOOD);
        CommonRegisterHelper.registerItem(NibiruItems.WASTE_ROD_PICKER);
        CommonRegisterHelper.registerItem(NibiruItems.NUCLEAR_WASTE_ROD);
        CommonRegisterHelper.registerItem(NibiruItems.TIER_6_ROCKET);

        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WOOD_SWORD);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WOOD_SHOVEL);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WOOD_PICKAXE);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WOOD_AXE);
        CommonRegisterHelper.registerItem(NibiruItems.INFECTED_WOOD_HOE);
        CommonRegisterHelper.registerItem(NibiruItems.ALIEN_BERRY_WOOD_SWORD);
        CommonRegisterHelper.registerItem(NibiruItems.ALIEN_BERRY_WOOD_SHOVEL);
        CommonRegisterHelper.registerItem(NibiruItems.ALIEN_BERRY_WOOD_PICKAXE);
        CommonRegisterHelper.registerItem(NibiruItems.ALIEN_BERRY_WOOD_AXE);
        CommonRegisterHelper.registerItem(NibiruItems.ALIEN_BERRY_WOOD_HOE);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_STONE_SWORD);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_STONE_SHOVEL);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_STONE_PICKAXE);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_STONE_AXE);
        CommonRegisterHelper.registerItem(NibiruItems.NIBIRU_STONE_HOE);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_SWORD);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_SHOVEL);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_PICKAXE);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_AXE);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_HOE);

        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_HELMET);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS);
        CommonRegisterHelper.registerItem(NibiruItems.MULTALIC_CRYSTAL_BOOTS);
        CommonRegisterHelper.registerItem(NibiruItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET);

        /**************************************************************/
        /********************HARVEST LEVEL STUFF***********************/
        /**************************************************************/

        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.INFECTED_WOOD_SHOVEL, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.INFECTED_WOOD_PICKAXE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.INFECTED_WOOD_AXE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.ALIEN_BERRY_WOOD_SHOVEL, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.ALIEN_BERRY_WOOD_PICKAXE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.ALIEN_BERRY_WOOD_AXE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.NIBIRU_STONE_SHOVEL, EnumHarvestLevel.SHOVEL, 1);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.NIBIRU_STONE_PICKAXE, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.NIBIRU_STONE_AXE, EnumHarvestLevel.AXE, 1);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.MULTALIC_CRYSTAL_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        CommonRegisterHelper.setToolHarvestLevel(NibiruItems.MULTALIC_CRYSTAL_AXE, EnumHarvestLevel.AXE, 3);

        /**************************************************************/
        /********************ORE DICTIONARY STUFF**********************/
        /**************************************************************/

        CommonRegisterHelper.registerOreDictionary("cropWheat", NibiruItems.INFECTED_WHEAT);
        CommonRegisterHelper.registerOreDictionary("sugarcane", NibiruItems.INFECTED_SUGAR_CANE);
        CommonRegisterHelper.registerOreDictionary("gemPrismarine", new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("dustPrismarine", new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 1));
        CommonRegisterHelper.registerOreDictionary("egg", NibiruItems.INFECTED_EGG);

        /**************************************************************/
        /************************FLUID STUFF***************************/
        /**************************************************************/

        CommonRegisterHelper.registerForgeBucket(NibiruBlocks.INFECTED_WATER_FLUID);
        CommonRegisterHelper.registerForgeBucket(NibiruBlocks.HELIUM_GAS);
        CommonRegisterHelper.registerForgeBucket(NibiruBlocks.NUCLEAR_WASTE_FLUID);
        CommonRegisterHelper.registerForgeBucket(NibiruBlocks.PURIFY_WATER_FLUID);

        /**************************************************************/
        /************************OTHER STUFF***************************/
        /**************************************************************/

        NibiruBlocks.INFECTED_DOOR_BLOCK.setDoorItem(NibiruItems.INFECTED_DOOR);
        NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK.setDoorItem(NibiruItems.INFECTED_DEAD_OAK_DOOR);
        NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK.setDoorItem(NibiruItems.ALIEN_BERRY_OAK_DOOR);
    }
}