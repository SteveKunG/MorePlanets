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

        this.add(MPItems.GLOWING_IRON_INGOT, "Glowing Iron Ingot");
        this.add(MPItems.RAW_GLOWING_IRON, "Raw Glowing Iron");

        this.add(MorePlanetsMod.TAB.getDisplayName().getString(), "More Planets");
    }
}