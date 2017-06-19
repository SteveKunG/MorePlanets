package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class RocketCrusherRecipeCategory extends BlankRecipeCategory
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png");

    @Nonnull
    private IDrawable background;
    @Nonnull
    private IDrawableAnimated progressBar;

    public RocketCrusherRecipeCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createDrawable(this.texture, 18, 17, 137, 78);
        IDrawableStatic progressBarDrawable = guiHelper.createDrawable(this.texture, 176, 13, 52, 17);
        this.progressBar = guiHelper.createAnimatedDrawable(progressBarDrawable, 70, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return "moreplanets.rocketCrusherRecipes";
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return StatCollector.translateToLocal("moreplanets.jei.rocketCrusher");
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
        this.progressBar.draw(mc, 77, 36);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

        for (int j = 0; j < 9; j++)
        {
            itemStacks.init(j, true, 21 + j % 3 * 18, 26 + j / 3 * 18);
        }

        itemStacks.init(9, false, 140, 46);

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