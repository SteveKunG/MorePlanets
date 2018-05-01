package stevekung.mods.moreplanets.init;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.items.*;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;
import stevekung.mods.moreplanets.utils.items.ItemRecordMP;

public class MPItems
{
    // Capsule
    public static Item EMPTY_CAPSULE;
    public static Item INFECTED_SPORE_PROTECTION_CAPSULE;
    public static Item DARK_ENERGY_PROTECTION_CAPSULE;

    public static Item LASER_BULLET;
    public static Item INFECTED_CRYSTALLIZED_LASER_BULLET;

    public static Item ION_CANNON_SCHEMATIC;
    public static Item BLACK_HOLE_STORAGE_SCHEMATIC;

    public static Item ZELIUS_FISH;
    public static Item GLOWING_ALIEN_FISH;
    public static Item CHEESE_FISH;

    public static Item SPACE_WARPER_CORE;
    public static Item SPACE_BOW;
    public static Item SPACE_FISHING_ROD;
    public static Item DYE;
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

    public static void init()
    {
        /**************************************************************/
        /**********************INITIAL STUFF***************************/
        /**************************************************************/

        // Capsule
        MPItems.EMPTY_CAPSULE = new ItemCapsule("empty_capsule", ItemCapsule.CapsuleType.EMPTY);
        MPItems.INFECTED_SPORE_PROTECTION_CAPSULE = new ItemCapsule("infected_spore_protection_capsule", ItemCapsule.CapsuleType.INFECTED_SPORE);
        MPItems.DARK_ENERGY_PROTECTION_CAPSULE = new ItemCapsule("dark_energy_protection_capsule", ItemCapsule.CapsuleType.DARK_ENERGY);

        MPItems.LASER_BULLET = new ItemBaseMP("laser_bullet").setSortCategory(EnumSortCategoryItem.PROJECTILE);
        MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET = new ItemBaseMP("infected_crystallized_laser_bullet").setSortCategory(EnumSortCategoryItem.PROJECTILE);

        MPItems.ION_CANNON_SCHEMATIC = new ItemIonCannonSchematic("ion_cannon_schematic");
        MPItems.BLACK_HOLE_STORAGE_SCHEMATIC = new ItemBlackHoleStorageSchematic("black_hole_storage_schematic");

        MPItems.ZELIUS_FISH = new ItemSpaceFish("zelius_fish", ItemSpaceFish.ItemType.ZELIUS_FISH);
        MPItems.GLOWING_ALIEN_FISH = new ItemSpaceFish("glowing_alien_fish", ItemSpaceFish.ItemType.GLOWING_ALIEN_FISH);
        MPItems.CHEESE_FISH = new ItemSpaceFish("cheese_fish", ItemSpaceFish.ItemType.CHEESE_FISH);

        MPItems.SPACE_WARPER_CORE = new ItemSpaceWarperCore("space_warper_core");
        MPItems.SPACE_BOW = new ItemSpaceBow("space_bow");
        MPItems.SPACE_FISHING_ROD = new ItemSpaceFishingRod("space_fishing_rod");
        MPItems.DYE = new ItemDyeMP("dye_mp");
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

        /**************************************************************/
        /**********************REGISTER STUFF**************************/
        /**************************************************************/

        // Capsule
        BlocksItemsRegistry.registerItem(MPItems.EMPTY_CAPSULE);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE);
        BlocksItemsRegistry.registerItem(MPItems.DARK_ENERGY_PROTECTION_CAPSULE);

        BlocksItemsRegistry.registerItem(MPItems.ION_CANNON_SCHEMATIC);
        BlocksItemsRegistry.registerItem(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC);

        BlocksItemsRegistry.registerItem(MPItems.ZELIUS_FISH);
        BlocksItemsRegistry.registerItem(MPItems.GLOWING_ALIEN_FISH);
        BlocksItemsRegistry.registerItem(MPItems.CHEESE_FISH);

        BlocksItemsRegistry.registerItem(MPItems.SPACE_WARPER_CORE);
        BlocksItemsRegistry.registerItem(MPItems.SPACE_BOW);
        BlocksItemsRegistry.registerItem(MPItems.SPACE_FISHING_ROD);
        BlocksItemsRegistry.registerItem(MPItems.DYE);
        BlocksItemsRegistry.registerItem(MPItems.LASER_BULLET);
        BlocksItemsRegistry.registerItem(MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET);
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

        DionaItems.init();
        ChalosItems.init();
        NibiruItems.init();
        FronosItems.init();
    }
}