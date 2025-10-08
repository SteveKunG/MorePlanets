package com.stevekung.moreplanets.recipe;

import java.util.ArrayList;
import java.util.List;

import com.stevekung.moreplanets.integration.jei.black_hole_storage.BlackHoleStorageRecipeWrapper;
import com.stevekung.moreplanets.inventory.InventoryBlackHoleStorageSchematic;

import net.minecraft.item.ItemStack;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;

public class BlackHoleStorageRecipes
{
    public static final List<INasaWorkbenchRecipe> RECIPES = new ArrayList<>();

    public static ItemStack findMatchingBlackHoleStorageRecipe(InventoryBlackHoleStorageSchematic craftMatrix)
    {
        for (INasaWorkbenchRecipe recipe : BlackHoleStorageRecipes.RECIPES)
        {
            if (recipe.matches(craftMatrix))
            {
                return recipe.getRecipeOutput();
            }
        }
        return ItemStack.EMPTY;
    }

    public static List<BlackHoleStorageRecipeWrapper> getRecipesList()
    {
        List<BlackHoleStorageRecipeWrapper> recipes = new ArrayList<>();
        BlackHoleStorageRecipes.RECIPES.forEach(recipe -> recipes.add(new BlackHoleStorageRecipeWrapper(recipe)));
        return recipes;
    }
}