/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;

public class ItemTier4RocketModule extends ItemBaseMP
{
    public ItemTier4RocketModule(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (par2EntityPlayer.worldObj.isRemote)
        {
            if (par1ItemStack.getItemDamage() == 1)
            {
                par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier4.name"));
            }
            else if (par1ItemStack.getItemDamage() == 4)
            {
                if (ConfigManagerMP.enableThaiFlagAndCanvas)
                {
                    par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.rocket.noflag.name"));
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (ConfigManagerMP.enableRocketWithThaiFlag)
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                par3List.add(new ItemStack(par1, 1, i));
            }
        }
        else
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                if (i != 0)
                {
                    par3List.add(new ItemStack(par1, 1, i));
                }
            }
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "tier4_nose_cone", "tier4_heavy_duty_plate", "tier4_rocket_engine", "tier4_booster", "tier4_nose_cone_no_flag", "tier5_rocket_engine", "tier5_booster" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "diona";
    }
}