package stevekung.mods.moreplanets.integration.jei;

import java.util.Locale;

import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

/***
 *
 * For contributors, you can improve/change/fix blocks and items description.
 * My English skill not pretty much good at all.
 *
 */
public class ItemDescription
{
    static void init()
    {
        JEIRegistryHelper.addInfo(MPBlocks.SPACE_PORTAL, text("A %blue%Space Portal%black% allow you to travel into %red%Nether%black% across the space dimension."), "", text("Note: %dark_gray%To use a %blue%Space Portal%dark_gray%, Enable Started Planet in the Config"));
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