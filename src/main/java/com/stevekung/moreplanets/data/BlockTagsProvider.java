package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.registry.MPBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tags.BlockTags;

public class BlockTagsProvider extends FabricTagProvider.BlockTagProvider
{
    public BlockTagsProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    protected void generateTags()
    {
        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(MPBlocks.GLOWING_IRON_BLOCK);

        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.PURLONITE_BLOCK);
        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.BUDDING_PURLONITE);
        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.SMALL_PURLONITE_BUD);
        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(MPBlocks.LARGE_PURLONITE_BUD);

        this.tag(BlockTags.MOSS_REPLACEABLE).add(MPBlocks.DIONA_REGOLITH);
        this.tag(BlockTags.MOSS_REPLACEABLE).add(MPBlocks.DIONA_FINE_REGOLITH);
        this.tag(BlockTags.MOSS_REPLACEABLE).add(MPBlocks.DIONA_STONE);

        this.tag(BlockTags.SCULK_REPLACEABLE).add(MPBlocks.DIONA_REGOLITH);
        this.tag(BlockTags.SCULK_REPLACEABLE).add(MPBlocks.DIONA_FINE_REGOLITH);
        this.tag(BlockTags.SCULK_REPLACEABLE).add(MPBlocks.DIONA_STONE);

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