package stevekung.mods.moreplanets.integration.jei.rockett6;

import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import stevekung.mods.moreplanets.module.planets.nibiru.recipe.Tier6RocketRecipes;

public class Tier6RocketRecipeMaker
{
    public static List<Tier6RocketRecipeWrapper> getRecipesList()
    {
        List<Tier6RocketRecipeWrapper> recipes = Lists.newArrayList();

        for (INasaWorkbenchRecipe recipe : Tier6RocketRecipes.getRocketRecipes())
        {
            Tier6RocketRecipeWrapper wrapper = new Tier6RocketRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}