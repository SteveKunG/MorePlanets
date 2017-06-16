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
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;

public class ItemCheeseFood extends ItemFoodMP
{
    private static final int[] foodHunger = new int[] {
            3,
            3,
            8
    };
    private static final float[] foodSaturation = new float[] {
            0.35F,
            0.2F,
            0.8F
    };

    public ItemCheeseFood(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
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
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        --itemStack.stackSize;
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemStack, world, player);
        player.getFoodStats().func_151686_a(this, itemStack);
        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() == 0)
        {
            return 8;
        }
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.eat;
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
        return new String[] { "cheese_of_milk_curd", "raw_cheese_beef", "cooked_cheese_beef", };
    }

    @Override
    public String getResourceLocation()
    {
        return "polongnius";
    }
}