package stevekung.mods.moreplanets.client.renderer;

import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.BlockCrashedAlienProbe;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.utils.blocks.BlockDropableLitOre;
import stevekung.mods.moreplanets.utils.blocks.BlockSaplingMP;
import stevekung.mods.moreplanets.utils.client.renderer.StateMapperCTM;
import stevekung.mods.moreplanets.utils.client.renderer.StateMapperType;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;
import stevekung.mods.stevekunglib.utils.enums.EnumStateMapper;

public class BlockStateMapper
{
    public static void init()
    {
        ClientRegistryUtils.registerStateMapper(MPBlocks.SHIELD_GENERATOR, BlockStateProperty.FACING_HORIZON);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.LARGE_INFECTED_CRYSTALLIZED, BlockStateProperty.FACING_ALL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CHEESE_MILK_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.GASEOUS_CHEESE_MILK_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CHEESE_SPORE_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CHEESE_SPORE_DOOR, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_OAK_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_SPRUCE_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_JUNGLE_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALIEN_BERRY_OAK_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_CACTUS, EnumStateMapper.PLANT_AGE_15);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_OAK_DOOR, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALIEN_BERRY_OAK_DOOR, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ELECTRICAL_FIRE, EnumStateMapper.FIRE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_SUGAR_CANE, EnumStateMapper.PLANT_AGE_15);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_SEAWEED, EnumStateMapper.VANILLA_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.HELIUM_GAS_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.VEIN_FRAME, BlockVeinFrame.EYE, BlockVeinFrame.FACING);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.PURIFIED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.MULTALIC_CRYSTAL, BlockStateProperty.FACING_ALL);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_REDSTONE_ORE, BlockDropableLitOre.LIT);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_OAK_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_SPRUCE_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_JUNGLE_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CHEESE_SPORE_FLOWER, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALIEN_BERRY_OAK_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(MPBlocks.FRONOS_REDSTONE_ORE, BlockDropableLitOre.LIT);

        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_MELON_STEM, new StateMapperType("stem"));

        // CTM Integration
        StateMapperCTM ctm = new StateMapperCTM();
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALIEN_SHIP_BOOSTER, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALBETIUS_WORM_EGG_ROCK, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.ALIEN_MINER_BLOOD, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.CRASHED_ALIEN_PROBE, new StateMapperCTM(BlockCrashedAlienProbe.HAS_ALIEN));
        ClientRegistryUtils.registerStateMapper(MPBlocks.GLOWING_IRON_BLOCK, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_CRYSTALLIZED_PLANKS, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_CRYSTALLIZED_FENCE, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_CRYSTALLIZED_WEB, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.SPORELILY, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.TERRAPUFF_HERB, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.INFECTED_SUGAR_CANE, new StateMapperCTM(BlockStateProperty.AGE_15));
        ClientRegistryUtils.registerStateMapper(MPBlocks.ANTI_GRAVITY_ORE, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.GRAVITY_CREEP_BLOCK, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.GRAVITY_CREEP_EXTRACTOR, ctm);
        ClientRegistryUtils.registerStateMapper(MPBlocks.GRAVITY_CREEP_VINES, ctm);
    }
}