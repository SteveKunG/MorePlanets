package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;

public class BlackHoleStorageRecipeMaker
{
    public static List<BlackHoleStorageRecipeWrapper> getRecipesList()
    {
        List<BlackHoleStorageRecipeWrapper> recipes = Lists.newArrayList();

        for (INasaWorkbenchRecipe recipe : BlackHoleStorageRecipes.recipes)
        {
            BlackHoleStorageRecipeWrapper wrapper = new BlackHoleStorageRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}