package com.stevekung.moreplanets.data;

import java.util.function.Consumer;

import com.stevekung.moreplanets.registry.MPBlocks;
import com.stevekung.moreplanets.registry.MPItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

public class RecipeProvider extends FabricRecipeProvider
{
    public RecipeProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(MPBlocks.GLOWING_IRON_BLOCK).define('#', MPItems.GLOWING_IRON_INGOT).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(MPItems.GLOWING_IRON_INGOT), has(MPItems.GLOWING_IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(MPBlocks.RAW_GLOWING_IRON_BLOCK).define('#', MPItems.RAW_GLOWING_IRON).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(MPItems.RAW_GLOWING_IRON), has(MPItems.RAW_GLOWING_IRON)).save(consumer);
        ShapelessRecipeBuilder.shapeless(MPItems.GLOWING_IRON_INGOT, 9).requires(MPBlocks.GLOWING_IRON_BLOCK).unlockedBy(getHasName(MPBlocks.GLOWING_IRON_BLOCK), has(MPBlocks.GLOWING_IRON_BLOCK)).save(consumer, getConversionRecipeName(MPBlocks.GLOWING_IRON_BLOCK, MPItems.GLOWING_IRON_INGOT));
        ShapelessRecipeBuilder.shapeless(MPItems.RAW_GLOWING_IRON, 9).requires(MPBlocks.RAW_GLOWING_IRON_BLOCK).unlockedBy(getHasName(MPBlocks.RAW_GLOWING_IRON_BLOCK), has(MPBlocks.RAW_GLOWING_IRON_BLOCK)).save(consumer, getConversionRecipeName(MPBlocks.RAW_GLOWING_IRON_BLOCK, MPItems.RAW_GLOWING_IRON));
        ShapedRecipeBuilder.shaped(MPBlocks.PURLONITE_CRYSTAL_LANTERN).define('#', MPItems.PURLONITE_SHARD).define('X', Items.IRON_NUGGET).pattern("XXX").pattern("X#X").pattern("XXX").unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET)).unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(MPBlocks.DARK_CRYSTAL_LANTERN).define('#', MPItems.DARK_CRYSTAL_SHARD).define('X', Items.IRON_NUGGET).pattern("XXX").pattern("X#X").pattern("XXX").unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET)).unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(consumer);
    }
}