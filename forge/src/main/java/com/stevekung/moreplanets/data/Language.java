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
        this.add(MPBlocks.GLOWING_IRON_BLOCK, "Glowing Iron Block");
        this.add(MPBlocks.RAW_GLOWING_IRON_BLOCK, "Raw Glowing Iron Block");
        this.add(MPBlocks.METEORIC_IRON_STABILIZER, "Meteoric Iron Stabilizer");
        this.add(MPBlocks.ION_PLASMA_ROD, "Ion Plasma Rod");

        this.add(MPItems.GLOWING_IRON_INGOT, "Glowing Iron Ingot");
        this.add(MPItems.RAW_GLOWING_IRON, "Raw Glowing Iron");

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