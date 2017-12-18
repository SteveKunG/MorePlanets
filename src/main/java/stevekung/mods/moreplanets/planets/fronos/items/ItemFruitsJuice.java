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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;

public class ItemFruitsJuice extends ItemFoodMP
{
    public ItemFruitsJuice(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            switch (itemStack.getItemDamage())
            {
            case 0:
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 1));
                break;
            case 1:
                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2400, 1));
                break;
            case 2:
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2400, 1));
                break;
            case 3:
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 1));
                break;
            }
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.drink;
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        --itemStack.stackSize;
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemStack, world, player);
        player.getFoodStats().func_151686_a(this, itemStack);

        if (!player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
        {
            player.entityDropItem(new ItemStack(Items.glass_bottle, 1, 0), 0.0F);
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
        return 5;
    }

    @Override
    public float getFoodSaturation(ItemStack itemStack)
    {
        return 0.6F;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "strawberry_juice", "berry_juice", "kiwi_juice", "lemon_juice" } ;
    }

    @Override
    public String getResourceLocation()
    {
        return "fronos";
    }
}