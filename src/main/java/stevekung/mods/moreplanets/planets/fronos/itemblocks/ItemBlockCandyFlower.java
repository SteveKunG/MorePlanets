/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockCandyFlower extends ItemBlockBaseMP
{
    public ItemBlockCandyFlower(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return this.field_150939_a.getIcon(0, meta);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "pink", "orange", "green", "yellow", "light_blue", "blue", "red", "purple" };
    }

    @Override
    public boolean reverseName()
    {
        return true;
    }
}