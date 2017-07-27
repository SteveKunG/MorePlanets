package stevekung.mods.moreplanets.integration.jei;

import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.CachedEnumUtil;

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
        JEIRegistryHelper.addInfo(MPBlocks.SPACE_PORTAL, text("A %blue%Space Portal %black%allow you to travel into %red%Nether %black%across the space dimension."), text("%dark_gray%In the space%black%, it doesn't have an %dark_aqua%Oxygen %black%to activate the %red%Nether Portal!"), text("Note: To use a %blue%Space Portal%black%, %dark_purple%Enable Started Planet%black% in %dark_green%More Planets Config"));
    }

    static String text(String original)
    {
        for (TextFormatting formatting : CachedEnumUtil.valuesTextFormattingCached())
        {
            if (original.contains("%" + formatting.getFriendlyName().toLowerCase() + "%"))
            {
                original = original.replace("%" + formatting.getFriendlyName().toLowerCase() + "%", formatting.toString());
            }
        }
        return original;
    }
}