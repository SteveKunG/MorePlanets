package stevekung.mods.moreplanets.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPOreDictionary
{
    public static void init()
    {
        CommonRegisterHelper.registerOreDictionary("glowstone", MPBlocks.DUNGEON_GLOWSTONE);
        CommonRegisterHelper.registerOreDictionary("slabWood", new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("blockGlass", new ItemStack(MPBlocks.TINTED_GLASS, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("paneGlass", new ItemStack(MPBlocks.TINTED_GLASS_PANE, 1, OreDictionary.WILDCARD_VALUE));
        
        CommonRegisterHelper.registerOreDictionary("plankWood", DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS);
        CommonRegisterHelper.registerOreDictionary("stone", new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 2));
        CommonRegisterHelper.registerOreDictionary("cobblestone", new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 3));
        CommonRegisterHelper.registerOreDictionary("blockSlime", DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK);
        CommonRegisterHelper.registerOreDictionary("chestWood", DionaBlocks.DIONA_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", DionaBlocks.DIONA_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("torch", DionaBlocks.INFECTED_CRYSTALLIZE_TORCH);
        
        CommonRegisterHelper.registerOreDictionary("plankWood", new ItemStack(ChalosBlocks.CHEESE_SPORE_PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("logWood", new ItemStack(ChalosBlocks.CHEESE_SPORE_STEM, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("stairWood", new ItemStack(ChalosBlocks.CHEESE_SPORE_STAIRS));
        CommonRegisterHelper.registerOreDictionary("dirt", new ItemStack(ChalosBlocks.CHEESE_DIRT, 1, 0));
        CommonRegisterHelper.registerOreDictionary("grass", ChalosBlocks.CHEESE_GRASS);
        CommonRegisterHelper.registerOreDictionary("stone", new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 0));
        CommonRegisterHelper.registerOreDictionary("cobblestone", new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 1));
        CommonRegisterHelper.registerOreDictionary("blockSlime", ChalosBlocks.CHEESE_SLIME_BLOCK);
        CommonRegisterHelper.registerOreDictionary("workbench", ChalosBlocks.CHALOS_CRAFTING_TABLE);
        CommonRegisterHelper.registerOreDictionary("chestWood", ChalosBlocks.CHEESE_SPORE_CHEST);
        CommonRegisterHelper.registerOreDictionary("chestWood", ChalosBlocks.CHALOS_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", ChalosBlocks.CHEESE_SPORE_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", ChalosBlocks.CHALOS_ANCIENT_CHEST);
        
        CommonRegisterHelper.registerOreDictionary("plankWood", new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("logWood", new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("treeLeaves", new ItemStack(NibiruBlocks.NIBIRU_LEAVES, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("treeSapling", new ItemStack(NibiruBlocks.NIBIRU_SAPLING, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("stairWood", NibiruBlocks.INFECTED_OAK_STAIRS);
        CommonRegisterHelper.registerOreDictionary("stairWood", NibiruBlocks.INFECTED_DEAD_OAK_STAIRS);
        CommonRegisterHelper.registerOreDictionary("stairWood", NibiruBlocks.ALIEN_BERRY_OAK_STAIRS);
        CommonRegisterHelper.registerOreDictionary("grass", NibiruBlocks.INFECTED_GRASS);
        CommonRegisterHelper.registerOreDictionary("grass", NibiruBlocks.GREEN_VEIN_GRASS);
        CommonRegisterHelper.registerOreDictionary("dirt", new ItemStack(NibiruBlocks.INFECTED_DIRT, 1, 0));
        CommonRegisterHelper.registerOreDictionary("stone", new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 0));
        CommonRegisterHelper.registerOreDictionary("cobblestone", new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1));
        CommonRegisterHelper.registerOreDictionary("sand", NibiruBlocks.INFECTED_SAND);
        CommonRegisterHelper.registerOreDictionary("gravel", NibiruBlocks.INFECTED_GRAVEL);
        CommonRegisterHelper.registerOreDictionary("gravel", NibiruBlocks.PURIFY_GRAVEL);
        CommonRegisterHelper.registerOreDictionary("blockPrismarine", new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("blockPrismarineBrick", new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 1));
        CommonRegisterHelper.registerOreDictionary("blockPrismarineDark", new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 2));
        CommonRegisterHelper.registerOreDictionary("oreCoal", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("oreIron", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 1));
        CommonRegisterHelper.registerOreDictionary("oreGold", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 2));
        CommonRegisterHelper.registerOreDictionary("oreDiamond", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 3));
        CommonRegisterHelper.registerOreDictionary("oreRedstone", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 4));
        CommonRegisterHelper.registerOreDictionary("oreLapis", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 5));
        CommonRegisterHelper.registerOreDictionary("oreEmerald", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 6));
        CommonRegisterHelper.registerOreDictionary("workbench", new ItemStack(NibiruBlocks.NIBIRU_CRAFTING_TABLE, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("chestWood", NibiruBlocks.INFECTED_CHEST);
        CommonRegisterHelper.registerOreDictionary("chestWood", NibiruBlocks.ALIEN_BERRY_CHEST);
        CommonRegisterHelper.registerOreDictionary("chestWood", NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", NibiruBlocks.INFECTED_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", NibiruBlocks.ALIEN_BERRY_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("blockCactus", NibiruBlocks.INFECTED_CACTUS);
        CommonRegisterHelper.registerOreDictionary("vine", NibiruBlocks.INFECTED_VINES);
        CommonRegisterHelper.registerOreDictionary("torch", NibiruBlocks.INFECTED_TORCH);
        CommonRegisterHelper.registerOreDictionary("nibiruSandstone", new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("nibiruSandstone", new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 1));
        
        CommonRegisterHelper.registerOreDictionary("blockCandy", FronosBlocks.CANDY_CANE_1);
        CommonRegisterHelper.registerOreDictionary("blockCandy", FronosBlocks.CANDY_CANE_2);
        
        CommonRegisterHelper.registerOreDictionary("dyeBlue", MPItems.DYE);
        
        CommonRegisterHelper.registerOreDictionary("slimeball", DionaItems.INFECTED_CRYSTALLIZE_SLIMEBALL);
        
        CommonRegisterHelper.registerOreDictionary("slimeball", ChalosItems.CHEESE_SLIMEBALL);
        
        CommonRegisterHelper.registerOreDictionary("cropWheat", NibiruItems.INFECTED_WHEAT);
        CommonRegisterHelper.registerOreDictionary("sugarcane", NibiruItems.INFECTED_SUGAR_CANE);
        CommonRegisterHelper.registerOreDictionary("gemPrismarine", new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("dustPrismarine", new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 1));
        CommonRegisterHelper.registerOreDictionary("egg", NibiruItems.INFECTED_EGG);
    }
}