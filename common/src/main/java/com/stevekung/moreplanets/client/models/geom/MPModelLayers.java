package com.stevekung.moreplanets.client.models.geom;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;

public class MPModelLayers
{
    public static ModelLayerLocation DARK_ENERGY_BALL;

    public static void init()
    {
        DARK_ENERGY_BALL = ModelLayers.register("dark_energy_ball");
    }
}