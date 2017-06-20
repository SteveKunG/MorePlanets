package stevekung.mods.moreplanets.integration.jei.rockett6;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.util.MPLog;

public class Tier6RocketRecipeHandler implements IRecipeHandler<Tier6RocketRecipeWrapper>
{
    @Nonnull
    @Override
    public Class<Tier6RocketRecipeWrapper> getRecipeClass()
    {
        return Tier6RocketRecipeWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return "moreplanets.rocketT6";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull Tier6RocketRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull Tier6RocketRecipeWrapper recipe)
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
    public String getRecipeCategoryUid(Tier6RocketRecipeWrapper recipe)
    {
        return "moreplanets.rocketT6";
    }
}