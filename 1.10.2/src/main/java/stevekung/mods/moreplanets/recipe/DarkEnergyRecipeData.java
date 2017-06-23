package stevekung.mods.moreplanets.recipe;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;

public class DarkEnergyRecipeData
{
    private HashMap<Integer, ItemStack> input;
    private ItemStack output;
    private int timeMultiplier;
    private static List<DarkEnergyRecipeData> recipes = Lists.newArrayList();

    private DarkEnergyRecipeData(HashMap<Integer, ItemStack> input, ItemStack output, int timeMultiplier)
    {
        this.input = input;
        this.output = output;
        this.timeMultiplier = timeMultiplier;
    }

    public HashMap<Integer, ItemStack> getInput()
    {
        return this.input;
    }

    public ItemStack getOutput()
    {
        return this.output;
    }

    public int getTimeMultiplier()
    {
        return this.timeMultiplier;
    }

    public static void registerRecipe(HashMap<Integer, ItemStack> input, ItemStack output, int timeMultiplier)
    {
        DarkEnergyRecipeData.recipes.add(new DarkEnergyRecipeData(input, output, timeMultiplier));
    }

    public static List<DarkEnergyRecipeData> getRecipeList()
    {
        return DarkEnergyRecipeData.recipes;
    }
}