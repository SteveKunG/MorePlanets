package stevekung.mods.moreplanets.integration.jei.rockett4;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.util.MPLog;

public class Tier4RocketRecipeHandler implements IRecipeHandler<Tier4RocketRecipeWrapper>
{
    @Nonnull
    @Override
    public Class<Tier4RocketRecipeWrapper> getRecipeClass()
    {
        return Tier4RocketRecipeWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return "moreplanets.rocketT4";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull Tier4RocketRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull Tier4RocketRecipeWrapper recipe)
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

    @Override
    public String getRecipeCategoryUid(Tier4RocketRecipeWrapper recipe)
    {
        return "moreplanets.rocketT4";
    }
}