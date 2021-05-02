package com.stevekung.moreplanets.data;

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
    }
}