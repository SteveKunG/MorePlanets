package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;

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
        return true;
    }
}