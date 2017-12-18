/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.inventory.InventorySchematicRocket;

public class Tier7RocketRecipes
{
    private static List<INasaWorkbenchRecipe> tier7RocketRecipes = new ArrayList<INasaWorkbenchRecipe>();

    public static ItemStack findMatchingTier7RocketRecipe(InventorySchematicRocket inventoryRocketBench)
    {
        for (final INasaWorkbenchRecipe recipe : getTier7RocketRecipes())
        {
            if (recipe.matches(inventoryRocketBench))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier7RocketRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier7RocketRecipes.addTier7RocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier7RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier7RocketRecipes.tier7RocketRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier7RocketRecipes()
    {
        return Tier7RocketRecipes.tier7RocketRecipes;
    }
}