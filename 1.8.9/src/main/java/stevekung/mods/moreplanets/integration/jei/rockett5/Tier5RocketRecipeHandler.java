package stevekung.mods.moreplanets.integration.jei.rockett5;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.util.MPLog;

public class Tier5RocketRecipeHandler implements IRecipeHandler<Tier5RocketRecipeWrapper>
{
    @Nonnull
    @Override
    public Class<Tier5RocketRecipeWrapper> getRecipeClass()
    {
        return Tier5RocketRecipeWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return "moreplanets.rocketT5";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull Tier5RocketRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull Tier5RocketRecipeWrapper recipe)
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