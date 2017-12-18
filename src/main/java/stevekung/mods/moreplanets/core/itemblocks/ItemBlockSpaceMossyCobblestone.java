/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockSpaceMossyCobblestone extends ItemBlockBaseMP
{
    public ItemBlockSpaceMossyCobblestone(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "diona", "polongnius", "nibiru", "koentus", "kapteynb", "siriusb", "venus", "mercury", "pluto", "martian" };
    }
}