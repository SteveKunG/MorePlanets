package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

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

public class BlackHoleStorageRecipeCategory extends BlankRecipeCategory
{
    @Override
    public String getUid()
    {
        return MPJEIRecipes.BLACK_HOLE_STORAGE;
    }

    @Override
    public String getTitle()
    {
        return GCCoreUtil.translate("tile.black_hole_storage.name");
    }

    @Override
    public IDrawable getBackground()
    {
        return JEIRegistryHelper.guiHelper.createBlankDrawable(200, 150);
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        JEIRegistryHelper.guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/jei/black_hole_storage.png"), 0, 0, 152, 119).draw(mc, 24, 0);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        int x = 24;

        itemStacks.init(0, true, x + 18, 0);
        itemStacks.init(1, true, x + 36, 0);
        itemStacks.init(2, true, x + 54, 0);

        itemStacks.init(3, true, x + 18, 18);
        itemStacks.init(4, true, x + 36, 18);
        itemStacks.init(5, true, x + 54, 18);

        itemStacks.init(6, true, x + 18, 36);
        itemStacks.init(7, true, x + 36, 36);
        itemStacks.init(8, true, x + 54, 36);

        itemStacks.init(9, true, x, 64);
        itemStacks.init(10, true, x + 36, 64);
        itemStacks.init(11, true, x + 72, 64);

        itemStacks.init(12, true, x, 82);
        itemStacks.init(13, true, x + 18, 82);
        itemStacks.init(14, true, x + 36, 82);
        itemStacks.init(15, true, x + 54, 82);
        itemStacks.init(16, true, x + 72, 82);

        itemStacks.init(17, true, x, 100);
        itemStacks.init(18, true, x + 18, 100);
        itemStacks.init(19, true, x + 36, 100);
        itemStacks.init(20, true, x + 54, 100);
        itemStacks.init(21, true, x + 72, 100);

        itemStacks.init(22, false, 150, 93);

        itemStacks.set(ingredients);
    }

    @Override
    public String getModName()
    {
        return MorePlanetsCore.NAME;
    }
}