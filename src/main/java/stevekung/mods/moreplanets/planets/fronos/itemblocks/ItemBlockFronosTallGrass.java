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

public class ItemBlockFronosTallGrass extends ItemBlockBaseMP
{
    public ItemBlockFronosTallGrass(Block block)
    {
        super(block);
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
        return new String[] { "short_grass", "medium_grass", "tall_grass", "pink_short_grass", "pink_medium_grass", "pink_tall_grass", "purple_short_grass", "purple_medium_grass", "purple_tall_grass", "plains_short_grass", "plains_medium_grass", "plains_tall_grass", "golden_short_grass", "golden_medium_grass", "golden_tall_grass" };
    }
}