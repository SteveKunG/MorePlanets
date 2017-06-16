package stevekung.mods.moreplanets.integration.jei.rockett4;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import stevekung.mods.moreplanets.module.planets.diona.recipe.Tier4RocketRecipes;

public class Tier4RocketRecipeMaker
{
    public static List<Tier4RocketRecipeWrapper> getRecipesList()
    {
        List<Tier4RocketRecipeWrapper> recipes = Lists.newArrayList();

        for (INasaWorkbenchRecipe recipe : Tier4RocketRecipes.getRocketRecipes())
        {
            Tier4RocketRecipeWrapper wrapper = new Tier4RocketRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}