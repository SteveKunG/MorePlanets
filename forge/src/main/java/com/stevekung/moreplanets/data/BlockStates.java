package com.stevekung.moreplanets.data;

import com.google.common.collect.ObjectArrays;
import com.stevekung.moreplanets.world.level.block.*;
import com.stevekung.stevekungslib.data.BlockStateProviderBase;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Lantern;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
        this.simpleBlock(MPBlocks.PURLONITE_BLOCK);
        this.simpleBlock(MPBlocks.BUDDING_PURLONITE);
        this.generateCrystalModel(MPBlocks.PURLONITE_CLUSTER);
        this.generateCrystalModel(MPBlocks.LARGE_PURLONITE_BUD);
        this.generateCrystalModel(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.generateCrystalModel(MPBlocks.SMALL_PURLONITE_BUD);
        this.generateTranslucentLanternModel(MPBlocks.PURLONITE_CRYSTAL_LANTERN);
        this.generateTranslucentLanternModel(MPBlocks.DARK_CRYSTAL_LANTERN);
        this.getVariantBuilder(MPBlocks.DARK_ENERGY_CORE).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/" + state.getValue(DarkEnergyCoreBlock.STATE).getSerializedName() + "_" + this.toString(MPBlocks.DARK_ENERGY_CORE)))).build(), DarkEnergyCoreBlock.WATERLOGGED);
        this.simpleBlock(MPBlocks.ZELIUS_EGG, this.models().getExistingFile(this.modLoc("block/zelius_egg")));
        this.getVariantBuilder(MPBlocks.DARK_ENERGY_GENERATOR).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(state.getValue(DarkEnergyGeneratorBlock.ACTIVE) ? this.models().getExistingFile(this.modLoc("block/dark_energy_generator_on")) : this.models().getExistingFile(this.modLoc("block/dark_energy_generator"))).build(), DarkEnergyGeneratorBlock.WATERLOGGED);
        this.generateCompactedCrystal(MPBlocks.COMPACTED_PURLONITE_BLOCK, "purlonite_block");
        this.simpleBlock(MPBlocks.SPACE_JAR, ConfiguredModel.allYRotations(this.models().getExistingFile(this.modLoc("block/space_jar")), 0, false));
        this.simpleBlock(MPBlocks.PURLONITE_WORM_JAR, ConfiguredModel.allYRotations(this.models().getExistingFile(this.modLoc("block/purlonite_worm_jar")), 0, false));
        this.simpleBlock(MPBlocks.CHALOS_SPORE_JAR, ConfiguredModel.allYRotations(this.models().withExistingParent("chalos_spore_jar", this.modLoc("block/terrarium_jar")).texture("terrarium", this.modLoc("block/chalos_spore")), 0, false));

        ModelFile misModel = this.models().getExistingFile(this.modLoc("block/meteoric_iron_stabilizer"));
        this.getVariantBuilder(MPBlocks.METEORIC_IRON_STABILIZER).partialState().with(MeteoricIronStabilizerBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(misModel).addModel().partialState().with(MeteoricIronStabilizerBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(misModel).rotationX(90).addModel().partialState().with(MeteoricIronStabilizerBlock.AXIS, Direction.Axis.X).modelForState().modelFile(misModel).rotationX(90).rotationY(90).addModel();

        this.getVariantBuilder(MPBlocks.ION_PLASMA_ROD).forAllStatesExcept(state ->
        {
            Direction dir = state.getValue(BlockStateProperties.FACING);
            return ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc(state.getValue(IonPlasmaRodBlock.POWERED) ? "block/ion_plasma_rod_on" : "block/ion_plasma_rod"))).rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 90 : 0)).rotationY(dir.getAxis().isVertical() ? 0 : ((int)dir.toYRot() + 180) % 360).build();
        }, IonPlasmaRodBlock.WATERLOGGED);
    }

    private void generateCompactedCrystal(Block block, String texture)
    {
        this.getVariantBuilder(block).forAllStates(state ->
        {
            CompactedBlock.Type type = state.getValue(CompactedBlock.TYPE);
            String typeS = "";

            switch (type)
            {
                case TOP:
                    typeS = "_top";
                    break;
                case MIDDLE:
                    typeS = "_middle";
                    break;
                case BOTTOM:
                    typeS = "_bottom";
                    break;
            }
            return ConfiguredModel.builder().modelFile(this.models().withExistingParent(this.toString(block) + typeS, this.modLoc("block/compacted_crystal" + typeS)).texture("crystal", this.modLoc("block/" + texture))).build();
        });
    }

    private void generateCrystalModel(Block block)
    {
        this.getVariantBuilder(block).forAllStatesExcept(state ->
        {
            Direction dir = state.getValue(PurloniteClusterBlock.FACING);
            return ConfiguredModel.builder().modelFile(this.models().cross(this.toString(block), this.modLoc("block/" + this.toString(block)))).rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0).rotationY(dir.getAxis().isVertical() ? 0 : ((int)dir.toYRot() + 180) % 360).build();
        }, PurloniteClusterBlock.WATERLOGGED);
    }

    private void generateTranslucentLanternModel(Block block)
    {
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(this.models().withExistingParent((state.getValue(Lantern.HANGING) ? "hanging_" : "") + this.toString(block), this.modLoc("block/template_" + (state.getValue(Lantern.HANGING) ? "hanging_" : "") + "translucent_lantern")).texture("lantern", this.modLoc("block/" + this.toString(block)))).build(), Lantern.WATERLOGGED);
    }
}