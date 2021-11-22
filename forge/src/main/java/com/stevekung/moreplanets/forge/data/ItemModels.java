package com.stevekung.moreplanets.forge.data;

import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekunglib.forge.data.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProviderBase
{
    public ItemModels(DataGenerator generator, String modid, ExistingFileHelper helper)
    {
        super(generator, modid, helper);
    }

    @Override
    protected void registerModels()
    {
        this.parentedBlock(MPBlocks.DIONA_REGOLITH);
        this.parentedBlock(MPBlocks.DIONA_FINE_REGOLITH);
        this.parentedBlock(MPBlocks.DIONA_STONE);
        this.parentedBlock(MPBlocks.DIONA_COBBLESTONE);
        this.parentedBlock(MPBlocks.GLOWING_IRON_BLOCK);
        this.parentedBlock(MPBlocks.RAW_GLOWING_IRON_BLOCK);
        this.parentedBlock(MPBlocks.METEORIC_IRON_STABILIZER);
        this.parentedBlock(MPBlocks.ION_PLASMA_ROD);
        this.parentedBlock(MPBlocks.PURLONITE_BLOCK);
        this.parentedBlock(MPBlocks.BUDDING_PURLONITE);
        this.parentedBlock(MPBlocks.DARK_ENERGY_CORE, "block/full_dark_energy_core");
        this.parentedBlock(MPBlocks.ZELIUS_EGG);
        this.parentedBlock(MPBlocks.DARK_ENERGY_GENERATOR);
        this.parentedBlock(MPBlocks.COMPACTED_PURLONITE_BLOCK);
        this.parentedBlock(MPBlocks.DISPLAY_JAR);
        this.parentedBlock(MPBlocks.PURLONITE_WORM_JAR);
        this.parentedBlock(MPBlocks.CHALOS_SPORE_JAR);

        this.itemGenerated(MPBlocks.PURLONITE_CRYSTAL_LANTERN.asItem());
        this.itemGenerated(MPBlocks.DARK_CRYSTAL_LANTERN.asItem());

        this.itemGenerated(MPBlocks.PURLONITE_CLUSTER.asItem(), new ResourceLocation("amethyst_cluster")).texture("layer0", "moreplanets:block/purlonite_cluster");
        this.itemGenerated(MPBlocks.SMALL_PURLONITE_BUD.asItem(), new ResourceLocation("small_amethyst_bud")).texture("layer0", "moreplanets:block/small_purlonite_bud");
        this.itemGenerated(MPBlocks.MEDIUM_PURLONITE_BUD.asItem(), new ResourceLocation("medium_amethyst_bud")).texture("layer0", "moreplanets:block/medium_purlonite_bud");
        this.itemGenerated(MPBlocks.LARGE_PURLONITE_BUD.asItem(), new ResourceLocation("large_amethyst_bud")).texture("layer0", "moreplanets:block/large_purlonite_bud");

        this.itemGenerated(MPItems.GLOWING_IRON_INGOT);
        this.itemGenerated(MPItems.RAW_GLOWING_IRON);
        this.itemGenerated(MPItems.PURLONITE_SHARD);
        this.itemGenerated(MPItems.DARK_CRYSTAL_SHARD);

        this.itemHandheld(MPItems.GLOWING_IRON_SWORD);
        this.itemHandheld(MPItems.GLOWING_IRON_SHOVEL);
        this.itemHandheld(MPItems.GLOWING_IRON_PICKAXE);
        this.itemHandheld(MPItems.GLOWING_IRON_AXE);
        this.itemHandheld(MPItems.GLOWING_IRON_HOE);

        this.itemGenerated(MPItems.GLOWING_IRON_HELMET);
        this.itemGenerated(MPItems.GLOWING_IRON_CHESTPLATE);
        this.itemGenerated(MPItems.GLOWING_IRON_LEGGINGS);
        this.itemGenerated(MPItems.GLOWING_IRON_BOOTS);
    }
}