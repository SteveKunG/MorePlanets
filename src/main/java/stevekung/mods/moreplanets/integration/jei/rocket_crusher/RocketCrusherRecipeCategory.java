package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.integration.jei.JEIRegistryHelper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class RocketCrusherRecipeCategory implements IRecipeCategory
{
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png");
    private IDrawableAnimated progressBar;
    private static IDrawableStatic[] progressHammerDrawable = new IDrawableStatic[3];

    public RocketCrusherRecipeCategory()
    {
        IDrawableStatic progressBarDrawable = JEIRegistryHelper.guiHelper.createDrawable(texture, 176, 13, 52, 17);
        this.progressBar = JEIRegistryHelper.guiHelper.createAnimatedDrawable(progressBarDrawable, 80, IDrawableAnimated.StartDirection.LEFT, false);

        for (int i = 0; i < 3; i++)
        {
            RocketCrusherRecipeCategory.progressHammerDrawable[i] = JEIRegistryHelper.guiHelper.createDrawable(texture, 176, 0, 12, 13);
        }
    }

    @Override
    public String getUid()
    {
        return MPJEIRecipes.ROCKET_CRUSHER;
    }

    @Override
    public String getTitle()
    {
        return LangUtils.translate("tile.rocket_crusher.name");
    }

    @Override
    public IDrawable getBackground()
    {
        return JEIRegistryHelper.guiHelper.createDrawable(texture, 18, 19, 142, 54);
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        int x = 65;

        for (int i = 0; i < 3; i++)
        {
            RocketCrusherRecipeCategory.progressHammerDrawable[i].draw(mc, x, 11);
            x += 13;
        }
        this.progressBar.draw(mc, 62, 20);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

        for (int j = 0; j < 9; j++)
        {
            itemStacks.init(j, true, 0 + j % 3 * 18, 0 + j / 3 * 18);
        }
        itemStacks.init(9, false, 124, 18);
        itemStacks.set(ingredients);
    }

    @Override
    public String getModName()
    {
        return MorePlanetsMod.NAME;
    }
}