/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;

public class ItemBasicPolongnius extends ItemBaseMP
{
    public ItemBasicPolongnius(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            if (i != 11)
            {
                par3List.add(new ItemStack(par1, 1, i));
            }
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "flonium", "purple_crystal", "raw_polongnius_meteoric_iron", "raw_palladium", "polongnius_meteoric_iron_ingot", "palladium_ingot", "compressed_polongnius_meteoric_iron", "compressed_palladium", "polongnius_meteoric_iron_stick", "palladium_stick", "cheese_leather", "cheese_spore" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "polongnius";
    }
}