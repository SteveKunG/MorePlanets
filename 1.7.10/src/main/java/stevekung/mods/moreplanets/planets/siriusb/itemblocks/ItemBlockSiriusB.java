/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockSiriusB extends ItemBlockBaseMP
{
    public ItemBlockSiriusB(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_carbon", "sub_surface_carbon", "carbon_stone", "carbon_cobblestone", "sulfur_ore", "diamond_ore", "glowstone_ore", "black_spot", "sulfur_block", "dungeon_brick" };
    }
}