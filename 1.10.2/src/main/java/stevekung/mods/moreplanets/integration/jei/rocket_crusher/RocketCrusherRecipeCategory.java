package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;

public class RocketCrusherRecipeCategory extends BlankRecipeCategory
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png");

    @Nonnull
    private IDrawable background;
    @Nonnull
    private IDrawableAnimated progressBar;

    public RocketCrusherRecipeCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createDrawable(this.texture, 18, 17, 142, 54);
        IDrawableStatic progressBarDrawable = guiHelper.createDrawable(this.texture, 176, 13, 52, 17);
        this.progressBar = guiHelper.createAnimatedDrawable(progressBarDrawable, 70, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return MPJEIRecipes.ROCKET_CRUSHER;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return GCCoreUtil.translate("tile.rocket_crusher.name");
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public void drawAnimations(@Nonnull Minecraft mc)
    {
        this.progressBar.draw(mc, 59, 22);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

        for (int j = 0; j < 9; j++)
        {
            itemStacks.init(j, true, 0 + j % 3 * 18, 0 + j / 3 * 18);
        }

        itemStacks.init(9, false, 124, 20);

        if (recipeWrapper instanceof RocketCrusherRecipesWrapper)
        {
            RocketCrusherRecipesWrapper ingotCompressorRecipeWrapper = (RocketCrusherRecipesWrapper) recipeWrapper;
            List inputs = ingotCompressorRecipeWrapper.getInputs();

            for (int i = 0; i < inputs.size(); ++i)
            {
                Object o = inputs.get(i);

                if (o != null)
                {
                    itemStacks.setFromRecipe(i, o);
                }
            }
            itemStacks.setFromRecipe(9, ingotCompressorRecipeWrapper.getOutputs());
        }
    }
}