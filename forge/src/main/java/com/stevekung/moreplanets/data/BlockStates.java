package com.stevekung.moreplanets.data;

import com.google.common.collect.ObjectArrays;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.BlockStateProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
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
        this.simpleBlock(MPBlocks.DIONA_REGOLITH, ConfiguredModel.allYRotations(this.models().cubeBottomTop("diona_regolith", this.modLoc("block/diona_regolith_side"), this.modLoc("block/diona_fine_regolith"), this.modLoc("block/diona_regolith_top")), 0, false));
        this.simpleBlock(MPBlocks.DIONA_FINE_REGOLITH);
        this.simpleBlock(MPBlocks.DIONA_STONE, model -> ObjectArrays.concat(ConfiguredModel.allYRotations(model, 0, false), ConfiguredModel.allYRotations(model, 180, false), ConfiguredModel.class));
        this.simpleBlock(MPBlocks.DIONA_COBBLESTONE);
        this.simpleBlock(MPBlocks.GLOWING_IRON_BLOCK);
        this.simpleBlock(MPBlocks.RAW_GLOWING_IRON_BLOCK);
    }
}