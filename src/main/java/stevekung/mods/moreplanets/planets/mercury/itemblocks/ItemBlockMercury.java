/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockMercury extends ItemBlockBaseMP
{
    public ItemBlockMercury(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "surface_rock", "sub_surface_rock", "rock", "cobblestone", "tin_ore", "copper_ore", "aluminum_ore", "iron_ore", "metal_meteoric_iron_ore", "silicate_rock", "solid_metal_meteoric_iron", "dungeon_brick", "dungeon_spawner" };
    }
}