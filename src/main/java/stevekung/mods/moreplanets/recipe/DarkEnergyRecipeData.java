package stevekung.mods.moreplanets.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class DarkEnergyRecipeData
{
    private final HashMap<Integer, ItemStack> input;
    private final ItemStack output;
    private final int timeMultiplier;
    private static final List<DarkEnergyRecipeData> RECIPES = new ArrayList<>();

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
        DarkEnergyRecipeData.RECIPES.add(new DarkEnergyRecipeData(input, output, timeMultiplier));
    }

    public static List<DarkEnergyRecipeData> getRecipeList()
    {
        return DarkEnergyRecipeData.RECIPES;
    }
}