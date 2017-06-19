package stevekung.mods.moreplanets.integration.jei.black_hole_storage;

import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class BlackHoleStorageRecipeCategory extends BlankRecipeCategory
{
    private ResourceLocation texture = new ResourceLocation("moreplanets:textures/gui/jei/black_hole_storage.png");

    @Nonnull
    private IDrawable background;
    @Nonnull
    private IDrawable overlay;

    public BlackHoleStorageRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createBlankDrawable(200, 40);
        this.overlay = helper.createDrawable(this.texture, 0, 0, 152, 119);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return "moreplanets.blackHoleStorage";
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return GCCoreUtil.translate("tile.black_hole_storage.name");
    }

    @Override
    @Nonnull
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    @Nonnull
    public void drawExtras(@Nonnull Minecraft mc)
    {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        this.overlay.draw(mc, 24, 0);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
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

        if (recipeWrapper instanceof BlackHoleStorageRecipeWrapper)
        {
            BlackHoleStorageRecipeWrapper wrapper = (BlackHoleStorageRecipeWrapper) recipeWrapper;
            List inputs = wrapper.getInputs();

            for (int i = 0; i < inputs.size(); ++i)
            {
                Object o = inputs.get(i);

                if (o != null)
                {
                    itemStacks.setFromRecipe(i, o);
                }
            }
            itemStacks.setFromRecipe(22, wrapper.getOutputs());
        }
    }
}