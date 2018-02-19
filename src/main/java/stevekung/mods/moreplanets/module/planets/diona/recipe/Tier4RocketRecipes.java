package stevekung.mods.moreplanets.module.planets.diona.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.integration.jei.rockett4.Tier4RocketRecipeWrapper;
import stevekung.mods.moreplanets.inventory.InventorySchematicRocket;

public class Tier4RocketRecipes
{
    private static List<INasaWorkbenchRecipe> ROCKET_RECIPE = new ArrayList<>();

    public static ItemStack findMatchingRocketRecipe(InventorySchematicRocket inv)
    {
        for (INasaWorkbenchRecipe recipe : Tier4RocketRecipes.getRocketRecipes())
        {
            if (recipe.matches(inv))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static void addRocketRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier4RocketRecipes.addRocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addRocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier4RocketRecipes.ROCKET_RECIPE.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getRocketRecipes()
    {
        return Tier4RocketRecipes.ROCKET_RECIPE;
    }

    public static List<Tier4RocketRecipeWrapper> getRecipesList()
    {
        List<Tier4RocketRecipeWrapper> recipes = new ArrayList<>();
        int chestCount = -1;

        for (INasaWorkbenchRecipe recipe : Tier4RocketRecipes.getRocketRecipes())
        {
            int chests = MPJEIRecipes.countChests(recipe);

            if (chests == chestCount)
            {
                continue;
            }
            chestCount = chests;
            Tier4RocketRecipeWrapper wrapper = new Tier4RocketRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}