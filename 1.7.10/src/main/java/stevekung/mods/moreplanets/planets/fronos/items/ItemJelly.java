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
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;

public class ItemJelly extends ItemFoodMP
{
    public ItemJelly(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setHasSubtypes(true);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
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
        return 4;
    }

    @Override
    public float getFoodSaturation(ItemStack itemStack)
    {
        return 0.35F;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "grape_jelly", "raspberry_jelly", "strawberry_jelly", "berry_jelly", "lime_jelly", "orange_jelly", "green_jelly", "lemon_jelly" };
    }

    @Override
    public String getResourceLocation()
    {
        return "fronos";
    }

    @Override
    public boolean reverseName()
    {
        return true;
    }
}