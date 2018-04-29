package stevekung.mods.moreplanets.client.renderer;

import java.util.Arrays;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.BlockKoentus;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.*;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.*;
import stevekung.mods.moreplanets.utils.helper.ClientRegisterHelper;

public class VariantsRenderer
{
    public static void init()
    {
        VariantsRenderer.init3DRendering();

        ClientRegisterHelper.registerVariantsName(KoentusBlocks.KOENTUS_BLOCK, BlockKoentus.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_LOG, BlockNibiruLog.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_PLANKS, BlockNibiruPlanks.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_ORE, BlockNibiruOre.BlockType.class, Arrays.asList("nibiru_redstone_ore_active"), Arrays.asList("nibiru_redstone_ore"));
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_LEAVES, BlockNibiruLeaves.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_SAPLING, BlockNibiruSapling.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_FLOWER, BlockNibiruFlower.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_FENCE, BlockNibiruFence.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFECTED_PRISMARINE, BlockInfectedPrismarine.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFECTED_SPONGE, "infected_sponge", "infected_wet_sponge");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_CRAFTING_TABLE, BlockNibiruCraftingTable.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_SEAWEED, BlockNibiruSeaweed.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_BOOKSHELF, BlockNibiruBookshelf.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, BlockHalfInfectedPrismarineSlab.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, BlockHalfInfectedStoneBricksSlab.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, BlockHalfNibiruSandstoneSlab.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_GRASS_PATH, BlockNibiruGrassPath.BlockType.class);
        ClientRegisterHelper.registerVariantsName(FronosBlocks.FRONOS_DIRT, BlockFronosDirt.BlockType.class);
        ClientRegisterHelper.registerVariantsName(FronosBlocks.FRONOS_BLOCK, BlockFronos.BlockType.class);
        ClientRegisterHelper.registerVariantsName(FronosBlocks.FRONOS_ORE, BlockFronosOre.BlockType.class, Arrays.asList("fronos_redstone_ore_active"), Arrays.asList("fronos_redstone_ore"));
        ClientRegisterHelper.registerVariantsName(FronosBlocks.CANDY_CANE_1, BlockCandyCane1.BlockType.class);
        ClientRegisterHelper.registerVariantsName(FronosBlocks.CANDY_CANE_2, BlockCandyCane2.BlockType.class);
        ClientRegisterHelper.registerVariantsName(FronosBlocks.JELLY_BLOCK, BlockJelly.BlockType.class);

        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_NIBIRU_ROCK, "nibiru_rock");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_NIBIRU_COBBLESTONE, "nibiru_cobblestone");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_NIBIRU_VEIN_COBBLESTONE, "nibiru_vein_cobblestone");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_INFECTED_STONE_BRICKS, "infected_stone_bricks");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_INFECTED_VEIN_STONE_BRICKS, "infected_vein_stone_bricks");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_INFECTED_CRACKED_STONE_BRICKS, "infected_cracked_stone_bricks");
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFESTED_INFECTED_CHISELED_STONE_BRICKS, "infected_chiseled_stone_bricks");

        ClientRegisterHelper.registerVariantsName(MPItems.SPACE_BOW, "space_bow", "space_bow_pulling_0", "space_bow_pulling_1", "space_bow_pulling_2");
        ClientRegisterHelper.registerVariantsName(MPItems.SPACE_FISHING_ROD, "space_fishing_rod", "space_fishing_rod_cast");
        ClientRegisterHelper.registerVariantsName(MPItems.LASER_GUN, "laser_gun", "laser_gun_charged", "laser_gun_shoot");
    }

    private static void init3DRendering()
    {
        if (!ConfigManagerMP.moreplanets_general.use3DTorchItemModel)
        {
            ClientRegisterHelper.registerVariantsName(DionaBlocks.INFECTED_CRYSTALLIZED_TORCH, "infected_crystallized_torch_vanilla");
            ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFECTED_TORCH, "infected_torch_vanilla");
        }
    }
}