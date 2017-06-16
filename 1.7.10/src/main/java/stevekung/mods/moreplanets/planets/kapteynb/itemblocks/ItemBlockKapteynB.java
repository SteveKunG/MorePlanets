/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockKapteynB extends ItemBlockBaseMP
{
    public ItemBlockKapteynB(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_ice","sub_surface_ice", "stone", "cobblestone", "namerium_ore", "frozen_iron_ore", "uranium_ore", "tin_ore", "copper_ore", "redstone_ore", "redstone_ore", "namerium_block", "frozen_iron_block", "uranium_block", "dungeon_brick" };
    }
}