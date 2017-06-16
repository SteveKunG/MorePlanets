/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.ItemFoodMP;

public class ItemEuropaFood extends ItemFoodMP
{
    private int[] foodHunger = new int[] {
            3,
            8,
    };
    private float[] foodSaturation = new float[] {
            0.15F,
            0.75F,
    };

    public ItemEuropaFood(String name)
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
    public int getFoodAmount(ItemStack itemStack)
    {
        return this.foodHunger[itemStack.getItemDamage()];
    }

    @Override
    public float getFoodSaturation(ItemStack itemStack)
    {
        return this.foodSaturation[itemStack.getItemDamage()];
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "raw_europa_eel_meat", "cooked_europa_eel_meat" };
    }

    @Override
    public String getResourceLocation()
    {
        return "europa";
    }
}