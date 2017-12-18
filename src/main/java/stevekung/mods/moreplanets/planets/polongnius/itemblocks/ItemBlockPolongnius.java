/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.itemblocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockPolongnius extends ItemBlockBaseMP
{
    public ItemBlockPolongnius(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        if (player.worldObj.isRemote)
        {
            if (itemStack.getItemDamage() == 13)
            {
                list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.slime.name"));
            }
        }
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "cheese_gas", "solid_cheese_gas", "stone", "cobblestone", "copper_ore", "tin_ore", "iron_ore", "palladium_ore", "flonium_ore", "purple_crystal_ore", "meteor_block", "purple_crystal_block", "palladium_block", "dungeon_brick", "dungeon_brick2" };
    }
}