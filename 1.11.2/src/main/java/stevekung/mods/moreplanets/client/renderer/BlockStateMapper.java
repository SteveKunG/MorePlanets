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
import stevekung.mods.moreplanets.util.EnumStateMapper;
import stevekung.mods.moreplanets.util.client.renderer.StateMapperCTM;
import stevekung.mods.moreplanets.util.client.renderer.StateMapperUtil;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class BlockStateMapper
{
    public static void init()
    {
        ClientRegisterHelper.registerStateMapper(MPBlocks.SHIELD_GENERATOR, BlockStateHelper.FACING_HORIZON);
        ClientRegisterHelper.registerStateMapper(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(DionaBlocks.LARGE_INFECTED_CRYSTALLIZE, BlockStateHelper.FACING_ALL);
        ClientRegisterHelper.registerStateMapper(ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(ChalosBlocks.CHEESE_OF_MILK_GAS_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegisterHelper.registerStateMapper(ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.NIBIRU_LEAVES, EnumStateMapper.LEAVES);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_CACTUS, EnumStateMapper.PLANT_AGE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, EnumStateMapper.DOOR);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.ELECTRICAL_FIRE, EnumStateMapper.FIRE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK, BlockInfectedSugarCane.AGE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.NIBIRU_SEAWEED, EnumStateMapper.VANILLA_LEVEL);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.HELIUM_GAS_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_TANK, BlockNuclearWasteTank.STATE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.VEIN_FRAME, BlockVeinFrame.EYE, BlockVeinFrame.FACING);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.MULTALIC_CRYSTAL, BlockStateHelper.FACING_ALL);

        ClientRegisterHelper.registerStateMapperSplitVariants(MPBlocks.COBBLESTONE_WALL, BlockCobblestoneWall.VARIANT);
        ClientRegisterHelper.registerStateMapperSplitVariants(MPBlocks.DUNGEON_BRICK_WALL, BlockDungeonBrickWall.VARIANT);

        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_MELON_STEM, new StateMapperUtil("stem"));

        // CTM Integration
        ClientRegisterHelper.registerStateMapper(DionaBlocks.ALIEN_MINER_BLOOD, new StateMapperCTM());
        ClientRegisterHelper.registerStateMapper(DionaBlocks.CRASHED_ALIEN_PROBE, new StateMapperCTM());
        ClientRegisterHelper.registerStateMapper(DionaBlocks.GLOWING_IRON_BLOCK, new StateMapperCTM());
        ClientRegisterHelper.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS, new StateMapperCTM());
        ClientRegisterHelper.registerStateMapper(DionaBlocks.INFECTED_CRYSTALLIZE_WEB, new StateMapperCTM());
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.SPORELILY, new StateMapperCTM());
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.NIBIRU_FLOWER, new StateMapperCTM());
    }
}