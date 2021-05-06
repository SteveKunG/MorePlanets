package com.stevekung.moreplanets.world.level.block.entity;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MPBlockEntities
{
    public static final BlockEntityType<DarkEnergyCoreBlockEntity> DARK_ENERGY_CORE = BlockEntityType.Builder.of(DarkEnergyCoreBlockEntity::new, MPBlocks.DARK_ENERGY_CORE).build(null);

    public static void init()
    {
        MorePlanetsMod.COMMON.registerTileEntityType("dark_energy_core", DARK_ENERGY_CORE);
    }
}