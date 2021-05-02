package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.world.level.block.MPBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsBuilder extends BlockTagsProvider
{
    public BlockTagsBuilder(DataGenerator generator, String modid, ExistingFileHelper helper)
    {
        super(generator, modid, helper);
    }

    @Override
    protected void addTags()
    {
        this.tag(Tags.Blocks.STONE).add(MPBlocks.DIONA_STONE);
        this.tag(Tags.Blocks.COBBLESTONE).add(MPBlocks.DIONA_COBBLESTONE);
        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(MPBlocks.GLOWING_IRON_BLOCK);
        this.tag(Tags.Blocks.STORAGE_BLOCKS_IRON).add(MPBlocks.GLOWING_IRON_BLOCK);
        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(MPBlocks.GLOWING_IRON_BLOCK);
    }
}