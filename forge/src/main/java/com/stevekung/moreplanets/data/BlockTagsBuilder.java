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

        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.PURLONITE_BLOCK);
        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.BUDDING_PURLONITE);

        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MPBlocks.GLOWING_IRON_BLOCK);
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MPBlocks.RAW_GLOWING_IRON_BLOCK);
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MPBlocks.COMPACTED_PURLONITE_BLOCK);
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MPBlocks.METEORIC_IRON_STABILIZER);
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MPBlocks.ION_PLASMA_ROD);

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(MPBlocks.DARK_ENERGY_CORE);
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(MPBlocks.ZELIUS_EGG);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DIONA_REGOLITH);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DIONA_FINE_REGOLITH);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DIONA_STONE);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DIONA_COBBLESTONE);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.GLOWING_IRON_BLOCK);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.RAW_GLOWING_IRON_BLOCK);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.COMPACTED_PURLONITE_BLOCK);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DARK_ENERGY_GENERATOR);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.PURLONITE_CRYSTAL_LANTERN);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DARK_CRYSTAL_LANTERN);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.METEORIC_IRON_STABILIZER);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.ION_PLASMA_ROD);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.DISPLAY_JAR);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.PURLONITE_WORM_JAR);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.CHALOS_SPORE_JAR);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.BUDDING_PURLONITE);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.PURLONITE_CLUSTER);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.SMALL_PURLONITE_BUD);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MPBlocks.LARGE_PURLONITE_BUD);
    }
}