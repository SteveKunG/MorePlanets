/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockDiona extends ItemBlockBaseMP
{
    public ItemBlockDiona(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_rock", "sub_surface", "rock", "cobblestone", "quontonium_ore", "fronisium_ore", "tin_ore", "copper_ore", "silicon_ore", "aluminum_ore", "quontonium_block", "smooth_quontonium", "quontonium_brick", "chiseled_quontonium", "dungeon_brick" };
    }
}