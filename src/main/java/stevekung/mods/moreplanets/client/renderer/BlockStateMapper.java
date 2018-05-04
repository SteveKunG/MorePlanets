package stevekung.mods.moreplanets.client.renderer;

import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockInfectedSugarCane;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockDropableLitOre;
import stevekung.mods.moreplanets.utils.blocks.BlockSaplingMP;
import stevekung.mods.moreplanets.utils.client.renderer.StateMapperCTM;
import stevekung.mods.moreplanets.utils.client.renderer.StateMapperType;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.ClientRegistryUtils;
import stevekung.mods.stevekunglib.utils.EnumStateMapper;

public class BlockStateMapper
{
    public static void init()
    {
        ClientRegistryUtils.registerStateMapper(MPBlocks.SHIELD_GENERATOR, BlockStateProperty.FACING_HORIZON);
        ClientRegistryUtils.registerStateMapper(DionaBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(DionaBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(DionaBlocks.LARGE_INFECTED_CRYSTALLIZED, BlockStateProperty.FACING_ALL);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_MILK_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.GASEOUS_CHEESE_MILK_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_SPORE_DOOR, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_OAK_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_JUNGLE_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_CACTUS, EnumStateMapper.PLANT_AGE_15);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_OAK_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ELECTRICAL_FIRE, EnumStateMapper.FIRE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK, BlockInfectedSugarCane.AGE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_SEAWEED, EnumStateMapper.VANILLA_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.HELIUM_GAS_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_TANK, BlockNuclearWasteTank.STATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.VEIN_FRAME, BlockVeinFrame.EYE, BlockVeinFrame.FACING);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.PURIFIED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.MULTALIC_CRYSTAL, BlockStateProperty.FACING_ALL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_REDSTONE_ORE, BlockDropableLitOre.LIT);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_OAK_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_JUNGLE_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_SPORE_FLOWER, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_SAPLING, BlockSaplingMP.STAGE);
        ClientRegistryUtils.registerStateMapper(FronosBlocks.FRONOS_REDSTONE_ORE, BlockDropableLitOre.LIT);

        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_MELON_STEM, new StateMapperType("stem"));

        // CTM Integration
        ClientRegistryUtils.registerStateMapper(DionaBlocks.ALBETIUS_WORM_EGG_ROCK, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.ALIEN_MINER_BLOOD, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.CRASHED_ALIEN_PROBE, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.GLOWING_IRON_BLOCK, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZED_PLANKS, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZED_FENCE, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZED_WEB, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.SPORELILY, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.TERRAPUFF_HERB, new StateMapperCTM());
    }
}