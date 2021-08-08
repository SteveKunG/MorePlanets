package com.stevekung.moreplanets.world.level.block;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import dev.architectury.registry.block.BlockProperties;
import dev.architectury.registry.block.ToolType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class MPBlocks
{
    public static final Block DIONA_REGOLITH = new Block(BlockProperties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_FINE_REGOLITH = new Block(BlockProperties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_STONE = new Block(BlockProperties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_COBBLESTONE = new Block(BlockProperties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final Block RAW_GLOWING_IRON_BLOCK = new Block(BlockProperties.of(Material.STONE, MaterialColor.COLOR_GRAY).tool(ToolType.PICKAXE, 2).sound(SoundType.STONE).lightLevel(state -> 4).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
    public static final Block GLOWING_IRON_BLOCK = new Block(BlockProperties.of(Material.METAL, MaterialColor.COLOR_GRAY).tool(ToolType.PICKAXE, 2).sound(SoundType.METAL).lightLevel(state -> 4).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
    public static final Block PURLONITE_BLOCK = new PurloniteBlock(BlockProperties.of(Material.GLASS, MaterialColor.COLOR_PURPLE).tool(ToolType.PICKAXE).strength(1.5F).lightLevel(state -> 5).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block BUDDING_PURLONITE = new BuddingPurloniteBlock(BlockProperties.of(Material.GLASS).tool(ToolType.PICKAXE).strength(1.5F).sound(SoundType.GLASS).lightLevel(state -> 5).randomTicks().requiresCorrectToolForDrops().noOcclusion().noDrops().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block COMPACTED_PURLONITE_BLOCK = new CompactedBlock(BlockProperties.of(Material.STONE).tool(ToolType.PICKAXE).strength(2.0F).requiresCorrectToolForDrops().noOcclusion());
    public static final Block DARK_ENERGY_GENERATOR = new DarkEnergyGeneratorBlock(BlockProperties.of(Material.METAL).tool(ToolType.PICKAXE).strength(2.0F).lightLevel(DarkEnergyGeneratorBlock::getLightLevel).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion());
    public static final Block PURLONITE_CRYSTAL_LANTERN = new LanternBlock(BlockProperties.of(Material.METAL).sound(SoundType.LANTERN).lightLevel(state -> 8).requiresCorrectToolForDrops().strength(3.5F));
    public static final Block DARK_CRYSTAL_LANTERN = new LanternBlock(BlockProperties.of(Material.METAL).sound(SoundType.LANTERN).lightLevel(state -> 5).requiresCorrectToolForDrops().strength(3.5F));
    public static final Block METEORIC_IRON_STABILIZER = new MeteoricIronStabilizerBlock(BlockProperties.of(Material.METAL, MaterialColor.COLOR_BROWN).tool(ToolType.PICKAXE, 2).sound(SoundType.METAL).lightLevel(state -> 2).requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion());
    public static final Block ION_PLASMA_ROD = new IonPlasmaRodBlock(BlockProperties.of(Material.METAL, MaterialColor.COLOR_LIGHT_BLUE).tool(ToolType.PICKAXE, 2).sound(SoundType.METAL).lightLevel(state -> 10).requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion());
    public static final Block ZELIUS_EGG = new ZeliusEggBlock(BlockProperties.of(Material.CLAY, MaterialColor.COLOR_PURPLE).tool(ToolType.HOE).friction(0.8F).sound(MPSoundType.ALIEN).lightLevel(state -> 5).strength(0.5F).noOcclusion().isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block DARK_ENERGY_CORE = new DarkEnergyCoreBlock(BlockProperties.of(Material.CLAY, MaterialColor.COLOR_BLACK).tool(ToolType.HOE).friction(0.8F).sound(MPSoundType.ALIEN).lightLevel(state -> 5).strength(0.5F).noOcclusion().isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block DISPLAY_JAR = new DisplayJarBlock(BlockProperties.of(Material.GLASS).sound(SoundType.GLASS).strength(0.5F).noOcclusion());
    public static final Block PURLONITE_WORM_JAR = new DisplayJarBlock(BlockProperties.copy(DISPLAY_JAR).lightLevel(state -> 5));
    public static final Block CHALOS_SPORE_JAR = new DisplayJarBlock(BlockProperties.copy(DISPLAY_JAR));
    public static final Block PURLONITE_CLUSTER = new PurloniteClusterBlock(7, 3, BlockProperties.of(Material.GLASS).tool(ToolType.PICKAXE).noOcclusion().randomTicks().sound(SoundType.GLASS).strength(1.5F).lightLevel(state -> 5).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block SMALL_PURLONITE_BUD = new PurloniteClusterBlock(3, 4, BlockProperties.copy(PURLONITE_CLUSTER).tool(ToolType.PICKAXE).sound(SoundType.GLASS).lightLevel(state -> 2).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block MEDIUM_PURLONITE_BUD = new PurloniteClusterBlock(4, 3, BlockProperties.copy(PURLONITE_CLUSTER).tool(ToolType.PICKAXE).sound(SoundType.GLASS).lightLevel(state -> 3).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block LARGE_PURLONITE_BUD = new PurloniteClusterBlock(5, 3, BlockProperties.copy(PURLONITE_CLUSTER).tool(ToolType.PICKAXE).sound(SoundType.GLASS).lightLevel(state -> 4).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));

    //TODO Amethyst material and sound

    public static void init()
    {
        MorePlanetsMod.COMMON.registerBlock("diona_regolith", DIONA_REGOLITH, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_fine_regolith", DIONA_FINE_REGOLITH, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_stone", DIONA_STONE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_cobblestone", DIONA_COBBLESTONE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_block", PURLONITE_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("budding_purlonite", BUDDING_PURLONITE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("raw_glowing_iron_block", RAW_GLOWING_IRON_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("glowing_iron_block", GLOWING_IRON_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("compacted_purlonite_block", COMPACTED_PURLONITE_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("dark_energy_generator", DARK_ENERGY_GENERATOR, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_crystal_lantern", PURLONITE_CRYSTAL_LANTERN, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("dark_crystal_lantern", DARK_CRYSTAL_LANTERN, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("meteoric_iron_stabilizer", METEORIC_IRON_STABILIZER, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("ion_plasma_rod", ION_PLASMA_ROD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("dark_energy_core", DARK_ENERGY_CORE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("zelius_egg", ZELIUS_EGG, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("display_jar", DISPLAY_JAR, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_worm_jar", PURLONITE_WORM_JAR, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("chalos_spore_jar", CHALOS_SPORE_JAR, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("small_purlonite_bud", SMALL_PURLONITE_BUD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("medium_purlonite_bud", MEDIUM_PURLONITE_BUD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("large_purlonite_bud", LARGE_PURLONITE_BUD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_cluster", PURLONITE_CLUSTER, new Item.Properties().tab(MorePlanetsMod.TAB));
    }

    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType)
    {
        return false;
    }

    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos)
    {
        return false;
    }
}