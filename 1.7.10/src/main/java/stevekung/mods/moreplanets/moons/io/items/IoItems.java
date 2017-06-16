/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;

public class IoItems
{
    public static Item titanium_bucket;
    public static Item io_lava_bucket;
    public static Item io_black_lava_bucket;
    public static Item liquid_red_sulfur_bucket;
    public static Item liquid_yellow_sulfur_bucket;
    public static Item liquid_orange_sulfur_bucket;
    public static Item liquid_brown_sulfur_bucket;

    public static void init()
    {
        initItems();
        registerItems();
        registerFluidContainer();
    }

    private static void initItems()
    {
        IoItems.titanium_bucket = new ItemTitaniumBucket(Blocks.air, "titanium_bucket");
        IoItems.io_lava_bucket = new ItemTitaniumBucket(IoBlocks.io_lava, "io_lava_bucket");
        IoItems.io_black_lava_bucket = new ItemTitaniumBucket(IoBlocks.io_black_lava, "io_black_lava_bucket");
        IoItems.liquid_red_sulfur_bucket = new ItemTitaniumBucket(IoBlocks.liquid_red_sulfur, "liquid_red_sulfur_bucket");
        IoItems.liquid_yellow_sulfur_bucket = new ItemTitaniumBucket(IoBlocks.liquid_yellow_sulfur, "liquid_yellow_sulfur_bucket");
        IoItems.liquid_orange_sulfur_bucket = new ItemTitaniumBucket(IoBlocks.liquid_orange_sulfur, "liquid_orange_sulfur_bucket");
        IoItems.liquid_brown_sulfur_bucket = new ItemTitaniumBucket(IoBlocks.liquid_brown_sulfur, "liquid_brown_sulfur_bucket");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(titanium_bucket);
        RegisterHelper.registerItem(io_lava_bucket);
        RegisterHelper.registerItem(io_black_lava_bucket);
        RegisterHelper.registerItem(liquid_red_sulfur_bucket);
        RegisterHelper.registerItem(liquid_yellow_sulfur_bucket);
        RegisterHelper.registerItem(liquid_orange_sulfur_bucket);
        RegisterHelper.registerItem(liquid_brown_sulfur_bucket);
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(IoBlocks.io_lava_fluid, new ItemStack(IoItems.io_lava_bucket), new ItemStack(IoItems.titanium_bucket));
        RegisterHelper.registerFluidContainer(IoBlocks.io_black_lava_fluid, new ItemStack(IoItems.io_black_lava_bucket), new ItemStack(IoItems.titanium_bucket));
        RegisterHelper.registerFluidContainer(IoBlocks.liquid_red_sulfur_fluid, new ItemStack(IoItems.liquid_red_sulfur_bucket), new ItemStack(IoItems.titanium_bucket));
        RegisterHelper.registerFluidContainer(IoBlocks.liquid_yellow_sulfur_fluid, new ItemStack(IoItems.liquid_yellow_sulfur_bucket), new ItemStack(IoItems.titanium_bucket));
        RegisterHelper.registerFluidContainer(IoBlocks.liquid_orange_sulfur_fluid, new ItemStack(IoItems.liquid_orange_sulfur_bucket), new ItemStack(IoItems.titanium_bucket));
        RegisterHelper.registerFluidContainer(IoBlocks.liquid_brown_sulfur_fluid, new ItemStack(IoItems.liquid_brown_sulfur_bucket), new ItemStack(IoItems.titanium_bucket));
    }
}