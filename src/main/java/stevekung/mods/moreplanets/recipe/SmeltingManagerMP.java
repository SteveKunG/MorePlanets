package stevekung.mods.moreplanets.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class SmeltingManagerMP
{
    public static void init()
    {
        SmeltingManagerMP.addBlockSmelting();
        SmeltingManagerMP.addItemSmelting();
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE), new ItemStack(MPBlocks.DIONA_ROCK), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE), new ItemStack(MPBlocks.CHALOS_ROCK), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.NIBIRU_COBBLESTONE), new ItemStack(MPBlocks.NIBIRU_ROCK), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_STONE_BRICKS), new ItemStack(MPBlocks.INFECTED_CRACKED_STONE_BRICKS), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_WET_SPONGE), new ItemStack(MPBlocks.INFECTED_SPONGE), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_CLAY), new ItemStack(Blocks.HARDENED_CLAY), 0.35F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_COBBLESTONE), new ItemStack(MPBlocks.FRONOS_STONE), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_STONE_BRICKS), new ItemStack(MPBlocks.FRONOS_CRACKED_STONE_BRICKS), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.SETRORIUM_ORE), new ItemStack(MPItems.SETRORIUM_SHARD), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.ILLENIUM_ORE), new ItemStack(MPItems.ILLENIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIREMSIUM_ORE), new ItemStack(MPItems.DIREMSIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.ZYPTORIUM_ORE), new ItemStack(MPItems.ZYPTORIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHEESE_MILK_ORE), new ItemStack(MPItems.CHEESE_MILK_CURD), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPItems.RAW_CHEESE_BEEF), new ItemStack(MPItems.COOKED_CHEESE_BEEF), 0.35F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_STEM), new ItemStack(Items.COAL, 1, 1), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_COAL_ORE), new ItemStack(MPItems.INFECTED_COAL), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_GOLD_ORE), new ItemStack(Items.GOLD_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_DIAMOND_ORE), new ItemStack(Items.DIAMOND), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_REDSTONE_ORE), new ItemStack(Items.REDSTONE), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_LAPIS_ORE), new ItemStack(Items.DYE, 1, 4), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_EMERALD_ORE), new ItemStack(Items.EMERALD), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_SILICON_ORE), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFERUMITE_CRYSTAL_ORE), new ItemStack(MPItems.INFERUMITE_CRYSTAL), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_CACTUS), new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPItems.INFECTED_CLAY_BALL), new ItemStack(Items.BRICK), 0.3F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_OAK_LOG), new ItemStack(MPItems.INFECTED_CHARCOAL), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_DEADWOOD_LOG), new ItemStack(MPItems.INFECTED_CHARCOAL), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.INFECTED_JUNGLE_LOG), new ItemStack(MPItems.INFECTED_CHARCOAL), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_LOG), new ItemStack(Items.COAL, 1, 1), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPItems.RAW_SHLIME_MEAT), new ItemStack(MPItems.COOKED_SHLIME_MEAT), 0.35F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_GOLD_ORE), new ItemStack(Items.GOLD_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_LEAD_ORE), new ItemStack(VenusItems.basicItem, 1, 1), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_COAL_ORE), new ItemStack(Items.COAL), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_LAPIS_ORE), new ItemStack(Items.DYE, 1, 4), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_DIAMOND_ORE), new ItemStack(Items.DIAMOND), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_EMERALD_ORE), new ItemStack(Items.EMERALD), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_REDSTONE_ORE), new ItemStack(Items.REDSTONE), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_SILICON_ORE), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_QUARTZ_ORE), new ItemStack(Items.QUARTZ), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.EXTRAILONITE_ORE), new ItemStack(MPItems.EXTRAILONITE_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPItems.MARSHMALLOW), new ItemStack(MPItems.COOKED_MARSHMALLOW), 0.25F);
    }
}