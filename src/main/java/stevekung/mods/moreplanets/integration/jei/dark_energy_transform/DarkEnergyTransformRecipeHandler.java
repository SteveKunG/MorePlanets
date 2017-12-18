package stevekung.mods.moreplanets.integration.jei.dark_energy_transform;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;

public class DarkEnergyTransformRecipeHandler implements IRecipeHandler<DarkEnergyRecipeData>
{
    @Override
    public Class<DarkEnergyRecipeData> getRecipeClass()
    {
        return DarkEnergyRecipeData.class;
    }

    @Override
    public String getRecipeCategoryUid()
    {
        return MPJEIRecipes.DARK_ENERGY_TRANSFORM;
    }

    @Override
    public String getRecipeCategoryUid(DarkEnergyRecipeData recipe)
    {
        return this.getRecipeCategoryUid();
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(DarkEnergyRecipeData recipe)
    {
        return new DarkEnergyTransformRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(DarkEnergyRecipeData recipe)
    {
        return true;
    }
}