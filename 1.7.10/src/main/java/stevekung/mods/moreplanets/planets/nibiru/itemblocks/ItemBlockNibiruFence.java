/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockNibiruFence extends ItemBlockBaseMP
{
    public ItemBlockNibiruFence(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
        return this.field_150939_a.getIcon(0, metadata);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "ancient_dark", "orange" };
    }
}