package stevekung.mods.moreplanets.integration.jei.rockett6;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.integration.jei.JEIRegistryHelper;
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;

public class Tier6RocketRecipeCategory extends BlankRecipeCategory
{
    @Override
    public String getUid()
    {
        return MPJEIRecipes.TIER_6_ROCKET;
    }

    @Override
    public String getTitle()
    {
        return GCCoreUtil.translate("gui.jei.rocket_t6");
    }

    @Override
    public IDrawable getBackground()
    {
        return JEIRegistryHelper.guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/jei/tier_6_rocket_schematic.png"), 0, 0, 168, 126);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
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
        itemStacks.set(ingredients);
    }

    @Override
    public String getModName()
    {
        return MorePlanetsCore.NAME;
    }
}