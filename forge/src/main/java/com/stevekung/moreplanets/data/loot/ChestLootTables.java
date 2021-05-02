package com.stevekung.moreplanets.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class ChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>
{
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer)
    {

    }
}