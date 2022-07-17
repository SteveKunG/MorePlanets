package com.stevekung.moreplanets.data.client;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.stevekung.moreplanets.data.client.model.ModelTemplatesMP;

import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ItemModelGeneratorsMP extends ItemModelGenerators
{
    public ItemModelGeneratorsMP(BiConsumer<ResourceLocation, Supplier<JsonElement>> output)
    {
        super(output);
    }

    public void generateCrystalsSetItem(Block cluster, Block small, Block medium, Block large)
    {
        ModelTemplatesMP.AMETHYST_CLUSTER.create(ModelLocationUtils.getModelLocation(cluster.asItem()), TextureMapping.layer0(cluster), this.output);
        ModelTemplatesMP.SMALL_AMETHYST_BUD.create(ModelLocationUtils.getModelLocation(small.asItem()), TextureMapping.layer0(small), this.output);
        ModelTemplatesMP.MEDIUM_AMETHYST_BUD.create(ModelLocationUtils.getModelLocation(medium.asItem()), TextureMapping.layer0(medium), this.output);
        ModelTemplatesMP.LARGE_AMETHYST_BUD.create(ModelLocationUtils.getModelLocation(large.asItem()), TextureMapping.layer0(large), this.output);
    }
}