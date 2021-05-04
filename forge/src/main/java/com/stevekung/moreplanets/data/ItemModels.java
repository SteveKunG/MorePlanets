package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
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

        this.itemGenerated(MPBlocks.PURLONITE_CLUSTER);
        this.itemGenerated(MPBlocks.LARGE_PURLONITE_BUD);
        this.itemGenerated(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.itemGenerated(MPBlocks.SMALL_PURLONITE_BUD);
        this.itemGenerated(MPBlocks.PURLONITE_CRYSTAL_LANTERN.asItem());

        this.itemGenerated(MPItems.GLOWING_IRON_INGOT);
        this.itemGenerated(MPItems.RAW_GLOWING_IRON);
        this.itemGenerated(MPItems.PURLONITE_SHARD);

        this.itemHandheld(MPItems.GLOWING_IRON_SWORD, "glowing_iron_sword");
        this.itemHandheld(MPItems.GLOWING_IRON_SHOVEL, "glowing_iron_shovel");
        this.itemHandheld(MPItems.GLOWING_IRON_PICKAXE, "glowing_iron_pickaxe");
        this.itemHandheld(MPItems.GLOWING_IRON_AXE, "glowing_iron_axe");
        this.itemHandheld(MPItems.GLOWING_IRON_HOE, "glowing_iron_hoe");

        this.itemGenerated(MPItems.GLOWING_IRON_HELMET);
        this.itemGenerated(MPItems.GLOWING_IRON_CHESTPLATE);
        this.itemGenerated(MPItems.GLOWING_IRON_LEGGINGS);
        this.itemGenerated(MPItems.GLOWING_IRON_BOOTS);
    }
}