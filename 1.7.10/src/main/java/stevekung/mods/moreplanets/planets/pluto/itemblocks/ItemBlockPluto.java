/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockPluto extends ItemBlockBaseMP
{
    public ItemBlockPluto(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_rock", "sub_surface_rock", "rock", "cobblestone", "meteoric_iron_ore", "frozen_iron_ore", "iron_ore", "xeonium_ore", "dungeon_brick", "", "surface_rock_brown", "surface_rock_light_brown" };
    }
}