package stevekung.mods.moreplanets.integration.jei.rockett4;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;

public class Tier4RocketRecipeHandler implements IRecipeHandler<Tier4RocketRecipeWrapper>
{
    @Override
    public Class<Tier4RocketRecipeWrapper> getRecipeClass()
    {
        return Tier4RocketRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid()
    {
        return MPJEIRecipes.TIER_4_ROCKET;
    }

    @Override
    public String getRecipeCategoryUid(Tier4RocketRecipeWrapper recipe)
    {
        return this.getRecipeCategoryUid();
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(Tier4RocketRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(Tier4RocketRecipeWrapper recipe)
    {
        return true;
    }
}