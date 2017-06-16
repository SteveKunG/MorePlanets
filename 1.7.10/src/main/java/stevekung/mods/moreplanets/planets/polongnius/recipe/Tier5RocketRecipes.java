/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.inventory.InventorySchematicRocket;

public class Tier5RocketRecipes
{
    private static List<INasaWorkbenchRecipe> tier5RocketBenchRecipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> tier5RocketBenchNoFlagRecipes = new ArrayList<INasaWorkbenchRecipe>();

    public static ItemStack findMatchingTier5RocketRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : Tier5RocketRecipes.getTier5RocketRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier5RocketBenchRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier5RocketRecipes.addTier5RocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier5RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier5RocketRecipes.tier5RocketBenchRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier5RocketRecipes()
    {
        return Tier5RocketRecipes.tier5RocketBenchRecipes;
    }

    public static ItemStack findMatchingTier5RocketNoFlagRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : Tier5RocketRecipes.getTier5RocketNoFlagRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier5RocketBenchNoFlagRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier5RocketRecipes.addTier5RocketNoFlagRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier5RocketNoFlagRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier5RocketRecipes.tier5RocketBenchNoFlagRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier5RocketNoFlagRecipes()
    {
        return Tier5RocketRecipes.tier5RocketBenchNoFlagRecipes;
    }
}