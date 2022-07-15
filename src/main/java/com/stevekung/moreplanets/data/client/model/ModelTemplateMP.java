package com.stevekung.moreplanets.data.client.model;

import java.util.Optional;

import com.stevekung.moreplanets.core.MorePlanets;

import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

public class ModelTemplateMP
{
    public static final ModelTemplate TERRARIUM_JAR = create("terrarium_jar", TextureSlotMP.TERRARIUM);
    public static final ModelTemplate TRANSLUCENT_LANTERN = create("template_translucent_lantern", TextureSlot.LANTERN);
    public static final ModelTemplate TRANSLUCENT_HANGING_LANTERN = create("template_hanging_translucent_lantern", "_hanging", TextureSlot.LANTERN);

    private static ModelTemplate create(String blockModelLocation, TextureSlot... requiredSlots)
    {
        return new ModelTemplate(Optional.of(new ResourceLocation(MorePlanets.MOD_ID, "block/" + blockModelLocation)), Optional.empty(), requiredSlots);
    }

    private static ModelTemplate create(String blockModelLocation, String suffix, TextureSlot... requiredSlots)
    {
        return new ModelTemplate(Optional.of(new ResourceLocation(MorePlanets.MOD_ID, "block/" + blockModelLocation)), Optional.of(suffix), requiredSlots);
    }
}