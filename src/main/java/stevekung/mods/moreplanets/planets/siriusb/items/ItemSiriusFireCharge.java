/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class ItemSiriusFireCharge extends ItemMorePlanet
{
    public ItemSiriusFireCharge(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setTextureName("siriusb:sirius_fire_charge");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_onItemUse_8_, float p_onItemUse_9_, float p_onItemUse_10_)
    {
        if (world.isRemote)
        {
            return true;
        }
        if (side == 0)
        {
            y--;
        }
        if (side == 1)
        {
            y++;
        }
        if (side == 2)
        {
            z--;
        }
        if (side == 3)
        {
            z++;
        }
        if (side == 4)
        {
            x--;
        }
        if (side == 5)
        {
            x++;
        }
        if (!player.canPlayerEdit(x, y, z, side, itemStack))
        {
            return false;
        }
        if (world.getBlock(x, y, z).getMaterial() == Material.air)
        {
            world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, Item.itemRand.nextFloat() * 0.4F + 0.8F);
            world.setBlock(x, y, z, SiriusBBlocks.sirius_fire);
        }
        if (!player.capabilities.isCreativeMode)
        {
            itemStack.stackSize -= 1;
        }
        return true;
    }
}