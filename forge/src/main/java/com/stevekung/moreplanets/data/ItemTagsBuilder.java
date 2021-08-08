package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.utils.TagUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsBuilder extends ItemTagsProvider
{
    private static final Tag.Named<Item> FABRIC_AXES = TagUtils.createItemTag("fabric", "axes");
    private static final Tag.Named<Item> FABRIC_HOES = TagUtils.createItemTag("fabric", "hoes");
    private static final Tag.Named<Item> FABRIC_PICKAXES = TagUtils.createItemTag("fabric", "pickaxes");
    private static final Tag.Named<Item> FABRIC_SHOVELS = TagUtils.createItemTag("fabric", "shovels");
    private static final Tag.Named<Item> FABRIC_SWORDS = TagUtils.createItemTag("fabric", "swords");

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
        this.tag(Tags.Items.STORAGE_BLOCKS_IRON).add(MPBlocks.GLOWING_IRON_BLOCK.asItem());

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(MPItems.GLOWING_IRON_INGOT);
        this.tag(Tags.Items.INGOTS_IRON).add(MPItems.GLOWING_IRON_INGOT);
        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(MPItems.GLOWING_IRON_PICKAXE);

        this.tag(FABRIC_AXES).add(MPItems.GLOWING_IRON_AXE);
        this.tag(FABRIC_HOES).add(MPItems.GLOWING_IRON_HOE);
        this.tag(FABRIC_PICKAXES).add(MPItems.GLOWING_IRON_PICKAXE);
        this.tag(FABRIC_SHOVELS).add(MPItems.GLOWING_IRON_SHOVEL);
        this.tag(FABRIC_SWORDS).add(MPItems.GLOWING_IRON_SWORD);
    }
}