package com.stevekung.moreplanets.registry;

import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.world.level.block.*;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class MPBlocks
{
    public static final Block DIONA_REGOLITH = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_FINE_REGOLITH = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_STONE = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final Block DIONA_COBBLESTONE = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final Block RAW_GLOWING_IRON_BLOCK = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).sound(SoundType.STONE).lightLevel(state -> 4).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
    public static final Block GLOWING_IRON_BLOCK = new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).sound(SoundType.METAL).lightLevel(state -> 4).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
    public static final Block PURLONITE_BLOCK = new PurloniteBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_PURPLE).strength(1.5F).lightLevel(state -> 5).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block BUDDING_PURLONITE = new BuddingPurloniteBlock(BlockBehaviour.Properties.of(Material.AMETHYST).strength(1.5F).sound(SoundType.AMETHYST).lightLevel(state -> 5).randomTicks().requiresCorrectToolForDrops().noOcclusion().noLootTable().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block COMPACTED_PURLONITE_BLOCK = new CompactedBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F).requiresCorrectToolForDrops().noOcclusion());
    public static final Block DARK_ENERGY_GENERATOR = new DarkEnergyGeneratorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0F).lightLevel(DarkEnergyGeneratorBlock::getLightLevel).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion());
    public static final Block PURLONITE_CRYSTAL_LANTERN = new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.LANTERN).lightLevel(state -> 8).requiresCorrectToolForDrops().strength(3.5F));
    public static final Block DARK_CRYSTAL_LANTERN = new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.LANTERN).lightLevel(state -> 5).requiresCorrectToolForDrops().strength(3.5F));
    public static final Block METEORIC_IRON_STABILIZER = new MeteoricIronStabilizerBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).sound(SoundType.METAL).lightLevel(state -> 2).requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion());
    public static final Block ION_PLASMA_ROD = new IonPlasmaRodBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_BLUE).sound(SoundType.METAL).lightLevel(state -> 10).requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion());
    public static final Block ZELIUS_EGG = new ZeliusEggBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.COLOR_PURPLE).friction(0.8F).sound(MPSoundType.ALIEN).lightLevel(state -> 5).strength(0.5F).noOcclusion().isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block DARK_ENERGY_CORE = new DarkEnergyCoreBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.COLOR_BLACK).friction(0.8F).sound(MPSoundType.ALIEN).lightLevel(state -> 5).strength(0.5F).noOcclusion().isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block DISPLAY_JAR = new DisplayJarBlock(BlockBehaviour.Properties.of(Material.GLASS).sound(SoundType.GLASS).strength(0.5F).noOcclusion());
    public static final Block PURLONITE_WORM_JAR = new DisplayJarBlock(BlockBehaviour.Properties.copy(DISPLAY_JAR).lightLevel(state -> 5));
    public static final Block CHALOS_SPORE_JAR = new DisplayJarBlock(BlockBehaviour.Properties.copy(DISPLAY_JAR));
    public static final Block PURLONITE_CLUSTER = new PurloniteClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel(state -> 5).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block SMALL_PURLONITE_BUD = new PurloniteClusterBlock(3, 4, BlockBehaviour.Properties.copy(PURLONITE_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel(state -> 2).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block MEDIUM_PURLONITE_BUD = new PurloniteClusterBlock(4, 3, BlockBehaviour.Properties.copy(PURLONITE_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(state -> 3).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));
    public static final Block LARGE_PURLONITE_BUD = new PurloniteClusterBlock(5, 3, BlockBehaviour.Properties.copy(PURLONITE_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel(state -> 4).noOcclusion().isValidSpawn(MPBlocks::never).isRedstoneConductor(MPBlocks::never).isSuffocating(MPBlocks::never).isViewBlocking(MPBlocks::never));

    public static void init()
    {
        register("diona_regolith", DIONA_REGOLITH, new Item.Properties().tab(MorePlanets.TAB));
        register("diona_fine_regolith", DIONA_FINE_REGOLITH, new Item.Properties().tab(MorePlanets.TAB));
        register("diona_stone", DIONA_STONE, new Item.Properties().tab(MorePlanets.TAB));
        register("diona_cobblestone", DIONA_COBBLESTONE, new Item.Properties().tab(MorePlanets.TAB));
        register("purlonite_block", PURLONITE_BLOCK, new Item.Properties().tab(MorePlanets.TAB));
        register("budding_purlonite", BUDDING_PURLONITE, new Item.Properties().tab(MorePlanets.TAB));
        register("raw_glowing_iron_block", RAW_GLOWING_IRON_BLOCK, new Item.Properties().tab(MorePlanets.TAB));
        register("glowing_iron_block", GLOWING_IRON_BLOCK, new Item.Properties().tab(MorePlanets.TAB));
        register("compacted_purlonite_block", COMPACTED_PURLONITE_BLOCK, new Item.Properties().tab(MorePlanets.TAB));
        register("dark_energy_generator", DARK_ENERGY_GENERATOR, new Item.Properties().tab(MorePlanets.TAB));
        register("purlonite_crystal_lantern", PURLONITE_CRYSTAL_LANTERN, new Item.Properties().tab(MorePlanets.TAB));
        register("dark_crystal_lantern", DARK_CRYSTAL_LANTERN, new Item.Properties().tab(MorePlanets.TAB));
        register("meteoric_iron_stabilizer", METEORIC_IRON_STABILIZER, new Item.Properties().tab(MorePlanets.TAB));
        register("ion_plasma_rod", ION_PLASMA_ROD, new Item.Properties().tab(MorePlanets.TAB));
        register("dark_energy_core", DARK_ENERGY_CORE, new Item.Properties().tab(MorePlanets.TAB));
        register("zelius_egg", ZELIUS_EGG, new Item.Properties().tab(MorePlanets.TAB));
        register("display_jar", DISPLAY_JAR, new Item.Properties().tab(MorePlanets.TAB));
        register("purlonite_worm_jar", PURLONITE_WORM_JAR, new Item.Properties().tab(MorePlanets.TAB));
        register("chalos_spore_jar", CHALOS_SPORE_JAR, new Item.Properties().tab(MorePlanets.TAB));
        register("small_purlonite_bud", SMALL_PURLONITE_BUD, new Item.Properties().tab(MorePlanets.TAB));
        register("medium_purlonite_bud", MEDIUM_PURLONITE_BUD, new Item.Properties().tab(MorePlanets.TAB));
        register("large_purlonite_bud", LARGE_PURLONITE_BUD, new Item.Properties().tab(MorePlanets.TAB));
        register("purlonite_cluster", PURLONITE_CLUSTER, new Item.Properties().tab(MorePlanets.TAB));
    }

    public static void register(String key, Block block)
    {
        register(key, block, null);
    }

    public static void register(String key, Block block, Item.Properties properties)
    {
        Registry.register(Registry.BLOCK, new ResourceLocation(MorePlanets.MOD_ID, key), block);

        if (properties != null)
        {
            Registry.register(Registry.ITEM, new ResourceLocation(MorePlanets.MOD_ID, key), new BlockItem(block, properties));
        }
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