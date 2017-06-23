package stevekung.mods.moreplanets.client.renderer;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.EnumFacing;
import stevekung.mods.moreplanets.blocks.decoration.BlockCobblestoneWall;
import stevekung.mods.moreplanets.blocks.decoration.BlockDungeonBrickWall;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockInfectedSugarCane;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.EnumStateMapper;
import stevekung.mods.moreplanets.util.blocks.BlockStemMP;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class BlockStateMapper
{
    public static void init()
    {
        ClientRegisterHelper.registerStateMapper(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);
        ClientRegisterHelper.registerStateMapper(DionaBlocks.CRASHED_ALIEN_PROBE, BlockCrashedAlienProbe.HAS_ALIEN);
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
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.VEIN_FRAME, BlockVeinFrame.EYE, BlockVeinFrame.FACING);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.NUCLEAR_WASTE_TANK, BlockNuclearWasteTank.DEPLETE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumStateMapper.FENCE_GATE);
        ClientRegisterHelper.registerStateMapper(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK, EnumStateMapper.FORGE_LEVEL);

        ClientRegisterHelper.registerStateMapperSplitVariants(MPBlocks.COBBLESTONE_WALL, BlockCobblestoneWall.VARIANT);
        ClientRegisterHelper.registerStateMapperSplitVariants(MPBlocks.DUNGEON_BRICK_WALL, BlockDungeonBrickWall.VARIANT);

        ClientRegisterHelper.registerStateMapper(NibiruBlocks.INFECTED_MELON_STEM, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());

                if (state.getValue(BlockStemMP.FACING) != EnumFacing.UP)
                {
                    map.remove(BlockStemMP.AGE);
                }
                return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()), this.getPropertyString(map));
            }
        });
    }
}