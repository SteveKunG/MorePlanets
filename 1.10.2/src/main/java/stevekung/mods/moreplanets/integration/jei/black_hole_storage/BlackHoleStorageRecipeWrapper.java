package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import java.util.ArrayList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;

public class BlackHoleStorageRecipeWrapper extends BlankRecipeWrapper
{
    private INasaWorkbenchRecipe recipe;

    public BlackHoleStorageRecipeWrapper(INasaWorkbenchRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, new ArrayList<>(this.recipe.getRecipeInput().values()));
        ingredients.setOutput(ItemStack.class, this.recipe.getRecipeOutput());
    }
}