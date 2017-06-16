/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.inventory.InventorySchematicRocket;

public class Tier8RocketRecipes
{
    private static List<INasaWorkbenchRecipe> tier8RocketRecipes = new ArrayList<INasaWorkbenchRecipe>();

    public static ItemStack findMatchingTier8RocketRecipe(InventorySchematicRocket craftMatrix)
    {
        for (final INasaWorkbenchRecipe recipe : getTier8RocketRecipes())
        {
            if (recipe.matches(craftMatrix))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addTier8RocketRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier8RocketRecipes.addTier8RocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addTier8RocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier8RocketRecipes.tier8RocketRecipes.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getTier8RocketRecipes()
    {
        return Tier8RocketRecipes.tier8RocketRecipes;
    }
}