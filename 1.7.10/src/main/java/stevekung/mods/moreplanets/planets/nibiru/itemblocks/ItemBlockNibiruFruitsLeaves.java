/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockNibiruFruitsLeaves extends ItemBlockBaseMP
{
    public ItemBlockNibiruFruitsLeaves(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "state1", "state2", "state3", "state4" };
    }
}