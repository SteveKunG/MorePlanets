package stevekung.mods.moreplanets.integration.jei.rockett6;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.util.MPLog;

public class Tier6RocketRecipeHandler implements IRecipeHandler<Tier6RocketRecipeWrapper>
{
    @Override
    public Class<Tier6RocketRecipeWrapper> getRecipeClass()
    {
        return Tier6RocketRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid()
    {
        return MPJEIRecipes.TIER_6_ROCKET;
    }

    @Override
    public String getRecipeCategoryUid(Tier6RocketRecipeWrapper recipe)
    {
        return MPJEIRecipes.TIER_6_ROCKET;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(Tier6RocketRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(Tier6RocketRecipeWrapper recipe)
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