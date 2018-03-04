package stevekung.mods.moreplanets.init;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.items.*;
import stevekung.mods.moreplanets.items.capsule.ItemCapsule;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;
import stevekung.mods.moreplanets.util.items.ItemRecordMP;

public class MPItems
{
    public static Item SPACE_WARPER_CORE;
    public static Item CAPSULE;
    public static Item SPACE_BOW;
    public static Item SPACE_FISHING_ROD;
    public static Item DYE;
    public static Item LASER_BULLET;
    public static Item LASER_GUN;
    public static Item SPACE_FISH;
    public static Item SPECIAL_SCHEMATIC;
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

        MPItems.SPACE_WARPER_CORE = new ItemSpaceWarperCore("space_warper_core");
        MPItems.CAPSULE = new ItemCapsule("capsule");
        MPItems.SPACE_BOW = new ItemSpaceBow("space_bow");
        MPItems.SPACE_FISHING_ROD = new ItemSpaceFishingRod("space_fishing_rod");
        MPItems.DYE = new ItemDyeMP("dye_mp");
        MPItems.LASER_BULLET = new ItemLaserBullet("laser_bullet");
        MPItems.LASER_GUN = new ItemLaserGun("laser_gun");
        MPItems.SPACE_FISH = new ItemSpaceFish("space_fish");
        MPItems.SPECIAL_SCHEMATIC = new ItemSpecialSchematic("special_schematic");
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

        CommonRegisterHelper.registerItem(MPItems.SPACE_WARPER_CORE);
        CommonRegisterHelper.registerItem(MPItems.CAPSULE);
        CommonRegisterHelper.registerItem(MPItems.SPACE_BOW);
        CommonRegisterHelper.registerItem(MPItems.SPACE_FISHING_ROD);
        CommonRegisterHelper.registerItem(MPItems.DYE);
        CommonRegisterHelper.registerItem(MPItems.LASER_BULLET);
        CommonRegisterHelper.registerItem(MPItems.LASER_GUN);
        CommonRegisterHelper.registerItem(MPItems.SPACE_FISH);
        CommonRegisterHelper.registerItem(MPItems.SPECIAL_SCHEMATIC);
        CommonRegisterHelper.registerItem(MPItems.ALIEN_DEFENDER_REINFORCEMENT);
        CommonRegisterHelper.registerItem(MPItems.CREATIVE_SPACE_KIT);
        CommonRegisterHelper.registerItem(MPItems.VEIN_FLOATER_DISC);
        CommonRegisterHelper.registerItem(MPItems.UPGRADE_TEMPLATE);
        CommonRegisterHelper.registerItem(MPItems.SHIELD_DAMAGE_UPGRADE);
        CommonRegisterHelper.registerItem(MPItems.SHIELD_SIZE_UPGRADE);
        CommonRegisterHelper.registerItem(MPItems.SHIELD_CAPACITY_UPGRADE);
        CommonRegisterHelper.registerItem(MPItems.MACHINE_SPEED_UPGRADE);
        CommonRegisterHelper.registerItem(MPItems.GRAVITY_AMULET);

        /**************************************************************/
        /********************ORE DICTIONARY STUFF**********************/
        /**************************************************************/

        CommonRegisterHelper.registerOreDictionary("dyeBlue", MPItems.DYE);

        DionaItems.init();
        ChalosItems.init();
        NibiruItems.init();
        FronosItems.init();
    }
}