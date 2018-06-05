package stevekung.mods.moreplanets.init;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.items.*;
import stevekung.mods.moreplanets.planets.chalos.items.ItemCheeseSpore;
import stevekung.mods.moreplanets.planets.chalos.items.ItemCheeseSporeSeed;
import stevekung.mods.moreplanets.planets.chalos.items.armor.ItemArmorDiremsium;
import stevekung.mods.moreplanets.planets.chalos.items.armor.ItemBreathableDiremsium;
import stevekung.mods.moreplanets.planets.diona.items.ItemInfectedCrystallizedBomb;
import stevekung.mods.moreplanets.planets.diona.items.armor.ItemArmorIllenium;
import stevekung.mods.moreplanets.planets.diona.items.armor.ItemBreathableIllenium;
import stevekung.mods.moreplanets.planets.nibiru.items.*;
import stevekung.mods.moreplanets.planets.nibiru.items.armor.ItemArmorMultalicCrystal;
import stevekung.mods.moreplanets.planets.nibiru.items.armor.ItemBreathableMultalicCrystal;
import stevekung.mods.moreplanets.planets.nibiru.items.tools.*;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.*;
import stevekung.mods.moreplanets.utils.items.tools.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.enums.EnumHarvestLevel;
import stevekung.mods.stevekunglib.utils.enums.EnumToolSpeed;

public class MPItems
{
    // Capsule
    public static Item EMPTY_CAPSULE;
    public static Item INFECTED_SPORE_PROTECTION_CAPSULE;
    public static Item DARK_ENERGY_PROTECTION_CAPSULE;

    // Laser Bullet
    public static Item LASER_BULLET;
    public static Item INFECTED_CRYSTALLIZED_LASER_BULLET;

    // Schematic
    public static Item ION_CANNON_SCHEMATIC;
    public static Item BLACK_HOLE_STORAGE_SCHEMATIC;

    // Fish
    public static Item ZELIUS_FISH;
    public static Item GLOWING_ALIEN_FISH;
    public static Item CHEESE_FISH;

    // Other
    public static Item SPACE_WARPER_CORE;
    public static Item SPACE_BOW;
    public static Item SPACE_FISHING_ROD;
    public static Item BLUE_DYE;
    public static Item LASER_GUN;
    public static Item ALIEN_DEFENDER_REINFORCEMENT;
    public static Item CREATIVE_SPACE_KIT;
    public static Item VEIN_FLOATER_DISC;
    public static Item UPGRADE_TEMPLATE;
    public static Item SHIELD_DAMAGE_UPGRADE;
    public static Item SHIELD_SIZE_UPGRADE;
    public static Item SHIELD_CAPACITY_UPGRADE;
    public static Item MACHINE_SPEED_UPGRADE;
    public static Item GRAVITY_AMULET;

    //////////////////////// DIONA STUFF ////////////////////////

    // Diona Item
    public static Item ILLENIUM_INGOT;
    public static Item SETRORIUM_SHARD;
    public static Item COMPRESSED_ILLENIUM;
    public static Item COMPRESSED_SETRORIUM;
    public static Item INFECTED_CRYSTALLIZED_SHARD;
    public static Item ALIEN_MINER_PART;
    public static Item GLOWING_IRON_INGOT;
    public static Item BLACK_HOLE_FRAGMENTS;
    public static Item ANTI_GRAVITY_FRAGMENTS;

    // Other
    public static Item DIONA_DUNGEON_KEY;
    public static Item INFECTED_CRYSTALLIZED_BOMB;
    public static Item INFECTED_CRYSTALLIZED_SLIMEBALL;
    public static Item INFECTED_CRYSTALLIZED_ARROW;
    public static Item DARK_ENERGY_PEARL;
    public static Item ANTI_GRAVITY_ARROW;

    //////////////////////// CHALOS STUFF ////////////////////////

    // Chalos Item
    public static Item DIREMSIUM_INGOT;
    public static Item ZYPTORIUM_INGOT;
    public static Item COMPRESSED_DIREMSIUM;
    public static Item COMPRESSED_ZYPTORIUM;

    // Food
    public static Item CHEESE_MILK_CURD;
    public static Item RAW_CHEESE_BEEF;
    public static Item COOKED_CHEESE_BEEF;
    public static Item CHEESE_SPORE_BERRY;

    // Other
    public static Item CHEESE_SLIMEBALL;
    public static Item CHALOS_DUNGEON_KEY;
    public static Item CHEESE_SPORE;
    public static Item CHEESE_SPORE_SEED;
    public static Item CHEESE_SPORE_DOOR;

    //////////////////////// NIBIRU STUFF ////////////////////////

    // Nibiru Item
    public static Item INFERUMITE_CRYSTAL;
    public static Item MULTALIC_CRYSTAL_PIECES;
    public static Item INFECTED_COAL;
    public static Item INFECTED_CHARCOAL;
    public static Item SHLIME_TAIL;
    public static Item INFECTED_PRISMARINE_SHARD;
    public static Item INFECTED_PRISMARINE_CRYSTALS;

    // Food
    public static Item INFECTED_APPLE;
    public static Item INFECTED_GOLDEN_APPLE;
    public static Item ENCHANTED_INFECTED_GOLDEN_APPLE;
    public static Item INFECTED_MELON_SLICE;
    public static Item ALIEN_BERRY;
    public static Item GOLDEN_ALIEN_BERRY;
    public static Item TERRABERRY;
    public static Item RAW_SHLIME_MEAT;
    public static Item COOKED_SHLIME_MEAT;

    // Other
    public static Item INFECTED_WHEAT_SEEDS;
    public static Item INFECTED_OAK_DOOR;
    public static Item ALIEN_BERRY_OAK_DOOR;
    public static Item INFECTED_WHEAT;
    public static Item INFECTED_CLAY_BALL;
    public static Item INFECTED_SNOWBALL;
    public static Item PURIFIED_SNOWBALL;
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

    //////////////////////// FRONOS STUFF ////////////////////////

    // Fronos Item
    public static Item EXTRAILONITE_INGOT;
    public static Item COMPRESSED_EXTRAILONITE;

    // Food
    public static Item STRAWBERRY;
    public static Item GIANT_BLUEBERRY;
    public static Item CHOCOLATE_BAR;
    public static Item JELLY_BEANS;
    public static Item MARSHMALLOW;
    public static Item COOKED_MARSHMALLOW;

    // Candy Cane
    public static Item RED_CANDY_CANE_STICK;
    public static Item GREEN_CANDY_CANE_STICK;
    public static Item BLUE_CANDY_CANE_STICK;
    public static Item ORANGE_CANDY_CANE_STICK;
    public static Item PINK_CANDY_CANE_STICK;
    public static Item YELLOW_CANDY_CANE_STICK;
    public static Item PURPLE_CANDY_CANE_STICK;
    public static Item RAINBOW_CANDY_CANE_STICK;

    // Jelly
    public static Item GRAPE_JELLY;
    public static Item RASPBERRY_JELLY;
    public static Item STRAWBERRY_JELLY;
    public static Item BERRY_JELLY;
    public static Item LIME_JELLY;
    public static Item ORANGE_JELLY;
    public static Item GREEN_JELLY;
    public static Item LEMON_JELLY;

    //////////////////////// TOOL/ARMOR STUFF ////////////////////////

    // Tools
    public static Item ILLENIUM_SWORD;
    public static Item ILLENIUM_SHOVEL;
    public static Item ILLENIUM_PICKAXE;
    public static Item ILLENIUM_AXE;
    public static Item ILLENIUM_HOE;

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
    public static Item ILLENIUM_HELMET;
    public static Item ILLENIUM_CHESTPLATE;
    public static Item ILLENIUM_LEGGINGS;
    public static Item ILLENIUM_BOOTS;
    public static Item BREATHABLE_ILLENIUM_HELMET;

    public static Item DIREMSIUM_HELMET;
    public static Item DIREMSIUM_CHESTPLATE;
    public static Item DIREMSIUM_LEGGINGS;
    public static Item DIREMSIUM_BOOTS;
    public static Item BREATHABLE_DIREMSIUM_HELMET;

    public static Item MULTALIC_CRYSTAL_HELMET;
    public static Item MULTALIC_CRYSTAL_CHESTPLATE;
    public static Item MULTALIC_CRYSTAL_LEGGINGS;
    public static Item MULTALIC_CRYSTAL_BOOTS;
    public static Item BREATHABLE_MULTALIC_CRYSTAL_HELMET;

    //////////////////////// MATERIAL STUFF ////////////////////////

    // Tool/Armor Material
    public static final ToolMaterial ILLENIUM_TOOL = EnumHelper.addToolMaterial("ILLENIUM", 3, 1432, 8.5F, 4.5F, 10);
    public static final ToolMaterial DIREMSIUM_TOOL = EnumHelper.addToolMaterial("DIREMSIUM", 3, 1532, 9.0F, 5.0F, 10);
    public static final ToolMaterial MULTALIC_CRYSTAL_TOOL = EnumHelper.addToolMaterial("MULTALIC_CRYSTAL", 3, 1951, 10.0F, 3.75F, 12);

    public static final ArmorMaterial ILLENIUM_ARMOR = EnumHelper.addArmorMaterial("ILLENIUM", "ILLENIUM", 40, new int[] { 5, 10, 8, 5 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.25F);
    public static final ArmorMaterial DIREMSIUM_ARMOR = EnumHelper.addArmorMaterial("DIREMSIUM", "DIREMSIUM", 48, new int[] { 7, 12, 10, 7 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
    public static final ArmorMaterial MULTALIC_CRYSTAL_ARMOR = EnumHelper.addArmorMaterial("MULTALIC_CRYSTAL", "MULTALIC_CRYSTAL", 41, new int[] { 4, 10, 7, 4 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);

    public static void init()
    {
        // Capsule
        MPItems.EMPTY_CAPSULE = new ItemCapsule("empty_capsule", ItemCapsule.CapsuleType.EMPTY);
        MPItems.INFECTED_SPORE_PROTECTION_CAPSULE = new ItemCapsule("infected_spore_protection_capsule", ItemCapsule.CapsuleType.INFECTED_SPORE);
        MPItems.DARK_ENERGY_PROTECTION_CAPSULE = new ItemCapsule("dark_energy_protection_capsule", ItemCapsule.CapsuleType.DARK_ENERGY);

        // Laser Bullet
        MPItems.LASER_BULLET = new ItemBaseMP("laser_bullet").setSortCategory(EnumSortCategoryItem.PROJECTILE);
        MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET = new ItemBaseMP("infected_crystallized_laser_bullet").setSortCategory(EnumSortCategoryItem.PROJECTILE);

        // Schematic
        MPItems.ION_CANNON_SCHEMATIC = new ItemIonCannonSchematic("ion_cannon_schematic");
        MPItems.BLACK_HOLE_STORAGE_SCHEMATIC = new ItemBlackHoleStorageSchematic("black_hole_storage_schematic");

        // Fish
        MPItems.ZELIUS_FISH = new ItemSpaceFish("zelius_fish", ItemSpaceFish.ItemType.ZELIUS_FISH);
        MPItems.GLOWING_ALIEN_FISH = new ItemSpaceFish("glowing_alien_fish", ItemSpaceFish.ItemType.GLOWING_ALIEN_FISH);
        MPItems.CHEESE_FISH = new ItemSpaceFish("cheese_fish", ItemSpaceFish.ItemType.CHEESE_FISH);

        // Other
        MPItems.SPACE_WARPER_CORE = new ItemSpaceWarperCore("space_warper_core");
        MPItems.SPACE_BOW = new ItemSpaceBow("space_bow");
        MPItems.SPACE_FISHING_ROD = new ItemSpaceFishingRod("space_fishing_rod");
        MPItems.BLUE_DYE = new ItemDyeMP("blue_dye");
        MPItems.LASER_GUN = new ItemLaserGun("laser_gun");
        MPItems.ALIEN_DEFENDER_REINFORCEMENT = new ItemAlienDefenderReinforcement("alien_defender_reinforcement");
        MPItems.CREATIVE_SPACE_KIT = new ItemCreativeSpaceKit("creative_space_kit");
        MPItems.VEIN_FLOATER_DISC = new ItemRecordMP("vein_floater_disc", "a_planet_to_conquer", MPSounds.A_PLANET_TO_CONQUER);
        MPItems.UPGRADE_TEMPLATE = new ItemBaseMP("upgrade_template").setSortCategory(EnumSortCategoryItem.UPGRADE);
        MPItems.SHIELD_DAMAGE_UPGRADE = new ItemBaseMP("shield_damage_upgrade").setSortCategory(EnumSortCategoryItem.UPGRADE);
        MPItems.SHIELD_SIZE_UPGRADE = new ItemBaseMP("shield_size_upgrade").setSortCategory(EnumSortCategoryItem.UPGRADE);
        MPItems.SHIELD_CAPACITY_UPGRADE = new ItemBaseMP("shield_capacity_upgrade").setSortCategory(EnumSortCategoryItem.UPGRADE);
        MPItems.MACHINE_SPEED_UPGRADE = new ItemBaseMP("machine_speed_upgrade").setSortCategory(EnumSortCategoryItem.UPGRADE);
        MPItems.GRAVITY_AMULET = new ItemGravityAmulet("gravity_amulet");

        //////////////////////// DIONA STUFF ////////////////////////

        // Diona Item
        MPItems.ILLENIUM_INGOT = new ItemBaseMP("illenium_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        MPItems.SETRORIUM_SHARD = new ItemBaseMP("setrorium_shard");
        MPItems.COMPRESSED_ILLENIUM = new ItemCompressedMetal("compressed_illenium").setSortCategory(EnumSortCategoryItem.PLATE);
        MPItems.COMPRESSED_SETRORIUM = new ItemCompressedMetal("compressed_setrorium").setSortCategory(EnumSortCategoryItem.PLATE);
        MPItems.INFECTED_CRYSTALLIZED_SHARD = new ItemBaseMP("infected_crystallized_shard");
        MPItems.ALIEN_MINER_PART = new ItemBaseMP("alien_miner_part");
        MPItems.GLOWING_IRON_INGOT = new ItemBaseMP("glowing_iron_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        MPItems.BLACK_HOLE_FRAGMENTS = new ItemBaseMP("black_hole_fragments");
        MPItems.ANTI_GRAVITY_FRAGMENTS = new ItemAntiGravity("anti_gravity_fragments");

        // Other
        MPItems.DIONA_DUNGEON_KEY = new ItemDungeonKeyMP("diona_dungeon_key", 4);
        MPItems.INFECTED_CRYSTALLIZED_BOMB = new ItemInfectedCrystallizedBomb("infected_crystallized_bomb");
        MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL = new ItemBaseMP("infected_crystallized_slimeball");
        MPItems.INFECTED_CRYSTALLIZED_ARROW = new ItemArrowMP("infected_crystallized_arrow", ItemArrowMP.ArrowType.INFECTED_CRYSTALLIZED);
        MPItems.DARK_ENERGY_PEARL = new ItemBaseMP("dark_energy_pearl").setSortCategory(EnumSortCategoryItem.GENERAL);
        MPItems.ANTI_GRAVITY_ARROW = new ItemArrowMP("anti_gravity_arrow", ItemArrowMP.ArrowType.ANTI_GRAVITY);

        //////////////////////// CHALOS STUFF ////////////////////////

        // Chalos Item
        MPItems.DIREMSIUM_INGOT = new ItemBaseMP("diremsium_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        MPItems.ZYPTORIUM_INGOT = new ItemBaseMP("zyptorium_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        MPItems.COMPRESSED_DIREMSIUM = new ItemCompressedMetal("compressed_diremsium").setSortCategory(EnumSortCategoryItem.PLATE);
        MPItems.COMPRESSED_ZYPTORIUM = new ItemCompressedMetal("compressed_zyptorium").setSortCategory(EnumSortCategoryItem.PLATE);

        // Food
        MPItems.CHEESE_MILK_CURD = new ItemAllFood("cheese_milk_curd", ItemAllFood.ItemType.CHEESE_MILK_CURD);
        MPItems.RAW_CHEESE_BEEF = new ItemAllFood("raw_cheese_beef", ItemAllFood.ItemType.RAW_CHEESE_BEEF);
        MPItems.COOKED_CHEESE_BEEF = new ItemAllFood("cooked_cheese_beef", ItemAllFood.ItemType.COOKED_CHEESE_BEEF);
        MPItems.CHEESE_SPORE_BERRY = new ItemAllFood("cheese_spore_berry", ItemAllFood.ItemType.CHEESE_SPORE_BERRY);

        // Other
        MPItems.CHEESE_SLIMEBALL = new ItemBaseMP("cheese_slimeball");
        MPItems.CHALOS_DUNGEON_KEY = new ItemDungeonKeyMP("chalos_dungeon_key", 5);
        MPItems.CHEESE_SPORE = new ItemCheeseSpore("cheese_spore_item");
        MPItems.CHEESE_SPORE_SEED = new ItemCheeseSporeSeed("cheese_spore_seed");
        MPItems.CHEESE_SPORE_DOOR = new ItemDoorMP("cheese_spore_door", MPBlocks.CHEESE_SPORE_DOOR);

        //////////////////////// NIBIRU STUFF ////////////////////////

        // Nibiru Item
        MPItems.INFERUMITE_CRYSTAL = new ItemBaseMP("inferumite_crystal");
        MPItems.MULTALIC_CRYSTAL_PIECES = new ItemBeaconPayment("multalic_crystal_pieces");
        MPItems.INFECTED_COAL = new ItemBaseMP("infected_coal");
        MPItems.INFECTED_CHARCOAL = new ItemBaseMP("infected_charcoal");
        MPItems.SHLIME_TAIL = new ItemBaseMP("shlime_tail");
        MPItems.INFECTED_PRISMARINE_SHARD = new ItemBaseMP("infected_prismarine_shard");
        MPItems.INFECTED_PRISMARINE_CRYSTALS = new ItemBaseMP("infected_prismarine_crystals");

        // Food
        MPItems.INFECTED_APPLE = new ItemAllFood("infected_apple", ItemAllFood.ItemType.INFECTED_APPLE);
        MPItems.INFECTED_GOLDEN_APPLE = new ItemAllFood("infected_golden_apple", ItemAllFood.ItemType.INFECTED_GOLDEN_APPLE);
        MPItems.ENCHANTED_INFECTED_GOLDEN_APPLE = new ItemAllFood("enchanted_infected_golden_apple", ItemAllFood.ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE);
        MPItems.INFECTED_MELON_SLICE = new ItemAllFood("infected_melon_slice", ItemAllFood.ItemType.INFECTED_MELON_SLICE);
        MPItems.ALIEN_BERRY = new ItemAllFood("alien_berry", ItemAllFood.ItemType.ALIEN_BERRY);
        MPItems.GOLDEN_ALIEN_BERRY = new ItemAllFood("golden_alien_berry", ItemAllFood.ItemType.GOLDEN_ALIEN_BERRY);
        MPItems.TERRABERRY = new ItemAllFood("terraberry", ItemAllFood.ItemType.TERRABERRY);
        MPItems.RAW_SHLIME_MEAT = new ItemAllFood("raw_shlime_meat", ItemAllFood.ItemType.RAW_SHLIME_MEAT);
        MPItems.COOKED_SHLIME_MEAT = new ItemAllFood("cooked_shlime_meat", ItemAllFood.ItemType.COOKED_SHLIME_MEAT);

        // Other
        MPItems.INFECTED_WHEAT_SEEDS = new ItemInfectedWheatSeeds("infected_wheat_seeds");
        MPItems.INFECTED_OAK_DOOR = new ItemDoorMP("infected_oak_door", MPBlocks.INFECTED_OAK_DOOR);
        MPItems.ALIEN_BERRY_OAK_DOOR = new ItemDoorMP("alien_berry_oak_door", MPBlocks.ALIEN_BERRY_OAK_DOOR);
        MPItems.INFECTED_WHEAT = new ItemBaseMP("infected_wheat");
        MPItems.INFECTED_CLAY_BALL = new ItemBaseMP("infected_clay_ball");
        MPItems.INFECTED_SNOWBALL = new ItemInfectedSnowball("infected_snowball");
        MPItems.PURIFIED_SNOWBALL = new ItemPurifiedSnowball("purified_snowball");
        MPItems.NIBIRU_DUNGEON_KEY = new ItemDungeonKeyMP("nibiru_dungeon_key", 6);
        MPItems.NIBIRU_DUNGEON_KEY_BOW = new ItemBaseMP("nibiru_dungeon_key_bow").setSortCategory(EnumSortCategoryItem.DUNGEON_KEY);
        MPItems.NIBIRU_DUNGEON_KEY_BLADE = new ItemBaseMP("nibiru_dungeon_key_blade").setSortCategory(EnumSortCategoryItem.DUNGEON_KEY);
        MPItems.INFECTED_SUGAR_CANE = new ItemInfectedSugarCane("infected_sugar_cane");
        MPItems.INFECTED_EGG = new ItemInfectedEgg("infected_egg");
        MPItems.INFECTED_ARROW = new ItemArrowMP("infected_arrow", ItemArrowMP.ArrowType.INFECTED);
        MPItems.INFECTED_MELON_SEEDS = new ItemInfectedMelonSeeds("infected_melon_seeds");
        MPItems.VEIN_EYE = new ItemVeinEye("vein_eye");
        MPItems.WASTE_ROD_PICKER = new ItemWasteRodPicker("waste_rod_picker");
        MPItems.NUCLEAR_WASTE_ROD = new ItemBaseMP("nuclear_waste_rod");

        //////////////////////// FRONOS STUFF ////////////////////////

        // Fronos Item
        MPItems.EXTRAILONITE_INGOT = new ItemBaseMP("extrailonite_ingot").setSortCategory(EnumSortCategoryItem.INGOT);
        MPItems.COMPRESSED_EXTRAILONITE = new ItemBaseMP("compressed_extrailonite").setSortCategory(EnumSortCategoryItem.PLATE);

        // Food
        MPItems.STRAWBERRY = new ItemAllFood("strawberry", ItemAllFood.ItemType.STRAWBERRY);
        MPItems.GIANT_BLUEBERRY = new ItemAllFood("giant_blueberry", ItemAllFood.ItemType.GIANT_BLUEBERRY);
        MPItems.CHOCOLATE_BAR = new ItemAllFood("chocolate_bar", ItemAllFood.ItemType.CHOCOLATE_BAR);
        MPItems.JELLY_BEANS = new ItemAllFood("jelly_beans", ItemAllFood.ItemType.JELLY_BEANS);
        MPItems.MARSHMALLOW = new ItemAllFood("marshmallow", ItemAllFood.ItemType.MARSHMALLOW);
        MPItems.COOKED_MARSHMALLOW = new ItemAllFood("cooked_marshmallow", ItemAllFood.ItemType.COOKED_MARSHMALLOW);

        // Candy Cane
        MPItems.RED_CANDY_CANE_STICK = new ItemAllFood("red_candy_cane_stick", ItemAllFood.ItemType.RED_CANDY_CANE_STICK);
        MPItems.GREEN_CANDY_CANE_STICK = new ItemAllFood("green_candy_cane_stick", ItemAllFood.ItemType.GREEN_CANDY_CANE_STICK);
        MPItems.BLUE_CANDY_CANE_STICK = new ItemAllFood("blue_candy_cane_stick", ItemAllFood.ItemType.BLUE_CANDY_CANE_STICK);
        MPItems.ORANGE_CANDY_CANE_STICK = new ItemAllFood("orange_candy_cane_stick", ItemAllFood.ItemType.ORANGE_CANDY_CANE_STICK);
        MPItems.PINK_CANDY_CANE_STICK = new ItemAllFood("pink_candy_cane_stick", ItemAllFood.ItemType.PINK_CANDY_CANE_STICK);
        MPItems.YELLOW_CANDY_CANE_STICK = new ItemAllFood("yellow_candy_cane_stick", ItemAllFood.ItemType.YELLOW_CANDY_CANE_STICK);
        MPItems.PURPLE_CANDY_CANE_STICK = new ItemAllFood("purple_candy_cane_stick", ItemAllFood.ItemType.PURPLE_CANDY_CANE_STICK);
        MPItems.RAINBOW_CANDY_CANE_STICK = new ItemAllFood("rainbow_candy_cane_stick", ItemAllFood.ItemType.RAINBOW_CANDY_CANE_STICK);

        // Jelly
        MPItems.GRAPE_JELLY = new ItemAllFood("grape_jelly", ItemAllFood.ItemType.GRAPE_JELLY);
        MPItems.RASPBERRY_JELLY = new ItemAllFood("raspberry_jelly", ItemAllFood.ItemType.RASPBERRY_JELLY);
        MPItems.STRAWBERRY_JELLY = new ItemAllFood("strawberry_jelly", ItemAllFood.ItemType.STRAWBERRY_JELLY);
        MPItems.BERRY_JELLY = new ItemAllFood("berry_jelly", ItemAllFood.ItemType.BERRY_JELLY);
        MPItems.LIME_JELLY = new ItemAllFood("lime_jelly", ItemAllFood.ItemType.LIME_JELLY);
        MPItems.ORANGE_JELLY = new ItemAllFood("orange_jelly", ItemAllFood.ItemType.ORANGE_JELLY);
        MPItems.GREEN_JELLY = new ItemAllFood("green_jelly", ItemAllFood.ItemType.GREEN_JELLY);
        MPItems.LEMON_JELLY = new ItemAllFood("lemon_jelly", ItemAllFood.ItemType.LEMON_JELLY);

        //////////////////////// TOOL/ARMOR STUFF ////////////////////////

        MPItems.ILLENIUM_SWORD = new ItemSwordMP("illenium_sword", MPItems.ILLENIUM_TOOL, MPItems.COMPRESSED_ILLENIUM);
        MPItems.ILLENIUM_SHOVEL = new ItemShovelMP("illenium_shovel", MPItems.ILLENIUM_TOOL, MPItems.COMPRESSED_ILLENIUM);
        MPItems.ILLENIUM_PICKAXE = new ItemPickaxeMP("illenium_pickaxe", MPItems.ILLENIUM_TOOL, MPItems.COMPRESSED_ILLENIUM);
        MPItems.ILLENIUM_AXE = new ItemAxeMP("illenium_axe", MPItems.ILLENIUM_TOOL, MPItems.COMPRESSED_ILLENIUM, EnumToolSpeed.COMMON);
        MPItems.ILLENIUM_HOE = new ItemHoeMP("illenium_hoe", MPItems.ILLENIUM_TOOL, MPItems.COMPRESSED_ILLENIUM);

        MPItems.DIREMSIUM_SWORD = new ItemSwordMP("diremsium_sword", MPItems.DIREMSIUM_TOOL, MPItems.COMPRESSED_DIREMSIUM);
        MPItems.DIREMSIUM_SHOVEL = new ItemShovelMP("diremsium_shovel", MPItems.DIREMSIUM_TOOL, MPItems.COMPRESSED_DIREMSIUM);
        MPItems.DIREMSIUM_PICKAXE = new ItemPickaxeMP("diremsium_pickaxe", MPItems.DIREMSIUM_TOOL, MPItems.COMPRESSED_DIREMSIUM);
        MPItems.DIREMSIUM_AXE = new ItemAxeMP("diremsium_axe", MPItems.DIREMSIUM_TOOL, MPItems.COMPRESSED_DIREMSIUM, EnumToolSpeed.COMMON);
        MPItems.DIREMSIUM_HOE = new ItemHoeMP("diremsium_hoe", MPItems.DIREMSIUM_TOOL, MPItems.COMPRESSED_DIREMSIUM);
        MPItems.CHEESE_SPORE_WOOD_SWORD = new ItemSwordMP("cheese_spore_wood_sword", ToolMaterial.WOOD, MPBlocks.CHEESE_SPORE_PLANKS);
        MPItems.CHEESE_SPORE_WOOD_SHOVEL = new ItemShovelMP("cheese_spore_wood_shovel", ToolMaterial.WOOD, MPBlocks.CHEESE_SPORE_PLANKS);
        MPItems.CHEESE_SPORE_WOOD_PICKAXE = new ItemPickaxeMP("cheese_spore_wood_pickaxe", ToolMaterial.WOOD, MPBlocks.CHEESE_SPORE_PLANKS);
        MPItems.CHEESE_SPORE_WOOD_AXE = new ItemAxeMP("cheese_spore_wood_axe", ToolMaterial.WOOD, MPBlocks.CHEESE_SPORE_PLANKS, EnumToolSpeed.WOOD);
        MPItems.CHEESE_SPORE_WOOD_HOE = new ItemHoeMP("cheese_spore_wood_hoe", ToolMaterial.WOOD, MPBlocks.CHEESE_SPORE_PLANKS);

        MPItems.INFECTED_WOOD_SWORD = new ItemInfectedWoodSword("infected_wood_sword", ToolMaterial.WOOD);
        MPItems.INFECTED_WOOD_SHOVEL = new ItemInfectedWoodShovel("infected_wood_shovel", ToolMaterial.WOOD);
        MPItems.INFECTED_WOOD_PICKAXE = new ItemInfectedWoodPickaxe("infected_wood_pickaxe", ToolMaterial.WOOD);
        MPItems.INFECTED_WOOD_AXE = new ItemInfectedWoodAxe("infected_wood_axe", ToolMaterial.WOOD);
        MPItems.INFECTED_WOOD_HOE = new ItemInfectedWoodHoe("infected_wood_hoe", ToolMaterial.WOOD);
        MPItems.ALIEN_BERRY_WOOD_SWORD = new ItemSwordMP("alien_berry_wood_sword", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        MPItems.ALIEN_BERRY_WOOD_SHOVEL = new ItemShovelMP("alien_berry_wood_shovel", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        MPItems.ALIEN_BERRY_WOOD_PICKAXE = new ItemPickaxeMP("alien_berry_wood_pickaxe", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        MPItems.ALIEN_BERRY_WOOD_AXE = new ItemAxeMP("alien_berry_wood_axe", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS, EnumToolSpeed.WOOD);
        MPItems.ALIEN_BERRY_WOOD_HOE = new ItemHoeMP("alien_berry_wood_hoe", ToolMaterial.WOOD, MPBlocks.INFECTED_OAK_PLANKS);
        MPItems.NIBIRU_STONE_SWORD = new ItemNibiruStoneSword("nibiru_stone_sword", ToolMaterial.STONE);
        MPItems.NIBIRU_STONE_SHOVEL = new ItemNibiruStoneShovel("nibiru_stone_shovel", ToolMaterial.STONE);
        MPItems.NIBIRU_STONE_PICKAXE = new ItemNibiruStonePickaxe("nibiru_stone_pickaxe", ToolMaterial.STONE);
        MPItems.NIBIRU_STONE_AXE = new ItemNibiruStoneAxe("nibiru_stone_axe", ToolMaterial.STONE);
        MPItems.NIBIRU_STONE_HOE = new ItemNibiruStoneHoe("nibiru_stone_hoe", ToolMaterial.STONE);
        MPItems.MULTALIC_CRYSTAL_SWORD = new ItemSwordMP("multalic_crystal_sword", MPItems.MULTALIC_CRYSTAL_TOOL, MPItems.MULTALIC_CRYSTAL_PIECES);
        MPItems.MULTALIC_CRYSTAL_SHOVEL = new ItemShovelMP("multalic_crystal_shovel", MPItems.MULTALIC_CRYSTAL_TOOL, MPItems.MULTALIC_CRYSTAL_PIECES);
        MPItems.MULTALIC_CRYSTAL_PICKAXE = new ItemPickaxeMP("multalic_crystal_pickaxe", MPItems.MULTALIC_CRYSTAL_TOOL, MPItems.MULTALIC_CRYSTAL_PIECES);
        MPItems.MULTALIC_CRYSTAL_AXE = new ItemAxeMP("multalic_crystal_axe", MPItems.MULTALIC_CRYSTAL_TOOL, MPItems.MULTALIC_CRYSTAL_PIECES, EnumToolSpeed.COMMON);
        MPItems.MULTALIC_CRYSTAL_HOE = new ItemHoeMP("multalic_crystal_hoe", MPItems.MULTALIC_CRYSTAL_TOOL, MPItems.MULTALIC_CRYSTAL_PIECES);

        MPItems.ILLENIUM_HELMET = new ItemArmorIllenium("illenium_helmet", MPItems.ILLENIUM_ARMOR, EntityEquipmentSlot.HEAD);
        MPItems.ILLENIUM_CHESTPLATE = new ItemArmorIllenium("illenium_chestplate", MPItems.ILLENIUM_ARMOR, EntityEquipmentSlot.CHEST);
        MPItems.ILLENIUM_LEGGINGS = new ItemArmorIllenium("illenium_leggings", MPItems.ILLENIUM_ARMOR, EntityEquipmentSlot.LEGS);
        MPItems.ILLENIUM_BOOTS = new ItemArmorIllenium("illenium_boots", MPItems.ILLENIUM_ARMOR, EntityEquipmentSlot.FEET);
        MPItems.BREATHABLE_ILLENIUM_HELMET = new ItemBreathableIllenium("breathable_illenium_helmet", MPItems.ILLENIUM_ARMOR, EntityEquipmentSlot.HEAD);

        MPItems.DIREMSIUM_HELMET = new ItemArmorDiremsium("diremsium_helmet", MPItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.HEAD);
        MPItems.DIREMSIUM_CHESTPLATE = new ItemArmorDiremsium("diremsium_chestplate", MPItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.CHEST);
        MPItems.DIREMSIUM_LEGGINGS = new ItemArmorDiremsium("diremsium_leggings", MPItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.LEGS);
        MPItems.DIREMSIUM_BOOTS = new ItemArmorDiremsium("diremsium_boots", MPItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.FEET);
        MPItems.BREATHABLE_DIREMSIUM_HELMET = new ItemBreathableDiremsium("breathable_diremsium_helmet", MPItems.DIREMSIUM_ARMOR, EntityEquipmentSlot.HEAD);

        MPItems.MULTALIC_CRYSTAL_HELMET = new ItemArmorMultalicCrystal("multalic_crystal_helmet", MPItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.HEAD);
        MPItems.MULTALIC_CRYSTAL_CHESTPLATE = new ItemArmorMultalicCrystal("multalic_crystal_chestplate", MPItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.CHEST);
        MPItems.MULTALIC_CRYSTAL_LEGGINGS = new ItemArmorMultalicCrystal("multalic_crystal_leggings", MPItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.LEGS);
        MPItems.MULTALIC_CRYSTAL_BOOTS = new ItemArmorMultalicCrystal("multalic_crystal_boots", MPItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.FEET);
        MPItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET = new ItemBreathableMultalicCrystal("breathable_multalic_crystal_helmet", MPItems.MULTALIC_CRYSTAL_ARMOR, EntityEquipmentSlot.HEAD);

        MPItems.preRegister();
        MPItems.register();
        MPItems.postRegister();
    }

    private static void preRegister()
    {
        // Capsule
        BlocksItemsRegistry.registerItem(MPItems.EMPTY_CAPSULE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE);
        BlocksItemsRegistry.registerItem(MPItems.DARK_ENERGY_PROTECTION_CAPSULE);

        // Laser Bullet
        BlocksItemsRegistry.registerItem(MPItems.LASER_BULLET);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET);

        // Schematic
        BlocksItemsRegistry.registerItem(MPItems.ION_CANNON_SCHEMATIC);
        BlocksItemsRegistry.registerItem(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC);

        // Fish
        BlocksItemsRegistry.registerItem(MPItems.ZELIUS_FISH);
        BlocksItemsRegistry.registerItem(MPItems.GLOWING_ALIEN_FISH);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_FISH);

        // Other
        BlocksItemsRegistry.registerItem(MPItems.SPACE_WARPER_CORE);
        BlocksItemsRegistry.registerItem(MPItems.SPACE_BOW);
        BlocksItemsRegistry.registerItem(MPItems.SPACE_FISHING_ROD);
        BlocksItemsRegistry.registerItem(MPItems.BLUE_DYE);
        BlocksItemsRegistry.registerItem(MPItems.LASER_GUN);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_DEFENDER_REINFORCEMENT);
        BlocksItemsRegistry.registerItem(MPItems.CREATIVE_SPACE_KIT);
        BlocksItemsRegistry.registerItem(MPItems.VEIN_FLOATER_DISC);
        BlocksItemsRegistry.registerItem(MPItems.UPGRADE_TEMPLATE);
        BlocksItemsRegistry.registerItem(MPItems.SHIELD_DAMAGE_UPGRADE);
        BlocksItemsRegistry.registerItem(MPItems.SHIELD_SIZE_UPGRADE);
        BlocksItemsRegistry.registerItem(MPItems.SHIELD_CAPACITY_UPGRADE);
        BlocksItemsRegistry.registerItem(MPItems.MACHINE_SPEED_UPGRADE);
        BlocksItemsRegistry.registerItem(MPItems.GRAVITY_AMULET);
    }

    private static void register()
    {
        // Diona Item
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_INGOT);
        BlocksItemsRegistry.registerItem(MPItems.SETRORIUM_SHARD);
        BlocksItemsRegistry.registerItem(MPItems.COMPRESSED_ILLENIUM);
        BlocksItemsRegistry.registerItem(MPItems.COMPRESSED_SETRORIUM);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CRYSTALLIZED_SHARD);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_MINER_PART);
        BlocksItemsRegistry.registerItem(MPItems.GLOWING_IRON_INGOT);
        BlocksItemsRegistry.registerItem(MPItems.BLACK_HOLE_FRAGMENTS);
        BlocksItemsRegistry.registerItem(MPItems.ANTI_GRAVITY_FRAGMENTS);

        // Other
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CRYSTALLIZED_BOMB);
        BlocksItemsRegistry.registerItem(MPItems.DIONA_DUNGEON_KEY);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CRYSTALLIZED_ARROW);
        BlocksItemsRegistry.registerItem(MPItems.DARK_ENERGY_PEARL);
        BlocksItemsRegistry.registerItem(MPItems.ANTI_GRAVITY_ARROW);

        // Tool/Armor
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_AXE);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_HOE);

        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_HELMET);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_CHESTPLATE);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_LEGGINGS);
        BlocksItemsRegistry.registerItem(MPItems.ILLENIUM_BOOTS);
        BlocksItemsRegistry.registerItem(MPItems.BREATHABLE_ILLENIUM_HELMET);

        // Chalos Item
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_INGOT);
        BlocksItemsRegistry.registerItem(MPItems.ZYPTORIUM_INGOT);
        BlocksItemsRegistry.registerItem(MPItems.COMPRESSED_DIREMSIUM);
        BlocksItemsRegistry.registerItem(MPItems.COMPRESSED_ZYPTORIUM);

        // Food
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_MILK_CURD);
        BlocksItemsRegistry.registerItem(MPItems.RAW_CHEESE_BEEF);
        BlocksItemsRegistry.registerItem(MPItems.COOKED_CHEESE_BEEF);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_BERRY);

        // Other
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SLIMEBALL);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_SEED);
        BlocksItemsRegistry.registerItem(MPItems.CHALOS_DUNGEON_KEY);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_DOOR);

        // Nibiru Item
        BlocksItemsRegistry.registerItem(MPItems.INFERUMITE_CRYSTAL);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_PIECES);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_COAL);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CHARCOAL);
        BlocksItemsRegistry.registerItem(MPItems.SHLIME_TAIL);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_PRISMARINE_SHARD);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_PRISMARINE_CRYSTALS);

        // Food
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_APPLE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_GOLDEN_APPLE);
        BlocksItemsRegistry.registerItem(MPItems.ENCHANTED_INFECTED_GOLDEN_APPLE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_MELON_SLICE);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY);
        BlocksItemsRegistry.registerItem(MPItems.GOLDEN_ALIEN_BERRY);
        BlocksItemsRegistry.registerItem(MPItems.TERRABERRY);
        BlocksItemsRegistry.registerItem(MPItems.RAW_SHLIME_MEAT);
        BlocksItemsRegistry.registerItem(MPItems.COOKED_SHLIME_MEAT);

        // Other
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WHEAT_SEEDS);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_OAK_DOOR);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY_OAK_DOOR);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WHEAT);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CLAY_BALL);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_SNOWBALL);
        BlocksItemsRegistry.registerItem(MPItems.PURIFIED_SNOWBALL);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_DUNGEON_KEY);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_DUNGEON_KEY_BOW);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_DUNGEON_KEY_BLADE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_SUGAR_CANE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_EGG);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_ARROW);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_MELON_SEEDS);
        BlocksItemsRegistry.registerItem(MPItems.VEIN_EYE);
        BlocksItemsRegistry.registerItem(MPItems.WASTE_ROD_PICKER);
        BlocksItemsRegistry.registerItem(MPItems.NUCLEAR_WASTE_ROD);

        // Fronos Item
        BlocksItemsRegistry.registerItem(MPItems.EXTRAILONITE_INGOT);
        BlocksItemsRegistry.registerItem(MPItems.COMPRESSED_EXTRAILONITE);

        // Food
        BlocksItemsRegistry.registerItem(MPItems.STRAWBERRY);
        BlocksItemsRegistry.registerItem(MPItems.GIANT_BLUEBERRY);
        BlocksItemsRegistry.registerItem(MPItems.CHOCOLATE_BAR);
        BlocksItemsRegistry.registerItem(MPItems.JELLY_BEANS);
        BlocksItemsRegistry.registerItem(MPItems.MARSHMALLOW);
        BlocksItemsRegistry.registerItem(MPItems.COOKED_MARSHMALLOW);

        // Candy Cane
        BlocksItemsRegistry.registerItem(MPItems.RED_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.GREEN_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.BLUE_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.ORANGE_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.PINK_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.YELLOW_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.PURPLE_CANDY_CANE_STICK);
        BlocksItemsRegistry.registerItem(MPItems.RAINBOW_CANDY_CANE_STICK);

        // Jelly
        BlocksItemsRegistry.registerItem(MPItems.GRAPE_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.RASPBERRY_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.STRAWBERRY_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.BERRY_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.LIME_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.ORANGE_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.GREEN_JELLY);
        BlocksItemsRegistry.registerItem(MPItems.LEMON_JELLY);

        // Tool/Armor
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_AXE);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_HOE);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_WOOD_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_WOOD_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_WOOD_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_WOOD_AXE);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_SPORE_WOOD_HOE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WOOD_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WOOD_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WOOD_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WOOD_AXE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_WOOD_HOE);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY_WOOD_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY_WOOD_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY_WOOD_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY_WOOD_AXE);
        BlocksItemsRegistry.registerItem(MPItems.ALIEN_BERRY_WOOD_HOE);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_STONE_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_STONE_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_STONE_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_STONE_AXE);
        BlocksItemsRegistry.registerItem(MPItems.NIBIRU_STONE_HOE);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_SWORD);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_SHOVEL);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_PICKAXE);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_AXE);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_HOE);

        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_HELMET);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_CHESTPLATE);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_LEGGINGS);
        BlocksItemsRegistry.registerItem(MPItems.DIREMSIUM_BOOTS);
        BlocksItemsRegistry.registerItem(MPItems.BREATHABLE_DIREMSIUM_HELMET);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_HELMET);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_CHESTPLATE);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_LEGGINGS);
        BlocksItemsRegistry.registerItem(MPItems.MULTALIC_CRYSTAL_BOOTS);
        BlocksItemsRegistry.registerItem(MPItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET);
    }

    private static void postRegister()
    {
        BlockUtils.setToolHarvestLevel(MPItems.ILLENIUM_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        BlockUtils.setToolHarvestLevel(MPItems.ILLENIUM_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setToolHarvestLevel(MPItems.ILLENIUM_AXE, EnumHarvestLevel.AXE, 3);
        BlockUtils.setToolHarvestLevel(MPItems.DIREMSIUM_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        BlockUtils.setToolHarvestLevel(MPItems.DIREMSIUM_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setToolHarvestLevel(MPItems.DIREMSIUM_AXE, EnumHarvestLevel.AXE, 3);
        BlockUtils.setToolHarvestLevel(MPItems.INFECTED_WOOD_SHOVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setToolHarvestLevel(MPItems.INFECTED_WOOD_PICKAXE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setToolHarvestLevel(MPItems.INFECTED_WOOD_AXE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setToolHarvestLevel(MPItems.ALIEN_BERRY_WOOD_SHOVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setToolHarvestLevel(MPItems.ALIEN_BERRY_WOOD_PICKAXE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setToolHarvestLevel(MPItems.ALIEN_BERRY_WOOD_AXE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setToolHarvestLevel(MPItems.NIBIRU_STONE_SHOVEL, EnumHarvestLevel.SHOVEL, 1);
        BlockUtils.setToolHarvestLevel(MPItems.NIBIRU_STONE_PICKAXE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setToolHarvestLevel(MPItems.NIBIRU_STONE_AXE, EnumHarvestLevel.AXE, 1);
        BlockUtils.setToolHarvestLevel(MPItems.MULTALIC_CRYSTAL_SHOVEL, EnumHarvestLevel.SHOVEL, 3);
        BlockUtils.setToolHarvestLevel(MPItems.MULTALIC_CRYSTAL_PICKAXE, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setToolHarvestLevel(MPItems.MULTALIC_CRYSTAL_AXE, EnumHarvestLevel.AXE, 3);

        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.CRYSTALLIZED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.CRYSTALLIZED_LAVA_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.CHEESE_MILK_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.GASEOUS_CHEESE_MILK);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.INFECTED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.HELIUM_GAS);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.NUCLEAR_WASTE_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerForgeBucket(MPBlocks.PURIFIED_WATER_FLUID);

        MPBlocks.CHEESE_SPORE_DOOR.setDoorItem(MPItems.CHEESE_SPORE_DOOR);
        MPBlocks.INFECTED_OAK_DOOR.setDoorItem(MPItems.INFECTED_OAK_DOOR);
        MPBlocks.ALIEN_BERRY_OAK_DOOR.setDoorItem(MPItems.ALIEN_BERRY_OAK_DOOR);
        MPBlocks.INFECTED_SNOW_LAYER.setSnowball(MPItems.INFECTED_SNOWBALL);
        MPBlocks.PURIFIED_SNOW_LAYER.setSnowball(MPItems.PURIFIED_SNOWBALL);
    }
}