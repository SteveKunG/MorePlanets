package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.client.entity.models.DarkEnergyOrbModel;
import com.stevekung.moreplanets.client.renderer.blockentity.DarkEnergyCoreRenderer;
import com.stevekung.moreplanets.registry.MPBlockEntities;
import com.stevekung.moreplanets.registry.MPBlocks;
import com.stevekung.moreplanets.registry.MPItems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.impl.client.texture.SpriteRegistryCallbackHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class MorePlanetsClient implements ClientModInitializer
{
    private HumanoidModel<LivingEntity> armorModel;
    private static final ResourceLocation GLOWING_IRON = new ResourceLocation(MorePlanets.MOD_ID, "textures/models/armor/glowing_iron.png");

    @Override
    public void onInitializeClient()
    {
        ArmorRenderer.register((poseStack, vertexConsumer, itemStack, entity, slot, packedLight, contextModel) ->
        {
            if (this.armorModel == null)
            {
                this.armorModel = new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_INNER_ARMOR));
            }
            contextModel.copyPropertiesTo(this.armorModel);
            this.armorModel.setAllVisible(false);
            this.armorModel.head.visible = slot == EquipmentSlot.HEAD;
            this.armorModel.body.visible = slot == EquipmentSlot.CHEST;
            this.armorModel.leftArm.visible = slot == EquipmentSlot.CHEST;
            this.armorModel.rightArm.visible = slot == EquipmentSlot.CHEST;
            this.armorModel.leftLeg.visible = slot == EquipmentSlot.LEGS;
            this.armorModel.rightLeg.visible = slot == EquipmentSlot.LEGS;
            ArmorRenderer.renderPart(poseStack, vertexConsumer, packedLight, itemStack, this.armorModel, GLOWING_IRON);
        }, MPItems.GLOWING_IRON_HELMET, MPItems.GLOWING_IRON_CHESTPLATE, MPItems.GLOWING_IRON_LEGGINGS, MPItems.GLOWING_IRON_BOOTS);

        var cutout = RenderType.cutout();
        var translucent = RenderType.translucent();

        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.PURLONITE_BLOCK);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.BUDDING_PURLONITE);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.PURLONITE_CLUSTER);// TODO Fix render type
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.LARGE_PURLONITE_BUD);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.MEDIUM_PURLONITE_BUD);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.SMALL_PURLONITE_BUD);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.PURLONITE_CRYSTAL_LANTERN);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.DARK_CRYSTAL_LANTERN);
        BlockRenderLayerMap.INSTANCE.putBlocks(translucent, MPBlocks.COMPACTED_PURLONITE_BLOCK);
        BlockRenderLayerMap.INSTANCE.putBlocks(cutout, MPBlocks.DARK_ENERGY_CORE);
        BlockRenderLayerMap.INSTANCE.putBlocks(cutout, MPBlocks.ZELIUS_EGG);
        BlockRenderLayerMap.INSTANCE.putBlocks(cutout, MPBlocks.DARK_ENERGY_GENERATOR);
        BlockRenderLayerMap.INSTANCE.putBlocks(cutout, MPBlocks.DISPLAY_JAR);
        BlockRenderLayerMap.INSTANCE.putBlocks(cutout, MPBlocks.PURLONITE_WORM_JAR);
        BlockRenderLayerMap.INSTANCE.putBlocks(cutout, MPBlocks.CHALOS_SPORE_JAR);

        BlockEntityRendererRegistry.register(MPBlockEntities.DARK_ENERGY_CORE, DarkEnergyCoreRenderer::new);

        SpriteRegistryCallbackHolder.EVENT_GLOBAL.register((atlasTexture, registry) ->
        {
            registry.register(new ResourceLocation(MorePlanets.MOD_ID, "entity/dark_energy_orb"));
            registry.register(new ResourceLocation(MorePlanets.MOD_ID, "entity/dark_energy_egg"));
        });

        EntityModelLayerRegistry.registerModelLayer(DarkEnergyOrbModel.LAYER, DarkEnergyOrbModel::createBodyLayer);
    }
}