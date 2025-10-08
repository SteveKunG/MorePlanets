package com.stevekung.moreplanets.integration.jei;

import java.util.Locale;

import com.stevekung.lib.utils.enums.CachedEnum;
import com.stevekung.moreplanets.init.MPBlocks;

import net.minecraft.util.text.TextFormatting;

public class ItemDescription
{
    static void init()
    {
        JEIRegistryHelper.addInfo(MPBlocks.CRASHED_ALIEN_PROBE, text("A %blue%Crashed Alien Probe%black% can be found randomly on Diona surface, which contain some pieces of metal plate."), text("Also beware an %red%Alien Miner%black% around it!"));
    }

    static String text(String original)
    {
        for (TextFormatting formatting : CachedEnum.textFormatValues)
        {
            if (original.contains("%" + formatting.getFriendlyName().toLowerCase(Locale.ROOT) + "%"))
            {
                original = original.replace("%" + formatting.getFriendlyName().toLowerCase(Locale.ROOT) + "%", formatting.toString());
            }
        }
        return original;
    }
}