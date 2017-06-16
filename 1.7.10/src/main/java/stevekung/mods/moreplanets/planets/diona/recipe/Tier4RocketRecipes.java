/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.inventory.InventorySchematicRocket;

public class Tier4RocketRecipes
{
    private static List<INasaWorkbenchRecipe> tier4RocketRecipes = new ArrayList<INasaWorkbenchRecipe>();
    private static List<INasaWorkbenchRecipe> tier4RocketNoFlagRecipes = new ArrayList<INasaWorkbenchRecipe>();

    public static ItemStack findMatchingTier4RocketRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : Tier4RocketRecipes.getTier4RocketRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier4RocketRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier4RocketRecipes.addTier4RocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier4RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier4RocketRecipes.tier4RocketRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier4RocketRecipes()
    {
        return Tier4RocketRecipes.tier4RocketRecipes;
    }

    public static ItemStack findMatchingTier4RocketNoFlagRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : Tier4RocketRecipes.getTier4RocketNoFlagRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier4RocketNoFlagRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier4RocketNoFlagRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier4RocketRecipes.tier4RocketNoFlagRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier4RocketNoFlagRecipes()
    {
        return Tier4RocketRecipes.tier4RocketNoFlagRecipes;
    }
}