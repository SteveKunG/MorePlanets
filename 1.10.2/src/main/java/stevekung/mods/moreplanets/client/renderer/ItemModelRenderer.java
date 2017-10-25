package stevekung.mods.moreplanets.client.renderer;

import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.blocks.BlockPolishedSpaceDecoration;
import stevekung.mods.moreplanets.blocks.decoration.*;
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
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class ItemModelRenderer
{
    public static void init()
    {
        ItemModelRenderer.registerBlockRenderer();
        ItemModelRenderer.registerBlockVariantsRenderer();
        ItemModelRenderer.registerItemRenderer();
        ItemModelRenderer.registerItemVariantsRenderer();
    }

    private static void registerBlockRenderer()
    {
        for (Entry<Block, String> map : CommonRegisterHelper.SINGLE_BLOCK_RENDER_LIST.entrySet())
        {
            Block block = map.getKey();
            String name = map.getValue();
            ClientRegisterHelper.registerModelRender(block, name);
        }
    }

    private static void registerBlockVariantsRenderer()
    {
        ClientRegisterHelper.registerModelRender(MPBlocks.TIERED_ENERGY_STORAGE_CLUSTER, 0, "dark_energy_storage_module");
        ClientRegisterHelper.registerModelRender(MPBlocks.TIERED_ENERGY_STORAGE_CLUSTER, 4, "nuclear_waste_storage_module");
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_SPONGE, 0, "infected_sponge");
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_SPONGE, 1, "infected_wet_sponge");
        ClientRegisterHelper.registerModelRenderWithDyeColor(MPBlocks.TINTED_GLASS);
        ClientRegisterHelper.registerModelRenderWithDyeColor(MPBlocks.TINTED_GLASS_PANE);
        ClientRegisterHelper.registerModelRenderWithDyeColor(KoentusBlocks.GLOWING_HARDENED_ICE);

        ClientRegisterHelper.registerModelRender(MPBlocks.POLISHED_SPACE_DECORATION, BlockPolishedSpaceDecoration.BlockType.class);
        ClientRegisterHelper.registerModelRender(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, BlockHalfDungeonBrickSlab1.BlockType.class);
        ClientRegisterHelper.registerModelRender(MPBlocks.HALF_COBBLESTONE_SLAB_1, BlockHalfCobblestoneSlab1.BlockType.class);
        ClientRegisterHelper.registerModelRender(MPBlocks.HALF_WOODEN_SLAB_1, BlockHalfWoodenSlab1.BlockType.class);
        ClientRegisterHelper.registerModelRender(MPBlocks.COBBLESTONE_WALL, BlockCobblestoneWall.BlockType.class);
        ClientRegisterHelper.registerModelRender(MPBlocks.DUNGEON_BRICK_WALL, BlockDungeonBrickWall.BlockType.class);
        ClientRegisterHelper.registerModelRender(DionaBlocks.DIONA_BLOCK, BlockDiona.BlockType.class);
        ClientRegisterHelper.registerModelRender(DionaBlocks.INFECTED_CRYSTALLIZE_PART, BlockInfectedCrystallizePart.BlockType.class);
        ClientRegisterHelper.registerModelRender(KoentusBlocks.KOENTUS_BLOCK, BlockKoentus.BlockType.class);
        ClientRegisterHelper.registerModelRender(ChalosBlocks.CHALOS_BLOCK, BlockChalos.BlockType.class);
        ClientRegisterHelper.registerModelRender(ChalosBlocks.CHEESE_DIRT, BlockCheeseDirt.BlockType.class);
        ClientRegisterHelper.registerModelRender(ChalosBlocks.CHALOS_DOUBLE_PLANT, BlockChalosDoublePlant.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_DIRT, BlockInfectedDirt.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_SANDSTONE, BlockNibiruSandstone.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_LOG, BlockNibiruLog.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_PLANKS, BlockNibiruPlanks.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_ORE, BlockNibiruOre.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_LEAVES, BlockNibiruLeaves.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_SAPLING, BlockNibiruSapling.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_BLOCK, BlockNibiru.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_FLOWER, BlockNibiruFlower.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_TALL_GRASS, BlockNibiruTallGrass.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_SILVERFISH_STONE, BlockNibiruSilverfish.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_FENCE, BlockNibiruFence.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_PRISMARINE, BlockInfectedPrismarine.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_CRAFTING_TABLE, BlockNibiruCraftingTable.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_SEAWEED, BlockNibiruSeaweed.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, BlockHalfInfectedPrismarineSlab.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, BlockHalfInfectedStoneBricksSlab.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, BlockHalfNibiruSandstoneSlab.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_DOUBLE_PLANT, BlockNibiruDoublePlant.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_BOOKSHELF, BlockNibiruBookshelf.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_GRASS_PATH, BlockNibiruGrassPath.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.FRONOS_DIRT, BlockFronosDirt.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.FRONOS_BLOCK, BlockFronos.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.FRONOS_ORE, BlockFronosOre.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.CANDY_CANE_1, BlockCandyCane1.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.CANDY_CANE_2, BlockCandyCane2.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.JELLY_BLOCK, BlockJelly.BlockType.class);
    }

    private static void registerItemRenderer()
    {
        for (Entry<Item, String> map : CommonRegisterHelper.SINGLE_ITEM_RENDER_LIST.entrySet())
        {
            Item item = map.getKey();
            String name = map.getValue();
            ClientRegisterHelper.registerModelRender(item, name);
        }
        ClientRegisterHelper.registerToolsModelRender(DionaItems.ILLENIUM_SWORD, DionaItems.ILLENIUM_SHOVEL, DionaItems.ILLENIUM_PICKAXE, DionaItems.ILLENIUM_AXE, DionaItems.ILLENIUM_HOE, "illenium");
        ClientRegisterHelper.registerToolsModelRender(ChalosItems.DIREMSIUM_SWORD, ChalosItems.DIREMSIUM_SHOVEL, ChalosItems.DIREMSIUM_PICKAXE, ChalosItems.DIREMSIUM_AXE, ChalosItems.DIREMSIUM_HOE, "diremsium");
        ClientRegisterHelper.registerToolsModelRender(ChalosItems.CHEESE_SPORE_WOOD_SWORD, ChalosItems.CHEESE_SPORE_WOOD_SHOVEL, ChalosItems.CHEESE_SPORE_WOOD_PICKAXE, ChalosItems.CHEESE_SPORE_WOOD_AXE, ChalosItems.CHEESE_SPORE_WOOD_HOE, "cheese_spore_wood");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.INFECTED_WOOD_SWORD, NibiruItems.INFECTED_WOOD_SHOVEL, NibiruItems.INFECTED_WOOD_PICKAXE, NibiruItems.INFECTED_WOOD_AXE, NibiruItems.INFECTED_WOOD_HOE, "infected_wood");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.ALIEN_BERRY_WOOD_SWORD, NibiruItems.ALIEN_BERRY_WOOD_SHOVEL, NibiruItems.ALIEN_BERRY_WOOD_PICKAXE, NibiruItems.ALIEN_BERRY_WOOD_AXE, NibiruItems.ALIEN_BERRY_WOOD_HOE, "alien_berry_wood");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.NIBIRU_STONE_SWORD, NibiruItems.NIBIRU_STONE_SHOVEL, NibiruItems.NIBIRU_STONE_PICKAXE, NibiruItems.NIBIRU_STONE_AXE, NibiruItems.NIBIRU_STONE_HOE, "nibiru_stone");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.MULTALIC_CRYSTAL_SWORD, NibiruItems.MULTALIC_CRYSTAL_SHOVEL, NibiruItems.MULTALIC_CRYSTAL_PICKAXE, NibiruItems.MULTALIC_CRYSTAL_AXE, NibiruItems.MULTALIC_CRYSTAL_HOE, "multalic_crystal");
        ClientRegisterHelper.registerArmorModelRender(DionaItems.ILLENIUM_HELMET, DionaItems.ILLENIUM_CHESTPLATE, DionaItems.ILLENIUM_LEGGINGS, DionaItems.ILLENIUM_BOOTS, "illenium");
        ClientRegisterHelper.registerArmorModelRender(ChalosItems.DIREMSIUM_HELMET, ChalosItems.DIREMSIUM_CHESTPLATE, ChalosItems.DIREMSIUM_LEGGINGS, ChalosItems.DIREMSIUM_BOOTS, "diremsium");
        ClientRegisterHelper.registerArmorModelRender(NibiruItems.MULTALIC_CRYSTAL_HELMET, NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, NibiruItems.MULTALIC_CRYSTAL_BOOTS, "multalic_crystal");
        ClientRegisterHelper.registerRocketModelRender(DionaItems.TIER_4_ROCKET, 4);
        ClientRegisterHelper.registerRocketModelRender(ChalosItems.TIER_5_ROCKET, 5);
        ClientRegisterHelper.registerRocketModelRender(NibiruItems.TIER_6_ROCKET, 6);
    }

    private static void registerItemVariantsRenderer()
    {
        ClientRegisterHelper.registerModelRender(MPItems.LASER_BULLET, ItemLaserBullet.ItemType.class);
        ClientRegisterHelper.registerModelRender(MPItems.SPACE_FISH, ItemSpaceFish.ItemType.class);
        ClientRegisterHelper.registerModelRender(MPItems.SPECIAL_SCHEMATIC, ItemSpecialSchematic.ItemType.class);
        ClientRegisterHelper.registerModelRender(DionaItems.DIONA_ITEM, ItemDiona.ItemType.class);
        ClientRegisterHelper.registerModelRender(DionaItems.TIER_4_ROCKET_PART, ItemTier4RocketPart.ItemType.class);
        ClientRegisterHelper.registerModelRender(DionaItems.TIER_5_ROCKET_SCHEMATIC, ItemTier5RocketSchematic.ItemType.class);
        ClientRegisterHelper.registerModelRender(ChalosItems.CHALOS_ITEM, ItemChalos.ItemType.class);
        ClientRegisterHelper.registerModelRender(ChalosItems.CHEESE_FOOD, ItemCheeseFood.ItemType.class);
        ClientRegisterHelper.registerModelRender(NibiruItems.NIBIRU_ITEM, ItemNibiru.ItemType.class);
        ClientRegisterHelper.registerModelRender(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.class);
        ClientRegisterHelper.registerModelRender(NibiruItems.INFECTED_PRISMARINE, ItemInfectedPrismarine.ItemType.class);
        ClientRegisterHelper.registerModelRender(NibiruItems.NIBIRU_FOOD, ItemNibiruFood.ItemType.class);
        ClientRegisterHelper.registerModelRender(NibiruItems.NIBIRU_DUNGEON_KEY, ItemNibiruDungeonKey.ItemType.class);
        ClientRegisterHelper.registerModelRender(FronosItems.FRONOS_ITEM, ItemFronos.ItemType.class);
        ClientRegisterHelper.registerModelRender(FronosItems.FRONOS_FRUITS, ItemFronosFruits.ItemType.class);
        ClientRegisterHelper.registerModelRender(FronosItems.FRONOS_FOOD, ItemFronosFood.ItemType.class);
        ClientRegisterHelper.registerModelRender(FronosItems.JELLY, ItemJelly.ItemType.class);
        ClientRegisterHelper.registerModelRender(FronosItems.CANDY_CANE, ItemCandyCane.ItemType.class);
    }
}