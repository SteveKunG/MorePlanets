package com.stevekung.moreplanets.data.client.model;

import java.util.Optional;

import com.stevekung.moreplanets.core.MorePlanets;

import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

public class ModelTemplatesMP
{
    public static final ModelTemplate TERRARIUM_JAR = create("terrarium_jar", TextureSlotMP.TERRARIUM);
    public static final ModelTemplate TRANSLUCENT_LANTERN = create("template_translucent_lantern", TextureSlot.LANTERN);
    public static final ModelTemplate TRANSLUCENT_HANGING_LANTERN = create("template_hanging_translucent_lantern", "_hanging", TextureSlot.LANTERN);
    public static final ModelTemplate COMPACTED_CRYSTAL = create("compacted_crystal", TextureSlotMP.CRYSTAL);
    public static final ModelTemplate COMPACTED_CRYSTAL_BOTTOM = create("compacted_crystal_bottom", TextureSlotMP.CRYSTAL);
    public static final ModelTemplate COMPACTED_CRYSTAL_MIDDLE = create("compacted_crystal_middle", TextureSlotMP.CRYSTAL);
    public static final ModelTemplate COMPACTED_CRYSTAL_TOP = create("compacted_crystal_top", TextureSlotMP.CRYSTAL);

    public static final ModelTemplate AMETHYST_CLUSTER = ModelTemplates.createItem("amethyst_cluster", TextureSlot.LAYER0);
    public static final ModelTemplate SMALL_AMETHYST_BUD = ModelTemplates.createItem("small_amethyst_bud", TextureSlot.LAYER0);
    public static final ModelTemplate MEDIUM_AMETHYST_BUD = ModelTemplates.createItem("medium_amethyst_bud", TextureSlot.LAYER0);
    public static final ModelTemplate LARGE_AMETHYST_BUD = ModelTemplates.createItem("large_amethyst_bud", TextureSlot.LAYER0);

    private static ModelTemplate create(String blockModelLocation, TextureSlot... requiredSlots)
    {
        return new ModelTemplate(Optional.of(new ResourceLocation(MorePlanets.MOD_ID, "block/" + blockModelLocation)), Optional.empty(), requiredSlots);
    }

    private static ModelTemplate create(String blockModelLocation, String suffix, TextureSlot... requiredSlots)
    {
        return new ModelTemplate(Optional.of(new ResourceLocation(MorePlanets.MOD_ID, "block/" + blockModelLocation)), Optional.of(suffix), requiredSlots);
    }
}