package stevekung.mods.moreplanets.module.planets.nibiru.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.integration.jei.rockett6.Tier6RocketRecipeWrapper;
import stevekung.mods.moreplanets.inventory.InventorySchematicRocket;

public class Tier6RocketRecipes
{
    private static List<INasaWorkbenchRecipe> ROCKET_RECIPE = new ArrayList<>();

    public static ItemStack findMatchingRocketRecipe(InventorySchematicRocket inv)
    {
        for (INasaWorkbenchRecipe recipe : Tier6RocketRecipes.getRocketRecipes())
        {
            if (recipe.matches(inv))
            {
                return recipe.getRecipeOutput();
            }
        }
        return ItemStack.EMPTY;
    }

    public static void addRocketRecipe(ItemStack result, HashMap<Integer, ItemStack> input)
    {
        Tier6RocketRecipes.addRocketRecipe(new NasaWorkbenchRecipe(result, input));
    }

    public static void addRocketRecipe(INasaWorkbenchRecipe recipe)
    {
        Tier6RocketRecipes.ROCKET_RECIPE.add(recipe);
    }

    public static List<INasaWorkbenchRecipe> getRocketRecipes()
    {
        return Tier6RocketRecipes.ROCKET_RECIPE;
    }

    public static List<Tier6RocketRecipeWrapper> getRecipesList()
    {
        List<Tier6RocketRecipeWrapper> recipes = new ArrayList<>();

        for (INasaWorkbenchRecipe recipe : Tier6RocketRecipes.getRocketRecipes())
        {
            Tier6RocketRecipeWrapper wrapper = new Tier6RocketRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}