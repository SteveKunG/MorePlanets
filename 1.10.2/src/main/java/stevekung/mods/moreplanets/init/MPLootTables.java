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
    public static ResourceLocation CHEESE_COW = CommonRegisterHelper.registerEntityLoot("cheese_cow");
    public static ResourceLocation CHEESE_FLOATER = CommonRegisterHelper.registerEntityLoot("cheese_floater");
    public static ResourceLocation CHEESE_SLIME = CommonRegisterHelper.registerEntityLoot("cheese_slime");

    // Chests
    public static ResourceLocation COMMON_SPACE_DUNGEON = CommonRegisterHelper.registerChestLoot("common_space_dungeon");

    public static ItemStack getTieredKey(Random rand, int tier)
    {
        return GalacticraftRegistry.getDungeonLoot(tier).get(rand.nextInt(GalacticraftRegistry.getDungeonLoot(tier).size())).copy();
    }
}