package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.util.MPLog;

public class BlackHoleStorageRecipeHandler implements IRecipeHandler<BlackHoleStorageRecipeWrapper>
{
    @Override
    public Class<BlackHoleStorageRecipeWrapper> getRecipeClass()
    {
        return BlackHoleStorageRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid()
    {
        return MPJEIRecipes.BLACK_HOLE_STORAGE;
    }

    @Override
    public String getRecipeCategoryUid(BlackHoleStorageRecipeWrapper recipe)
    {
        return this.getRecipeCategoryUid();
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(BlackHoleStorageRecipeWrapper recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(BlackHoleStorageRecipeWrapper recipe)
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
}