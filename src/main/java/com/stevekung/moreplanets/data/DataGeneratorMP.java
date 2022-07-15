package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.data.client.ModelProvider;
import com.stevekung.moreplanets.data.loot.BlockLootTableProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGeneratorMP implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator)
    {
        dataGenerator.addProvider(ModelProvider::new);

        dataGenerator.addProvider(BlockTagsProvider::new);
        dataGenerator.addProvider(ItemTagsProvider::new);
        dataGenerator.addProvider(BlockLootTableProvider::new);
        dataGenerator.addProvider(RecipeProvider::new);
    }
}