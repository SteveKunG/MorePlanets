/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockFronosStone extends ItemBlockBaseMP
{
    public ItemBlockFronosStone(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "rock", "cobblestone", "iron_ore", "coal_ore", "aluminum_ore", "tin_ore", "copper_ore", "lapis_ore", "mineral_ore", "black_diamond_ore", "iridium_ore", "stone_brick", "cracked_stone_brick", "chiseled_stone_brick", "dungeon_brick" };
    }
}