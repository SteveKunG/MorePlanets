package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.registry.MPBlocks;
import com.stevekung.moreplanets.registry.MPItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.tags.ItemTags;

public class ItemTagsProvider extends FabricTagProvider.ItemTagProvider
{
    public ItemTagsProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    protected void generateTags()
    {
        this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(MPBlocks.DIONA_COBBLESTONE.asItem());
        this.tag(ItemTags.STONE_TOOL_MATERIALS).add(MPBlocks.DIONA_COBBLESTONE.asItem());

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(MPItems.GLOWING_IRON_INGOT);
        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(MPItems.GLOWING_IRON_PICKAXE);

        // Fabric
        this.tag(ConventionalItemTags.AXES).add(MPItems.GLOWING_IRON_AXE);
        this.tag(ConventionalItemTags.HOES).add(MPItems.GLOWING_IRON_HOE);
        this.tag(ConventionalItemTags.PICKAXES).add(MPItems.GLOWING_IRON_PICKAXE);
        this.tag(ConventionalItemTags.SHOVELS).add(MPItems.GLOWING_IRON_SHOVEL);
        this.tag(ConventionalItemTags.SWORDS).add(MPItems.GLOWING_IRON_SWORD);
    }
}