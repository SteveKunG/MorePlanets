/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockIo extends ItemBlockBaseMP
{
    public ItemBlockIo(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_rock", "sub_surface_rock", "rock", "cobblestone", "sulfur_ore", "ash_stone", "ash_cobblestone", "silicate_rock", "dungeon_brick" };
    }
}