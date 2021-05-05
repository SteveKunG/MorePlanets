package com.stevekung.moreplanets.world.item;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;

public class MPItems
{
    public static final Item GLOWING_IRON_INGOT = new Item(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item RAW_GLOWING_IRON = new Item(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item PURLONITE_SHARD = new Item(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item DARK_CRYSTAL_SHARD = new Item(new Item.Properties().tab(MorePlanetsMod.TAB));

    public static final Item GLOWING_IRON_SWORD = new GlowingIronSwordItem(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_SHOVEL = new GlowingIronShovelItem(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_PICKAXE = new GlowingIronPickaxeItem(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_AXE = new GlowingIronAxeItem(new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_HOE = new GlowingIronHoeItem(new Item.Properties().tab(MorePlanetsMod.TAB));

    public static final Item GLOWING_IRON_HELMET = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.HEAD, new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_CHESTPLATE = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.CHEST, new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_LEGGINGS = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.LEGS, new Item.Properties().tab(MorePlanetsMod.TAB));
    public static final Item GLOWING_IRON_BOOTS = new GlowingIronArmorItem(ArmorMaterialsMP.GLOWING_IRON, EquipmentSlot.FEET, new Item.Properties().tab(MorePlanetsMod.TAB));

    public static void init()
    {
        MorePlanetsMod.COMMON.registerItem("glowing_iron_ingot", GLOWING_IRON_INGOT);
        MorePlanetsMod.COMMON.registerItem("raw_glowing_iron", RAW_GLOWING_IRON);
        MorePlanetsMod.COMMON.registerItem("purlonite_shard", PURLONITE_SHARD);
        MorePlanetsMod.COMMON.registerItem("dark_crystal_shard", DARK_CRYSTAL_SHARD);

        MorePlanetsMod.COMMON.registerItem("glowing_iron_sword", GLOWING_IRON_SWORD);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_shovel", GLOWING_IRON_SHOVEL);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_pickaxe", GLOWING_IRON_PICKAXE);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_axe", GLOWING_IRON_AXE);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_hoe", GLOWING_IRON_HOE);

        MorePlanetsMod.COMMON.registerItem("glowing_iron_helmet", GLOWING_IRON_HELMET);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_chestplate", GLOWING_IRON_CHESTPLATE);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_leggings", GLOWING_IRON_LEGGINGS);
        MorePlanetsMod.COMMON.registerItem("glowing_iron_boots", GLOWING_IRON_BOOTS);
    }
}