package com.stevekung.moreplanets.data.client;

import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.registry.MPBlocks;
import com.stevekung.moreplanets.registry.MPItems;
import com.stevekung.moreplanets.world.level.block.DarkEnergyGeneratorBlock;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

public class ModelProvider extends FabricModelProvider
{
    public ModelProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator)
    {
        var mpGen = new BlockModelGeneratorsMP(generator.blockStateOutput, generator.modelOutput, generator.skippedAutoModelsOutput);

        mpGen.createRotatedSurfaceBlock(MPBlocks.DIONA_FINE_REGOLITH, MPBlocks.DIONA_REGOLITH);
        generator.createTrivialCube(MPBlocks.DIONA_FINE_REGOLITH);
        mpGen.createMirroredCube(MPBlocks.DIONA_STONE);
        generator.createTrivialCube(MPBlocks.DIONA_COBBLESTONE);
        generator.createTrivialCube(MPBlocks.GLOWING_IRON_BLOCK);
        generator.createTrivialCube(MPBlocks.RAW_GLOWING_IRON_BLOCK);
        generator.createTrivialCube(MPBlocks.PURLONITE_BLOCK);
        generator.createTrivialCube(MPBlocks.BUDDING_PURLONITE);
        mpGen.createTranslucentLantern(MPBlocks.PURLONITE_CRYSTAL_LANTERN);
        mpGen.createTranslucentLantern(MPBlocks.DARK_CRYSTAL_LANTERN);
        mpGen.createRotatedPillar(MPBlocks.METEORIC_IRON_STABILIZER);
        mpGen.createRod(MPBlocks.ION_PLASMA_ROD);
        mpGen.createPoweredMachine(MPBlocks.DARK_ENERGY_GENERATOR, DarkEnergyGeneratorBlock.ACTIVE);
        generator.createAmethystCluster(MPBlocks.PURLONITE_CLUSTER);
        generator.createAmethystCluster(MPBlocks.SMALL_PURLONITE_BUD);
        generator.createAmethystCluster(MPBlocks.MEDIUM_PURLONITE_BUD);
        generator.createAmethystCluster(MPBlocks.LARGE_PURLONITE_BUD);
        mpGen.createDarkEnergyCore();
        generator.createNonTemplateModelBlock(MPBlocks.ZELIUS_EGG);
        mpGen.createCompactedCrystal(MPBlocks.COMPACTED_PURLONITE_BLOCK, MPBlocks.PURLONITE_BLOCK);
        mpGen.createDisplayJar(MPBlocks.DISPLAY_JAR);
        mpGen.createDisplayJar(MPBlocks.PURLONITE_WORM_JAR);
        mpGen.createTerrariumJar(MPBlocks.CHALOS_SPORE_JAR, new ResourceLocation(MorePlanets.MOD_ID, "block/chalos_spore"));
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator)
    {
        var mpGen = new ItemModelGeneratorsMP(generator.output);
        generator.generateFlatItem(MPItems.GLOWING_IRON_INGOT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(MPItems.RAW_GLOWING_IRON, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(MPItems.PURLONITE_SHARD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(MPItems.DARK_CRYSTAL_SHARD, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(MPItems.GLOWING_IRON_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        generator.generateFlatItem(MPItems.GLOWING_IRON_HELMET, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_LEGGINGS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(MPItems.GLOWING_IRON_BOOTS, ModelTemplates.FLAT_ITEM);

        mpGen.generateCrystalsSetItem(MPBlocks.PURLONITE_CLUSTER, MPBlocks.SMALL_PURLONITE_BUD, MPBlocks.MEDIUM_PURLONITE_BUD, MPBlocks.LARGE_PURLONITE_BUD);
    }
}