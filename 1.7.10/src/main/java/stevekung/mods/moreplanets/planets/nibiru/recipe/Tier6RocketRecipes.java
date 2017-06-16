/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.inventory.InventorySchematicRocket;

public class Tier6RocketRecipes
{
    private static List<INasaWorkbenchRecipe> tier6RocketBenchRecipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> tier6RocketBenchNoFlagRecipes = new ArrayList<INasaWorkbenchRecipe>();

    public static ItemStack findMatchingTier6RocketRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : Tier6RocketRecipes.getTier6RocketRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier6RocketBenchRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier6RocketRecipes.addTier6RocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier6RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier6RocketRecipes.tier6RocketBenchRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier6RocketRecipes()
    {
        return Tier6RocketRecipes.tier6RocketBenchRecipes;
    }

    public static ItemStack findMatchingTier6RocketNoFlagRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : Tier6RocketRecipes.getTier6RocketNoFlagRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier6RocketBenchNoFlagRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier6RocketRecipes.addTier6RocketNoFlagRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier6RocketNoFlagRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier6RocketRecipes.tier6RocketBenchNoFlagRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier6RocketNoFlagRecipes()
    {
        return Tier6RocketRecipes.tier6RocketBenchNoFlagRecipes;
    }
}