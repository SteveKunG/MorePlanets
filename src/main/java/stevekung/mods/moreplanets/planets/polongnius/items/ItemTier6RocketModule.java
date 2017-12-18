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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;

public class ItemTier6RocketModule extends ItemBaseMP
{
    public ItemTier6RocketModule(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        if (player.worldObj.isRemote)
        {
            if (itemStack.getItemDamage() == 2)
            {
                list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier6.name"));
            }
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "tier6_rocket_engine", "tier6_booster", "tier6_heavy_duty_plate" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "polongnius";
    }
}