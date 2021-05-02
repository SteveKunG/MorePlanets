package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.BlockStateProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProviderBase
{
    public BlockStates(DataGenerator generator, String modid, ExistingFileHelper helper)
    {
        super(generator, modid, helper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        this.simpleBlock(MPBlocks.DIONA_REGOLITH);
        this.simpleBlock(MPBlocks.DIONA_FINE_REGOLITH);
        this.simpleBlock(MPBlocks.DIONA_STONE);
        this.simpleBlock(MPBlocks.DIONA_COBBLESTONE);
    }
}