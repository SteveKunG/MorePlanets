package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.world.level.block.MPBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsBuilder extends ItemTagsProvider
{
    public ItemTagsBuilder(DataGenerator generator, BlockTagsProvider blockTagProvider, String modid, ExistingFileHelper helper)
    {
        super(generator, blockTagProvider, modid, helper);
    }

    @Override
    protected void addTags()
    {
        this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(MPBlocks.DIONA_COBBLESTONE.asItem());
        this.tag(ItemTags.STONE_TOOL_MATERIALS).add(MPBlocks.DIONA_COBBLESTONE.asItem());
        this.tag(Tags.Items.STONE).add(MPBlocks.DIONA_STONE.asItem());
        this.tag(Tags.Items.COBBLESTONE).add(MPBlocks.DIONA_COBBLESTONE.asItem());
    }
}