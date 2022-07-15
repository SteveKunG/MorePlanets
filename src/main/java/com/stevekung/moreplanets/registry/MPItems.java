package com.stevekung.moreplanets.registry;

import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.world.item.*;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;

public class MPItems
{
    public static final Item RAW_GLOWING_IRON = new Item(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_INGOT = new Item(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item PURLONITE_SHARD = new Item(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item DARK_CRYSTAL_SHARD = new Item(new Item.Properties().tab(MorePlanets.TAB));

    public static final Item GLOWING_IRON_SWORD = new GlowingIronSwordItem(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_SHOVEL = new GlowingIronShovelItem(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_PICKAXE = new GlowingIronPickaxeItem(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_AXE = new GlowingIronAxeItem(new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_HOE = new GlowingIronHoeItem(new Item.Properties().tab(MorePlanets.TAB));

    public static final Item GLOWING_IRON_HELMET = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.HEAD, new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_CHESTPLATE = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.CHEST, new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_LEGGINGS = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.LEGS, new Item.Properties().tab(MorePlanets.TAB));
    public static final Item GLOWING_IRON_BOOTS = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.FEET, new Item.Properties().tab(MorePlanets.TAB));

    public static void init()
    {
        register("raw_glowing_iron", RAW_GLOWING_IRON);
        register("glowing_iron_ingot", GLOWING_IRON_INGOT);
        register("purlonite_shard", PURLONITE_SHARD);
        register("dark_crystal_shard", DARK_CRYSTAL_SHARD);

        register("glowing_iron_sword", GLOWING_IRON_SWORD);
        register("glowing_iron_shovel", GLOWING_IRON_SHOVEL);
        register("glowing_iron_pickaxe", GLOWING_IRON_PICKAXE);
        register("glowing_iron_axe", GLOWING_IRON_AXE);
        register("glowing_iron_hoe", GLOWING_IRON_HOE);

        register("glowing_iron_helmet", GLOWING_IRON_HELMET);
        register("glowing_iron_chestplate", GLOWING_IRON_CHESTPLATE);
        register("glowing_iron_leggings", GLOWING_IRON_LEGGINGS);
        register("glowing_iron_boots", GLOWING_IRON_BOOTS);
    }

    private static void register(String key, Item item)
    {
        Registry.register(Registry.ITEM, new ResourceLocation(MorePlanets.MOD_ID, key), item);
    }
}