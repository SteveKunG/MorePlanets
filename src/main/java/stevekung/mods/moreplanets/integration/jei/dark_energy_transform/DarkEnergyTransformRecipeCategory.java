package stevekung.mods.moreplanets.integration.jei.dark_energy_transform;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.integration.jei.JEIRegistryHelper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class DarkEnergyTransformRecipeCategory implements IRecipeCategory
{
    @Override
    public String getUid()
    {
        return MPJEIRecipes.DARK_ENERGY_TRANSFORM;
    }

    @Override
    public String getTitle()
    {
        return LangUtils.translate("gui.jei.dark_energy_transform");
    }

    @Override
    public IDrawable getBackground()
    {
        return JEIRegistryHelper.guiHelper.createBlankDrawable(145, 44);
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        JEIRegistryHelper.guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/jei/dark_energy_transform.png"), 0, 0, 89, 44).draw(mc, 24, 0);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemStack = recipeLayout.getItemStacks();
        itemStack.init(0, true, 26, 2);
        itemStack.init(1, true, 44, 2);
        itemStack.init(2, false, 93, 2);
        itemStack.set(ingredients);
    }

    @Override
    public String getModName()
    {
        return MorePlanetsMod.NAME;
    }
}