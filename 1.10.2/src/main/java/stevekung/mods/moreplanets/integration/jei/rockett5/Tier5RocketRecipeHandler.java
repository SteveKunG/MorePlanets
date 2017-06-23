package stevekung.mods.moreplanets.integration.jei.rockett5;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.util.MPLog;

public class Tier5RocketRecipeHandler implements IRecipeHandler<Tier5RocketRecipeWrapper>
{
    @Override
    public Class<Tier5RocketRecipeWrapper> getRecipeClass()
    {
        return Tier5RocketRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid()
    {
        return MPJEIRecipes.TIER_5_ROCKET;
    }

    @Override
    public String getRecipeCategoryUid(Tier5RocketRecipeWrapper recipe)
    {
        return MPJEIRecipes.TIER_5_ROCKET;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(Tier5RocketRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(Tier5RocketRecipeWrapper recipe)
    {
        if (recipe.getInputs().size() != 21)
        {
            MPLog.error(this.getClass().getSimpleName() + " JEI recipe has wrong number of inputs!");
        }
        if (recipe.getOutputs().size() != 1)
        {
            MPLog.error(this.getClass().getSimpleName() + " JEI recipe has wrong number of outputs!");
        }
        return true;
    }
}