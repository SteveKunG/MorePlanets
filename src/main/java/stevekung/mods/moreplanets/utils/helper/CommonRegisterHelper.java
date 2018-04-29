package stevekung.mods.moreplanets.utils.helper;

import java.util.*;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import micdoodle8.mods.galacticraft.core.util.StackSorted;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.utils.blocks.ISortableBlock;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ISingleItemRender;
import stevekung.mods.moreplanets.utils.items.ISortableItem;
import stevekung.mods.stevekunglib.utils.ClientUtils;

public class CommonRegisterHelper
{
    public static final Map<EnumSortCategoryBlock, List<StackSorted>> SORT_MAP_BLOCKS = new HashMap<>();
    public static final Map<EnumSortCategoryItem, List<StackSorted>> SORT_MAP_ITEMS = new HashMap<>();
    public static final Map<Block, String> SINGLE_BLOCK_RENDER_LIST = new HashMap<>();
    public static final Map<Item, String> SINGLE_ITEM_RENDER_LIST = new HashMap<>();

    public static void registerBlock(Block block)
    {
        CommonRegisterHelper.registerBlock(block, ItemBlock::new);
    }

    public static void registerBlock(Block block, @Nullable Function<Block, ItemBlock> itemBlock)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBlock(block, itemBlock);

        if (block instanceof ISingleBlockRender)
        {
            ISingleBlockRender blockRender = (ISingleBlockRender) block;

            if (blockRender.getName() != null)
            {
                CommonRegisterHelper.SINGLE_BLOCK_RENDER_LIST.put(block, blockRender.getName());
            }
        }
        if (itemBlock != null)
        {
            if (ClientUtils.isEffectiveClient())
            {
                CommonRegisterHelper.registerSorted(block);
            }
        }
    }

    public static void registerItem(Item item)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerItem(item);

        if (item instanceof ISingleItemRender)
        {
            ISingleItemRender itemRender = (ISingleItemRender) item;

            if (itemRender.getName() != null)
            {
                CommonRegisterHelper.SINGLE_ITEM_RENDER_LIST.put(item, itemRender.getName());
            }
        }
        if (ClientUtils.isEffectiveClient())
        {
            CommonRegisterHelper.registerSorted(item);
        }
    }

    public static void registerSorted(Block block)
    {
        if (block instanceof ISortableBlock)
        {
            Item item = Item.getItemFromBlock(block);

            if (item == Items.AIR)
            {
                return;
            }

            ISortableBlock sortableBlock = (ISortableBlock) block;
            NonNullList<ItemStack> blocks = NonNullList.create();
            block.getSubBlocks(null, blocks);//TODO Remove 1.13

            blocks.forEach(itemStack ->
            {
                EnumSortCategoryBlock categoryBlock = sortableBlock.getBlockCategory();

                if (!CommonRegisterHelper.SORT_MAP_BLOCKS.containsKey(categoryBlock))
                {
                    CommonRegisterHelper.SORT_MAP_BLOCKS.put(categoryBlock, new ArrayList<>());
                }
                CommonRegisterHelper.SORT_MAP_BLOCKS.get(categoryBlock).add(new StackSorted(itemStack.getItem(), itemStack.getItemDamage()));
            });
        }
        else if (block.getCreativeTabToDisplayOn() == MorePlanetsMod.BLOCK_TAB)
        {
            throw new RuntimeException(block.getClass() + " must inherit " + ISortableBlock.class.getSimpleName() + "!");
        }
    }

    public static void postRegisteredSortBlock()
    {
        List<StackSorted> itemOrderListBlocks = new ArrayList<>();

        Arrays.asList(EnumSortCategoryBlock.values).forEach(type ->
        {
            List<StackSorted> stackSorteds = CommonRegisterHelper.SORT_MAP_BLOCKS.get(type);

            if (stackSorteds != null)
            {
                itemOrderListBlocks.addAll(stackSorteds);
            }
        });
        Comparator<ItemStack> tabSorterBlocks = Ordering.explicit(itemOrderListBlocks).onResultOf(input -> new StackSorted(input.getItem(), input.getItemDamage()));
        MorePlanetsMod.BLOCK_TAB.setTabSorter(tabSorterBlocks);
    }

    public static void registerSorted(Item item)
    {
        if (item instanceof ISortableItem)
        {
            ISortableItem sortableItem = (ISortableItem) item;
            NonNullList<ItemStack> items = NonNullList.create();
            item.getSubItems(MorePlanetsMod.ITEM_TAB, items);//TODO Remove 1.13

            items.forEach(itemStack ->
            {
                EnumSortCategoryItem categoryItem = sortableItem.getItemCategory(itemStack.getItemDamage());

                if (!CommonRegisterHelper.SORT_MAP_ITEMS.containsKey(categoryItem))
                {
                    CommonRegisterHelper.SORT_MAP_ITEMS.put(categoryItem, new ArrayList<>());
                }
                CommonRegisterHelper.SORT_MAP_ITEMS.get(categoryItem).add(new StackSorted(itemStack.getItem(), itemStack.getItemDamage()));
            });
        }
        else if (item.getCreativeTab() == MorePlanetsMod.ITEM_TAB)
        {
            throw new RuntimeException(item.getClass() + " must inherit " + ISortableItem.class.getSimpleName() + "!");
        }
    }

    public static void postRegisteredSortItem()
    {
        List<StackSorted> itemOrderListItems = new ArrayList<>();

        Arrays.asList(EnumSortCategoryItem.values).forEach(type ->
        {
            List<StackSorted> stackSorteds = CommonRegisterHelper.SORT_MAP_ITEMS.get(type);

            if (stackSorteds != null)
            {
                itemOrderListItems.addAll(stackSorteds);
            }
        });
        Comparator<ItemStack> tabSorterItems = Ordering.explicit(itemOrderListItems).onResultOf(input -> new StackSorted(input.getItem(), input.getItemDamage()));
        MorePlanetsMod.ITEM_TAB.setTabSorter(tabSorterItems);
    }

    public static boolean isItemTab(CreativeTabs creativeTabs)
    {
        return creativeTabs == MorePlanetsMod.ITEM_TAB || creativeTabs == CreativeTabs.SEARCH;
    }
}