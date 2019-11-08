package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import java.util.ArrayList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;

public class BlackHoleStorageRecipeWrapper implements IRecipeWrapper
{
    private INasaWorkbenchRecipe recipe;

    public BlackHoleStorageRecipeWrapper(INasaWorkbenchRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(VanillaTypes.ITEM, new ArrayList<>(this.recipe.getRecipeInput().values()));
        ingredients.setOutput(VanillaTypes.ITEM, this.recipe.getRecipeOutput());
    }
}