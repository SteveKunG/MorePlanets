package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MorePlanetsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneratorMP
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        String modId = MorePlanetsMod.MOD_ID;
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient())
        {
            generator.addProvider(new BlockStates(generator, modId, helper));
            generator.addProvider(new ItemModels(generator, modId, helper));
            generator.addProvider(new Language(generator, modId));
        }
        if (event.includeServer())
        {
            BlockTagsProvider blockTagProvider = new BlockTagsBuilder(generator, modId, helper);
            generator.addProvider(blockTagProvider);
            generator.addProvider(new ItemTagsBuilder(generator, blockTagProvider, modId, helper));
            generator.addProvider(new Recipe(generator, modId));
            generator.addProvider(new LootTables(generator, modId));
            //            generator.addProvider(new AdvancementProviderGC(generator));
        }
    }
}