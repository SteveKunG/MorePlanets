package stevekung.mods.moreplanets.integration.jei.dark_energy;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class DarkEnergyTransformRecipeCategory extends BlankRecipeCategory
{
    @Nonnull
    private IDrawable background;
    @Nonnull
    private IDrawable overlay;

    public DarkEnergyTransformRecipeCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createBlankDrawable(120, 45);
        this.overlay = guiHelper.createDrawable(new ResourceLocation("moreplanets:textures/gui/jei/dark_energy_transform.png"), 0, 0, 71, 46);
    }

    @Override
    @Nonnull
    public String getUid()
    {
        return "moreplanets.darkEnergyTransform";
    }

    @Override
    @Nonnull
    public String getTitle()
    {
        return I18n.translateToLocal("moreplanets.jei.darkEnergyTransform");
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
    @Nonnull
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {
        if (!(recipeWrapper instanceof DarkEnergyTransformRecipeWrapper))
        {
            return;
        }
        IGuiItemStackGroup itemStack = recipeLayout.getItemStacks();
        itemStack.init(0, true, 26, 14);
        itemStack.init(1, false, 75, 14);
        itemStack.set(0, recipeWrapper.getInputs());
        itemStack.set(1, recipeWrapper.getOutputs());
    }
}