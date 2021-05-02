package com.stevekung.moreplanets.data;

import java.util.function.Consumer;

import com.stevekung.stevekungslib.data.recipes.RecipeProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;

public class Recipe extends RecipeProviderBase
{
    public Recipe(DataGenerator generator, String modid)
    {
        super(generator, modid);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer)
    {
    }
}