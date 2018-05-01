package stevekung.mods.moreplanets.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.stevekunglib.utils.BlockUtils;

public class MPOreDictionary
{
    public static void init()
    {
        BlockUtils.registerOreDictionary("glowstone", MPBlocks.DUNGEON_GLOWSTONE);
        //        BlockUtils.registerOreDictionary("slabWood", new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 1, OreDictionary.WILDCARD_VALUE));
        //        BlockUtils.registerOreDictionary("blockGlass", new ItemStack(MPBlocks.TINTED_GLASS, 1, OreDictionary.WILDCARD_VALUE));
        //        BlockUtils.registerOreDictionary("paneGlass", new ItemStack(MPBlocks.TINTED_GLASS_PANE, 1, OreDictionary.WILDCARD_VALUE));
        BlockUtils.registerOreDictionary("plankWood", DionaBlocks.INFECTED_CRYSTALLIZED_PLANKS);
        BlockUtils.registerOreDictionary("stone", DionaBlocks.DIONA_ROCK);
        BlockUtils.registerOreDictionary("cobblestone", DionaBlocks.DIONA_COBBLESTONE);
        BlockUtils.registerOreDictionary("blockSlime", DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);
        BlockUtils.registerOreDictionary("chestWood", DionaBlocks.DIONA_ANCIENT_CHEST);
        BlockUtils.registerOreDictionary("chest", DionaBlocks.DIONA_ANCIENT_CHEST);
        BlockUtils.registerOreDictionary("torch", DionaBlocks.INFECTED_CRYSTALLIZED_TORCH);
        BlockUtils.registerOreDictionary("plankWood", new ItemStack(ChalosBlocks.CHEESE_SPORE_PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        BlockUtils.registerOreDictionary("logWood", new ItemStack(ChalosBlocks.CHEESE_SPORE_STEM, 1, OreDictionary.WILDCARD_VALUE));
        BlockUtils.registerOreDictionary("stairWood", new ItemStack(ChalosBlocks.CHEESE_SPORE_STAIRS));
        BlockUtils.registerOreDictionary("dirt", ChalosBlocks.CHEESE_DIRT);
        BlockUtils.registerOreDictionary("grass", ChalosBlocks.CHEESE_GRASS_BLOCK);
        BlockUtils.registerOreDictionary("stone", ChalosBlocks.CHALOS_ROCK);
        BlockUtils.registerOreDictionary("cobblestone", ChalosBlocks.CHALOS_ROCK);
        BlockUtils.registerOreDictionary("blockSlime", ChalosBlocks.CHEESE_SLIME_BLOCK);
        BlockUtils.registerOreDictionary("workbench", ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE);
        BlockUtils.registerOreDictionary("chestWood", ChalosBlocks.CHEESE_SPORE_CHEST);
        BlockUtils.registerOreDictionary("chestWood", ChalosBlocks.CHALOS_ANCIENT_CHEST);
        BlockUtils.registerOreDictionary("chest", ChalosBlocks.CHEESE_SPORE_CHEST);
        BlockUtils.registerOreDictionary("chest", ChalosBlocks.CHALOS_ANCIENT_CHEST);
        BlockUtils.registerOreDictionary("plankWood", NibiruBlocks.INFECTED_OAK_PLANKS);
        BlockUtils.registerOreDictionary("plankWood", NibiruBlocks.ALIEN_BERRY_OAK_PLANKS);
        BlockUtils.registerOreDictionary("logWood", NibiruBlocks.INFECTED_OAK_LOG);
        BlockUtils.registerOreDictionary("logWood", NibiruBlocks.INFECTED_JUNGLE_LOG);
        BlockUtils.registerOreDictionary("logWood", NibiruBlocks.ALIEN_BERRY_OAK_LOG);
        BlockUtils.registerOreDictionary("treeLeaves", NibiruBlocks.INFECTED_OAK_LEAVES);
        BlockUtils.registerOreDictionary("treeLeaves", NibiruBlocks.INFECTED_JUNGLE_LEAVES);
        BlockUtils.registerOreDictionary("treeLeaves", NibiruBlocks.ALIEN_BERRY_OAK_LEAVES);
        BlockUtils.registerOreDictionary("treeSapling", NibiruBlocks.INFECTED_OAK_SAPLING);
        BlockUtils.registerOreDictionary("treeSapling", NibiruBlocks.INFECTED_JUNGLE_SAPLING);
        BlockUtils.registerOreDictionary("treeSapling", NibiruBlocks.ALIEN_BERRY_OAK_SAPLING);
        BlockUtils.registerOreDictionary("stairWood", NibiruBlocks.INFECTED_OAK_STAIRS);
        BlockUtils.registerOreDictionary("stairWood", NibiruBlocks.ALIEN_BERRY_OAK_STAIRS);
        BlockUtils.registerOreDictionary("grass", NibiruBlocks.INFECTED_GRASS_BLOCK);
        BlockUtils.registerOreDictionary("grass", NibiruBlocks.GREEN_VEIN_GRASS_BLOCK);
        BlockUtils.registerOreDictionary("dirt", NibiruBlocks.INFECTED_DIRT);
        BlockUtils.registerOreDictionary("stone", NibiruBlocks.NIBIRU_ROCK);
        BlockUtils.registerOreDictionary("cobblestone", NibiruBlocks.NIBIRU_COBBLESTONE);
        BlockUtils.registerOreDictionary("cobblestone", NibiruBlocks.NIBIRU_VEIN_COBBLESTONE);
        BlockUtils.registerOreDictionary("sand", NibiruBlocks.INFECTED_SAND);
        BlockUtils.registerOreDictionary("gravel", NibiruBlocks.INFECTED_GRAVEL);
        BlockUtils.registerOreDictionary("gravel", NibiruBlocks.PURIFY_GRAVEL);
        BlockUtils.registerOreDictionary("blockPrismarine", NibiruBlocks.INFECTED_PRISMARINE);
        BlockUtils.registerOreDictionary("blockPrismarineBrick", NibiruBlocks.INFECTED_PRISMARINE_BRICKS);
        BlockUtils.registerOreDictionary("blockPrismarineDark", NibiruBlocks.INFECTED_DARK_PRISMARINE);
        BlockUtils.registerOreDictionary("oreCoal", NibiruBlocks.INFECTED_COAL_ORE);
        BlockUtils.registerOreDictionary("oreIron", NibiruBlocks.INFECTED_IRON_ORE);
        BlockUtils.registerOreDictionary("oreGold", NibiruBlocks.INFECTED_GOLD_ORE);
        BlockUtils.registerOreDictionary("oreDiamond", NibiruBlocks.INFECTED_DIAMOND_ORE);
        BlockUtils.registerOreDictionary("oreRedstone", NibiruBlocks.INFECTED_REDSTONE_ORE);
        BlockUtils.registerOreDictionary("oreLapis", NibiruBlocks.INFECTED_LAPIS_ORE);
        BlockUtils.registerOreDictionary("oreEmerald", NibiruBlocks.INFECTED_EMERALD_ORE);
        BlockUtils.registerOreDictionary("workbench", NibiruBlocks.INFECTED_CRAFTING_TABLE);
        BlockUtils.registerOreDictionary("workbench", NibiruBlocks.ALIEN_BERRY_CRAFTING_TABLE);
        BlockUtils.registerOreDictionary("chestWood", NibiruBlocks.INFECTED_CHEST);
        BlockUtils.registerOreDictionary("chestWood", NibiruBlocks.ALIEN_BERRY_CHEST);
        BlockUtils.registerOreDictionary("chestWood", NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        BlockUtils.registerOreDictionary("chest", NibiruBlocks.INFECTED_CHEST);
        BlockUtils.registerOreDictionary("chest", NibiruBlocks.ALIEN_BERRY_CHEST);
        BlockUtils.registerOreDictionary("chest", NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        BlockUtils.registerOreDictionary("blockCactus", NibiruBlocks.INFECTED_CACTUS);
        BlockUtils.registerOreDictionary("vine", NibiruBlocks.INFECTED_VINES);
        BlockUtils.registerOreDictionary("torch", NibiruBlocks.INFECTED_TORCH);
        BlockUtils.registerOreDictionary("nibiruSandstone", NibiruBlocks.INFECTED_SANDSTONE);
        BlockUtils.registerOreDictionary("nibiruSandstone", NibiruBlocks.INFECTED_CHISELED_SANDSTONE);
        BlockUtils.registerOreDictionary("nibiruSandstone", NibiruBlocks.INFECTED_CUT_SANDSTONE);

        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.RED_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.GREEN_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.BLUE_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.ORANGE_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.PINK_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.YELLOW_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.PURPLE_CANDY_CANE);
        BlockUtils.registerOreDictionary("blockCandy", FronosBlocks.RAINBOW_CANDY_CANE);

        BlockUtils.registerOreDictionary("dyeBlue", MPItems.DYE);
        BlockUtils.registerOreDictionary("slimeball", DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL);
        BlockUtils.registerOreDictionary("slimeball", ChalosItems.CHEESE_SLIMEBALL);
        BlockUtils.registerOreDictionary("cropWheat", NibiruItems.INFECTED_WHEAT);
        BlockUtils.registerOreDictionary("sugarcane", NibiruItems.INFECTED_SUGAR_CANE);
        BlockUtils.registerOreDictionary("gemPrismarine", NibiruItems.INFECTED_PRISMARINE_SHARD);
        BlockUtils.registerOreDictionary("dustPrismarine", NibiruItems.INFECTED_PRISMARINE_CRYSTALS);
        BlockUtils.registerOreDictionary("egg", NibiruItems.INFECTED_EGG);
        BlockUtils.registerOreDictionary("oreSetrorium", DionaBlocks.SETRORIUM_ORE);
        BlockUtils.registerOreDictionary("oreIllenium", DionaBlocks.ILLENIUM_ORE);
        BlockUtils.registerOreDictionary("oreTin", DionaBlocks.DIONA_TIN_ORE);
        BlockUtils.registerOreDictionary("oreCopper", DionaBlocks.DIONA_COPPER_ORE);
        BlockUtils.registerOreDictionary("oreAluminum", DionaBlocks.DIONA_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreAluminium", DionaBlocks.DIONA_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreDirensium", ChalosBlocks.DIREMSIUM_ORE);
        BlockUtils.registerOreDictionary("oreZyptorium", ChalosBlocks.ZYPTORIUM_ORE);
        BlockUtils.registerOreDictionary("oreCheese", ChalosBlocks.CHEESE_MILK_ORE);
        BlockUtils.registerOreDictionary("oreCheeseMilk", ChalosBlocks.CHEESE_MILK_ORE);
        BlockUtils.registerOreDictionary("oreIron", ChalosBlocks.CHALOS_IRON_ORE);
        BlockUtils.registerOreDictionary("oreTin", ChalosBlocks.CHALOS_TIN_ORE);
        BlockUtils.registerOreDictionary("oreCopper", ChalosBlocks.CHALOS_COPPER_ORE);
        BlockUtils.registerOreDictionary("oreAluminum", ChalosBlocks.CHALOS_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreAluminium", ChalosBlocks.CHALOS_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreOil", NibiruBlocks.OIL_ORE);
        BlockUtils.registerOreDictionary("oreInferumite", NibiruBlocks.INFERUMITE_CRYSTAL_ORE);
        BlockUtils.registerOreDictionary("oreSilicon", NibiruBlocks.INFECTED_SILICON_ORE);
        BlockUtils.registerOreDictionary("oreTin", NibiruBlocks.INFECTED_TIN_ORE);
        BlockUtils.registerOreDictionary("oreCopper", NibiruBlocks.INFECTED_COPPER_ORE);
        BlockUtils.registerOreDictionary("oreAluminum", NibiruBlocks.INFECTED_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreAluminium", NibiruBlocks.INFECTED_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreIron", FronosBlocks.FRONOS_IRON_ORE);
        BlockUtils.registerOreDictionary("oreGold", FronosBlocks.FRONOS_GOLD_ORE);
        BlockUtils.registerOreDictionary("oreTin", FronosBlocks.FRONOS_TIN_ORE);
        BlockUtils.registerOreDictionary("oreCopper", FronosBlocks.FRONOS_COPPER_ORE);
        BlockUtils.registerOreDictionary("oreAluminum", FronosBlocks.FRONOS_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreAluminium", FronosBlocks.FRONOS_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreLead", FronosBlocks.FRONOS_LEAD_ORE);
        BlockUtils.registerOreDictionary("oreCoal", FronosBlocks.FRONOS_COAL_ORE);
        BlockUtils.registerOreDictionary("oreLapis", FronosBlocks.FRONOS_LAPIS_ORE);
        BlockUtils.registerOreDictionary("oreDiamond", FronosBlocks.FRONOS_DIAMOND_ORE);
        BlockUtils.registerOreDictionary("oreEmerald", FronosBlocks.FRONOS_EMERALD_ORE);
        BlockUtils.registerOreDictionary("oreRedstone", FronosBlocks.FRONOS_REDSTONE_ORE);
        BlockUtils.registerOreDictionary("oreSilicon", FronosBlocks.FRONOS_SILICON_ORE);
        BlockUtils.registerOreDictionary("oreQuartz", FronosBlocks.FRONOS_QUARTZ_ORE);
        BlockUtils.registerOreDictionary("oreExtrailonite", FronosBlocks.EXTRAILONITE_ORE);
        BlockUtils.registerOreDictionary("oreAntiGrav", KoentusBlocks.ANTI_GRAVITY_ORE);
        BlockUtils.registerOreDictionary("oreGoldenite", KoentusBlocks.GOLDENITE_CRYSTALS_ORE);
        BlockUtils.registerOreDictionary("oreTin", KoentusBlocks.KOENTUS_TIN_ORE);
        BlockUtils.registerOreDictionary("oreCopper", KoentusBlocks.KOENTUS_COPPER_ORE);
        BlockUtils.registerOreDictionary("oreAluminum", KoentusBlocks.KOENTUS_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreAluminium", KoentusBlocks.KOENTUS_ALUMINUM_ORE);
        BlockUtils.registerOreDictionary("oreIron", KoentusBlocks.KOENTUS_IRON_ORE);
    }
}