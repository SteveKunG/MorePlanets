package com.stevekung.moreplanets.forge.data;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekunglib.forge.data.LanguageProviderBase;
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
        this.add(MPBlocks.DIONA_REGOLITH);
        this.add(MPBlocks.DIONA_FINE_REGOLITH);
        this.add(MPBlocks.DIONA_STONE);
        this.add(MPBlocks.DIONA_COBBLESTONE);
        this.add(MPBlocks.GLOWING_IRON_BLOCK, "Block of Glowing Iron");
        this.add(MPBlocks.RAW_GLOWING_IRON_BLOCK, "Block of Raw Glowing Iron");
        this.add(MPBlocks.METEORIC_IRON_STABILIZER);
        this.add(MPBlocks.ION_PLASMA_ROD);
        this.add(MPBlocks.PURLONITE_BLOCK, "Block of Purlonite");
        this.add(MPBlocks.BUDDING_PURLONITE);
        this.add(MPBlocks.PURLONITE_CLUSTER);
        this.add(MPBlocks.LARGE_PURLONITE_BUD);
        this.add(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.add(MPBlocks.SMALL_PURLONITE_BUD);
        this.add(MPBlocks.PURLONITE_CRYSTAL_LANTERN);
        this.add(MPBlocks.DARK_CRYSTAL_LANTERN);
        this.add(MPBlocks.DARK_ENERGY_CORE);
        this.add(MPBlocks.ZELIUS_EGG);
        this.add(MPBlocks.DARK_ENERGY_GENERATOR);
        this.add(MPBlocks.COMPACTED_PURLONITE_BLOCK, "Compacted Block of Purlonite");
        this.add(MPBlocks.DISPLAY_JAR);
        this.add(MPBlocks.PURLONITE_WORM_JAR);
        this.add(MPBlocks.CHALOS_SPORE_JAR);

        this.add(MPItems.GLOWING_IRON_INGOT);
        this.add(MPItems.RAW_GLOWING_IRON);
        this.add(MPItems.PURLONITE_SHARD);
        this.add(MPItems.DARK_CRYSTAL_SHARD);

        this.add(MPItems.GLOWING_IRON_SWORD);
        this.add(MPItems.GLOWING_IRON_SHOVEL);
        this.add(MPItems.GLOWING_IRON_PICKAXE);
        this.add(MPItems.GLOWING_IRON_AXE);
        this.add(MPItems.GLOWING_IRON_HOE);

        this.add(MPItems.GLOWING_IRON_HELMET);
        this.add(MPItems.GLOWING_IRON_CHESTPLATE);
        this.add(MPItems.GLOWING_IRON_LEGGINGS);
        this.add(MPItems.GLOWING_IRON_BOOTS);

        this.add(MorePlanetsMod.TAB.getDisplayName().getString(), "More Planets");
    }
}