package stevekung.mods.moreplanets.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CapsuleRecipes
{
    public static void init()
    {
        RecipeHelper.addRecipe(new ItemStack(MPItems.EMPTY_CAPSULE), "capsule", new Object[] { " C", "X ", 'C', new ItemStack(GCItems.canister, 1, 0), 'X', Items.IRON_INGOT });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE), "capsule", MPItems.EMPTY_CAPSULE, new ItemStack(MPItems.CHEESE_SPORE_BERRY), MPBlocks.CHEESE_SPORE_FLOWER, Blocks.BROWN_MUSHROOM);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE), "capsule", "infected_spore_protection_capsule_from_herb", MPItems.EMPTY_CAPSULE, MPBlocks.PURE_HERB, new ItemStack(MPItems.INFERUMITE_CRYSTAL));
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.DARK_ENERGY_PROTECTION_CAPSULE), "capsule", MPItems.EMPTY_CAPSULE, new ItemStack(MPItems.INFECTED_CRYSTALLIZED_SHARD), new ItemStack(MarsItems.marsItemBasic, 1, 0));
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.DARK_ENERGY_PROTECTION_CAPSULE), "capsule", "dark_energy_protection_capsule_herb", MPItems.EMPTY_CAPSULE, MPBlocks.TERRAPUFF_HERB, new ItemStack(MPItems.INFERUMITE_CRYSTAL), new ItemStack(MarsItems.marsItemBasic, 1, 0));
    }
}