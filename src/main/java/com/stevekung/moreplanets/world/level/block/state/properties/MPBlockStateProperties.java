package com.stevekung.moreplanets.world.level.block.state.properties;

import com.stevekung.moreplanets.world.level.block.CompactedBlock;
import com.stevekung.moreplanets.world.level.block.DarkEnergyCoreBlock;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class MPBlockStateProperties
{
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final EnumProperty<CompactedBlock.Type> TYPE = EnumProperty.create("type", CompactedBlock.Type.class);
    public static final EnumProperty<DarkEnergyCoreBlock.State> STATE = EnumProperty.create("state", DarkEnergyCoreBlock.State.class);
}