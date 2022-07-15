package com.stevekung.moreplanets.registry;

import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.world.level.block.entity.DarkEnergyCoreBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MPBlockEntities
{
    public static final BlockEntityType<DarkEnergyCoreBlockEntity> DARK_ENERGY_CORE = FabricBlockEntityTypeBuilder.create(DarkEnergyCoreBlockEntity::new, MPBlocks.DARK_ENERGY_CORE).build();

    public static void init()
    {
        register("dark_energy_core", DARK_ENERGY_CORE);
    }

    private static void register(String key, BlockEntityType<?> type)
    {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(MorePlanets.MOD_ID, key), type);
    }
}