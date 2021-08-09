package com.stevekung.moreplanets.mixin.client.model.geom;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import com.google.common.collect.ImmutableMap;
import com.stevekung.moreplanets.client.entity.models.DarkEnergyBallModel;
import com.stevekung.moreplanets.client.models.geom.MPModelLayers;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

@Mixin(LayerDefinitions.class)
public class MixinLayerDefinitions
{
    @Inject(method = "createRoots", at = @At(value = "FIELD", target = "net/minecraft/client/model/geom/builders/CubeDeformation.NONE:Lnet/minecraft/client/model/geom/builders/CubeDeformation;", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void createRoots(CallbackInfoReturnable<Map<ModelLayerLocation, LayerDefinition>> info, ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder)
    {
        builder.put(MPModelLayers.DARK_ENERGY_BALL, DarkEnergyBallModel.createBodyLayer());
    }
}