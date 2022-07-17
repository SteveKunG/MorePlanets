package com.stevekung.moreplanets.data.client;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.data.client.model.ModelTemplatesMP;
import com.stevekung.moreplanets.data.client.model.TextureMappingMP;
import com.stevekung.moreplanets.data.client.model.TextureSlotMP;
import com.stevekung.moreplanets.registry.MPBlocks;
import com.stevekung.moreplanets.world.level.block.CompactedBlock;
import com.stevekung.moreplanets.world.level.block.DarkEnergyCoreBlock;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BlockModelGeneratorsMP extends BlockModelGenerators
{
    public static final TexturedModel.Provider TRANSLUCENT_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, ModelTemplatesMP.TRANSLUCENT_LANTERN);
    public static final TexturedModel.Provider TRANSLUCENT_HANGING_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, ModelTemplatesMP.TRANSLUCENT_HANGING_LANTERN);

    public BlockModelGeneratorsMP(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput)
    {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
    }

    public void createTranslucentLantern(Block lantern)
    {
        var resourceLocation = TRANSLUCENT_LANTERN.create(lantern, this.modelOutput);
        var resourceLocation2 = TRANSLUCENT_HANGING_LANTERN.create(lantern, this.modelOutput);
        this.createSimpleFlatItemModel(lantern.asItem());
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(lantern).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.HANGING, resourceLocation2, resourceLocation)));
    }

    public void createDarkEnergyCore()
    {
        var fullCore = new ResourceLocation(MorePlanets.MOD_ID, "block/full_dark_energy_core");
        var textureMapping = TextureMapping.singleSlot(TextureSlot.PARTICLE, fullCore);
        this.delegateItemModel(MPBlocks.DARK_ENERGY_CORE, fullCore);
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(MPBlocks.DARK_ENERGY_CORE, ModelTemplates.PARTICLE_ONLY.create(MPBlocks.DARK_ENERGY_CORE, textureMapping, this.modelOutput)).with(createDarkEnergyCoreModelDispatch(new ResourceLocation(MorePlanets.MOD_ID, "block/partial_dark_energy_core"), fullCore)));
    }

    public void createCompactedCrystal(Block block, Block crystal)
    {
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(this.createCompactedCrystalModelDispatch(block, crystal)));
    }

    public void createTerrariumJar(Block terrariumJar, ResourceLocation terrarium)
    {
        var textureMapping = TextureMapping.singleSlot(TextureSlotMP.TERRARIUM, terrarium);
        var resourceLocation = ModelTemplatesMP.TERRARIUM_JAR.create(terrariumJar, textureMapping, this.modelOutput);
        this.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(terrariumJar, resourceLocation));
    }

    public void createPoweredMachine(Block block, BooleanProperty property)
    {
        var resourceLocation = ModelLocationUtils.getModelLocation(block);
        var resourceLocation2 = ModelLocationUtils.getModelLocation(block, "_on");
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(BlockModelGenerators.createBooleanModelDispatch(property, resourceLocation2, resourceLocation)));
    }

    public void createRotatedPillar(Block block)
    {
        var resourceLocation = ModelLocationUtils.getModelLocation(block);
        this.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(block, resourceLocation, resourceLocation).with(BlockModelGenerators.createRotatedPillar()));
    }

    public void createRotatedSurfaceBlock(Block bottomTexture, Block block)
    {
        var textureMapping = new TextureMapping().put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(bottomTexture)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
        this.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(block, ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, this.modelOutput)));
    }

    public void createDisplayJar(Block block)
    {
        this.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(block, ModelLocationUtils.getModelLocation(block)));
    }

    public void createSimpleSurfaceBlock(Block bottomTexture, Block block)
    {
        var textureMapping = new TextureMapping().put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(bottomTexture)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, this.modelOutput)));
    }

    public void createMirroredCube(Block block)
    {
        this.blockStateOutput.accept(BlockModelGenerators.createMirroredCubeGenerator(block, TexturedModel.CUBE.create(block, this.modelOutput), TextureMapping.cube(block), this.modelOutput));
    }

    public void createRod(Block block)
    {
        var resourceLocation = ModelLocationUtils.getModelLocation(block, "_on");
        var resourceLocation2 = ModelLocationUtils.getModelLocation(block);
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourceLocation2)).with(this.createColumnWithFacing()).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.POWERED, resourceLocation, resourceLocation2)));
    }

    private PropertyDispatch createCompactedCrystalModelDispatch(Block block, Block crystal)
    {
        return PropertyDispatch.property(CompactedBlock.TYPE)
                .select(CompactedBlock.Type.NONE, Variant.variant().with(VariantProperties.MODEL, ModelTemplatesMP.COMPACTED_CRYSTAL.create(block, TextureMappingMP.compactedCrystal(crystal), this.modelOutput)))
                .select(CompactedBlock.Type.BOTTOM, Variant.variant().with(VariantProperties.MODEL, ModelTemplatesMP.COMPACTED_CRYSTAL_BOTTOM.createWithSuffix(block, "_bottom", TextureMappingMP.compactedCrystal(crystal), this.modelOutput)))
                .select(CompactedBlock.Type.MIDDLE, Variant.variant().with(VariantProperties.MODEL, ModelTemplatesMP.COMPACTED_CRYSTAL_MIDDLE.createWithSuffix(block, "_middle", TextureMappingMP.compactedCrystal(crystal), this.modelOutput)))
                .select(CompactedBlock.Type.TOP, Variant.variant().with(VariantProperties.MODEL, ModelTemplatesMP.COMPACTED_CRYSTAL_TOP.createWithSuffix(block, "_top", TextureMappingMP.compactedCrystal(crystal), this.modelOutput)));
    }

    private static PropertyDispatch createDarkEnergyCoreModelDispatch(ResourceLocation partialModel, ResourceLocation fullModel)
    {
        return PropertyDispatch.property(DarkEnergyCoreBlock.STATE).select(DarkEnergyCoreBlock.State.PARTIAL, Variant.variant().with(VariantProperties.MODEL, partialModel)).select(DarkEnergyCoreBlock.State.FULL, Variant.variant().with(VariantProperties.MODEL, fullModel));
    }
}