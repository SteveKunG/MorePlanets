package stevekung.mods.moreplanets.integration.jei.dark_energy_transform;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.recipe.DarkEnergyRecipeData;

public class DarkEnergyTransformRecipeWrapper extends BlankRecipeWrapper
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
    public List getInputs()
    {
        List<ItemStack> list = Lists.newArrayList();
        list.addAll(this.input.values());
        return list;
    }

    @Override
    public List getOutputs()
    {
        return ImmutableList.of(this.output);
    }

    @Override
    public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        mc.currentScreen.drawCenteredString(mc.fontRendererObj, "Time : " + this.time, 50, 38, 16777215);
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        List<ItemStack> list = Lists.newArrayList();
        list.addAll(this.input.values());
        ingredients.setInputs(ItemStack.class, list);
        ingredients.setOutput(ItemStack.class, this.output);
    }
}