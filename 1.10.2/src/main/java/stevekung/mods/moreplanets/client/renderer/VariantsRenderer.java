package stevekung.mods.moreplanets.client.renderer;

import java.util.Arrays;

import stevekung.mods.moreplanets.blocks.BlockPolishedSpaceDecoration;
import stevekung.mods.moreplanets.blocks.BlockTieredEnergyStorage;
import stevekung.mods.moreplanets.blocks.decoration.*;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.items.ItemLaserBullet;
import stevekung.mods.moreplanets.items.ItemSpaceFish;
import stevekung.mods.moreplanets.items.ItemSpecialSchematic;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.BlockKoentus;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockChalos;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockChalosDoublePlant;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockCheeseDirt;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.chalos.items.ItemChalos;
import stevekung.mods.moreplanets.module.planets.chalos.items.ItemCheeseFood;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockDiona;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockInfectedCrystallizePart;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.items.ItemDiona;
import stevekung.mods.moreplanets.module.planets.diona.items.ItemTier4RocketPart;
import stevekung.mods.moreplanets.module.planets.diona.items.ItemTier5RocketSchematic;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.*;
import stevekung.mods.moreplanets.module.planets.fronos.items.*;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.*;
import stevekung.mods.moreplanets.module.planets.nibiru.items.*;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class VariantsRenderer
{
    public static void init()
    {
        VariantsRenderer.init3DRendering();

        ClientRegisterHelper.registerVariantsName(MPBlocks.POLISHED_SPACE_DECORATION, BlockPolishedSpaceDecoration.BlockType.class);
        ClientRegisterHelper.registerVariantsName(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, BlockHalfDungeonBrickSlab1.BlockType.class);
        ClientRegisterHelper.registerVariantsName(MPBlocks.HALF_COBBLESTONE_SLAB_1, BlockHalfCobblestoneSlab1.BlockType.class);
        ClientRegisterHelper.registerVariantsName(MPBlocks.HALF_WOODEN_SLAB_1, BlockHalfWoodenSlab1.BlockType.class);
        ClientRegisterHelper.registerVariantsName(MPBlocks.TIERED_ENERGY_STORAGE_CLUSTER, BlockTieredEnergyStorage.BlockType.class);
        ClientRegisterHelper.registerVariantsName(MPBlocks.COBBLESTONE_WALL, BlockCobblestoneWall.BlockType.class);
        ClientRegisterHelper.registerVariantsName(MPBlocks.DUNGEON_BRICK_WALL, BlockDungeonBrickWall.BlockType.class);
        ClientRegisterHelper.registerVariantsName(DionaBlocks.DIONA_BLOCK, BlockDiona.BlockType.class);
        ClientRegisterHelper.registerVariantsName(DionaBlocks.INFECTED_CRYSTALLIZE_PART, BlockInfectedCrystallizePart.BlockType.class);
        ClientRegisterHelper.registerVariantsName(KoentusBlocks.KOENTUS_BLOCK, BlockKoentus.BlockType.class);
        ClientRegisterHelper.registerVariantsName(ChalosBlocks.CHALOS_BLOCK, BlockChalos.BlockType.class);
        ClientRegisterHelper.registerVariantsName(ChalosBlocks.CHEESE_DIRT, BlockCheeseDirt.BlockType.class);
        ClientRegisterHelper.registerVariantsName(ChalosBlocks.CHALOS_DOUBLE_PLANT, BlockChalosDoublePlant.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFECTED_DIRT, BlockInfectedDirt.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_SANDSTONE, BlockNibiruSandstone.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_LOG, BlockNibiruLog.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_PLANKS, BlockNibiruPlanks.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_ORE, BlockNibiruOre.BlockType.class, Arrays.asList("nibiru_redstone_ore_active"), Arrays.asList("nibiru_redstone_ore"));
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_LEAVES, BlockNibiruLeaves.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_SAPLING, BlockNibiruSapling.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_FLOWER, BlockNibiruFlower.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_BLOCK, BlockNibiru.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_TALL_GRASS, BlockNibiruTallGrass.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_SILVERFISH_STONE, BlockNibiruSilverfish.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_FENCE, BlockNibiruFence.BlockType.class);
        ClientRegisterHelper.registerVariantsName(NibiruBlocks.NIBIRU_DOUBLE_PLANT, BlockNibiruDoublePlant.BlockType.class);
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
        ClientRegisterHelper.registerVariantNameWithDyeColor(MPBlocks.TINTED_GLASS);
        ClientRegisterHelper.registerVariantNameWithDyeColor(MPBlocks.TINTED_GLASS_PANE);
        ClientRegisterHelper.registerVariantNameWithDyeColor(KoentusBlocks.GLOWING_HARDENED_ICE);

        ClientRegisterHelper.registerVariantsName(MPItems.SPACE_BOW, "space_bow", "space_bow_pulling_0", "space_bow_pulling_1", "space_bow_pulling_2");
        ClientRegisterHelper.registerVariantsName(MPItems.SPACE_FISHING_ROD, "space_fishing_rod", "space_fishing_rod_cast");
        ClientRegisterHelper.registerVariantsName(MPItems.LASER_BULLET, ItemLaserBullet.ItemType.class);
        ClientRegisterHelper.registerVariantsName(MPItems.LASER_GUN, "laser_gun", "laser_gun_charged", "laser_gun_shoot");
        ClientRegisterHelper.registerVariantsName(MPItems.SPACE_FISH, ItemSpaceFish.ItemType.class);
        ClientRegisterHelper.registerVariantsName(MPItems.SPECIAL_SCHEMATIC, ItemSpecialSchematic.ItemType.class);
        ClientRegisterHelper.registerVariantsName(DionaItems.DIONA_ITEM, ItemDiona.ItemType.class);
        ClientRegisterHelper.registerVariantsName(DionaItems.TIER_4_ROCKET_PART, ItemTier4RocketPart.ItemType.class);
        ClientRegisterHelper.registerVariantsName(DionaItems.TIER_5_ROCKET_SCHEMATIC, ItemTier5RocketSchematic.ItemType.class);
        ClientRegisterHelper.registerVariantsName(ChalosItems.CHALOS_ITEM, ItemChalos.ItemType.class);
        ClientRegisterHelper.registerVariantsName(ChalosItems.CHEESE_FOOD, ItemCheeseFood.ItemType.class);
        ClientRegisterHelper.registerVariantsName(NibiruItems.NIBIRU_ITEM, ItemNibiru.ItemType.class);
        ClientRegisterHelper.registerVariantsName(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.class);
        ClientRegisterHelper.registerVariantsName(NibiruItems.INFECTED_PRISMARINE, ItemInfectedPrismarine.ItemType.class);
        ClientRegisterHelper.registerVariantsName(NibiruItems.NIBIRU_FOOD, ItemNibiruFood.ItemType.class);
        ClientRegisterHelper.registerVariantsName(NibiruItems.NIBIRU_DUNGEON_KEY, ItemNibiruDungeonKey.ItemType.class);
        ClientRegisterHelper.registerVariantsName(FronosItems.FRONOS_ITEM, ItemFronos.ItemType.class);
        ClientRegisterHelper.registerVariantsName(FronosItems.FRONOS_FRUITS, ItemFronosFruits.ItemType.class);
        ClientRegisterHelper.registerVariantsName(FronosItems.FRONOS_FOOD, ItemFronosFood.ItemType.class);
        ClientRegisterHelper.registerVariantsName(FronosItems.JELLY, ItemJelly.ItemType.class);
        ClientRegisterHelper.registerVariantsName(FronosItems.CANDY_CANE, ItemCandyCane.ItemType.class);
    }

    private static void init3DRendering()
    {
        if (!ConfigManagerMP.use3DTorchItemModel)
        {
            ClientRegisterHelper.registerVariantsName(DionaBlocks.INFECTED_CRYSTALLIZE_TORCH, "infected_crystallize_torch_vanilla");
            ClientRegisterHelper.registerVariantsName(NibiruBlocks.INFECTED_TORCH, "infected_torch_vanilla");
        }
    }
}