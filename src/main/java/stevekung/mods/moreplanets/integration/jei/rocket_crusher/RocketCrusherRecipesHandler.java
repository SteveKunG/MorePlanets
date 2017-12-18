package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.recipes.ShapedRecipesMP;

public class RocketCrusherRecipesHandler implements IRecipeHandler<ShapedRecipesMP>
{
    @Nonnull
    @Override
    public Class<ShapedRecipesMP> getRecipeClass()
    {
        return ShapedRecipesMP.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return MPJEIRecipes.ROCKET_CRUSHER;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(ShapedRecipesMP recipe)
    {
        return this.getRecipeCategoryUid();
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull ShapedRecipesMP recipe)
    {
        return new RocketCrusherRecipesWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull ShapedRecipesMP recipe)
    {
        if (recipe.getRecipeOutput() == null)
        {
            MPLog.error(this.getClass().getSimpleName() + " JEI recipe has no output!");
            return false;
        }

        int inputCount = 0;

        for (Object input : recipe.recipeItems)
        {
            if (input instanceof ItemStack)
            {
                inputCount++;
            }
            else
            {
                MPLog.error(this.getClass().getSimpleName() + " JEI recipe has input that is not an ItemStack!");
                return false;
            }
        }
        if (inputCount > 9)
        {
            MPLog.error(this.getClass().getSimpleName() + " JEI recipe has too many inputs!");
            return false;
        }
        return inputCount > 0;
    }
}