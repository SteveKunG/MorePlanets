package com.stevekung.moreplanets.data;

import com.stevekung.moreplanets.core.MorePlanetsMod;
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

        this.add(MorePlanetsMod.TAB.getDisplayName().getString(), "More Planets");
    }
}