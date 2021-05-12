package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.LanguageProviderBase;
import net.minecraft.data.DataGenerator;

public class Language extends LanguageProviderBase
{
    public Language(DataGenerator generator, String modid)
    {
        super(generator, modid, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        this.add(MPBlocks.DIONA_REGOLITH, "Diona Regolith");
        this.add(MPBlocks.DIONA_FINE_REGOLITH, "Diona Fine Regolith");
        this.add(MPBlocks.DIONA_STONE, "Diona Stone");
        this.add(MPBlocks.DIONA_COBBLESTONE, "Diona Cobblestone");
        this.add(MPBlocks.GLOWING_IRON_BLOCK, "Block of Glowing Iron");
        this.add(MPBlocks.RAW_GLOWING_IRON_BLOCK, "Block of Raw Glowing Iron");
        this.add(MPBlocks.METEORIC_IRON_STABILIZER, "Meteoric Iron Stabilizer");
        this.add(MPBlocks.ION_PLASMA_ROD, "Ion Plasma Rod");
        this.add(MPBlocks.PURLONITE_BLOCK, "Block of Purlonite");
        this.add(MPBlocks.BUDDING_PURLONITE, "Budding Purlonite");
        this.add(MPBlocks.PURLONITE_CLUSTER, "Purlonite Cluster");
        this.add(MPBlocks.LARGE_PURLONITE_BUD, "Large Purlonite Bud");
        this.add(MPBlocks.MEDIUM_PURLONITE_BUD, "Medium Purlonite Bud");
        this.add(MPBlocks.SMALL_PURLONITE_BUD, "Small Purlonite Bud");
        this.add(MPBlocks.PURLONITE_CRYSTAL_LANTERN, "Purlonite Crystal Lantern");
        this.add(MPBlocks.DARK_CRYSTAL_LANTERN, "Dark Crystal Lantern");
        this.add(MPBlocks.DARK_ENERGY_CORE, "Dark Energy Core");
        this.add(MPBlocks.ZELIUS_EGG, "Zelius Egg");
        this.add(MPBlocks.DARK_ENERGY_GENERATOR, "Dark Energy Generator");
        this.add(MPBlocks.COMPACTED_PURLONITE_BLOCK, "Compacted Block of Purlonite");
        this.add(MPBlocks.SPACE_JAR, "Space Jar");
        this.add(MPBlocks.PURLONITE_WORM_JAR, "Purlonite Worm Jar");
        this.add(MPBlocks.CHALOS_SPORE_JAR, "Chalos Spore Jar");

        this.add(MPItems.GLOWING_IRON_INGOT, "Glowing Iron Ingot");
        this.add(MPItems.RAW_GLOWING_IRON, "Raw Glowing Iron");
        this.add(MPItems.PURLONITE_SHARD, "Purlonite Shard");
        this.add(MPItems.DARK_CRYSTAL_SHARD, "Dark Crystal Shard");

        this.add(MPItems.GLOWING_IRON_SWORD, "Glowing Iron Sword");
        this.add(MPItems.GLOWING_IRON_SHOVEL, "Glowing Iron Shovel");
        this.add(MPItems.GLOWING_IRON_PICKAXE, "Glowing Iron Pickaxe");
        this.add(MPItems.GLOWING_IRON_AXE, "Glowing Iron Axe");
        this.add(MPItems.GLOWING_IRON_HOE, "Glowing Iron Hoe");

        this.add(MPItems.GLOWING_IRON_HELMET, "Glowing Iron Helmet");
        this.add(MPItems.GLOWING_IRON_CHESTPLATE, "Glowing Iron Chestplate");
        this.add(MPItems.GLOWING_IRON_LEGGINGS, "Glowing Iron Leggings");
        this.add(MPItems.GLOWING_IRON_BOOTS, "Glowing Iron Boots");

        this.add(MorePlanetsMod.TAB.getDisplayName().getString(), "More Planets");
    }
}