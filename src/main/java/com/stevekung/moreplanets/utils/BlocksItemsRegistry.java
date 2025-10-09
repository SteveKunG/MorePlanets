package com.stevekung.moreplanets.utils;

import java.util.*;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.stevekung.lib.utils.LangUtils;
import com.stevekung.lib.utils.client.ClientUtils;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.itemblocks.ItemBlockTESRMP;
import com.stevekung.moreplanets.utils.blocks.MPBlockCategory;
import com.stevekung.moreplanets.utils.blocks.MorePlanetsBlock;
import com.stevekung.moreplanets.utils.itemblocks.ItemBlockMP;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.MorePlanetsItem;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.core.util.StackSorted;

import javax.annotation.Nullable;

public class BlocksItemsRegistry
{
    public static final Map<MPBlockCategory, List<StackSorted>> SORT_MAP_BLOCKS = new HashMap<>();
    public static final Map<MPItemCategory, List<StackSorted>> SORT_MAP_ITEMS = new HashMap<>();
    public static final Map<Block, String> SINGLE_BLOCK_RENDER_LIST = new HashMap<>();
    public static final Map<Item, String> SINGLE_ITEM_RENDER_LIST = new HashMap<>();
    public static final List<Item> TESR_ITEM_RENDER = new ArrayList<>();

    public static void registerBlock(Block block)
    {
        BlocksItemsRegistry.registerBlock(block, ItemBlockMP::new);
    }

    public static void registerBlock(Block block, @Nullable Function<Block, ItemBlock> itemBlock)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBlock(block, itemBlock);

        if (block instanceof ModelNameGatherer)
        {
            ModelNameGatherer blockRender = (ModelNameGatherer) block;

            if (blockRender.getModelName() != null)
            {
                BlocksItemsRegistry.SINGLE_BLOCK_RENDER_LIST.put(block, blockRender.getModelName());
            }
        }
        if (itemBlock != null)
        {
            if (ClientUtils.isEffectiveClient())
            {
                BlocksItemsRegistry.registerSorted(block);
                ItemBlock itemBlockTESR = itemBlock.apply(block);

                if (itemBlockTESR instanceof ItemBlockTESRMP)
                {
                    BlocksItemsRegistry.TESR_ITEM_RENDER.add(Item.getItemFromBlock(itemBlockTESR.getBlock()));
                }
            }
        }
    }

    public static void registerItem(Item item)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerItem(item);

        if (item instanceof ModelNameGatherer)
        {
            ModelNameGatherer itemRender = (ModelNameGatherer) item;

            if (itemRender.getModelName() != null)
            {
                BlocksItemsRegistry.SINGLE_ITEM_RENDER_LIST.put(item, itemRender.getModelName());
            }
        }
        if (ClientUtils.isEffectiveClient())
        {
            BlocksItemsRegistry.registerSorted(item);
            BlocksItemsRegistry.TESR_ITEM_RENDER.add(MPItems.INFECTED_PURLONITE_BOMB);
        }
    }

    public static void registerSorted(Block block)
    {
        if (block instanceof MorePlanetsBlock)
        {
            Item item = Item.getItemFromBlock(block);

            if (item == Items.AIR)
            {
                return;
            }

            MorePlanetsBlock sortableBlock = (MorePlanetsBlock) block;
            MPBlockCategory categoryBlock = sortableBlock.getBlockCategory();

            if (!BlocksItemsRegistry.SORT_MAP_BLOCKS.containsKey(categoryBlock))
            {
                BlocksItemsRegistry.SORT_MAP_BLOCKS.put(categoryBlock, new ArrayList<>());
            }
            BlocksItemsRegistry.SORT_MAP_BLOCKS.get(categoryBlock).add(new StackSorted(block, 0));
        }
        else if (block.getCreativeTab() == MorePlanetsMod.BLOCK_TAB)
        {
            throw new RuntimeException(block.getClass() + " must inherit " + MorePlanetsBlock.class.getSimpleName() + "!");
        }
    }

    public static void postRegisteredSortBlock()
    {
        List<StackSorted> itemOrderListBlocks = new ArrayList<>();

        for (MPBlockCategory type : MPBlockCategory.VALUES)
        {
            List<StackSorted> stackSorteds = BlocksItemsRegistry.SORT_MAP_BLOCKS.get(type);

            if (stackSorteds != null)
            {
                itemOrderListBlocks.addAll(stackSorteds);
            }
        }
        Comparator<ItemStack> tabSorterBlocks = Ordering.explicit(itemOrderListBlocks).onResultOf(input -> new StackSorted(input.getItem(), 0));
        MorePlanetsMod.BLOCK_TAB.setTabSorter(tabSorterBlocks);
    }

    public static void registerSorted(Item item)
    {
        if (item instanceof MorePlanetsItem)
        {
            MorePlanetsItem sortableItem = (MorePlanetsItem) item;
            MPItemCategory categoryItem = sortableItem.getItemCategory();

            if (!BlocksItemsRegistry.SORT_MAP_ITEMS.containsKey(categoryItem))
            {
                BlocksItemsRegistry.SORT_MAP_ITEMS.put(categoryItem, new ArrayList<>());
            }
            BlocksItemsRegistry.SORT_MAP_ITEMS.get(categoryItem).add(new StackSorted(item, 0));
        }
        else if (item.getCreativeTab() == MorePlanetsMod.ITEM_TAB)
        {
            throw new RuntimeException(item.getClass() + " must inherit " + MorePlanetsItem.class.getSimpleName() + "!");
        }
    }

    public static void postRegisteredSortItem()
    {
        List<StackSorted> itemOrderListItems = new ArrayList<>();

        for (MPItemCategory type : MPItemCategory.VALUES)
        {
            List<StackSorted> stackSorteds = BlocksItemsRegistry.SORT_MAP_ITEMS.get(type);

            if (stackSorteds != null)
            {
                itemOrderListItems.addAll(stackSorteds);
            }
        }
        Comparator<ItemStack> tabSorterItems = Ordering.explicit(itemOrderListItems).onResultOf(input -> new StackSorted(input.getItem(), 0));
        MorePlanetsMod.ITEM_TAB.setTabSorter(tabSorterItems);
    }

    public static boolean isItemTab(CreativeTabs creativeTabs)
    {
        return creativeTabs == MorePlanetsMod.ITEM_TAB || creativeTabs == CreativeTabs.SEARCH;
    }

    @SideOnly(Side.CLIENT)
    public static List<String> getDescription(String name)
    {
        return Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(LangUtils.translate(name), 150);
    }
}