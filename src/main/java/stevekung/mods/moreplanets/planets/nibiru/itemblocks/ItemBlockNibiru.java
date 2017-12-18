/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockNibiru extends ItemBlockBaseMP
{
    public ItemBlockNibiru(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_rock", "sub_surface_rock", "rock", "cobblestone", "ichorius_ore", "norium_ore", "diamond_ore", "coal_ore", "red_gem_ore", "ichorius_block", "norium_block", "red_gem_block", "dungeon_brick" };
    }
}