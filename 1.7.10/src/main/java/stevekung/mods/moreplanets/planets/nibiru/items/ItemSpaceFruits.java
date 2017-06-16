/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;

public class ItemSpaceFruits extends ItemFoodMP
{
    private static final int[] foodHunger = new int[] {
            4,
            4,
            5
    };
    private static final float[] foodSaturation = new float[] {
            0.2F,
            0.4F,
            0.6F
    };

    public ItemSpaceFruits(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            if (itemStack.getItemDamage() == 2)
            {
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 4000, 1));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2500, 1));
            }
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        if (itemStack.getItem() == this && itemStack.getItemDamage() == 2)
        {
            return EnumAction.drink;
        }
        return EnumAction.eat;
    }

    @Override
    public int getItemStackLimit(ItemStack itemStack)
    {
        if (itemStack.getItem() == this && itemStack.getItemDamage() == 2)
        {
            return 1;
        }
        return 64;
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        --itemStack.stackSize;
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemStack, world, player);
        player.getFoodStats().func_151686_a(this, itemStack);

        if (itemStack.getItemDamage() == 2)
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
            {
                player.entityDropItem(new ItemStack(Items.glass_bottle, 1, 0), 0.0F);
            }
        }
        return itemStack;
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
    public int getFoodAmount(ItemStack itemStack)
    {
        return foodHunger[itemStack.getItemDamage()];
    }

    @Override
    public float getFoodSaturation(ItemStack itemStack)
    {
        return foodSaturation[itemStack.getItemDamage()];
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "space_apple", "space_orange", "orange_juice" };
    }

    @Override
    public String getResourceLocation()
    {
        return "nibiru";
    }
}