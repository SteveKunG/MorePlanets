/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.*;

public class ItemCreamBall extends ItemBaseMP
{
    public ItemCreamBall(String name)
    {
        super();
        this.setMaxStackSize(16);
        this.setUnlocalizedName(name);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        int meta = itemStack.getItemDamage();
        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            if (meta == 0)
            {
                world.spawnEntityInWorld(new EntityVanillaCreamBall(world, player));
            }
            else if (meta == 1)
            {
                world.spawnEntityInWorld(new EntityChocolateCreamBall(world, player));
            }
            else if (meta == 2)
            {
                world.spawnEntityInWorld(new EntityStrawberryCreamBall(world, player));
            }
            else if (meta == 3)
            {
                world.spawnEntityInWorld(new EntityOrangeCreamBall(world, player));
            }
            else if (meta == 4)
            {
                world.spawnEntityInWorld(new EntityTeaCreamBall(world, player));
            }
            else if (meta == 5)
            {
                world.spawnEntityInWorld(new EntityLemonCreamBall(world, player));
            }
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "vanilla_cream_ball", "chocolate_cream_ball", "strawberry_cream_ball", "orange_cream_ball", "tea_cream_ball", "lemon_cream_ball" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "fronos";
    }
}