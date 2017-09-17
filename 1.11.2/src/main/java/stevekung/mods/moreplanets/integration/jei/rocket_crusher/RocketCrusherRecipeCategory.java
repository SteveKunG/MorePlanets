package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.integration.jei.JEIRegistryHelper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;

public class RocketCrusherRecipeCategory extends BlankRecipeCategory
{
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png");
    private IDrawableAnimated progressBar;
    private IDrawableStatic[] progressHammerDrawable = new IDrawableStatic[3];

    public RocketCrusherRecipeCategory()
    {
        IDrawableStatic progressBarDrawable = JEIRegistryHelper.guiHelper.createDrawable(texture, 176, 13, 52, 17);
        this.progressBar = JEIRegistryHelper.guiHelper.createAnimatedDrawable(progressBarDrawable, 80, IDrawableAnimated.StartDirection.LEFT, false);

        for (int i = 0; i < 3; i++)
        {
            this.progressHammerDrawable[i] = JEIRegistryHelper.guiHelper.createDrawable(texture, 176, 0, 12, 13);
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
        return GCCoreUtil.translate("tile.rocket_crusher.name");
    }

    @Override
    public IDrawable getBackground()
    {
        return JEIRegistryHelper.guiHelper.createDrawable(texture, 18, 17, 142, 54);
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        int x = 62;

        for (int i = 0; i < 3; i++)
        {
            this.progressHammerDrawable[i].draw(mc, x, 13);
            x += 13;
        }
        this.progressBar.draw(mc, 59, 22);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

        for (int j = 0; j < 9; j++)
        {
            itemStacks.init(j, true, 0 + j % 3 * 18, 0 + j / 3 * 18);
        }
        itemStacks.init(9, false, 124, 20);
        itemStacks.set(ingredients);
    }

    @Override
    public String getModName()
    {
        return MorePlanetsCore.NAME;
    }
}