/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.itemblocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;

public class ItemBlockKoentusMeteor extends ItemBlockMorePlanet
{
    public ItemBlockKoentusMeteor(Block block)
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
                list.add("Can be found in the Diona");
                list.add(EnumChatFormatting.DARK_RED + "Not in the Koentus!");
            }
            else
            {
                list.add("Press LSHIFT for more info");
            }
        }
    }
}