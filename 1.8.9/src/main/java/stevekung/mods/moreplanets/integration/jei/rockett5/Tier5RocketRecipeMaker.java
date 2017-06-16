package stevekung.mods.moreplanets.integration.jei.rockett5;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import stevekung.mods.moreplanets.module.planets.chalos.recipe.Tier5RocketRecipes;

public class Tier5RocketRecipeMaker
{
    public static List<Tier5RocketRecipeWrapper> getRecipesList()
    {
        List<Tier5RocketRecipeWrapper> recipes = Lists.newArrayList();

        for (INasaWorkbenchRecipe recipe : Tier5RocketRecipes.getRocketRecipes())
        {
            Tier5RocketRecipeWrapper wrapper = new Tier5RocketRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}