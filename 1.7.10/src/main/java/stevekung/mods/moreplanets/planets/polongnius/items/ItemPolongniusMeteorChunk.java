/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityPolongniusMeteorChunk;

public class ItemPolongniusMeteorChunk extends ItemMorePlanet
{
    public ItemPolongniusMeteorChunk(String name)
    {
        super();
        this.setMaxStackSize(16);
        this.setUnlocalizedName(name);
        this.setTextureName("mpcore:blank");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 1.0F, 0.0001F / (Item.itemRand.nextFloat() * 0.1F));

        if (!world.isRemote)
        {
            EntityPolongniusMeteorChunk meteor = new EntityPolongniusMeteorChunk(world, player, 1.0F);
            meteor.canBePickedUp = player.capabilities.isCreativeMode ? 2 : 1;
            world.spawnEntityInWorld(meteor);
        }
        return itemStack;
    }
}