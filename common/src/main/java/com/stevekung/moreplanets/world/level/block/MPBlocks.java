package com.stevekung.moreplanets.world.level.block;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Lantern;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class MPBlocks
{
    public static final Block DIONA_REGOLITH = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_FINE_REGOLITH = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_STONE = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_COBBLESTONE = new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final Block GLOWING_IRON_BLOCK = new Block(BlockPropertiesUtils.pickaxeLevel(Block.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY), 2).sound(SoundType.METAL).lightLevel(state -> 4).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
    public static final Block RAW_GLOWING_IRON_BLOCK = new Block(BlockPropertiesUtils.pickaxeLevel(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).sound(SoundType.STONE), 2).lightLevel(state -> 4).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
    public static final Block PURLONITE_BLOCK = new PurloniteBlock(Block.Properties.of(Material.GLASS, MaterialColor.COLOR_PURPLE).strength(1.5F).lightLevel(state -> 5).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block BUDDING_PURLONITE = new BuddingPurloniteBlock(Block.Properties.of(Material.GLASS).strength(1.5F).sound(SoundType.GLASS).lightLevel(state -> 5).randomTicks().strength(1.5F).sound(SoundType.GLASS).requiresCorrectToolForDrops().noOcclusion().noDrops().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block PURLONITE_CRYSTAL_LANTERN = new Lantern(Block.Properties.of(Material.METAL).sound(SoundType.METAL).lightLevel(state -> 8).requiresCorrectToolForDrops().strength(3.5F));
    public static final Block DARK_CRYSTAL_LANTERN = new Lantern(Block.Properties.of(Material.METAL).sound(SoundType.METAL).lightLevel(state -> 5).requiresCorrectToolForDrops().strength(3.5F));
    public static final Block METEORIC_IRON_STABILIZER = new MeteoricIronStabilizerBlock(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).sound(SoundType.METAL).lightLevel(state -> 2).requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion());
    public static final Block ION_PLASMA_ROD = new IonPlasmaRodBlock(Block.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_BLUE).sound(SoundType.METAL).lightLevel(state -> 10).requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion());
    public static final Block ZELIUS_EGG = new ZeliusEggBlock(Block.Properties.of(Material.CLAY, MaterialColor.COLOR_PURPLE).friction(0.8F).sound(MPSoundType.ALIEN).lightLevel(state -> 5).strength(0.5F).noOcclusion().isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block DARK_ENERGY_CORE = new DarkEnergyCoreBlock(Block.Properties.of(Material.CLAY, MaterialColor.COLOR_BLACK).friction(0.8F).sound(MPSoundType.ALIEN).lightLevel(state -> 5).strength(0.5F).noOcclusion().isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block PURLONITE_CLUSTER = new PurloniteClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.GLASS).noOcclusion().randomTicks().sound(SoundType.GLASS).strength(1.5F).lightLevel(state -> 5).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block LARGE_PURLONITE_BUD = new PurloniteClusterBlock(5, 3, BlockBehaviour.Properties.copy(PURLONITE_CLUSTER).sound(SoundType.GLASS).lightLevel(state -> 4).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block MEDIUM_PURLONITE_BUD = new PurloniteClusterBlock(4, 3, BlockBehaviour.Properties.copy(PURLONITE_CLUSTER).sound(SoundType.GLASS).lightLevel(state -> 3).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block SMALL_PURLONITE_BUD = new PurloniteClusterBlock(3, 4, BlockBehaviour.Properties.copy(PURLONITE_CLUSTER).sound(SoundType.GLASS).lightLevel(state -> 2).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));

    //TODO Amethyst material and sound

    public static void init()
    {
        MorePlanetsMod.COMMON.registerBlock("diona_regolith", DIONA_REGOLITH, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_fine_regolith", DIONA_FINE_REGOLITH, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_stone", DIONA_STONE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("diona_cobblestone", DIONA_COBBLESTONE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_block", PURLONITE_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("budding_purlonite", BUDDING_PURLONITE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("glowing_iron_block", GLOWING_IRON_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("raw_glowing_iron_block", RAW_GLOWING_IRON_BLOCK, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_crystal_lantern", PURLONITE_CRYSTAL_LANTERN, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("dark_crystal_lantern", DARK_CRYSTAL_LANTERN, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("meteoric_iron_stabilizer", METEORIC_IRON_STABILIZER, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("ion_plasma_rod", ION_PLASMA_ROD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("dark_energy_core", DARK_ENERGY_CORE, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("zelius_egg", ZELIUS_EGG, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("purlonite_cluster", PURLONITE_CLUSTER, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("large_purlonite_bud", LARGE_PURLONITE_BUD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("medium_purlonite_bud", MEDIUM_PURLONITE_BUD, new Item.Properties().tab(MorePlanetsMod.TAB));
        MorePlanetsMod.COMMON.registerBlock("small_purlonite_bud", SMALL_PURLONITE_BUD, new Item.Properties().tab(MorePlanetsMod.TAB));
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