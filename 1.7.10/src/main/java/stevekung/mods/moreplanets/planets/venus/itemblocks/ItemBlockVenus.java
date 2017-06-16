/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockVenus extends ItemBlockBaseMP
{
    public ItemBlockVenus(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface", "sub_surface", "rock", "cobblestone", "sulfur_ore", "lead_ore", "tin_ore", "copper_ore", "coal_ore", "iron_ore", "gold_ore", "lead_block", "stone_brick", "cracked_stone_brick", "dungeon_brick" };
    }
}