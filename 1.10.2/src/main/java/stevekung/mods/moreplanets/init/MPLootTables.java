package stevekung.mods.moreplanets.init;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPLootTables
{
    // Entity
    public static ResourceLocation INFECTED_CRYSTALLIZE_SPIDER = CommonRegisterHelper.registerEntityLoot("infected_crystallize_spider");
    public static ResourceLocation INFECTED_CRYSTALLIZE_SLIME_BOSS = CommonRegisterHelper.registerEntityLoot("infected_crystallize_slime_boss");
    public static ResourceLocation INFECTED_CRYSTALLIZE_SLIME = CommonRegisterHelper.registerEntityLoot("infected_crystallize_slime");
    public static ResourceLocation ZELIUS_CREEPER = CommonRegisterHelper.registerEntityLoot("zelius_creeper");
    public static ResourceLocation ZELIUS_ZOMBIE = CommonRegisterHelper.registerEntityLoot("zelius_zombie");
    public static ResourceLocation ZELIUS_SKELETON = CommonRegisterHelper.registerEntityLoot("zelius_skeleton");
    public static ResourceLocation ALIEN_MINER = CommonRegisterHelper.registerEntityLoot("alien_miner");
    public static ResourceLocation CHEESE_COW = CommonRegisterHelper.registerEntityLoot("cheese_cow");
    public static ResourceLocation CHEESE_FLOATER = CommonRegisterHelper.registerEntityLoot("cheese_floater");
    public static ResourceLocation CHEESE_SLIME = CommonRegisterHelper.registerEntityLoot("cheese_slime");
    public static ResourceLocation INFECTED_GUARDIAN = CommonRegisterHelper.registerEntityLoot("infected_guardian");
    public static ResourceLocation INFECTED_ELDER_GUARDIAN = CommonRegisterHelper.registerEntityLoot("infected_elder_guardian");
    public static ResourceLocation INFECTED_SKELETON = CommonRegisterHelper.registerEntityLoot("infected_skeleton");
    public static ResourceLocation INFECTED_SNOWMAN = CommonRegisterHelper.registerEntityLoot("infected_snowman");
    public static ResourceLocation SHLIME = CommonRegisterHelper.registerEntityLoot("shlime");
    public static ResourceLocation BEARRY = CommonRegisterHelper.registerEntityLoot("bearry");
    public static ResourceLocation GIANT_BLUEBERRY = CommonRegisterHelper.registerEntityLoot("giant_blueberry");
    public static ResourceLocation MARSHMALLOW = CommonRegisterHelper.registerEntityLoot("marshmallow");

    // Dyed
    public static ResourceLocation SHLIME_WOOL_WHITE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "white");
    public static ResourceLocation SHLIME_WOOL_ORANGE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "orange");
    public static ResourceLocation SHLIME_WOOL_MAGENTA = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "magenta");
    public static ResourceLocation SHLIME_WOOL_LIGHT_BLUE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "light_blue");
    public static ResourceLocation SHLIME_WOOL_YELLOW = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "yellow");
    public static ResourceLocation SHLIME_WOOL_LIME = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "lime");
    public static ResourceLocation SHLIME_WOOL_PINK = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "pink");
    public static ResourceLocation SHLIME_WOOL_GRAY = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "gray");
    public static ResourceLocation SHLIME_WOOL_SILVER = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "silver");
    public static ResourceLocation SHLIME_WOOL_CYAN = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "cyan");
    public static ResourceLocation SHLIME_WOOL_PURPLE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "purple");
    public static ResourceLocation SHLIME_WOOL_BLUE = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "blue");
    public static ResourceLocation SHLIME_WOOL_BROWN = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "brown");
    public static ResourceLocation SHLIME_WOOL_GREEN = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "green");
    public static ResourceLocation SHLIME_WOOL_RED = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "red");
    public static ResourceLocation SHLIME_WOOL_BLACK = CommonRegisterHelper.registerEntityDyeLoot("shlime_wool", "black");

    // Chests
    public static ResourceLocation COMMON_SPACE_DUNGEON = CommonRegisterHelper.registerChestLoot("common_space_dungeon");
    public static ResourceLocation COMMON_SPACE_MINESHAFT = CommonRegisterHelper.registerChestLoot("common_space_mineshaft");

    public static ItemStack getTieredKey(Random rand, int tier)
    {
        return GalacticraftRegistry.getDungeonLoot(tier).get(rand.nextInt(GalacticraftRegistry.getDungeonLoot(tier).size())).copy();
    }
}