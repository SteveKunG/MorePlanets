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

public class ItemBasicDiona extends ItemBaseMP
{
    public ItemBasicDiona(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        int meta = itemStack.getItemDamage();

        if (player.worldObj.isRemote)
        {
            if (meta == 4)
            {
                list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier5.name"));
            }
            else if (meta == 8)
            {
                list.add(EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("desc.red.canvas.name"));
                list.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("desc.white.canvas.name"));
                list.add(EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("desc.blue.canvas.name"));
            }
            else if (meta == 9)
            {
                list.add(EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("desc.blue.canvas.name"));
                list.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("desc.white.canvas.name"));
                list.add(EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("desc.red.canvas.name"));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        if (ConfigManagerMP.enableThaiFlagAndCanvas)
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
        else
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                if (i != 5 && i != 6 && i != 7 && i != 8 && i != 9)
                {
                    list.add(new ItemStack(item, 1, i));
                }
            }
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "quontonium_ingot", "fronisium_ingot", "compressed_quontonium", "compressed_fronisium", "tier5_heavy_duty_plate", "red_canvas", "white_canvas", "blue_canvas", "thai_canvas_bottom", "thai_canvas_top", "quontonium_stick", "fronisium_stick", "green_redstone_torch" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "diona";
    }
}