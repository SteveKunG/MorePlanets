package com.stevekung.moreplanets.world.level.block;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class MPBlocks
{
    public static final Block DIONA_REGOLITH = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_FINE_REGOLITH = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_STONE = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_COBBLESTONE = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));

    public static void init()
    {
        MorePlanetsMod.COMMON.registerBlock("diona_regolith", DIONA_REGOLITH, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_fine_regolith", DIONA_FINE_REGOLITH, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_stone", DIONA_STONE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_cobblestone", DIONA_COBBLESTONE, new Item.Properties().tab(MorePlanetsMod.TAB));
    }
}