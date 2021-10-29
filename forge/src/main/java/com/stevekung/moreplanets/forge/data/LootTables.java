package com.stevekung.moreplanets.forge.data;

import com.mojang.datafixers.util.Pair;
import com.stevekung.moreplanets.forge.data.loot.BlockLootTable;
import com.stevekung.moreplanets.forge.data.loot.ChestLootTables;
import com.stevekung.moreplanets.forge.data.loot.EntityLootTable;
import com.stevekung.stevekungslib.forge.data.loot.LootTableProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class LootTables extends LootTableProviderBase
{
    public LootTables(DataGenerator generator, String modId)
    {
        super(generator);
        this.addTable(Pair.of(() -> new BlockLootTable(modId), LootContextParamSets.BLOCK)).addTable(Pair.of(() -> new EntityLootTable(modId), LootContextParamSets.ENTITY)).addTable(Pair.of(ChestLootTables::new, LootContextParamSets.CHEST));
    }
}