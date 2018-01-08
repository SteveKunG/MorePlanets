package stevekung.mods.moreplanets.util.helper;

import java.util.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import micdoodle8.mods.galacticraft.core.util.StackSorted;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.EnumHarvestLevel;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.blocks.ISortableBlock;
import stevekung.mods.moreplanets.util.entity.EnumEntityTrackerType;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISingleItemRender;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public class CommonRegisterHelper
{
    private static int ID = 0;
    public static Map<EnumSortCategoryBlock, List<StackSorted>> SORT_MAP_BLOCKS = new HashMap<>();
    public static Map<EnumSortCategoryItem, List<StackSorted>> SORT_MAP_ITEMS = new HashMap<>();
    public static Map<Block, String> SINGLE_BLOCK_RENDER_LIST = new HashMap<>();
    public static Map<Item, String> SINGLE_ITEM_RENDER_LIST = new HashMap<>();

    public static void registerBlock(Block block)
    {
        CommonRegisterHelper.registerBlock(block, ItemBlock::new);
    }

    public static void registerBlock(Block block, @Nullable Function<Block, ItemBlock> itemBlock)
    {
        String name = block.getUnlocalizedName().substring(5);
        ForgeRegistries.BLOCKS.register(block.setRegistryName(name));

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
            ForgeRegistries.ITEMS.register(itemBlock.apply(block).setRegistryName(block.getRegistryName()));

            if (CommonRegisterHelper.isEffectiveClient())
            {
                CommonRegisterHelper.registerSorted(block);
            }
        }
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
    }

    public static void registerItem(Item item)
    {
        String name = item.getUnlocalizedName().substring(5);
        ForgeRegistries.ITEMS.register(item.setRegistryName(name));

        if (item instanceof ISingleItemRender)
        {
            ISingleItemRender itemRender = (ISingleItemRender) item;

            if (itemRender.getName() != null)
            {
                CommonRegisterHelper.SINGLE_ITEM_RENDER_LIST.put(item, itemRender.getName());
            }
        }
        if (CommonRegisterHelper.isClient())
        {
            CommonRegisterHelper.registerSorted(item);
        }
    }

    public static void registerPotion(Potion potion, String name)
    {
        ForgeRegistries.POTIONS.register(potion.setRegistryName(name));
    }

    public static void registerBiome(int id, String name, Biome biome, @Nonnull BiomeDictionary.Type... biomeType)
    {
        Biome.registerBiome(id, "moreplanets:" + name, biome);
        BiomeDictionary.addTypes(biome, biomeType);
    }

    public static SoundEvent registerSound(String name)
    {
        ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(new ResourceLocation("moreplanets:" + name)).setRegistryName(new ResourceLocation("moreplanets:" + name)));
        return new SoundEvent(new ResourceLocation("moreplanets:" + name));
    }

    public static SoundEvent registerRecord(String name)
    {
        return CommonRegisterHelper.registerSound("record." + name);
    }

    public static ResourceLocation registerEntityLoot(String name)
    {
        return LootTableList.register(new ResourceLocation("moreplanets:entities/" + name));
    }

    public static ResourceLocation registerEntityDyeLoot(String folder, String name)
    {
        return LootTableList.register(new ResourceLocation("moreplanets:entities/" + folder + "/" + name));
    }

    public static ResourceLocation registerChestLoot(String name)
    {
        return LootTableList.register(new ResourceLocation("moreplanets:chests/" + name));
    }

    public static ResourceLocation registerGameplayLoot(String name)
    {
        return LootTableList.register(new ResourceLocation("moreplanets:gameplay/" + name));
    }

    public static ResourceLocation registerFishingLoot(String name)
    {
        return LootTableList.register(new ResourceLocation("moreplanets:gameplay/fishing/" + name));
    }

    public static void registerTileEntity(Class<? extends TileEntity> tile, String name)
    {
        GameRegistry.registerTileEntity(tile, name);
    }

    public static void registerForgeBucket(Fluid fluid)
    {
        FluidRegistry.addBucketForFluid(fluid);
    }

    public static void registerGUIHandler(Object obj, IGuiHandler handler)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(obj, handler);
    }

    public static void setFireBurn(Block block, int encouragement, int flammibility)
    {
        Blocks.FIRE.setFireInfo(block, encouragement, flammibility);
    }

    public static void registerEntity(Class<? extends Entity> entity, String name, int backgroundColor, int foregroundColor)
    {
        CommonRegisterHelper.registerEntity(entity, name, backgroundColor, foregroundColor, EnumEntityTrackerType.NORMAL);
    }

    public static void registerEntity(Class<? extends Entity> entity, String name, int backgroundColor, int foregroundColor, EnumEntityTrackerType type)
    {
        CommonRegisterHelper.registerEntity(entity, name, backgroundColor, foregroundColor, type.getTrackingRange(), type.getUpdateFrequency());
    }

    public static void registerEntity(Class<? extends Entity> entity, String name, int backgroundColor, int foregroundColor, int trackingRange, int updateFrequency)
    {
        EntityRegistry.registerModEntity(new ResourceLocation("moreplanets:" + name), entity, "moreplanets." + name, CommonRegisterHelper.ID++, MorePlanetsCore.MOD_ID, trackingRange, updateFrequency, true, backgroundColor, foregroundColor);
    }

    public static void registerNonMobEntity(Class<? extends Entity> entity, String name)
    {
        CommonRegisterHelper.registerNonMobEntity(entity, name, EnumEntityTrackerType.NORMAL);
    }

    public static void registerNonMobEntity(Class<? extends Entity> entity, String name, EnumEntityTrackerType type)
    {
        CommonRegisterHelper.registerNonMobEntity(entity, name, type.getTrackingRange(), type.getUpdateFrequency());
    }

    public static void registerNonMobEntity(Class<? extends Entity> entity, String name, int trackingRange, int updateFrequency)
    {
        EntityRegistry.registerModEntity(new ResourceLocation("moreplanets:" + name), entity, name, CommonRegisterHelper.ID++, MorePlanetsCore.MOD_ID, trackingRange, updateFrequency, true);
    }

    public static void registerEntityPlacement(Class<? extends Entity> entity, SpawnPlacementType type)
    {
        EntitySpawnPlacementRegistry.setPlacementType(entity, type);
    }

    public static void registerCarriable(Block block)
    {
        EntityEnderman.setCarriable(block, true);
    }

    public static void setBlockHarvestLevel(Block block, EnumHarvestLevel harvestLevel, int level)
    {
        block.setHarvestLevel(harvestLevel.toString(), level);
    }

    public static void setBlockHarvestLevel(Block block, EnumHarvestLevel harvestLevel, int level, int meta)
    {
        block.setHarvestLevel(harvestLevel.toString(), level, block.getStateFromMeta(meta));
    }

    public static void setToolHarvestLevel(Item item, EnumHarvestLevel harvestLevel, int level)
    {
        item.setHarvestLevel(harvestLevel.toString(), level);
    }

    public static void registerOreDictionary(String name, Block block)
    {
        OreDictionary.registerOre(name, block);
    }

    public static void registerOreDictionary(String name, Item item)
    {
        OreDictionary.registerOre(name, item);
    }

    public static void registerOreDictionary(String name, ItemStack itemStack)
    {
        OreDictionary.registerOre(name, itemStack);
    }

    public static void registerForgeEvent(Object event)
    {
        MinecraftForge.EVENT_BUS.register(event);
    }

    public static void registerProjectileDispense(Item item, IBehaviorDispenseItem projectile)
    {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item, projectile);
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide() == Side.CLIENT;
    }

    public static boolean isEffectiveClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }

    private static void registerSorted(Block block)
    {
        if (block instanceof ISortableBlock)
        {
            ISortableBlock sortableBlock = (ISortableBlock) block;
            NonNullList<ItemStack> blocks = NonNullList.create();
            block.getSubBlocks(null, blocks);

            for (ItemStack itemStack : blocks)
            {
                EnumSortCategoryBlock categoryBlock = sortableBlock.getBlockCategory(itemStack.getItemDamage());

                if (!CommonRegisterHelper.SORT_MAP_BLOCKS.containsKey(categoryBlock))
                {
                    CommonRegisterHelper.SORT_MAP_BLOCKS.put(categoryBlock, new ArrayList<>());
                }
                CommonRegisterHelper.SORT_MAP_BLOCKS.get(categoryBlock).add(new StackSorted(itemStack.getItem(), itemStack.getItemDamage()));
            }
        }
        else if (block.getCreativeTabToDisplayOn() == MorePlanetsCore.BLOCK_TAB)
        {
            throw new RuntimeException(block.getClass() + " must inherit " + ISortableBlock.class.getSimpleName() + "!");
        }
    }

    public static void postRegisteredSortBlock()
    {
        List<StackSorted> itemOrderListBlocks = new ArrayList<>();

        for (EnumSortCategoryBlock type : EnumSortCategoryBlock.valuesCached())
        {
            List<StackSorted> stackSorteds = CommonRegisterHelper.SORT_MAP_BLOCKS.get(type);
            itemOrderListBlocks.addAll(stackSorteds);
        }
        Comparator<ItemStack> tabSorterBlocks = Ordering.explicit(itemOrderListBlocks).onResultOf(input -> new StackSorted(input.getItem(), input.getItemDamage()));
        MorePlanetsCore.BLOCK_TAB.setTabSorter(tabSorterBlocks);
    }

    private static void registerSorted(Item item)
    {
        if (item instanceof ISortableItem)
        {
            ISortableItem sortableItem = (ISortableItem) item;
            NonNullList<ItemStack> items = NonNullList.create();
            item.getSubItems(null, items);

            for (ItemStack itemStack : items)
            {
                EnumSortCategoryItem categoryItem = sortableItem.getItemCategory(itemStack.getItemDamage());

                if (!CommonRegisterHelper.SORT_MAP_ITEMS.containsKey(categoryItem))
                {
                    CommonRegisterHelper.SORT_MAP_ITEMS.put(categoryItem, new ArrayList<>());
                }
                CommonRegisterHelper.SORT_MAP_ITEMS.get(categoryItem).add(new StackSorted(itemStack.getItem(), itemStack.getItemDamage()));
            }
        }
        else if (item.getCreativeTab() == MorePlanetsCore.ITEM_TAB)
        {
            throw new RuntimeException(item.getClass() + " must inherit " + ISortableItem.class.getSimpleName() + "!");
        }
    }

    public static void postRegisteredSortItem()
    {
        List<StackSorted> itemOrderListItems = new ArrayList<>();

        for (EnumSortCategoryItem type : EnumSortCategoryItem.valuesCached())
        {
            itemOrderListItems.addAll(CommonRegisterHelper.SORT_MAP_ITEMS.get(type));
        }
        Comparator<ItemStack> tabSorterItems = Ordering.explicit(itemOrderListItems).onResultOf(input -> new StackSorted(input.getItem(), input.getItemDamage()));
        MorePlanetsCore.ITEM_TAB.setTabSorter(tabSorterItems);
    }

    public static boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static boolean isControlKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }

    public static String ticksToElapsedTime(int ticks)
    {
        int i = ticks / 20;
        int j = i / 60;
        i = i % 60;
        return i < 10 ? j + ":0" + i : j + ":" + i;
    }
}