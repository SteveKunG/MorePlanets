package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
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
        return JEIRegistryHelper.guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png"), 18, 17, 142, 54);
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        JEIRegistryHelper.guiHelper.createAnimatedDrawable(JEIRegistryHelper.guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/rocket_crusher.png"), 176, 13, 52, 17), 70, IDrawableAnimated.StartDirection.LEFT, false).draw(mc, 59, 22);
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