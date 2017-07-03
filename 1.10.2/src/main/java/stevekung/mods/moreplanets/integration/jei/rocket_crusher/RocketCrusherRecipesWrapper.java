package stevekung.mods.moreplanets.integration.jei.rocket_crusher;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stevekung.mods.moreplanets.util.recipes.ShapedRecipesMP;

public class RocketCrusherRecipesWrapper extends BlankRecipeWrapper implements ICraftingRecipeWrapper
{
    @Nonnull
    private ShapedRecipesMP recipe;

    public RocketCrusherRecipesWrapper(@Nonnull ShapedRecipesMP recipe)
    {
        this.recipe = recipe;

        for (Object input : this.recipe.recipeItems)
        {
            if (input instanceof ItemStack)
            {
                ItemStack itemStack = (ItemStack) input;

                if (itemStack.stackSize != 1)
                {
                    itemStack.stackSize = 1;
                }
            }
        }
    }

    @Nonnull
    @Override
    public List getInputs()
    {
        return Arrays.asList(this.recipe.recipeItems);
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs()
    {
        return Collections.singletonList(this.recipe.getRecipeOutput());
    }

    @Override
    public void getIngredients(IIngredients ingredients) {}

    @Override
    public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
        float experience = 0;

        try
        {
            experience = furnaceRecipes.getSmeltingExperience(this.recipe.getRecipeOutput());
        }
        catch (Exception e) {}

        if (experience > 0)
        {
            String experienceString = GCCoreUtil.translateWithFormat("gui.jei.category.smelting.experience", experience);
            FontRenderer fontRendererObj = mc.fontRendererObj;
            int stringWidth = fontRendererObj.getStringWidth(experienceString);
            fontRendererObj.drawString(experienceString, recipeWidth + 6 - stringWidth, 8, Color.gray.getRGB());
        }
    }
}