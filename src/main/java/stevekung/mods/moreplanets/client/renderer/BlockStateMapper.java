package stevekung.mods.moreplanets.client.renderer;

import stevekung.mods.moreplanets.blocks.decoration.BlockCobblestoneWall;
import stevekung.mods.moreplanets.blocks.decoration.BlockDungeonBrickWall;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockInfectedSugarCane;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.client.renderer.StateMapperCTM;
import stevekung.mods.moreplanets.util.client.renderer.StateMapperUtil;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.ClientRegistryUtils;
import stevekung.mods.stevekunglib.utils.EnumStateMapper;

public class BlockStateMapper
{
    public static void init()
    {
        ClientRegistryUtils.registerStateMapper(MPBlocks.SHIELD_GENERATOR, BlockStateProperty.FACING_HORIZON);
        ClientRegistryUtils.registerStateMapper(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(DionaBlocks.LARGE_INFECTED_CRYSTALLIZE, BlockStateProperty.FACING_ALL);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_OF_MILK_GAS_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NIBIRU_LEAVES, EnumStateMapper.LEAVES);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_CACTUS, EnumStateMapper.PLANT_AGE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ELECTRICAL_FIRE, EnumStateMapper.FIRE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK, BlockInfectedSugarCane.AGE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NIBIRU_SEAWEED, EnumStateMapper.VANILLA_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.HELIUM_GAS_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_TANK, BlockNuclearWasteTank.STATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.VEIN_FRAME, BlockVeinFrame.EYE, BlockVeinFrame.FACING);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.MULTALIC_CRYSTAL, BlockStateProperty.FACING_ALL);

        ClientRegistryUtils.registerStateMapperSplitVariants(MPBlocks.COBBLESTONE_WALL, BlockCobblestoneWall.VARIANT);
        ClientRegistryUtils.registerStateMapperSplitVariants(MPBlocks.DUNGEON_BRICK_WALL, BlockDungeonBrickWall.VARIANT);

        ClientRegistryUtils.registerStateMapper(NibiruBlocks.INFECTED_MELON_STEM, new StateMapperUtil("stem"));

        // CTM Integration
        ClientRegistryUtils.registerStateMapper(DionaBlocks.ALIEN_MINER_BLOOD, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.CRASHED_ALIEN_PROBE, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.GLOWING_IRON_BLOCK, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZE_WEB, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZE_PART, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.SPORELILY, new StateMapperCTM());
        ClientRegistryUtils.registerStateMapper(NibiruBlocks.NIBIRU_FLOWER, new StateMapperCTM());
    }
}