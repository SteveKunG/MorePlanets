package stevekung.mods.moreplanets.init;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public class MPLootTables
{
    // Entity
    public static final ResourceLocation INFECTED_CRYSTALLIZED_SPIDER = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_crystallized_spider");
    public static final ResourceLocation INFECTED_CRYSTALLIZED_SLIME_BOSS = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_crystallized_slime_boss");
    public static final ResourceLocation INFECTED_CRYSTALLIZED_SLIME = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_crystallized_slime");
    public static final ResourceLocation ZELIUS_CREEPER = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("zelius_creeper");
    public static final ResourceLocation ZELIUS_ZOMBIE = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("zelius_zombie");
    public static final ResourceLocation ZELIUS_SKELETON = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("zelius_skeleton");
    public static final ResourceLocation ALIEN_MINER = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("alien_miner");
    public static final ResourceLocation CHEESE_COW = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("cheese_cow");
    public static final ResourceLocation CHEESE_FLOATER = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("cheese_floater");
    public static final ResourceLocation CHEESE_SLIME = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("cheese_slime");
    public static final ResourceLocation INFECTED_GUARDIAN = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_guardian");
    public static final ResourceLocation INFECTED_ELDER_GUARDIAN = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_elder_guardian");
    public static final ResourceLocation INFECTED_SKELETON = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_skeleton");
    public static final ResourceLocation INFECTED_SNOWMAN = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("infected_snowman");
    public static final ResourceLocation SHLIME = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("shlime");
    public static final ResourceLocation BEARRY = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("bearry");
    public static final ResourceLocation GIANT_BLUEBERRY = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("giant_blueberry");
    public static final ResourceLocation MARSHMALLOW = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("marshmallow");
    public static final ResourceLocation TERRASTONE_GOLEM = MorePlanetsMod.COMMON_REGISTRY.registerEntityLoot("terrastone_golem");

    // Jelly Slime
    public static final ResourceLocation JELLY_SLIME_GRAPE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "grape");
    public static final ResourceLocation JELLY_SLIME_RASPBERRY = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "raspberry");
    public static final ResourceLocation JELLY_SLIME_STRAWBERRY = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "strawberry");
    public static final ResourceLocation JELLY_SLIME_BERRY = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "berry");
    public static final ResourceLocation JELLY_SLIME_LIME = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "lime");
    public static final ResourceLocation JELLY_SLIME_ORANGE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "orange");
    public static final ResourceLocation JELLY_SLIME_GREEN = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "green");
    public static final ResourceLocation JELLY_SLIME_LEMON = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "lemon");

    // Dyed
    public static final ResourceLocation SHLIME_WOOL_WHITE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "white");
    public static final ResourceLocation SHLIME_WOOL_ORANGE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "orange");
    public static final ResourceLocation SHLIME_WOOL_MAGENTA = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "magenta");
    public static final ResourceLocation SHLIME_WOOL_LIGHT_BLUE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "light_blue");
    public static final ResourceLocation SHLIME_WOOL_YELLOW = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "yellow");
    public static final ResourceLocation SHLIME_WOOL_LIME = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "lime");
    public static final ResourceLocation SHLIME_WOOL_PINK = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "pink");
    public static final ResourceLocation SHLIME_WOOL_GRAY = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "gray");
    public static final ResourceLocation SHLIME_WOOL_SILVER = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "silver");
    public static final ResourceLocation SHLIME_WOOL_CYAN = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "cyan");
    public static final ResourceLocation SHLIME_WOOL_PURPLE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "purple");
    public static final ResourceLocation SHLIME_WOOL_BLUE = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "blue");
    public static final ResourceLocation SHLIME_WOOL_BROWN = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "brown");
    public static final ResourceLocation SHLIME_WOOL_GREEN = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "green");
    public static final ResourceLocation SHLIME_WOOL_RED = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "red");
    public static final ResourceLocation SHLIME_WOOL_BLACK = MorePlanetsMod.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "black");

    // Chests
    public static final ResourceLocation COMMON_SPACE_DUNGEON = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("common_space_dungeon");
    public static final ResourceLocation COMMON_SPACE_MINESHAFT = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("common_space_mineshaft");
    public static final ResourceLocation CRASHED_ALIEN_PROBE = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("crashed_alien_probe");
    public static final ResourceLocation NIBIRU_JUNGLE_TEMPLE = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_jungle_temple");
    public static final ResourceLocation NIBIRU_JUNGLE_TEMPLE_DISPENSER = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_jungle_temple_dispenser");
    public static final ResourceLocation NIBIRU_DESERT_PYRAMID = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_desert_pyramid");
    public static final ResourceLocation NIBIRU_STRONGHOLD_CORRIDOR = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_stronghold_corridor");
    public static final ResourceLocation NIBIRU_STRONGHOLD_LIBRARY = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_stronghold_library");
    public static final ResourceLocation NIBIRU_STRONGHOLD_CROSSING = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_stronghold_crossing");
    public static final ResourceLocation NIBIRU_VILLAGE_LIBRARY = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_village_library");
    public static final ResourceLocation NIBIRU_VILLAGE_BLACKSMITH = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("nibiru_village_blacksmith");
    public static final ResourceLocation CRASHED_ALIEN_SHIP = MorePlanetsMod.COMMON_REGISTRY.registerChestLoot("crashed_alien_ship");

    // Gameplay
    public static final ResourceLocation SPACE_FISHING = MorePlanetsMod.COMMON_REGISTRY.registerGameplayLoot("space_fishing");
    public static final ResourceLocation SPACE_FISH_JUNK = MorePlanetsMod.COMMON_REGISTRY.registerFishingLoot("junk_space");
    public static final ResourceLocation SPACE_FISH_TREASURE = MorePlanetsMod.COMMON_REGISTRY.registerFishingLoot("treasure_space");
    public static final ResourceLocation CRYSTALLIZED_WATER_FISHING = MorePlanetsMod.COMMON_REGISTRY.registerGameplayLoot("crystallized_water_fishing");
    public static final ResourceLocation CHEESE_MILK_FISHING = MorePlanetsMod.COMMON_REGISTRY.registerGameplayLoot("cheese_milk_fishing");
    public static final ResourceLocation CRYSTALLIZED_WATER_FISH = MorePlanetsMod.COMMON_REGISTRY.registerFishingLoot("crystallized_fish");
    public static final ResourceLocation CHEESE_FISH = MorePlanetsMod.COMMON_REGISTRY.registerFishingLoot("cheese_fish");

    public static ItemStack getTieredKey(Random rand, int tier)
    {
        return GalacticraftRegistry.getDungeonLoot(tier).get(rand.nextInt(GalacticraftRegistry.getDungeonLoot(tier).size())).copy();
    }
}