package stevekung.mods.moreplanets.init;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPLootTables
{
    // Entity
    public static final ResourceLocation INFECTED_CRYSTALLIZE_SPIDER = CommonRegisterHelper.registerEntityLoot("infected_crystallize_spider");
    public static final ResourceLocation INFECTED_CRYSTALLIZE_SLIME_BOSS = CommonRegisterHelper.registerEntityLoot("infected_crystallize_slime_boss");
    public static final ResourceLocation INFECTED_CRYSTALLIZE_SLIME = CommonRegisterHelper.registerEntityLoot("infected_crystallize_slime");
    public static final ResourceLocation ZELIUS_CREEPER = CommonRegisterHelper.registerEntityLoot("zelius_creeper");
    public static final ResourceLocation ZELIUS_ZOMBIE = CommonRegisterHelper.registerEntityLoot("zelius_zombie");
    public static final ResourceLocation ZELIUS_SKELETON = CommonRegisterHelper.registerEntityLoot("zelius_skeleton");
    public static final ResourceLocation ALIEN_MINER = CommonRegisterHelper.registerEntityLoot("alien_miner");
    public static final ResourceLocation CHEESE_COW = CommonRegisterHelper.registerEntityLoot("cheese_cow");
    public static final ResourceLocation CHEESE_FLOATER = CommonRegisterHelper.registerEntityLoot("cheese_floater");
    public static final ResourceLocation CHEESE_SLIME = CommonRegisterHelper.registerEntityLoot("cheese_slime");
    public static final ResourceLocation INFECTED_GUARDIAN = CommonRegisterHelper.registerEntityLoot("infected_guardian");
    public static final ResourceLocation INFECTED_ELDER_GUARDIAN = CommonRegisterHelper.registerEntityLoot("infected_elder_guardian");
    public static final ResourceLocation INFECTED_SKELETON = CommonRegisterHelper.registerEntityLoot("infected_skeleton");
    public static final ResourceLocation INFECTED_SNOWMAN = CommonRegisterHelper.registerEntityLoot("infected_snowman");
    public static final ResourceLocation SHLIME = CommonRegisterHelper.registerEntityLoot("shlime");
    public static final ResourceLocation BEARRY = CommonRegisterHelper.registerEntityLoot("bearry");
    public static final ResourceLocation GIANT_BLUEBERRY = CommonRegisterHelper.registerEntityLoot("giant_blueberry");
    public static final ResourceLocation MARSHMALLOW = CommonRegisterHelper.registerEntityLoot("marshmallow");

    // Dyed
    public static final ResourceLocation SHLIME_WOOL_WHITE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "white");
    public static final ResourceLocation SHLIME_WOOL_ORANGE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "orange");
    public static final ResourceLocation SHLIME_WOOL_MAGENTA = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "magenta");
    public static final ResourceLocation SHLIME_WOOL_LIGHT_BLUE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "light_blue");
    public static final ResourceLocation SHLIME_WOOL_YELLOW = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "yellow");
    public static final ResourceLocation SHLIME_WOOL_LIME = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "lime");
    public static final ResourceLocation SHLIME_WOOL_PINK = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "pink");
    public static final ResourceLocation SHLIME_WOOL_GRAY = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "gray");
    public static final ResourceLocation SHLIME_WOOL_SILVER = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "silver");
    public static final ResourceLocation SHLIME_WOOL_CYAN = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "cyan");
    public static final ResourceLocation SHLIME_WOOL_PURPLE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "purple");
    public static final ResourceLocation SHLIME_WOOL_BLUE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "blue");
    public static final ResourceLocation SHLIME_WOOL_BROWN = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "brown");
    public static final ResourceLocation SHLIME_WOOL_GREEN = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "green");
    public static final ResourceLocation SHLIME_WOOL_RED = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "red");
    public static final ResourceLocation SHLIME_WOOL_BLACK = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "black");

    // Chests
    public static final ResourceLocation COMMON_SPACE_DUNGEON = CommonRegisterHelper.registerChestLoot("common_space_dungeon");
    public static final ResourceLocation COMMON_SPACE_MINESHAFT = CommonRegisterHelper.registerChestLoot("common_space_mineshaft");
    public static final ResourceLocation CRASHED_ALIEN_PROBE = CommonRegisterHelper.registerChestLoot("crashed_alien_probe");
    public static final ResourceLocation NIBIRU_JUNGLE_TEMPLE = CommonRegisterHelper.registerChestLoot("nibiru_jungle_temple");
    public static final ResourceLocation NIBIRU_JUNGLE_TEMPLE_DISPENSER = CommonRegisterHelper.registerChestLoot("nibiru_jungle_temple_dispenser");
    public static final ResourceLocation NIBIRU_DESERT_PYRAMID = CommonRegisterHelper.registerChestLoot("nibiru_desert_pyramid");
    public static final ResourceLocation NIBIRU_STRONGHOLD_CORRIDOR = CommonRegisterHelper.registerChestLoot("nibiru_stronghold_corridor");
    public static final ResourceLocation NIBIRU_STRONGHOLD_LIBRARY = CommonRegisterHelper.registerChestLoot("nibiru_stronghold_library");
    public static final ResourceLocation NIBIRU_STRONGHOLD_CROSSING = CommonRegisterHelper.registerChestLoot("nibiru_stronghold_crossing");
    public static final ResourceLocation NIBIRU_VILLAGE_LIBRARY = CommonRegisterHelper.registerChestLoot("nibiru_village_library");
    public static final ResourceLocation NIBIRU_VILLAGE_BLACKSMITH = CommonRegisterHelper.registerChestLoot("nibiru_village_blacksmith");

    // Gameplay
    public static final ResourceLocation SPACE_FISHING = CommonRegisterHelper.registerGameplayLoot("space_fishing");
    public static final ResourceLocation SPACE_FISH_JUNK = CommonRegisterHelper.registerFishingLoot("junk_space");
    public static final ResourceLocation SPACE_FISH_TREASURE = CommonRegisterHelper.registerFishingLoot("treasure_space");
    public static final ResourceLocation CRYSTALLIZE_WATER_FISHING = CommonRegisterHelper.registerGameplayLoot("crystallize_water_fishing");
    public static final ResourceLocation CHEESE_OF_MILK_FISHING = CommonRegisterHelper.registerGameplayLoot("cheese_of_milk_fishing");
    public static final ResourceLocation CRYSTALLIZE_WATER_FISH = CommonRegisterHelper.registerFishingLoot("crystallize_fish");
    public static final ResourceLocation CHEESE_FISH = CommonRegisterHelper.registerFishingLoot("cheese_fish");

    public static ItemStack getTieredKey(Random rand, int tier)
    {
        return GalacticraftRegistry.getDungeonLoot(tier).get(rand.nextInt(GalacticraftRegistry.getDungeonLoot(tier).size())).copy();
    }
}