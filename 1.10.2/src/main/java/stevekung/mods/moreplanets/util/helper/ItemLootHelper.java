package stevekung.mods.moreplanets.util.helper;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.items.ItemDiona;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruSapling;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.ItemNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.items.ItemNibiruFruits;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;

public class ItemLootHelper
{
    public static String COMMON_SPACE_MINESHAFT = "common_space_mineshaft";
    public static String COMMON_SPACE_DUNGEON = "common_space_dungeon";
    public static String CRASHED_ALIEN_PROBE = "crashed_alien_probe";
    public static String NIBIRU_PYRAMID = "nibiru_pyramid";
    public static String NIBIRU_STRONGHOLD_CORRIDOR = "nibiru_stronghold_corridor";
    public static String NIBIRU_STRONGHOLD_CROSSING = "nibiru_stronghold_crossing";
    public static String NIBIRU_VILLAGE_BLACKSMITH = "nibiru_village_blacksmith";
    public static String NIBIRU_VILLAGE_LIBRARY = "nibiru_village_library";
    public static String NIBIRU_JUNGLE_TEMPLE = "nibiru_jungle_temple";

    public static WeightedRandomChestContent ENCHANTED_BOOK = new WeightedRandomChestContent(new ItemStack(Items.enchanted_book, 1, 0), 1, 1, 1);
    public static WeightedRandomChestContent ENCHANTED_INFECTED_GOLDEN_APPLE = new WeightedRandomChestContent(NibiruItems.NIBIRU_ITEM, ItemNibiruFruits.ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE.ordinal(), 3, 8, 10);
    public static WeightedRandomChestContent INFECTED_MELON_SEEDS = new WeightedRandomChestContent(NibiruItems.INFECTED_MELON_SEEDS, 0, 2, 4, 10);
    public static WeightedRandomChestContent GOLDEN_ALIEN_BERRY = new WeightedRandomChestContent(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.GOLDEN_ALIEN_BERRY.ordinal(), 1, 2, 5);

    public static List<WeightedRandomChestContent> COMMON_SPACE_MINESHAFT_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
            new WeightedRandomChestContent(Items.dye, EnumDyeColor.BLUE.getDyeDamage(), 4, 9, 5),
            new WeightedRandomChestContent(Items.diamond, 0, 1, 2, 3),
            new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10),
            new WeightedRandomChestContent(GCItems.basicItem, 16, 1, 3, 15), //carrot
            new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.rail), 0, 4, 8, 1),
            new WeightedRandomChestContent(GCItems.meteorChunk, 0, 2, 4, 10),
            new WeightedRandomChestContent(DionaItems.DARK_ENERGY_PEARL, 0, 1, 3, 3),
            new WeightedRandomChestContent(AsteroidsItems.titaniumChestplate, 0, 1, 1, 1),
            new WeightedRandomChestContent(AsteroidsItems.titaniumPickaxe, 0, 1, 1, 1),
            new WeightedRandomChestContent(MarsItems.deshPickSlime, 0, 1, 1, 1)
    });

    public static List<WeightedRandomChestContent> COMMON_SPACE_DUNGEON_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10),
            new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10),
            new WeightedRandomChestContent(Items.string, 0, 1, 4, 10),
            new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10),
            new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.golden_apple, 1, 1, 1, 1),
            new WeightedRandomChestContent(Items.redstone, 0, 1, 4, 10),
            new WeightedRandomChestContent(GCItems.basicItem, 15, 1, 3, 15), //apple
            new WeightedRandomChestContent(GCItems.basicItem, 16, 1, 3, 15), //carrot
            new WeightedRandomChestContent(Items.record_13, 0, 1, 1, 4),
            new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, 4),
            new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10),
            new WeightedRandomChestContent(VenusItems.volcanicPickaxe, 0, 1, 1, 1),
            new WeightedRandomChestContent(MarsItems.deshPickSlime, 0, 1, 1, 1)
    });

    public static List<WeightedRandomChestContent> NIBIRU_PYRAMID_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15),
            new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2),
            new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20),
            new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, 0, 1, 1, 3),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_HELMET, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_BOOTS, 0, 1, 1, 1),
            new WeightedRandomChestContent(Item.getItemFromBlock(NibiruBlocks.INFECTED_VINES), 0, 3, 7, 16)
    });

    public static List<WeightedRandomChestContent> NIBIRU_STRONGHOLD_CORRIDOR_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 1, 10),
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15),
            new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
            new WeightedRandomChestContent(GCItems.basicItem, 16, 1, 3, 15), //carrot
            new WeightedRandomChestContent(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.INFECTED_APPLE.ordinal(), 1, 3, 15),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_SWORD, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_HELMET, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_BOOTS, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.INFECTED_GOLDEN_APPLE.ordinal(), 3, 1, 1)
    });

    public static List<WeightedRandomChestContent> NIBIRU_STRONGHOLD_CROSSING_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_ITEM, ItemNibiru.ItemType.INFECTED_COAL.ordinal(), 3, 8, 10),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.INFECTED_APPLE.ordinal(), 1, 3, 15),
            new WeightedRandomChestContent(GCItems.basicItem, 16, 1, 3, 15), //carrot
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, 0, 1, 1, 5)
    });

    public static List<WeightedRandomChestContent> NIBIRU_VILLAGE_BLACKSMITH_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_ITEM, ItemNibiru.ItemType.MULTALIC_CRYSTAL_PIECES.ordinal(), 1, 5, 1),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_ITEM, ItemNibiru.ItemType.INFECTED_COAL.ordinal(), 3, 8, 10),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_FRUITS, ItemNibiruFruits.ItemType.INFECTED_APPLE.ordinal(), 1, 3, 15),
            new WeightedRandomChestContent(GCItems.basicItem, 16, 1, 3, 15), //carrot
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_SWORD, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_HELMET, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, 0, 1, 1, 5),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_BOOTS, 0, 1, 1, 5),
            new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5),
            new WeightedRandomChestContent(Item.getItemFromBlock(NibiruBlocks.NIBIRU_SAPLING), BlockNibiruSapling.BlockType.INFECTED_OAK_SAPLING.ordinal(), 3, 7, 5),
            new WeightedRandomChestContent(Item.getItemFromBlock(NibiruBlocks.NIBIRU_SAPLING), BlockNibiruSapling.BlockType.INFECTED_JUNGLE_SAPLING.ordinal(), 3, 7, 5),
            new WeightedRandomChestContent(Item.getItemFromBlock(NibiruBlocks.NIBIRU_SAPLING), BlockNibiruSapling.BlockType.ALIEN_BERRY_SAPLING.ordinal(), 2, 4, 5)
    });

    public static List<WeightedRandomChestContent> NIBIRU_JUNGLE_TEMPLE_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15),
            new WeightedRandomChestContent(NibiruItems.NIBIRU_ITEM, ItemNibiru.ItemType.MULTALIC_CRYSTAL_PIECES.ordinal(), 1, 3, 4),
            new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2),
            new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20),
            new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16),
            new WeightedRandomChestContent(NibiruItems.NUCLEAR_WASTE_BUCKET, 0, 1, 1, 3),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_PICKAXE, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_SWORD, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_HELMET, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, 0, 1, 1, 1),
            new WeightedRandomChestContent(NibiruItems.MULTALIC_CRYSTAL_BOOTS, 0, 1, 1, 1),
            new WeightedRandomChestContent(Item.getItemFromBlock(NibiruBlocks.INFECTED_VINES), 0, 3, 7, 16)
    });

    public static List<WeightedRandomChestContent> CRASHED_ALIEN_PROBE_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(MarsItems.marsItemBasic, 3, 3, 6, 5), //Tier 2 plate
            new WeightedRandomChestContent(GCItems.heavyPlatingTier1, 0, 3, 6, 5), //Tier 1 plate
            new WeightedRandomChestContent(AsteroidsItems.basicItem, 6, 3, 6, 5), //Titanium plate
            new WeightedRandomChestContent(Items.iron_ingot, 0, 5, 9, 5),
            new WeightedRandomChestContent(AsteroidsItems.basicItem, 0, 3, 6, 5), //Tier 3 plate
            new WeightedRandomChestContent(DionaItems.DIONA_ITEM, ItemDiona.ItemType.ALIEN_MINER_PART.ordinal(), 1, 3, 2),
    });

    public static List<WeightedRandomChestContent> NIBIRU_VILLAGE_LIBRARY_LOOT = Lists.newArrayList(new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(Items.book, 0, 1, 3, 20),
            new WeightedRandomChestContent(Items.paper, 0, 2, 7, 20),
            new WeightedRandomChestContent(Items.map, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.emerald, 0, 1, 4, 4)
    });

    public static List<WeightedRandomFishable> SPACE_JUNK_LOOT = Arrays.asList(new WeightedRandomFishable[] {
            new WeightedRandomFishable(new ItemStack(VenusItems.basicItem, 1, 2), 1),
            new WeightedRandomFishable(new ItemStack(GCItems.cheeseCurd), 10),
            new WeightedRandomFishable(new ItemStack(GCItems.canister), 10),
            new WeightedRandomFishable(new ItemStack(GCItems.meteorChunk), 4),
            new WeightedRandomFishable(new ItemStack(MPItems.SPACE_FISHING_ROD), 2).setMaxDamagePercent(0.9F),
            new WeightedRandomFishable(new ItemStack(Items.redstone, 10), 6),
            new WeightedRandomFishable(new ItemStack(Blocks.glass_pane, 4), 5),
            new WeightedRandomFishable(new ItemStack(Items.iron_helmet), 2).setMaxDamagePercent(0.7F),
            new WeightedRandomFishable(new ItemStack(Items.gunpowder), 6).setMaxDamagePercent(0.9F),
            new WeightedRandomFishable(new ItemStack(MPItems.CAPSULE, 1, 0), 3)
    });

    public static List<WeightedRandomFishable> SPACE_TREASURE_LOOT = Arrays.asList(new WeightedRandomFishable[] {
            new WeightedRandomFishable(new ItemStack(Items.name_tag), 1),
            new WeightedRandomFishable(new ItemStack(MPItems.SPACE_BOW), 1).setMaxDamagePercent(0.25F).setEnchantable(),
            new WeightedRandomFishable(new ItemStack(MPItems.SPACE_FISHING_ROD), 1).setMaxDamagePercent(0.25F).setEnchantable(),
            new WeightedRandomFishable(new ItemStack(Items.book), 1).setEnchantable(),
            new WeightedRandomFishable(new ItemStack(GCItems.oxMask), 1),
            new WeightedRandomFishable(new ItemStack(GCItems.oxTankMedium), 1).setMaxDamagePercent(0.45F),
            new WeightedRandomFishable(new ItemStack(GCItems.oxTankHeavy), 1).setMaxDamagePercent(0.15F),
            new WeightedRandomFishable(new ItemStack(GCItems.itemBasicMoon, 1, 2), 1)
    });

    public static List<WeightedRandomFishable> SPACE_FISH_LOOT = Arrays.asList(new WeightedRandomFishable[] {
            new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getMetadata()), 60),
            new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.getMetadata()), 25),
            new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.getMetadata()), 2),
            new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.getMetadata()), 13)
    });

    public static void register(String category, List<WeightedRandomChestContent> list, int min, int max)
    {
        ChestGenHooks.init(category, list, max, max);
    }

    public static void add(String category, WeightedRandomChestContent item)
    {
        ChestGenHooks.addItem(category, item);
    }
}