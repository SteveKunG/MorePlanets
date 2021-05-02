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

        this.itemGenerated(MPItems.GLOWING_IRON_INGOT);
        this.itemGenerated(MPItems.RAW_GLOWING_IRON);

        this.itemGenerated(MPItems.GLOWING_IRON_SWORD);
        this.itemGenerated(MPItems.GLOWING_IRON_SHOVEL);
        this.itemGenerated(MPItems.GLOWING_IRON_PICKAXE);
        this.itemGenerated(MPItems.GLOWING_IRON_AXE);
        this.itemGenerated(MPItems.GLOWING_IRON_HOE);

        this.itemGenerated(MPItems.GLOWING_IRON_HELMET);
        this.itemGenerated(MPItems.GLOWING_IRON_CHESTPLATE);
        this.itemGenerated(MPItems.GLOWING_IRON_LEGGINGS);
        this.itemGenerated(MPItems.GLOWING_IRON_BOOTS);
    }
}