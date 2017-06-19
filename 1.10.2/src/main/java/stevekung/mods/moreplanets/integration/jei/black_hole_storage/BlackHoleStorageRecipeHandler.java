package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.util.MPLog;

public class BlackHoleStorageRecipeHandler implements IRecipeHandler<BlackHoleStorageRecipeWrapper>
{
    @Nonnull
    @Override
    public Class<BlackHoleStorageRecipeWrapper> getRecipeClass()
    {
        return BlackHoleStorageRecipeWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return "moreplanets.blackHoleStorage";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull BlackHoleStorageRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull BlackHoleStorageRecipeWrapper recipe)
    {
        if (recipe.getInputs().size() != 22)
        {
            MPLog.error(this.getClass().getSimpleName() + " JEI recipe has wrong number of inputs!");
        }
        if (recipe.getOutputs().size() != 1)
        {
            MPLog.error(this.getClass().getSimpleName() + " JEI recipe has wrong number of outputs!");
        }
        return true;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(BlackHoleStorageRecipeWrapper recipe)
    {
        return "moreplanets.blackHoleStorage";
    }
}