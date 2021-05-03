package com.stevekung.moreplanets.data;

import com.google.common.collect.ObjectArrays;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.moreplanets.world.level.block.PurloniteClusterBlock;
import com.stevekung.stevekungslib.data.BlockStateProviderBase;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
        this.simpleBlock(MPBlocks.PURLONITE_BLOCK);
        this.simpleBlock(MPBlocks.BUDDING_PURLONITE);
        this.generateCrystalModel(MPBlocks.PURLONITE_CLUSTER);
        this.generateCrystalModel(MPBlocks.LARGE_PURLONITE_BUD);
        this.generateCrystalModel(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.generateCrystalModel(MPBlocks.SMALL_PURLONITE_BUD);
    }

    private void generateCrystalModel(Block block)
    {
        this.getVariantBuilder(block).forAllStatesExcept(state ->
        {
            Direction dir = state.getValue(PurloniteClusterBlock.FACING);
            return ConfiguredModel.builder().modelFile(this.models().cross(this.toString(block), this.modLoc("block/" + this.toString(block)))).rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0).rotationY(dir.getAxis().isVertical() ? 0 : ((int)dir.toYRot() + 180) % 360).build();
        }, PurloniteClusterBlock.WATERLOGGED);
    }
}