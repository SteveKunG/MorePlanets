package stevekung.mods.moreplanets.utils.debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.JsonUtils;

public class JSONTags
{
    public static final boolean ENABLE = false;
    private static File BLOCKS_TAG_DIR = null;
    private static File ITEMS_TAG_DIR = null;

    private static void setupDir()
    {
        if (BLOCKS_TAG_DIR == null)
        {
            BLOCKS_TAG_DIR = Minecraft.getMinecraft().mcDataDir.toPath().resolve("../src/main/resources/assets/moreplanets/tags/blocks").toFile();
        }
        if (!BLOCKS_TAG_DIR.exists())
        {
            BLOCKS_TAG_DIR.mkdir();
        }
        if (ITEMS_TAG_DIR == null)
        {
            ITEMS_TAG_DIR = Minecraft.getMinecraft().mcDataDir.toPath().resolve("../src/main/resources/assets/moreplanets/tags/items").toFile();
        }
        if (!ITEMS_TAG_DIR.exists())
        {
            ITEMS_TAG_DIR.mkdir();
        }
    }

    public static void addBlocksTag(String tagName, Block... blocks)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        List<Object> values = new ArrayList<>();

        for (Block block : blocks)
        {
            values.add(block.getRegistryName().toString());
        }

        json.put("replace", "true");
        json.put("values", values);

        File file = new File(BLOCKS_TAG_DIR, tagName + ".json");

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void addItemsTag(String tagName, Block... blocks)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        List<Object> values = new ArrayList<>();

        for (Block block : blocks)
        {
            values.add(block.getRegistryName().toString());
        }

        json.put("replace", "true");
        json.put("values", values);

        File file = new File(ITEMS_TAG_DIR, tagName + ".json");

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void addItemsTag(String tagName, Item... items)
    {
        if (!ENABLE)
        {
            return;
        }

        setupDir();
        Map<String, Object> json = new LinkedHashMap<>();
        List<Object> values = new ArrayList<>();

        for (Item item : items)
        {
            values.add(item.getRegistryName().toString());
        }

        json.put("replace", "true");
        json.put("values", values);

        File file = new File(ITEMS_TAG_DIR, tagName + ".json");

        try (FileWriter writer = new FileWriter(file))
        {
            JsonUtils.toJson(json, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void init()
    {
        JSONTags.addBlocksTag("wooden_doors", MPBlocks.CHEESE_SPORE_DOOR, MPBlocks.INFECTED_OAK_DOOR, MPBlocks.ALIEN_BERRY_OAK_DOOR);
        JSONTags.addBlocksTag("wooden_pressure_plates", MPBlocks.INFECTED_OAK_PRESSURE_PLATE, MPBlocks.ALIEN_BERRY_OAK_PRESSURE_PLATE);
        JSONTags.addBlocksTag("wooden_slabs", MPBlocks.CHEESE_SPORE_SLAB, MPBlocks.INFECTED_OAK_SLAB, MPBlocks.ALIEN_BERRY_OAK_SLAB);
        JSONTags.addBlocksTag("wooden_stairs", MPBlocks.CHEESE_SPORE_STAIRS, MPBlocks.INFECTED_OAK_STAIRS, MPBlocks.ALIEN_BERRY_OAK_STAIRS);
        JSONTags.addBlocksTag("stairs", MPBlocks.CHEESE_SPORE_STAIRS, MPBlocks.INFECTED_OAK_STAIRS, MPBlocks.ALIEN_BERRY_OAK_STAIRS, MPBlocks.DIONA_COBBLESTONE_STAIRS, MPBlocks.DIONA_DUNGEON_BRICK_STAIRS,
                MPBlocks.CHALOS_COBBLESTONE_STAIRS, MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS, MPBlocks.NIBIRU_COBBLESTONE_STAIRS, MPBlocks.INFECTED_STONE_BRICKS_STAIRS, MPBlocks.INFECTED_SANDSTONE_STAIRS, MPBlocks.TERRASTONE_STAIRS,
                MPBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, MPBlocks.INFECTED_PRISMARINE_STAIRS, MPBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, MPBlocks.INFECTED_DARK_PRISMARINE_STAIRS, MPBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, MPBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, MPBlocks.INFECTED_CUT_SANDSTONE_STAIRS);
        JSONTags.addBlocksTag("slabs", MPBlocks.CHEESE_SPORE_SLAB, MPBlocks.INFECTED_OAK_SLAB, MPBlocks.ALIEN_BERRY_OAK_SLAB, MPBlocks.DIONA_COBBLESTONE_SLAB, MPBlocks.CHALOS_COBBLESTONE_SLAB, MPBlocks.NIBIRU_COBBLESTONE_SLAB,
                MPBlocks.DIONA_DUNGEON_BRICK_SLAB, MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, MPBlocks.ALIEN_SHIP_DECORATION_SLAB, MPBlocks.INFECTED_PRISMARINE_SLAB, MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB,
                MPBlocks.INFECTED_DARK_PRISMARINE_SLAB, MPBlocks.INFECTED_STONE_BRICKS_SLAB, MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, MPBlocks.TERRASTONE_SLAB, MPBlocks.INFECTED_SANDSTONE_SLAB, MPBlocks.INFECTED_CUT_SANDSTONE_SLAB);
        JSONTags.addBlocksTag("saplings", MPBlocks.CHEESE_SPORE_FLOWER, MPBlocks.INFECTED_OAK_SAPLING, MPBlocks.INFECTED_SPRUCE_SAPLING, MPBlocks.INFECTED_JUNGLE_SAPLING, MPBlocks.ALIEN_BERRY_OAK_SAPLING);
        JSONTags.addBlocksTag("sand", MPBlocks.INFECTED_SAND);
        JSONTags.addBlocksTag("planks", MPBlocks.INFECTED_CRYSTALLIZED_PLANKS, MPBlocks.CHEESE_SPORE_PLANKS, MPBlocks.INFECTED_OAK_PLANKS, MPBlocks.INFECTED_SPRUCE_PLANKS, MPBlocks.ALIEN_BERRY_OAK_PLANKS);
        JSONTags.addBlocksTag("logs", MPBlocks.CHEESE_SPORE_STEM, MPBlocks.INFECTED_OAK_LOG, MPBlocks.INFECTED_SPRUCE_LOG, MPBlocks.INFECTED_JUNGLE_LOG, MPBlocks.ALIEN_BERRY_OAK_LOG);
        JSONTags.addBlocksTag("leaves", MPBlocks.INFECTED_OAK_LEAVES, MPBlocks.INFECTED_SPRUCE_LEAVES, MPBlocks.INFECTED_JUNGLE_LEAVES, MPBlocks.ALIEN_BERRY_OAK_LEAVES);
        JSONTags.addBlocksTag("ice", MPBlocks.WHITE_GLOWING_HARDENED_ICE, MPBlocks.ORANGE_GLOWING_HARDENED_ICE, MPBlocks.MAGENTA_GLOWING_HARDENED_ICE, MPBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE, MPBlocks.YELLOW_GLOWING_HARDENED_ICE,
                MPBlocks.LIME_GLOWING_HARDENED_ICE, MPBlocks.PINK_GLOWING_HARDENED_ICE, MPBlocks.GRAY_GLOWING_HARDENED_ICE, MPBlocks.SILVER_GLOWING_HARDENED_ICE, MPBlocks.CYAN_GLOWING_HARDENED_ICE, MPBlocks.PURPLE_GLOWING_HARDENED_ICE,
                MPBlocks.BLUE_GLOWING_HARDENED_ICE, MPBlocks.BROWN_GLOWING_HARDENED_ICE, MPBlocks.GREEN_GLOWING_HARDENED_ICE, MPBlocks.RED_GLOWING_HARDENED_ICE, MPBlocks.BLACK_GLOWING_HARDENED_ICE, MPBlocks.INFECTED_ICE, MPBlocks.INFECTED_PACKED_ICE, MPBlocks.KOENTUS_ICE);
        JSONTags.addBlocksTag("enderman_holdable", MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK, MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT, MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE, MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE, MPBlocks.ZELIUS_EGG, MPBlocks.CHEESE_GRASS_BLOCK, MPBlocks.CHEESE_DIRT,
                MPBlocks.CHEESE_COARSE_DIRT, MPBlocks.CHEESE_SLIME_BLOCK, MPBlocks.CHEESE_MILK_CAKE, MPBlocks.INFECTED_GRASS_BLOCK, MPBlocks.INFECTED_DIRT, MPBlocks.INFECTED_COARSE_DIRT, MPBlocks.INFECTED_CLAY, MPBlocks.INFECTED_GRAVEL, MPBlocks.INFECTED_SNOW, MPBlocks.INFECTED_SNOW_LAYER,
                MPBlocks.GREEN_VEIN_GRASS_BLOCK, MPBlocks.INFECTED_MELON, MPBlocks.INFECTED_SAND, MPBlocks.INFECTED_CACTUS, MPBlocks.PURE_HERB, MPBlocks.TERRAPUFF_HERB, MPBlocks.BATASIA_DANDELION, MPBlocks.PYOLONIA, MPBlocks.PHILIPY, MPBlocks.WHITE_TAIL, MPBlocks.VEALIUM_VINES, MPBlocks.TERRASHROOM,
                MPBlocks.INFECTED_VINES_DIRT, MPBlocks.PURIFIED_GRAVEL, MPBlocks.INFECTED_GRASS_PATH, MPBlocks.GREEN_VEIN_GRASS_PATH, MPBlocks.FRONOS_GRASS_BLOCK, MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_COARSE_DIRT, MPBlocks.RED_CANDY_CANE, MPBlocks.GREEN_CANDY_CANE, MPBlocks.BLUE_CANDY_CANE,
                MPBlocks.ORANGE_CANDY_CANE, MPBlocks.PINK_CANDY_CANE, MPBlocks.YELLOW_CANDY_CANE, MPBlocks.PURPLE_CANDY_CANE, MPBlocks.RAINBOW_CANDY_CANE);
        JSONTags.addBlocksTag("stone_bricks", MPBlocks.INFECTED_STONE_BRICKS, MPBlocks.INFECTED_VEIN_STONE_BRICKS, MPBlocks.INFECTED_CRACKED_STONE_BRICKS, MPBlocks.INFECTED_CHISELED_STONE_BRICKS, MPBlocks.FRONOS_STONE_BRICKS, MPBlocks.FRONOS_MOSSY_STONE_BRICKS, MPBlocks.FRONOS_CRACKED_STONE_BRICKS, MPBlocks.FRONOS_CHISELED_STONE_BRICKS);

        JSONTags.addItemsTag("wooden_doors", MPBlocks.CHEESE_SPORE_DOOR, MPBlocks.INFECTED_OAK_DOOR, MPBlocks.ALIEN_BERRY_OAK_DOOR);
        JSONTags.addItemsTag("wooden_pressure_plates", MPBlocks.INFECTED_OAK_PRESSURE_PLATE, MPBlocks.ALIEN_BERRY_OAK_PRESSURE_PLATE);
        JSONTags.addItemsTag("wooden_slabs", MPBlocks.CHEESE_SPORE_SLAB, MPBlocks.INFECTED_OAK_SLAB, MPBlocks.ALIEN_BERRY_OAK_SLAB);
        JSONTags.addItemsTag("wooden_stairs", MPBlocks.CHEESE_SPORE_STAIRS, MPBlocks.INFECTED_OAK_STAIRS, MPBlocks.ALIEN_BERRY_OAK_STAIRS);
        JSONTags.addItemsTag("stairs", MPBlocks.CHEESE_SPORE_STAIRS, MPBlocks.INFECTED_OAK_STAIRS, MPBlocks.ALIEN_BERRY_OAK_STAIRS, MPBlocks.DIONA_COBBLESTONE_STAIRS, MPBlocks.DIONA_DUNGEON_BRICK_STAIRS,
                MPBlocks.CHALOS_COBBLESTONE_STAIRS, MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS, MPBlocks.NIBIRU_COBBLESTONE_STAIRS, MPBlocks.INFECTED_STONE_BRICKS_STAIRS, MPBlocks.INFECTED_SANDSTONE_STAIRS, MPBlocks.TERRASTONE_STAIRS,
                MPBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, MPBlocks.INFECTED_PRISMARINE_STAIRS, MPBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, MPBlocks.INFECTED_DARK_PRISMARINE_STAIRS, MPBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, MPBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, MPBlocks.INFECTED_CUT_SANDSTONE_STAIRS);
        JSONTags.addItemsTag("slabs", MPBlocks.CHEESE_SPORE_SLAB, MPBlocks.INFECTED_OAK_SLAB, MPBlocks.ALIEN_BERRY_OAK_SLAB, MPBlocks.DIONA_COBBLESTONE_SLAB, MPBlocks.CHALOS_COBBLESTONE_SLAB, MPBlocks.NIBIRU_COBBLESTONE_SLAB,
                MPBlocks.DIONA_DUNGEON_BRICK_SLAB, MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, MPBlocks.ALIEN_SHIP_DECORATION_SLAB, MPBlocks.INFECTED_PRISMARINE_SLAB, MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB,
                MPBlocks.INFECTED_DARK_PRISMARINE_SLAB, MPBlocks.INFECTED_STONE_BRICKS_SLAB, MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, MPBlocks.TERRASTONE_SLAB, MPBlocks.INFECTED_SANDSTONE_SLAB, MPBlocks.INFECTED_CUT_SANDSTONE_SLAB);
        JSONTags.addItemsTag("saplings", MPBlocks.CHEESE_SPORE_FLOWER, MPBlocks.INFECTED_OAK_SAPLING, MPBlocks.INFECTED_SPRUCE_SAPLING, MPBlocks.INFECTED_JUNGLE_SAPLING, MPBlocks.ALIEN_BERRY_OAK_SAPLING);
        JSONTags.addItemsTag("sand", MPBlocks.INFECTED_SAND);
        JSONTags.addItemsTag("planks", MPBlocks.INFECTED_CRYSTALLIZED_PLANKS, MPBlocks.CHEESE_SPORE_PLANKS, MPBlocks.INFECTED_OAK_PLANKS, MPBlocks.INFECTED_SPRUCE_PLANKS, MPBlocks.ALIEN_BERRY_OAK_PLANKS);
        JSONTags.addItemsTag("logs", MPBlocks.CHEESE_SPORE_STEM, MPBlocks.INFECTED_OAK_LOG, MPBlocks.INFECTED_SPRUCE_LOG, MPBlocks.INFECTED_JUNGLE_LOG, MPBlocks.ALIEN_BERRY_OAK_LOG);
        JSONTags.addItemsTag("leaves", MPBlocks.INFECTED_OAK_LEAVES, MPBlocks.INFECTED_SPRUCE_LEAVES, MPBlocks.INFECTED_JUNGLE_LEAVES, MPBlocks.ALIEN_BERRY_OAK_LEAVES);
        JSONTags.addItemsTag("stone_bricks", MPBlocks.INFECTED_STONE_BRICKS, MPBlocks.INFECTED_VEIN_STONE_BRICKS, MPBlocks.INFECTED_CRACKED_STONE_BRICKS, MPBlocks.INFECTED_CHISELED_STONE_BRICKS, MPBlocks.FRONOS_STONE_BRICKS, MPBlocks.FRONOS_MOSSY_STONE_BRICKS, MPBlocks.FRONOS_CRACKED_STONE_BRICKS, MPBlocks.FRONOS_CHISELED_STONE_BRICKS);
    }
}
