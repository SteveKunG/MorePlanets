package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import java.util.Arrays;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.recipes.ShapedRecipesMP;

public class RocketCrusherRecipesWrapper extends BlankRecipeWrapper implements IRecipeWrapper
{
    @Nonnull
    private ShapedRecipesMP recipe;

    public RocketCrusherRecipesWrapper(@Nonnull ShapedRecipesMP recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, Arrays.asList(this.recipe.recipeItems));
        ingredients.setOutput(ItemStack.class, this.recipe.getRecipeOutput());
    }
}