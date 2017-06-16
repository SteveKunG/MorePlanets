/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;

public class KapteynBItems
{
    public static Item kapteyn_b_item;
    public static Item kapteyn_b_dungeon_key;
    public static Item uranium_battery;
    public static Item frozen_water_bucket;
    public static Item namerium_crystal;
    public static Item tier8_rocket;
    public static Item tier8_rocket_schematic;
    public static Item ice_crystal_meteor_chunk;

    public static void init()
    {
        KapteynBItems.initItems();
        KapteynBItems.registerItems();
        KapteynBItems.registerFluidContainer();
    }

    private static void initItems()
    {
        KapteynBItems.kapteyn_b_item = new ItemBasicKapteynB("kapteyn-b_item");
        KapteynBItems.kapteyn_b_dungeon_key = new ItemKapteynBDungeonKey("kapteyn-b_dungeon_key");
        KapteynBItems.uranium_battery = new ItemUraniumBattery("uranium_battery");
        KapteynBItems.frozen_water_bucket = new ItemFrozenWaterBucket("frozen_water_bucket");
        KapteynBItems.namerium_crystal = new ItemNameriumCrystal("namerium_crystal");
        KapteynBItems.tier8_rocket = new ItemTier8Rocket("tier_8_rocket");
        KapteynBItems.tier8_rocket_schematic = new ItemTier8Schematic("tier_8_rocket_schematic");
        KapteynBItems.ice_crystal_meteor_chunk = new ItemIceCrystalMeteorChunk("ice_crystal_meteor_chunk");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(KapteynBItems.kapteyn_b_item);
        RegisterHelper.registerItem(KapteynBItems.namerium_crystal);
        RegisterHelper.registerItem(KapteynBItems.kapteyn_b_dungeon_key);
        RegisterHelper.registerItem(KapteynBItems.uranium_battery);
        RegisterHelper.registerItem(KapteynBItems.frozen_water_bucket);
        RegisterHelper.registerItem(KapteynBItems.ice_crystal_meteor_chunk);
        RegisterHelper.registerItem(KapteynBItems.tier8_rocket);
        RegisterHelper.registerItem(KapteynBItems.tier8_rocket_schematic);

        OreDictionary.registerOre("ingotFrozenIron", new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0));
        OreDictionary.registerOre("uranium", new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1));
        OreDictionary.registerOre("compressedFrozenIron", new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2));
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(KapteynBBlocks.frozen_water_fluid, new ItemStack(KapteynBItems.frozen_water_bucket, 1, 0), new ItemStack(Items.bucket));
    }
}