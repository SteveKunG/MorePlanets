package stevekung.mods.moreplanets.integration.jei.rockett5;

import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class Tier5RocketRecipeCategory extends BlankRecipeCategory
{
    private ResourceLocation rocketGuiTexture = new ResourceLocation("moreplanets:textures/gui/jei/tier_5_rocket_schematic.png");

    @Nonnull
    private IDrawable background;

    public Tier5RocketRecipeCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createDrawable(this.rocketGuiTexture, 0, 0, 168, 126);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return "moreplanets.rocketT5";
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return I18n.translateToLocal("tile.rocket_workbench.name");
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 44, 0);
        itemStacks.init(1, true, 35, 18);
        itemStacks.init(2, true, 35, 36);
        itemStacks.init(3, true, 35, 54);
        itemStacks.init(4, true, 35, 72);
        itemStacks.init(5, true, 35, 90);
        itemStacks.init(6, true, 53, 18);
        itemStacks.init(7, true, 53, 36);
        itemStacks.init(8, true, 53, 54);
        itemStacks.init(9, true, 53, 72);
        itemStacks.init(10, true, 53, 90);
        itemStacks.init(11, true, 17, 72); //Booster left
        itemStacks.init(12, true, 17, 90);
        itemStacks.init(13, true, 71, 90);
        itemStacks.init(14, true, 44, 108); //Rocket
        itemStacks.init(15, true, 71, 72); //Booster right
        itemStacks.init(16, true, 17, 108);
        itemStacks.init(17, true, 71, 108);
        itemStacks.init(18, true, 89, 7);
        itemStacks.init(19, true, 115, 7);
        itemStacks.init(20, true, 141, 7);
        itemStacks.init(21, false, 138, 95);

        if (recipeWrapper instanceof Tier5RocketRecipeWrapper)
        {
            Tier5RocketRecipeWrapper rocketRecipeWrapper = (Tier5RocketRecipeWrapper) recipeWrapper;
            List inputs = rocketRecipeWrapper.getInputs();

            for (int i = 0; i < inputs.size(); ++i)
            {
                Object o = inputs.get(i);

                if (o != null)
                {
                    itemStacks.setFromRecipe(i, o);
                }
            }
            itemStacks.setFromRecipe(21, rocketRecipeWrapper.getOutputs());
        }
    }
}