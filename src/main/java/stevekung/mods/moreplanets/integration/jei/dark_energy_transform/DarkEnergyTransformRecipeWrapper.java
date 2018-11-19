package stevekung.mods.moreplanets.integration.jei.dark_energy_transform;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;

public class DarkEnergyTransformRecipeWrapper implements IRecipeWrapper
{
    private HashMap<Integer, ItemStack> input;
    private ItemStack output;
    private int time;

    public DarkEnergyTransformRecipeWrapper(DarkEnergyRecipeData recipe)
    {
        this.input = recipe.getInput();
        this.output = recipe.getOutput();
        this.time = recipe.getTimeMultiplier();
    }

    @Override
    public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        int time = this.time / 20;
        String text = "Time : " + time + "s * Count";
        FontRenderer fontRendererObj = mc.fontRenderer;
        int width = fontRendererObj.getStringWidth(text) / 2;
        mc.fontRenderer.drawString(text, recipeWidth - width - 78, 30, Color.gray.getRGB());
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(VanillaTypes.ITEM, new ArrayList<>(this.input.values()));
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }
}