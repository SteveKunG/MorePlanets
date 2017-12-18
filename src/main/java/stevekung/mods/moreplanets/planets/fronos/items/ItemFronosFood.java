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
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;

public class ItemFronosFood extends ItemFoodMP
{
    private static int[] foodHunger = new int[] {
            4,//0
            4,//1
            3,//2
            6,//3
            4,//4
            4,//5
            4,//6
            4,//7
            4,//8
            5,//9
            2,//10
            4,//11
            5,//12
            6,//13
            6,//14
            4//15
    };
    private static float[] foodSaturation = new float[] {
            0.25F,//0
            0.25F,//1
            0.15F,//2
            0.35F,//3
            0.45F,//4
            0.45F,//5
            0.45F,//6
            0.45F,//7
            0.45F,//8
            0.5F,//9
            0.15F,//10
            0.45F,//11
            0.4F,//12
            0.6F,//13
            0.55F,//14
            0.45F//15
    };

    public ItemFronosFood(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.eat;
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        int meta = itemStack.getItemDamage();
        --itemStack.stackSize;

        player.getFoodStats().func_151686_a(this, itemStack);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemStack, world, player);

        if (meta >= 4 && meta <= 8 || meta >= 11 && meta <= 15)
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(Items.bowl), false);
            }
        }
        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() == 10)
        {
            return 8;
        }
        return 32;
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
        return new String[] { "strawberry", "berry", "marshmallow", "cooked_marshmallow", "vanilla_ice_cream", "chocolate_ice_cream", "strawberry_ice_cream", "strawberry_cloud_ice_cream", "orange_ice_cream", "golden_bread", "little_sun_flower_seeds", "tea_ice_cream", "berry_salad", "sky_mushroom_stew", "rainbow_cloud_ice_cream", "lemon_ice_cream" };
    }

    @Override
    public String getResourceLocation()
    {
        return "fronos";
    }
}