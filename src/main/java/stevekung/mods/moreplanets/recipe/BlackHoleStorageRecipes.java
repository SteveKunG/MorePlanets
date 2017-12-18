package stevekung.mods.moreplanets.recipe;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeWrapper;
import stevekung.mods.moreplanets.inventory.InventoryBlackHoleStorageSchematic;

public class BlackHoleStorageRecipes
{
    public static List<INasaWorkbenchRecipe> recipes = new ArrayList<>();

    public static ItemStack findMatchingBlackHoleStorageRecipe(InventoryBlackHoleStorageSchematic craftMatrix)
    {
        for (INasaWorkbenchRecipe recipe : BlackHoleStorageRecipes.recipes)
        {
            if (recipe.matches(craftMatrix))
            {
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public static List<BlackHoleStorageRecipeWrapper> getRecipesList()
    {
        List<BlackHoleStorageRecipeWrapper> recipes = new ArrayList<>();

        for (INasaWorkbenchRecipe recipe : BlackHoleStorageRecipes.recipes)
        {
            BlackHoleStorageRecipeWrapper wrapper = new BlackHoleStorageRecipeWrapper(recipe);
            recipes.add(wrapper);
        }
        return recipes;
    }
}