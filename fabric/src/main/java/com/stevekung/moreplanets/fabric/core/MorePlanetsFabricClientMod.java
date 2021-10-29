package com.stevekung.moreplanets.fabric.core;

import com.stevekung.moreplanets.client.entity.models.DarkEnergyBallModel;
import com.stevekung.moreplanets.client.models.geom.MPModelLayers;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.world.item.MPItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class MorePlanetsFabricClientMod implements ClientModInitializer
{
    private HumanoidModel<LivingEntity> armorModel;
    private static final ResourceLocation GLOWING_IRON = new ResourceLocation("moreplanets", "textures/models/armor/glowing_iron.png");

    @Override
    public void onInitializeClient()
    {
        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, model) ->
        {
            if (this.armorModel == null)
            {
                this.armorModel = new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_INNER_ARMOR));
            }
            model.copyPropertiesTo(this.armorModel);
            this.armorModel.setAllVisible(false);
            this.armorModel.head.visible = slot == EquipmentSlot.HEAD;
            this.armorModel.body.visible = slot == EquipmentSlot.CHEST;
            this.armorModel.leftArm.visible = slot == EquipmentSlot.CHEST;
            this.armorModel.rightArm.visible = slot == EquipmentSlot.CHEST;
            this.armorModel.leftLeg.visible = slot == EquipmentSlot.LEGS;
            this.armorModel.rightLeg.visible = slot == EquipmentSlot.LEGS;
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, this.armorModel, GLOWING_IRON);
        }, MPItems.GLOWING_IRON_HELMET, MPItems.GLOWING_IRON_CHESTPLATE, MPItems.GLOWING_IRON_LEGGINGS, MPItems.GLOWING_IRON_BOOTS);

        MorePlanetsMod.initClient();
        EntityModelLayerRegistry.registerModelLayer(MPModelLayers.DARK_ENERGY_BALL, DarkEnergyBallModel::createBodyLayer);
    }
}