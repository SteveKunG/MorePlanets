package com.stevekung.moreplanets.utils.forge;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.ToolType;

public class BlockPropertiesUtilsImpl
{
    public static BlockBehaviour.Properties pickaxeLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return copyOf.harvestLevel(level).harvestTool(ToolType.PICKAXE);
    }

    public static BlockBehaviour.Properties axeLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return copyOf.harvestLevel(level).harvestTool(ToolType.AXE);
    }

    public static BlockBehaviour.Properties shovelLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return copyOf.harvestLevel(level).harvestTool(ToolType.SHOVEL);
    }

    public static BlockBehaviour.Properties hoeLevel(BlockBehaviour.Properties copyOf, int level)
    {
        return copyOf.harvestLevel(level).harvestTool(ToolType.HOE);
    }
}