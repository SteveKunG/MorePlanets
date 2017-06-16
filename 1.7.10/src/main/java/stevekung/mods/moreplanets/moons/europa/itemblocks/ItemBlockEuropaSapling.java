/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.itemblocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;

public class ItemBlockEuropaSapling extends ItemBlockMorePlanet
{
    public ItemBlockEuropaSapling(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        if (player.worldObj.isRemote)
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                list.add(EnumChatFormatting.GRAY + "Can be placed under an Europa Ice and Packed Europa Ice");
                list.add(EnumChatFormatting.RED + "Note : When planted under an Europa Ice it need to grow manually");
            }
            else
            {
                list.add("Press LSHIFT for info");
            }
        }
    }
}