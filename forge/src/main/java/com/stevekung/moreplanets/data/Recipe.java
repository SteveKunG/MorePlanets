package com.stevekung.moreplanets.data;

import java.util.function.Consumer;

import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.recipes.RecipeProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

public class Recipe extends RecipeProviderBase
{
    public Recipe(DataGenerator generator, String modid)
    {
        super(generator, modid);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(MPBlocks.GLOWING_IRON_BLOCK).define('#', MPItems.GLOWING_IRON_INGOT).pattern("###").pattern("###").pattern("###").unlockedBy(this.toCriterion(MPItems.GLOWING_IRON_INGOT), has(MPItems.GLOWING_IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(MPBlocks.RAW_GLOWING_IRON_BLOCK).define('#', MPItems.RAW_GLOWING_IRON).pattern("###").pattern("###").pattern("###").unlockedBy(this.toCriterion(MPItems.RAW_GLOWING_IRON), has(MPItems.RAW_GLOWING_IRON)).save(consumer);
        ShapelessRecipeBuilder.shapeless(MPItems.GLOWING_IRON_INGOT, 9).requires(MPBlocks.GLOWING_IRON_BLOCK).unlockedBy(this.toCriterion(MPBlocks.GLOWING_IRON_BLOCK), has(MPBlocks.GLOWING_IRON_BLOCK)).save(consumer, this.from(MPBlocks.GLOWING_IRON_BLOCK, MPItems.GLOWING_IRON_INGOT));
        ShapelessRecipeBuilder.shapeless(MPItems.RAW_GLOWING_IRON, 9).requires(MPBlocks.RAW_GLOWING_IRON_BLOCK).unlockedBy(this.toCriterion(MPBlocks.RAW_GLOWING_IRON_BLOCK), has(MPBlocks.RAW_GLOWING_IRON_BLOCK)).save(consumer, this.from(MPBlocks.RAW_GLOWING_IRON_BLOCK, MPItems.RAW_GLOWING_IRON));
    }
}