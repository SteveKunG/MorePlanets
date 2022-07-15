package com.stevekung.moreplanets.data.client;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.data.client.model.ModelTemplateMP;
import com.stevekung.moreplanets.data.client.model.TextureSlotMP;
import com.stevekung.moreplanets.registry.MPBlocks;
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
    public static final TexturedModel.Provider TRANSLUCENT_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, ModelTemplateMP.TRANSLUCENT_LANTERN);
    public static final TexturedModel.Provider TRANSLUCENT_HANGING_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, ModelTemplateMP.TRANSLUCENT_HANGING_LANTERN);

    public BlockModelGeneratorsMP(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput)
    {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
    }

    public void createTranslucentLantern(Block lanternBlock)
    {
        var resourceLocation = TRANSLUCENT_LANTERN.create(lanternBlock, this.modelOutput);
        var resourceLocation2 = TRANSLUCENT_HANGING_LANTERN.create(lanternBlock, this.modelOutput);
        this.createSimpleFlatItemModel(lanternBlock.asItem());
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(lanternBlock).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.HANGING, resourceLocation2, resourceLocation)));
    }

    public void createDarkEnergyCore()
    {
        var fullCore = new ResourceLocation(MorePlanets.MOD_ID, "block/full_dark_energy_core");
        var textureMapping = new TextureMapping().put(TextureSlot.PARTICLE, fullCore);
        this.delegateItemModel(MPBlocks.DARK_ENERGY_CORE, fullCore);
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(MPBlocks.DARK_ENERGY_CORE, ModelTemplates.PARTICLE_ONLY.create(MPBlocks.DARK_ENERGY_CORE, textureMapping, this.modelOutput)).with(createDarkEnergyModelDispatch(new ResourceLocation(MorePlanets.MOD_ID, "block/partial_dark_energy_core"), fullCore)));
    }

    public void createTerrariumJar(Block terrariumJar, ResourceLocation terrarium)
    {
        var textureMapping = new TextureMapping().put(TextureSlotMP.TERRARIUM, terrarium);
        var resourceLocation = ModelTemplateMP.TERRARIUM_JAR.create(terrariumJar, textureMapping, this.modelOutput);
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

    public static PropertyDispatch createDarkEnergyModelDispatch(ResourceLocation trueModelLocation, ResourceLocation falseModelLocation)
    {
        return PropertyDispatch.property(DarkEnergyCoreBlock.STATE).select(DarkEnergyCoreBlock.State.PARTIAL, Variant.variant().with(VariantProperties.MODEL, trueModelLocation)).select(DarkEnergyCoreBlock.State.FULL, Variant.variant().with(VariantProperties.MODEL, falseModelLocation));
    }
}