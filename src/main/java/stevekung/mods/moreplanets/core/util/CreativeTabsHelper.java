/*******************************************************************************
 * Copyright 2015 SteveKunG - Steve's Core
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsHelper extends CreativeTabs
{
    private ItemStack itemStack;

    public CreativeTabsHelper(String name, ItemStack itemStack)
    {
        super(CreativeTabs.getNextID(), name);
        this.itemStack = itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        return this.itemStack;
    }

    @Override
    public Item getTabIconItem()
    {
        return null;
    }
}