package stevekung.mods.moreplanets.integration.jei.dark_energy_transform;

import mezz.jei.api.gui.IDrawable;
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

public class DarkEnergyTransformRecipeCategory extends BlankRecipeCategory
{
    @Override
    public String getUid()
    {
        return MPJEIRecipes.DARK_ENERGY_TRANSFORM;
    }

    @Override
    public String getTitle()
    {
        return GCCoreUtil.translate("gui.jei.dark_energy_transform");
    }

    @Override
    public IDrawable getBackground()
    {
        return JEIRegistryHelper.guiHelper.createBlankDrawable(120, 45);
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        JEIRegistryHelper.guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/jei/dark_energy_transform.png"), 0, 0, 71, 46).draw(mc, 24, 0);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemStack = recipeLayout.getItemStacks();
        itemStack.init(0, true, 26, 14);
        itemStack.init(1, false, 75, 14);
        itemStack.set(ingredients);
    }

    @Override
    public String getModName()
    {
        return MorePlanetsCore.MOD_ID;
    }
}