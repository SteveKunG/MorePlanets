package com.stevekung.moreplanets.utils.fabric;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockPropertiesUtilsImpl
{
    public static BlockBehaviour.Properties pickaxeLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return FabricBlockSettings.copyOf(copyOf).breakByTool(FabricToolTags.PICKAXES, level);
    }

    public static BlockBehaviour.Properties axeLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return FabricBlockSettings.copyOf(copyOf).breakByTool(FabricToolTags.AXES, level);
    }

    public static BlockBehaviour.Properties shovelLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return FabricBlockSettings.copyOf(copyOf).breakByTool(FabricToolTags.SHOVELS, level);
    }

    public static BlockBehaviour.Properties hoeLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return FabricBlockSettings.copyOf(copyOf).breakByTool(FabricToolTags.HOES, level);
    }
}